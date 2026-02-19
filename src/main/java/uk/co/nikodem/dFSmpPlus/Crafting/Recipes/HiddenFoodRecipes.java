package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class HiddenFoodRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addCustomFoodRecipes(recipesToAdd);
        addCustomDrinkRecipes(recipesToAdd);

        return recipesToAdd;
    }

    public void addCustomFoodRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.StrangeBucket)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "strange_bucket")
                        .addIngredient(Material.BUCKET)
                        .addIngredient(Material.RED_DYE)
                        .addIngredient(Material.WHITE_DYE)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.StrangeChipBox)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "strange_chip_box")
                        .addIngredient(Material.RED_DYE)
                        .addIngredient(Material.WHITE_DYE)
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.Cardboard.toItemStack()))
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.Cardboard.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.DiscreetChickenBucket)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "dcbucket")
                        .addIngredient(Material.COOKED_CHICKEN)
                        .addIngredient(Material.COOKED_CHICKEN)
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.StrangeBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.DiscreetChips)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "dcbox")
                        .addIngredient(Material.BAKED_POTATO)
                        .addIngredient(Material.BAKED_POTATO)
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.StrangeChipBox.toItemStack()))
        );
    }

    public void addCustomDrinkRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.Coke)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coke")
                        .addIngredient(Material.GLASS_BOTTLE)
                        .addIngredient(Material.WATER_BUCKET)
                        .addIngredient(Material.SUGAR)
                        .addIngredient(Material.BROWN_DYE)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.Pepsi)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "pepsi")
                        .addIngredient(Material.GLASS_BOTTLE)
                        .addIngredient(Material.WATER_BUCKET)
                        .addIngredient(Material.SUGAR)
                        .addIngredient(Material.BLACK_DYE)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.Sprite)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "sprite")
                        .addIngredient(Material.GLASS_BOTTLE)
                        .addIngredient(Material.WATER_BUCKET)
                        .addIngredient(Material.SUGAR)
                        .addIngredient(Material.YELLOW_DYE)
        );
    }

    @Override
    public String getRecipesID() {
        return "hiddenfoodrecipes";
    }
}
