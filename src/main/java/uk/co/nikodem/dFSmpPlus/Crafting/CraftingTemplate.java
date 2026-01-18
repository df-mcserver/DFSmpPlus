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

    public final RecipeTemplateInfo info;

    public CraftingTemplate() {
        this.info = new RecipeTemplateInfo(getRecipesID(), namespacedKeysToDiscover);

        List<Recipe> recipesToAdd = populateRecipes(); // add the recipes to the list
        int amnt = 0;

        for (Recipe recipe : recipesToAdd) { // add the recipes from the list :)
            DFSmpPlus.totalRecipes++;
            try {
                Bukkit.addRecipe(recipe);
                amnt++;
                DFSmpPlus.totalSuccessfulRecipes++;
            } catch (IllegalStateException e) {
                DFSmpPlus.getPlugin().getLogger().severe("Duplicate recipe! Cannot add this recipe!");
                DFSmpPlus.getPlugin().getLogger().severe("Recipe "+recipe.toString()+" | Result: "+recipe.getResult());
            } catch (IllegalArgumentException e) {
                DFSmpPlus.getPlugin().getLogger().severe("Invalid recipe! Cannot add this recipe!");
                e.printStackTrace();
            }
        }

        DFSmpPlus.getPlugin().getLogger().info("Added "+amnt+"/"+recipesToAdd.size()+" \""+this.getClass().getSimpleName()+"\" recipes");
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
