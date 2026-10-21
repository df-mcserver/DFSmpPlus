package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable;

import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;

import java.util.Map;
import java.util.function.Function;

public class SmithingTableRecipe {

    private final ItemStack result;
    private final CustomItemRepresentation template;
    private final CustomItemRepresentation base;
    private final CustomItemRepresentation addition;

    private Function<Map.Entry<SmithingTableRecipe, PrepareSmithingEvent>, ItemStack> transformResult;

    public SmithingTableRecipe(ItemStack result, CustomItemRepresentation template, CustomItemRepresentation base, CustomItemRepresentation addition, Function<Map.Entry<SmithingTableRecipe, PrepareSmithingEvent>, ItemStack> transformResult) {
        this.result = result;
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.transformResult = transformResult;
    }

    public SmithingTableRecipe(ItemStack result, CustomItemRepresentation template, CustomItemRepresentation base, CustomItemRepresentation addition) {
        this.result = result;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    public ItemStack transformResult(PrepareSmithingEvent event) {
        if (transformResult == null) return this.result;
        return transformResult.apply(Map.entry(this, event));
    }

    public ItemStack getResult() {
        return this.result;
    }

    public CustomItemRepresentation getTemplate() {
        return this.template;
    }

    public CustomItemRepresentation getBase() {
        return this.base;
    }

    public CustomItemRepresentation getAddition() {
        return this.addition;
    }
}
