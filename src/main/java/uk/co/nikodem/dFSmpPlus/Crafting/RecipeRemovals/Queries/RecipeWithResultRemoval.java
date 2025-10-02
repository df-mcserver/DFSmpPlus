package uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemovalQuery;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class RecipeWithResultRemoval extends RecipeRemovalQuery {
    private ItemStack result;
    private boolean onlyMinecraftNamespace = false;

    public RecipeWithResultRemoval setResult(ItemStack result) {
        this.result = result;
        return this;
    }

    public RecipeWithResultRemoval setResult(Material result) {
        this.result = new ItemStack(result);
        return this;
    }

    public RecipeWithResultRemoval setResult(DFMaterial result) {
        this.result = result.toItemStack();
        return this;
    }

    public RecipeWithResultRemoval onlyUseMinecraftNamespace() {
        this.onlyMinecraftNamespace = true;
        return this;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public boolean getOnlyUseMinecraftNamespace() {
        return this.onlyMinecraftNamespace;
    }
}
