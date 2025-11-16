package uk.co.nikodem.dFSmpPlus;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Commands.*;
import uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.DFWaypointCommand;
import uk.co.nikodem.dFSmpPlus.Commands.TabCompleters.DFDebugTabCompleter;
import uk.co.nikodem.dFSmpPlus.Commands.TabCompleters.GiveDFTabCompleter;
import uk.co.nikodem.dFSmpPlus.Constants.Chisel.ChiselBlockData;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.*;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets.*;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.Interchangable.MusicDiscRecipes;
import uk.co.nikodem.dFSmpPlus.Data.Global.GlobalDataHandler;
import uk.co.nikodem.dFSmpPlus.Entities.CustomDrops.DFCustomDrops;
import uk.co.nikodem.dFSmpPlus.Events.Entity.EntityDamageByEntityEvent;
import uk.co.nikodem.dFSmpPlus.Events.Entity.EntityDamageEvent;
import uk.co.nikodem.dFSmpPlus.Events.Entity.EntityDeathEvent;
import uk.co.nikodem.dFSmpPlus.Events.Entity.EntityPickupItemEvent;
import uk.co.nikodem.dFSmpPlus.Events.Entity.Food.FoodLevelChangeEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.*;
import uk.co.nikodem.dFSmpPlus.Events.Player.Block.BlockBreakEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Block.BlockBreakProgressUpdateEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Block.BlockPlaceEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.CraftItemEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting.PrepareAnvilEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.FurnaceSmeltEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.InventoryClickEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.InventoryOpenEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting.PrepareItemCraftEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting.PrepareSmithingEvent;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerDataHandler;
import uk.co.nikodem.dFSmpPlus.Messaging.MessageListener;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetEvents;
import uk.co.nikodem.dFSmpPlus.Utils.Server.MessageUtils;
import uk.co.nikodem.dFSmpPlus.Utils.Server.HidingUtils;
import uk.co.nikodem.dFSmpPlus.World.SetDefaults;

import java.util.List;
import java.util.Objects;

public final class DFSmpPlus extends JavaPlugin implements Listener {

    private static final Logger log = LoggerFactory.getLogger(DFSmpPlus.class);

    public static MessageListener messageListener;
    public static MessageUtils messageUtils;
    public static HidingUtils hidingUtils;

    public static List<? extends CraftingTemplate> craftingTemplateList;

    public static boolean panicMode = false;
    public static int totalSuccessfulRecipes;
    public static int totalRecipes;

    public static PlayerDataHandler playerDataHandler;
    public static GlobalDataHandler globalDataHandler;

    public static DFAdvancementsHandler advancements;

    @Override
    public void onEnable() {
        try {
            SetDefaults.checkRecommendedSettings(this);

            messageUtils = new MessageUtils(this);
            messageUtils.initiateChannels();

            messageListener = new MessageListener(this);
            messageListener.initialiseMessageHandlers();

            craftingTemplateList = List.of(
                    new VanillaRecipes(this),
                    new OtherCustomItemRecipes(this),
                    new CoralRecipes(this),
                    new ChiselRecipes(this),

                    new MusicDiscRecipes(this),

                    // lets hope this works :p
                    new CompassRecipes(this),

                    // customset
                    new CopperRecipes(this),
                    new FiridiumRecipes(this),
                    new SculkRecipes(this),
                    new ObsidianRecipes(this),
                    new VeinRecipes(this),
                    new SilkRecipes(this),
                    new CalciteRecipes(this)
            );

            new HiddenRecipes(this);
            new HiddenRepairRecipes(this);

            advancements = new DFAdvancementsHandler(this);

            playerDataHandler = new PlayerDataHandler(this);
            globalDataHandler = new GlobalDataHandler(this);

            hidingUtils = new HidingUtils(this);

            getLogger().info("Added "+totalSuccessfulRecipes+"/"+totalRecipes+" recipes in total!");

            RecipeRemover.Run(); // remove the recipes that the crafting templates want to remove
            ChiselBlockData.createChiselBlockData();
            DFCustomDrops.createCustomDropData();

            // Command initiation
            Objects.requireNonNull(getCommand("givedf")).setExecutor(new GiveDF());
            Objects.requireNonNull(getCommand("givedf")).setTabCompleter(new GiveDFTabCompleter());

            Objects.requireNonNull(getCommand("dfdebug")).setExecutor(new DFDebugCommand());
            Objects.requireNonNull(getCommand("dfdebug")).setTabCompleter(new DFDebugTabCompleter());

            Objects.requireNonNull(getCommand("dfmaterialview")).setExecutor(new DFMaterialView(this));
            Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
            Objects.requireNonNull(getCommand("bin")).setExecutor(new BinCommand());

            this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
                commands.registrar().register(DFWaypointCommand.createCommand().build());
            });

            List<Listener> eventListeners = List.of(
                    new FoodLevelChangeEvent(),
                    new EntityDamageByEntityEvent(),
                    new EntityDamageEvent(),
                    new EntityPickupItemEvent(),
                    new EntityDeathEvent(this),

                    new BlockBreakEvent(),
                    new BlockBreakProgressUpdateEvent(),
                    new BlockPlaceEvent(),

                    new CraftItemEvent(),
                    new FurnaceSmeltEvent(),
                    new InventoryOpenEvent(),
                    new InventoryClickEvent(),

                    new PrepareItemCraftEvent(),
                    new PrepareSmithingEvent(),
                    new PrepareAnvilEvent(),

                    new PlayerAttemptPickupItemEvent(),
                    new PlayerBucketFillEvent(),
                    new PlayerDeathEvent(),
                    new PlayerDropItemEvent(),
                    new PlayerInteractAtEntityEvent(),
                    new PlayerInteractEntityEvent(),
                    new PlayerInteractEvent(),
                    new PlayerJoinEvent(),
                    new PlayerChannelEvent(),
                    new PlayerQuitEvent()
            );

            for (Listener listener : eventListeners) {
                getServer().getPluginManager().registerEvents(listener, this);
            }

            // Event initiation
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                for (Player plr : Bukkit.getOnlinePlayers()) {
                    DFArmourSetEvents.ApplyRunPerSecond(plr);
                }
            }, 0, 20);

        } catch (Exception e) {
            log.error("Error occured whilst initialising DFSmpPlugin!", e);
            getLogger().severe("DFSmpPlus failed to initialise! Entering panic mode!");
            getLogger().severe("During panic mode, the server will stay active, however disallow any players from joining!");
            getLogger().severe("The game logic will stop during panic mode.");

            panicMode = true;
            for (World world : Bukkit.getWorlds()) {
                Bukkit.unloadWorld(world, false);
            }

            Bukkit.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @EventHandler
    public void panicModeOnJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        if (panicMode) {
            event.getPlayer().kick(MiniMessage.miniMessage().deserialize("<red>Server is in panic mode! You will be unable to join the server until manual intervention."));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WaypointManager.CleanupOnShutdown();
    }
}
