package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.MergingCraftingTable;

import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.CustomItemRepresentation;

import java.util.Map;
import java.util.function.Function;

public class ControlledShapelessRecipe {
    private final CustomItemRepresentation[] ingredients;
    private final ItemStack result;
    private Function<Map.Entry<ControlledShapelessRecipe, ItemStack[]>, ItemStack> transform;

    public ControlledShapelessRecipe(CustomItemRepresentation[] ingredients, ItemStack result, Function<Map.Entry<ControlledShapelessRecipe, ItemStack[]>, ItemStack> transformResult) {
        this.ingredients = ingredients;
        this.result = result;
        this.transform = transformResult;
    }

    public ControlledShapelessRecipe(CustomItemRepresentation[] ingredients, ItemStack result) {
        this.ingredients = ingredients;
        this.result = result;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public CustomItemRepresentation[] getIngredients() {
        return this.ingredients;
    }

    public Function<Map.Entry<ControlledShapelessRecipe, ItemStack[]>, ItemStack> getTransformer() {
        return this.transform;
    }
}
