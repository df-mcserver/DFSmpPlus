package uk.co.nikodem.dFSmpPlus.Crafting;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.ArrayList;
import java.util.List;

public abstract class CraftingTemplate {
    public abstract List<Recipe> populateRecipes(); // the function where the recipes are made
    public abstract String getRecipesID();

    List<NamespacedKey> namespacedKeysToDiscover = new ArrayList<>();

    public final DFSmpPlus plugin;
    public final RecipeTemplateInfo info;

    public CraftingTemplate(DFSmpPlus plugin) {
        this.plugin = plugin;
        this.info = new RecipeTemplateInfo(getRecipesID(), namespacedKeysToDiscover);

        List<Recipe> recipesToAdd = populateRecipes(); // add the recipes to the list

        for (Recipe recipe : recipesToAdd) { // add the recipes from the list :)
            try {
                Bukkit.addRecipe(recipe);
            } catch (IllegalStateException e) {
                plugin.getLogger().severe("Duplicate recipe! Cannot add this recipe!");
                plugin.getLogger().severe("Recipe "+recipe.toString()+" | Result: "+recipe.getResult());
            }
        }
    }



    public void discoverRecipes(Player plr) { // autodiscovery
        // no auto discovery
        for (NamespacedKey namespacedKey : namespacedKeysToDiscover) { // add the recipes from the list :)
            plr.discoverRecipe(namespacedKey);
        }
    }

    public List<NamespacedKey> getKeys() {
        return namespacedKeysToDiscover;
    }

    public RecipeTemplateInfo getInfo() {
        return this.info;
    }
}
