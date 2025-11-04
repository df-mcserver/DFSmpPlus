package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.ItemRepairCombineRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class VeinRecipes extends CraftingTemplate {
    public VeinRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinAxe)
                        .setGroup("VeinAxe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Left-Vein")
                        .shape("XXX", "XI ", " I ")
                        .setIngredient('X', Material.AMETHYST_SHARD)
                        .setIngredient('I', Material.STICK)
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.VeinAxe)
                        .build(getInfo(), "VeinAxe")
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinAxe)
                        .setGroup("VeinAxe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Right-Vein")
                        .shape("XXX", " IX", " I ")
                        .setIngredient('X', Material.AMETHYST_SHARD)
                        .setIngredient('I', Material.STICK)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinPickaxe)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Vein")
                        .shape("XXX", "XIX", " I ")
                        .setIngredient('X', Material.AMETHYST_SHARD)
                        .setIngredient('I', Material.STICK)
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.VeinPickaxe)
                        .build(getInfo(), "VeinPickaxe")
        );

        return recipesToAdd;
    }

    @Override
    public String getRecipesID() {
        return "vein-customsets";
    }
}
