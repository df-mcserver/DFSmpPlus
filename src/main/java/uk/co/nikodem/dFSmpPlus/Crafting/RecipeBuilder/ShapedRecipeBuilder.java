package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ShapedRecipeBuilder extends RecipeBuilder {
    public static Map<String, Function<Map.Entry<ShapedRecipe, PrepareItemCraftEvent>, ItemStack>> transformers = new HashMap<>();

    private ItemStack output;
    private CraftingBookCategory category;
    private String group;
    private Function<Map.Entry<ShapedRecipe, PrepareItemCraftEvent>, ItemStack> transform = null;

    public ShapedRecipeBuilder setOutput(Material output) {
        this.output = new ItemStack(output);
        return this;
    }

    public ShapedRecipeBuilder setOutput(DFMaterial output) {
        this.output = output.toItemStack();
        return this;
    }

    public ShapedRecipeBuilder setOutput(Material output, int amount) {
        this.output = new ItemStack(output, amount);
        return this;
    }

    public ShapedRecipeBuilder setOutput(DFMaterial output, int amount) {
        this.output = output.toItemStack(amount);
        return this;
    }

    public ShapedRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public ShapedRecipeBuilder setCategory(@Nullable CraftingBookCategory category) {
        this.category = category;
        return this;
    }

    public ShapedRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    public ShapedRecipeBuilder transformOutput(Function<Map.Entry<ShapedRecipe, PrepareItemCraftEvent>, ItemStack> transform) {
        this.transform = transform;
        return this;
    }

    @Override
    public ShapedRecipe build(RecipeTemplateInfo info, String extra) {
        ShapedRecipe recipe =  new ShapedRecipe(
                MakeNamespacedKey(
                        MakeId(info, output, extra)
                ),
                output
        );
        if (this.category != null) recipe.setCategory(this.category);
        if (this.group != null) recipe.setGroup(this.group);
        info.addKey(recipe.getKey());

        if (this.transform != null) transformers.put(recipe.getKey().asString(), this.transform);

        return recipe;
    }

    @Override
    public ShapedRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
