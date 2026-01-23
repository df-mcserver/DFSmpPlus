package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.BlastFurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.FurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class HiddenRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addComicallyLarge(recipesToAdd);
        addMattBlade(recipesToAdd);

        return recipesToAdd;
    }

    public void addComicallyLarge(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ComicallyLargeShovel)
                        .build(getInfo(), "cls")
                        .shape("X", "S", "S")
                        .setIngredient('X', Material.IRON_BLOCK)
                        .setIngredient('S', new RecipeChoice.MaterialChoice(Tag.PLANKS))
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.ComicallyLargeShovel)
                        .setOutput(Material.IRON_INGOT)
                        .build(getInfo(), "CLS-Ingot")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.ComicallyLargeShovel)
                        .setOutput(Material.IRON_INGOT)
                        .build(getInfo(), "CLS-IngotBlast")
        );
    }

    public void addMattBlade(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.MattBlade)
                        .build(getInfo(), "mblade")
                        .shape("E", "S")
                        .setIngredient('E', Material.CREEPER_SPAWN_EGG)
                        .setIngredient('S', new RecipeChoice.ExactChoice(ItemStack.of(Material.DIAMOND_SWORD)))
        );
    }

    @Override
    public String getRecipesID() {
        return "hidden";
    }
}
