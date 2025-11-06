package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.Transformer;

import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.AnvilRecipe;

public class AnvilTransformData {
    private final AnvilRecipe recipe;
    private final ItemStack base;
    private final ItemStack addition;

    public AnvilTransformData(AnvilRecipe recipe, ItemStack base, ItemStack addition) {
        this.recipe = recipe;
        this.base = base;
        this.addition = addition;
    }

    public AnvilRecipe getRecipe() {
        return recipe;
    }

    public ItemStack getBase() {
        return base;
    }

    public ItemStack getAddition() {
        return addition;
    }
}
