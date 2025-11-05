package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;

import java.util.Map;
import java.util.function.Function;

public class AnvilRecipe {
    private final CustomItemRepresentation base;
    private final CustomItemRepresentation addition;
    private final ItemStack result;
    private Function<Map.Entry<AnvilRecipe, Map.Entry<ItemStack, ItemStack>>, Map.Entry<ItemStack, ItemStack>> transform;

    public AnvilRecipe(CustomItemRepresentation base, CustomItemRepresentation addition, ItemStack result, Function<Map.Entry<AnvilRecipe, Map.Entry<ItemStack, ItemStack>>, Map.Entry<ItemStack, ItemStack>> transform) {
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

    public Function<Map.Entry<AnvilRecipe, Map.Entry<ItemStack, ItemStack>>, Map.Entry<ItemStack, ItemStack>> getTransformer() {
        return this.transform;
    }
}
