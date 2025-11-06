package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil;

import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.Transformer.AnvilTransformData;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.Transformer.AnvilTransformResponse;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;

import java.util.Map;
import java.util.function.Function;

public class AnvilRecipe {
    private final CustomItemRepresentation base;
    private final CustomItemRepresentation addition;
    private final ItemStack result;
    private Function<AnvilTransformData, AnvilTransformResponse> transform;

    public AnvilRecipe(CustomItemRepresentation base, CustomItemRepresentation addition, ItemStack result, Function<AnvilTransformData, AnvilTransformResponse> transform) {
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.transform = transform;
    }

    public AnvilRecipe(CustomItemRepresentation base, CustomItemRepresentation addition, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public CustomItemRepresentation getBase() {
        return this.base;
    }
    public CustomItemRepresentation getAddition() {
        return this.addition;
    }

    public Function<AnvilTransformData, AnvilTransformResponse> getTransformer() {
        return this.transform;
    }
}
