package uk.co.nikodem.dFSmpPlus;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.nikodem.dFSmpPlus.Commands.GiveDF;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipes.SmithingTable.SmithingTableEvents;
import uk.co.nikodem.dFSmpPlus.Crafting.OnCraft;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CoralRecipes;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSetRecipes;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.OtherCustomItemRecipes;
import uk.co.nikodem.dFSmpPlus.Crafting.Recipes.VanillaRecipes;
import uk.co.nikodem.dFSmpPlus.Entities.OnEntityPickUpItem;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.Player.*;
import uk.co.nikodem.dFSmpPlus.Player.DFUpdates.OnOpenContainer;
import uk.co.nikodem.dFSmpPlus.Player.DFUpdates.OnPlayerPickUpItem;
import uk.co.nikodem.dFSmpPlus.Utils.Server.BungeeUtils;
import uk.co.nikodem.dFSmpPlus.Utils.Storage.BlockData;
import uk.co.nikodem.dFSmpPlus.Utils.Storage.PlayerData;
import uk.co.nikodem.dFSmpPlus.WorldGen.PopulateChests;

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
                new CustomSetRecipes(this),
                new OtherCustomItemRecipes(this),
                new CoralRecipes(this)
        );

        RecipeRemover.Run(); // remove the recipes that the crafting templates want to remove

        // Command initiation
        Objects.requireNonNull(getCommand("givedf")).setExecutor(new GiveDF());

        // Event initiation
        getServer().getPluginManager().registerEvents(new OnCraft(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getPluginManager().registerEvents(new OnMove(), this);
        getServer().getPluginManager().registerEvents(new OnDeath(), this);
        getServer().getPluginManager().registerEvents(new OnRespawn(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerPickUpItem(), this);
        getServer().getPluginManager().registerEvents(new OnOpenContainer(), this);
        getServer().getPluginManager().registerEvents(new OnEat(), this);
        getServer().getPluginManager().registerEvents(new OnEntityPickUpItem(), this);
        getServer().getPluginManager().registerEvents(new DFMaterialEvents(), this);
        getServer().getPluginManager().registerEvents(new SmithingTableEvents(), this);
        getServer().getPluginManager().registerEvents(new PopulateChests(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
