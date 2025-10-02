package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.annotation.Nullable;

public class FurnaceRecipeBuilder extends RecipeBuilder {

    private ItemStack output;
    private RecipeChoice source;
    private Float experience;
    private Integer cookingTime;

    private CookingBookCategory category;
    private String group;

    public FurnaceRecipeBuilder setOutput(DFMaterial output) {
        this.output = output.toItemStack();
        return this;
    }

    public FurnaceRecipeBuilder setOutput(Material output) {
        this.output = new ItemStack(output);
        return this;
    }

    public FurnaceRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public FurnaceRecipeBuilder setSource(ItemStack source) {
        this.source = new RecipeChoice.ExactChoice(source);
        return this;
    }

    public FurnaceRecipeBuilder setSource(DFMaterial source) {
        this.source = new RecipeChoice.ExactChoice(source.toItemStack());
        return this;
    }

    public FurnaceRecipeBuilder setSource(Material source) {
        this.source = new RecipeChoice.ExactChoice(new ItemStack(source));
        return this;
    }

    public FurnaceRecipeBuilder setSource(RecipeChoice source) {
        this.source = source;
        return this;
    }

    public FurnaceRecipeBuilder setExperience(float exp) {
        this.experience = exp;
        return this;
    }

    public FurnaceRecipeBuilder setCookingTime(int time) {
        this.cookingTime = time;
        return this;
    }

    public FurnaceRecipeBuilder setCategory(@Nullable CookingBookCategory category) {
        this.category = category;
        return this;
    }

    public FurnaceRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public FurnaceRecipe build(RecipeTemplateInfo info, String extra) {
        FurnaceRecipe recipe =  new FurnaceRecipe(
                MakeNamespacedKey(
                        MakeId(info, output, extra)
                ),
                output,
                source,
                experience == null ? 0F : experience,
                cookingTime == null ? 200 : cookingTime
        );
        if (this.category != null) recipe.setCategory(this.category);
        if (this.group != null) recipe.setGroup(this.group);
        info.addKey(recipe.getKey());
        return recipe;
    }

    @Override
    public FurnaceRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
