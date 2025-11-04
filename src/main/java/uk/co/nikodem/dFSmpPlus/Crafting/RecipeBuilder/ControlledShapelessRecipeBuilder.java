package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.MergingCraftingTable.ControlledShapelessRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.MergingCraftingTable.CraftingTableEvents;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ControlledShapelessRecipeBuilder extends RecipeBuilder {
    private final List<CustomItemRepresentation> ingredients = new ArrayList<>();
    private ItemStack result;
    private Function<Map.Entry<ControlledShapelessRecipe, ItemStack[]>, ItemStack> transformer;

    public ControlledShapelessRecipeBuilder addIngredient(CustomItemRepresentation item) {
        this.ingredients.add(item);
        return this;
    }

    public ControlledShapelessRecipeBuilder addIngredient(Material material) {
        this.ingredients.add(new CustomItemRepresentation(material));
        return this;
    }

    public ControlledShapelessRecipeBuilder addIngredient(ItemStack item) {
        this.ingredients.add(new CustomItemRepresentation(item));
        return this;
    }

    public ControlledShapelessRecipeBuilder addIngredient(DFMaterial material) {
        this.ingredients.add(new CustomItemRepresentation(material));
        return this;
    }

    public ControlledShapelessRecipeBuilder setResult(CustomItemRepresentation item) {
        this.result = item.getItem();
        return this;
    }

    public ControlledShapelessRecipeBuilder setResult(Material material) {
        this.result = ItemStack.of(material);
        return this;
    }

    public ControlledShapelessRecipeBuilder setResult(ItemStack item) {
        this.result = item;
        return this;
    }

    public ControlledShapelessRecipeBuilder setResult(DFMaterial material) {
        this.result = material.toItemStack();
        return this;
    }

    public ControlledShapelessRecipeBuilder setResult(Function<ControlledShapelessRecipeBuilder, ItemStack> item) {
        this.result = item.apply(this);
        return this;
    }

    public ControlledShapelessRecipeBuilder setTransformer(Function<Map.Entry<ControlledShapelessRecipe, ItemStack[]>, ItemStack> transformer) {
        this.transformer = transformer;
        return this;
    }

    @Override
    public ShapelessRecipe build(RecipeTemplateInfo info, String extra) {

        ControlledShapelessRecipe realRecipe = transformer == null ?
                new ControlledShapelessRecipe(this.ingredients.toArray(new CustomItemRepresentation[0]), this.result) :
                new ControlledShapelessRecipe(this.ingredients.toArray(new CustomItemRepresentation[0]), this.result, this.transformer);

        CraftingTableEvents.recipes.add(realRecipe); // add the real recipe

        ShapelessRecipe dummyRecipe = new ShapelessRecipe(
                MakeNamespacedKey(
                        MakeId(info, this.result, extra)
                ),
                this.result
        );

        for (CustomItemRepresentation representation : this.ingredients) {
            dummyRecipe.addIngredient(new RecipeChoice.ExactChoice(representation.getItem()));
        }

        info.addKey(dummyRecipe.getKey());
        return dummyRecipe;
    }

    @Override
    public ShapelessRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
