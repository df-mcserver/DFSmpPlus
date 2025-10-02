package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.annotation.Nullable;

public class SmokerRecipeBuilder extends RecipeBuilder {

    private ItemStack output;
    private RecipeChoice source;
    private Float experience;
    private Integer cookingTime;

    private CookingBookCategory category;
    private String group;

    public SmokerRecipeBuilder setOutput(DFMaterial output) {
        this.output = output.toItemStack();
        return this;
    }

    public SmokerRecipeBuilder setOutput(Material output) {
        this.output = new ItemStack(output);
        return this;
    }

    public SmokerRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public SmokerRecipeBuilder setSource(ItemStack source) {
        this.source = new RecipeChoice.ExactChoice(source);
        return this;
    }

    public SmokerRecipeBuilder setSource(DFMaterial source) {
        this.source = new RecipeChoice.ExactChoice(source.toItemStack());
        return this;
    }

    public SmokerRecipeBuilder setSource(Material source) {
        this.source = new RecipeChoice.ExactChoice(new ItemStack(source));
        return this;
    }

    public SmokerRecipeBuilder setSource(RecipeChoice source) {
        this.source = source;
        return this;
    }

    public SmokerRecipeBuilder setExperience(float exp) {
        this.experience = exp;
        return this;
    }

    public SmokerRecipeBuilder setCookingTime(int time) {
        this.cookingTime = time;
        return this;
    }

    public SmokerRecipeBuilder setCategory(@Nullable CookingBookCategory category) {
        this.category = category;
        return this;
    }

    public SmokerRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public SmokingRecipe build(RecipeTemplateInfo info, String extra) {
        SmokingRecipe recipe =  new SmokingRecipe(
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
    public SmokingRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
