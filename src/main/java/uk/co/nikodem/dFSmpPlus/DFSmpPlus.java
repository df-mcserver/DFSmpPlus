package uk.co.nikodem.dFSmpPlus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Commands.BasicCommands.*;
import uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.DFGiveCommand;
import uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.DFWaypointCommand;
import uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.LocatorBarCommand;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.Commands.DFCommand;
import uk.co.nikodem.dFSmpPlus.Constants.ChiselBlockData;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.*;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets.*;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.Interchangable.MusicDiscRecipes;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.Interchangable.TotemRecipes;
import uk.co.nikodem.dFSmpPlus.Data.Adapters.ConfigurationSerializableAdapter;
import uk.co.nikodem.dFSmpPlus.Data.Global.GlobalDataHandler;
import uk.co.nikodem.dFSmpPlus.Entities.CustomDrops.DFCustomDrops;
import uk.co.nikodem.dFSmpPlus.Events.Block.*;
import uk.co.nikodem.dFSmpPlus.Events.Entity.*;
import uk.co.nikodem.dFSmpPlus.Events.Entity.Food.FoodLevelChangeEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.*;
import uk.co.nikodem.dFSmpPlus.Events.Player.Bucket.PlayerBucketEmptyEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Bucket.PlayerBucketEntityEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Bucket.PlayerBucketFillEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.*;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting.PrepareAnvilEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting.PrepareGrindstoneEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting.PrepareItemCraftEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting.PrepareSmithingEvent;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerDataHandler;
import uk.co.nikodem.dFSmpPlus.Events.World.ChunkLoadEvent;
import uk.co.nikodem.dFSmpPlus.Events.World.ChunkUnloadEvent;
import uk.co.nikodem.dFSmpPlus.Events.World.LootGenerateEvent;
import uk.co.nikodem.dFSmpPlus.Messaging.MessageListener;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatEvents;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointEventHandler;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetEvents;
import uk.co.nikodem.dFSmpPlus.Utils.Server.MessageUtils;
import uk.co.nikodem.dFSmpPlus.Utils.Server.HidingUtils;
import uk.co.nikodem.dFSmpPlus.World.SetDefaults;

import java.util.List;

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

    public static Gson gson;

    @Override
    public void onEnable() {
        try {
            SetDefaults.checkRecommendedSettings();

            messageUtils = new MessageUtils();
            messageUtils.initiateChannels();

            messageListener = new MessageListener();
            messageListener.initialiseMessageHandlers();

            craftingTemplateList = List.of(
                    new VanillaRecipes(),
                    new OtherCustomItemRecipes(),
                    new CoralRecipes(),
                    new ChiselRecipes(),
                    new AccessoryRecipes(),

                    new MusicDiscRecipes(),
                    new TotemRecipes(),

                    // lets hope this works :p
                    new CompassRecipes(),

                    // customset
                    new FiridiumRecipes(),
                    new SculkRecipes(),
                    new ObsidianRecipes(),
                    new VeinRecipes(),
                    new SilkRecipes(),
                    new CalciteRecipes(),
                    new FloralRecipes(),

                    new RecipeRemovals() // literally has 0 recipes
            );

            new HiddenRecipes();
            new HiddenRepairRecipes();
            new HiddenFoodRecipes();

            advancements = new DFAdvancementsHandler();

            gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .registerTypeHierarchyAdapter(ConfigurationSerializable.class, new ConfigurationSerializableAdapter())
                    .create();

            playerDataHandler = new PlayerDataHandler();
            globalDataHandler = new GlobalDataHandler();

            hidingUtils = new HidingUtils();

            getLogger().info("Added "+totalSuccessfulRecipes+"/"+totalRecipes+" recipes in total!");

            RecipeRemover.Run(); // remove the recipes that the crafting templates want to remove
            ChiselBlockData.createChiselBlockData();
            DFCustomDrops.createCustomDropData();

            // Command initiation
            List<DFBasicCommand> basicCommands = List.of(
                    new AccessoriesCommand(),
                    new SpawnCommand(),
                    new BinCommand(),
                    new BackCommand(),

                    new DFDebugCommand(),
                    new DFMaterialView()
            );

            List<DFCommand> brigaderCommands = List.of(
                    new DFWaypointCommand(),
                    new DFGiveCommand(),

                    new LocatorBarCommand()
            );

            this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
                for (DFCommand command : brigaderCommands) {
                    commands.registrar().register(command.createCommand().build(), command.getDescription(), command.getAliases());
                }
                for (DFBasicCommand command : basicCommands) {
                    commands.registrar().register(command.getLabel(), command.getDescription(), command.getAliases(), command);
                }
            });

            List<Listener> eventListeners = List.of(
                    new FoodLevelChangeEvent(),
                    new EntityDamageByEntityEvent(),
                    new EntityDamageEvent(),
                    new EntityPickupItemEvent(),
                    new EntityTargetEvent(),
                    new EntityDeathEvent(),
                    new EntityDropItemEvent(),
                    new EntityZapEvent(),

                    new BlockBreakEvent(),
                    new BlockBreakProgressUpdateEvent(),
                    new BlockDispenseArmorEvent(),
                    new BlockPlaceEvent(),
                    new BlockDropItemEvent(),

                    new CraftItemEvent(),
                    new FurnaceSmeltEvent(),
                    new InventoryOpenEvent(),
                    new InventoryClickEvent(),
                    new InventoryCloseEvent(),
                    new InventoryDragEvent(),
                    new InventoryPickupItemEvent(),

                    new PrepareItemCraftEvent(),
                    new PrepareSmithingEvent(),
                    new PrepareAnvilEvent(),
                    new PrepareGrindstoneEvent(),

                    new AsyncChatEvent(),
                    new PlayerAttemptPickupItemEvent(),
                    new PlayerBucketFillEvent(),
                    new PlayerBucketEmptyEvent(),
                    new PlayerBucketEntityEvent(),
                    new PlayerDeathEvent(),
                    new PlayerDropItemEvent(),
                    new PlayerInteractAtEntityEvent(),
                    new PlayerItemFrameChangeEvent(),
                    new PlayerInteractEntityEvent(),
                    new PlayerInteractEvent(),
                    new PlayerItemConsumeEvent(),
                    new PlayerJoinEvent(),
                    new PlayerChannelEvent(),
                    new PlayerQuitEvent(),
                    new PlayerRespawnEvent(),
                    new PlayerMoveEvent(),
                    new PlayerOpenSignEvent(),

                    new LootGenerateEvent(),
                    new ChunkUnloadEvent(),
                    new ChunkLoadEvent()
            );

            for (Listener listener : eventListeners) {
                getServer().getPluginManager().registerEvents(listener, this);
            }

            // Event initiation
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                for (Player plr : Bukkit.getOnlinePlayers()) {
                    DFArmourSetEvents.ApplyRunPerSecond(plr);
                    AccessoryEvents.ApplyRunPerSecond(plr);
                    CombatEvents.perSecond(plr);
                }

                WaypointEventHandler.onPerSecond();
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
        AccessoryManager.CleanupOnShutdown();
        WaypointManager.CleanupOnShutdown();
    }

    public static DFSmpPlus getPlugin() {
        return (DFSmpPlus) DFSmpPlus.getProvidingPlugin(DFSmpPlus.class);
    }
}
