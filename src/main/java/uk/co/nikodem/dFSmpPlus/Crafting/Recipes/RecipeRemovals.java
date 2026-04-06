package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithIngredientReplace;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithResultRemoval;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;

import java.util.List;

public class RecipeRemovals extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        replaceIngredientsWithExactChoices();

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.DISPENSER)
                        .onlyUseMinecraftNamespace()
        );

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.SOUL_LANTERN)
                        .onlyUseMinecraftNamespace()
        );

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.LANTERN)
                        .onlyUseMinecraftNamespace()
        );

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.COPPER_LANTERN)
                        .onlyUseMinecraftNamespace()
        );

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.CAKE)
                        .onlyUseMinecraftNamespace()
        );

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.SLIME_BLOCK)
                        .onlyUseMinecraftNamespace()
        );

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.END_ROD)
                        .onlyUseMinecraftNamespace()
        );

        RecipeRemover.addQuery( // custom one is implemented
                new RecipeWithResultRemoval()
                        .setResult(Material.PURPUR_BLOCK)
                        .onlyUseMinecraftNamespace()
        );

        return List.of();
    }

    public void replaceIngredientsWithExactChoices() {
        // TODO fix these not working
        RecipeRemover.addQuery(new RecipeWithIngredientReplace()
                .setReplacementIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.STICK)))
                .setIngredient(Material.STICK));

        RecipeRemover.addQuery(new RecipeWithIngredientReplace()
                .setReplacementIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)))
                .setIngredient(Material.IRON_INGOT));

        RecipeRemover.addQuery(new RecipeWithIngredientReplace()
                .setReplacementIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.POPPED_CHORUS_FRUIT)))
                .setIngredient(Material.POPPED_CHORUS_FRUIT));

        RecipeRemover.addQuery(new RecipeWithResultRemoval()
                .onlyUseMinecraftNamespace()
                .setResult(Material.NETHER_WART_BLOCK));
    }

    @Override
    public String getRecipesID() {
        return "removals";
    }
}
