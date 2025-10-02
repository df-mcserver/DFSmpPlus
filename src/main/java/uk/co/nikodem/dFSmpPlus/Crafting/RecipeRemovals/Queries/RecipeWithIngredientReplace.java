package uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class RecipeWithIngredientReplace extends RecipeWithIngredientRemoval {
    private RecipeChoice replacementIngredient;

    public RecipeWithIngredientReplace setReplacementIngredient(ItemStack result) {
        this.replacementIngredient = new RecipeChoice.ExactChoice(result);
        return this;
    }

    public RecipeWithIngredientReplace setReplacementIngredient(Material result) {
        this.replacementIngredient = new RecipeChoice.ExactChoice(new ItemStack(result));
        return this;
    }

    public RecipeWithIngredientReplace setReplacementIngredient(DFMaterial result) {
        this.replacementIngredient = new RecipeChoice.ExactChoice(result.toItemStack());
        return this;
    }

    public RecipeWithIngredientReplace setReplacementIngredient(RecipeChoice result) {
        this.replacementIngredient = result;
        return this;
    }

    public RecipeChoice getReplacementIngredient() {
        return this.replacementIngredient;
    }
}
