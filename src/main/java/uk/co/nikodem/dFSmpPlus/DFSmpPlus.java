package uk.co.nikodem.dFSmpPlus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.nikodem.dFSmpPlus.Commands.DFMaterialView;
import uk.co.nikodem.dFSmpPlus.Commands.GiveDF;
import uk.co.nikodem.dFSmpPlus.Commands.GiveDFTabCompleter;
import uk.co.nikodem.dFSmpPlus.Constants.Chisel.ChiselBlockData;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.*;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets.*;
import uk.co.nikodem.dFSmpPlus.Entities.CustomDrops.DFCustomDrops;
import uk.co.nikodem.dFSmpPlus.Events.Entity.EntityDamageByEntityEvent;
import uk.co.nikodem.dFSmpPlus.Events.Entity.EntityDeathEvent;
import uk.co.nikodem.dFSmpPlus.Events.Entity.EntityPickupItemEvent;
import uk.co.nikodem.dFSmpPlus.Events.Entity.Food.FoodLevelChangeEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.*;
import uk.co.nikodem.dFSmpPlus.Events.Player.Block.BlockBreakEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Block.BlockBreakProgressUpdateEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.CraftItemEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.InventoryOpenEvent;
import uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.PrepareSmithingEvent;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetEvents;
import uk.co.nikodem.dFSmpPlus.Utils.Server.BungeeUtils;
import uk.co.nikodem.dFSmpPlus.Utils.Storage.BlockData;
import uk.co.nikodem.dFSmpPlus.Utils.Storage.PlayerData;

import java.util.List;
import java.util.Objects;

public final class DFSmpPlus extends JavaPlugin {

    public static BungeeUtils bungeeUtils;

    public static List<? extends CraftingTemplate> craftingTemplateList;
    public static PlayerData playerData;
    public static BlockData blockData;

    @Override
    public void onEnable() {
        DFSmpPlus.playerData = new PlayerData(this);
        DFSmpPlus.blockData = new BlockData(this);

        bungeeUtils = new BungeeUtils(this);
        bungeeUtils.initiateBungeeCordChannel();

        craftingTemplateList = List.of(
                new VanillaRecipes(this),
                new OtherCustomItemRecipes(this),
                new CoralRecipes(this),
                new ChiselRecipes(this),

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

        RecipeRemover.Run(); // remove the recipes that the crafting templates want to remove
        ChiselBlockData.createChiselBlockData();
        DFCustomDrops.createCustomDropData();

        // Command initiation
        Objects.requireNonNull(getCommand("givedf")).setExecutor(new GiveDF());
        Objects.requireNonNull(getCommand("givedf")).setTabCompleter(new GiveDFTabCompleter());
        Objects.requireNonNull(getCommand("dfmaterialview")).setExecutor(new DFMaterialView(this));

        List<Listener> eventListeners = List.of(
                new FoodLevelChangeEvent(),
                new EntityDamageByEntityEvent(),
                new EntityDamageEvent(),
                new EntityPickupItemEvent(),
                new EntityDeathEvent(this),

                new BlockBreakEvent(),
                new BlockBreakProgressUpdateEvent(),

                new CraftItemEvent(),
                new InventoryOpenEvent(),
                new PrepareSmithingEvent(),

                new PlayerAttemptPickupItemEvent(),
                new PlayerBucketFillEvent(),
                new PlayerDeathEvent(),
                new PlayerDropItemEvent(),
                new PlayerInteractAtEntityEvent(),
                new PlayerInteractEntityEvent(),
                new PlayerInteractEvent(),
                new PlayerJoinEvent()
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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
