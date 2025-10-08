package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithIngredientReplace;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class ChiselRecipes extends CraftingTemplate {
    public ChiselRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addAllChisels(recipesToAdd);

        return recipesToAdd;
    }

    public void addAllChisels(List<Recipe> recipesToAdd) {
        RecipeRemover.addQuery(new RecipeWithIngredientReplace()
                .setReplacementIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.STICK)))
                .setIngredient(Material.STICK));

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.PointyStick)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "PointyStick")
                        .addIngredient(new RecipeChoice.ExactChoice(ItemStack.of(Material.STICK)))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.SharpStone)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "SharpStone")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.LooseStone.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.IronChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "IronChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.IRON_NUGGET)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.DiamondChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "DiamondChisel")
                        .addIngredient(Material.DIAMOND)
        );
    }

    @Override
    public String getRecipesID() {
        return "chisel";
    }
}
