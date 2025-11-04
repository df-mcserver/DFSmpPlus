package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.ItemRepairCombineRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class SculkRecipes extends CraftingTemplate {
    public SculkRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addArmour(recipesToAdd);

        return recipesToAdd;
    }

    public void addArmour(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.SculkHelmet)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("SES", "S S")
                        .setIngredient('S', new RecipeChoice.ExactChoice(DFMaterial.SculkFragment.toItemStack()))
                        .setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ECHO_SHARD)))
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkHelmet)
                        .build(getInfo(), "SculkHelmet")
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.SculkChestplate)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("S S", "SES", "ESE")
                        .setIngredient('S', new RecipeChoice.ExactChoice(DFMaterial.SculkFragment.toItemStack()))
                        .setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ECHO_SHARD)))
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkChestplate)
                        .build(getInfo(), "SculkChestplate")
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.SculkLeggings)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("SSS", "E E", "S S")
                        .setIngredient('S', new RecipeChoice.ExactChoice(DFMaterial.SculkFragment.toItemStack()))
                        .setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ECHO_SHARD)))
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkLeggings)
                        .build(getInfo(), "SculkLeggings")
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.SculkBoots)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("S S", "E E")
                        .setIngredient('S', new RecipeChoice.ExactChoice(DFMaterial.SculkFragment.toItemStack()))
                        .setIngredient('E', new RecipeChoice.ExactChoice(new ItemStack(Material.ECHO_SHARD)))
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkBoots)
                        .build(getInfo(), "SculkBoots")
        );
    }

    @Override
    public String getRecipesID() {
        return "sculk-customset";
    }
}
