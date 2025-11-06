package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.Transformer;

import org.bukkit.inventory.ItemStack;

public class AnvilTransformResponse {
    private final ItemStack result;
    private final ItemStack additionResult;

    public AnvilTransformResponse(ItemStack result, ItemStack additionResult) {
        this.result = result;
        this.additionResult = additionResult;
    }

    public ItemStack getResult() {
        return result;
    }

    public ItemStack getAdditionResult() {
        return additionResult;
    }
}
