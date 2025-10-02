package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.RecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.annotation.Nullable;

public class DirectConversionRecipeBuilder extends RecipeBuilder {
    private ItemStack output;
    private RecipeChoice source;

    private CraftingBookCategory category;
    private String group;

    public DirectConversionRecipeBuilder setOutput(Material output) {
        this.output = new ItemStack(output);
        return this;
    }

    public DirectConversionRecipeBuilder setOutput(Material output, int amount) {
        this.output = new ItemStack(output, amount);
        return this;
    }

    public DirectConversionRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public DirectConversionRecipeBuilder setOutput(DFMaterial output) {
        this.output = output.toItemStack();
        return this;
    }

    public DirectConversionRecipeBuilder setOutput(DFMaterial output, int amount) {
        this.output = output.toItemStack(amount);
        return this;
    }

    public DirectConversionRecipeBuilder setSource(DFMaterial source) {
        this.source = new RecipeChoice.ExactChoice(source.toItemStack());
        return this;
    }

    public DirectConversionRecipeBuilder setSource(ItemStack source) {
        this.source = new RecipeChoice.ExactChoice(source);
        return this;
    }

    public DirectConversionRecipeBuilder setSource(Material source) {
        this.source = new RecipeChoice.ExactChoice(new ItemStack(source));
        return this;
    }

    public DirectConversionRecipeBuilder setSource(RecipeChoice source) {
        this.source = source;
        return this;
    }

    public DirectConversionRecipeBuilder setCategory(@Nullable CraftingBookCategory category) {
        this.category = category;
        return this;
    }

    public DirectConversionRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Recipe build(RecipeTemplateInfo info, String extra) {
        return new ShapelessRecipeBuilder()
                .setOutput(output)
                .setGroup(this.group)
                .setCategory(this.category)
                .build(info, extra)
                .addIngredient(source);
    }

    @Override
    public Recipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
