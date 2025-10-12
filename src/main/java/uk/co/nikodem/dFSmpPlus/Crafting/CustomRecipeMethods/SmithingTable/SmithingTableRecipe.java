package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable;

import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class SmithingTableRecipe {

    private final ItemStack result;
    private final SmithingTableItem template;
    private final SmithingTableItem base;
    private final SmithingTableItem addition;

    private Function<SmithingTableRecipe, ItemStack> transformResult;

    public SmithingTableRecipe(ItemStack result, SmithingTableItem template, SmithingTableItem base, SmithingTableItem addition, Function<SmithingTableRecipe, ItemStack> transformResult) {
        this.result = result;
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.transformResult = transformResult;
    }

    public SmithingTableRecipe(ItemStack result, SmithingTableItem template, SmithingTableItem base, SmithingTableItem addition) {
        this.result = result;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    public ItemStack transformResult() {
        if (transformResult == null) return this.result;

        System.out.println("Running transform result!");

        return transformResult.apply(this);
    }

    public ItemStack getResult() {
        return this.result;
    }

    public SmithingTableItem getTemplate() {
        return this.template;
    }

    public SmithingTableItem getBase() {
        return this.base;
    }

    public SmithingTableItem getAddition() {
        return this.addition;
    }
}
