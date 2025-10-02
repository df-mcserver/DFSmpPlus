package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.annotation.Nullable;

public class BlastFurnaceRecipeBuilder extends RecipeBuilder {

    private ItemStack output;
    private RecipeChoice source;
    private Float experience;
    private Integer cookingTime;

    private CookingBookCategory category;
    private String group;

    public BlastFurnaceRecipeBuilder setOutput(DFMaterial output) {
        this.output = output.toItemStack();
        return this;
    }

    public BlastFurnaceRecipeBuilder setOutput(Material output) {
        this.output = new ItemStack(output);
        return this;
    }

    public BlastFurnaceRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public BlastFurnaceRecipeBuilder setSource(ItemStack source) {
        this.source = new RecipeChoice.ExactChoice(source);
        return this;
    }

    public BlastFurnaceRecipeBuilder setSource(DFMaterial source) {
        this.source = new RecipeChoice.ExactChoice(source.toItemStack());
        return this;
    }

    public BlastFurnaceRecipeBuilder setSource(Material source) {
        this.source = new RecipeChoice.ExactChoice(new ItemStack(source));
        return this;
    }

    public BlastFurnaceRecipeBuilder setSource(RecipeChoice source) {
        this.source = source;
        return this;
    }

    public BlastFurnaceRecipeBuilder setExperience(float exp) {
        this.experience = exp;
        return this;
    }

    public BlastFurnaceRecipeBuilder setCookingTime(int time) {
        this.cookingTime = time;
        return this;
    }

    public BlastFurnaceRecipeBuilder setCategory(@Nullable CookingBookCategory category) {
        this.category = category;
        return this;
    }

    public BlastFurnaceRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public BlastingRecipe build(RecipeTemplateInfo info, String extra) {
        BlastingRecipe recipe =  new BlastingRecipe(
                MakeNamespacedKey(
                        MakeId(info, output, extra)
                ),
                output,
                source,
                experience == null ? 0F : experience,
                cookingTime == null ? 100 : cookingTime
        );
        if (this.category != null) recipe.setCategory(this.category);
        if (this.group != null) recipe.setGroup(this.group);
        info.addKey(recipe.getKey());
        return recipe;
    }

    @Override
    public BlastingRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
