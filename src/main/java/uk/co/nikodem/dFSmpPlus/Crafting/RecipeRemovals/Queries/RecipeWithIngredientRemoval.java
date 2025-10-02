package uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemovalQuery;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class RecipeWithIngredientRemoval extends RecipeRemovalQuery {
    private RecipeChoice ingredient;
    private boolean onlyMinecraftNamespace = false;

    public RecipeWithIngredientRemoval setIngredient(ItemStack result) {
        this.ingredient = new RecipeChoice.ExactChoice(result);
        return this;
    }

    public RecipeWithIngredientRemoval setIngredient(Material result) {
        this.ingredient = new RecipeChoice.ExactChoice(new ItemStack(result));
        return this;
    }

    public RecipeWithIngredientRemoval setIngredient(DFMaterial result) {
        this.ingredient = new RecipeChoice.ExactChoice(result.toItemStack());
        return this;
    }

    public RecipeWithIngredientRemoval setIngredient(RecipeChoice result) {
        this.ingredient = result;
        return this;
    }

    public RecipeWithIngredientRemoval onlyUseMinecraftNamespace() {
        this.onlyMinecraftNamespace = true;
        return this;
    }

    public RecipeChoice getIngredient() {
        return this.ingredient;
    }

    public boolean getOnlyUseMinecraftNamespace() {
        return this.onlyMinecraftNamespace;
    }
}
