package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
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

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CobaltShield)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "cobaltshield")
                        .shape("NLN", "LGL", " N ")
                        .setIngredient('L', Material.LAPIS_LAZULI)
                        .setIngredient('G', Material.GOLD_INGOT)
                        .setIngredient('N', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_NUGGET)))
        );

        return recipesToAdd;
    }

    @Override
    public String getRecipesID() {
        return "accessory";
    }
}
