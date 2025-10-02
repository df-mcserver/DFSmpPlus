package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.annotation.Nullable;

public class ShapedRecipeBuilder extends RecipeBuilder {

    private ItemStack output;
    private CraftingBookCategory category;
    private String group;

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
        return recipe;
    }

    @Override
    public ShapedRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
