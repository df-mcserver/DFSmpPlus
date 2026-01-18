package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class AccessoryRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.LuckyHorseshoe)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "luckyhorseshoe")
                        .shape("FGN", "GIG", "NGF")
                        .setIngredient('G', Material.GOLD_INGOT)
                        .setIngredient('N', Material.GOLD_NUGGET)
                        .setIngredient('I', Material.BREEZE_ROD)
                        .setIngredient('F', Material.FEATHER)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ContaminatedMembrane)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "contaminatedmembrane")
                        .shape("PMP", " C ")
                        .setIngredient('M', Material.PHANTOM_MEMBRANE)
                        .setIngredient('P', Material.POISONOUS_POTATO)
                        .setIngredient('C', Material.FIRE_CHARGE)
        );

        return recipesToAdd;
    }

    @Override
    public String getRecipesID() {
        return "accessory";
    }
}
