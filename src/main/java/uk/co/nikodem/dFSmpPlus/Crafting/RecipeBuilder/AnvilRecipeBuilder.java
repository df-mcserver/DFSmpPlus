package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.AnvilEvents;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.AnvilRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.Map;
import java.util.function.Function;

public class AnvilRecipeBuilder {
    private CustomItemRepresentation base;
    private CustomItemRepresentation addition;
    private ItemStack result;
    private Function<Map.Entry<AnvilRecipe, Map.Entry<ItemStack, ItemStack>>, Map.Entry<ItemStack, ItemStack>> transformer;

    public AnvilRecipeBuilder setResult(CustomItemRepresentation item) {
        this.result = item.getItem();
        return this;
    }

    public AnvilRecipeBuilder setResult(Material material) {
        this.result = ItemStack.of(material);
        return this;
    }

    public AnvilRecipeBuilder setResult(ItemStack item) {
        this.result = item;
        return this;
    }

    public AnvilRecipeBuilder setResult(DFMaterial material) {
        this.result = material.toItemStack();
        return this;
    }

    public AnvilRecipeBuilder setResult(Function<AnvilRecipeBuilder, ItemStack> item) {
        this.result = item.apply(this);
        return this;
    }

    public AnvilRecipeBuilder setBase(CustomItemRepresentation item) {
        this.base = item;
        return this;
    }

    public AnvilRecipeBuilder setBase(Material material) {
        this.base = new CustomItemRepresentation(material);
        return this;
    }

    public AnvilRecipeBuilder setBase(ItemStack item) {
        this.base = new CustomItemRepresentation(item);
        return this;
    }

    public AnvilRecipeBuilder setBase(DFMaterial material) {
        this.base = new CustomItemRepresentation(material);
        return this;
    }

    public AnvilRecipeBuilder setBase(Function<AnvilRecipeBuilder, CustomItemRepresentation> item) {
        this.base = item.apply(this);
        return this;
    }

    public AnvilRecipeBuilder setAddition(CustomItemRepresentation item) {
        this.addition = item;
        return this;
    }

    public AnvilRecipeBuilder setAddition(Material material) {
        this.addition = new CustomItemRepresentation(material);
        return this;
    }

    public AnvilRecipeBuilder setAddition(ItemStack item) {
        this.addition = new CustomItemRepresentation(item);
        return this;
    }

    public AnvilRecipeBuilder setAddition(DFMaterial material) {
        this.addition = new CustomItemRepresentation(material);
        return this;
    }

    public AnvilRecipeBuilder setAddition(Function<AnvilRecipeBuilder, CustomItemRepresentation> item) {
        this.addition = item.apply(this);
        return this;
    }

    public AnvilRecipeBuilder setTransformer(Function<Map.Entry<AnvilRecipe, Map.Entry<ItemStack, ItemStack>>, Map.Entry<ItemStack, ItemStack>> transformer) {
        this.transformer = transformer;
        return this;
    }

    public void assign() {
        AnvilRecipe recipe = transformer == null ?
                new AnvilRecipe(this.base, this.addition, this.result) :
                new AnvilRecipe(this.base, this.addition, this.result, this.transformer);

        AnvilEvents.recipes.add(recipe); // add the real recipe
    }
}
