package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Etc;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.RecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.annotation.Nullable;

public class CoralRehydrationRecipeBuilder extends RecipeBuilder {
    private ItemStack coral;
    private RecipeChoice deadCoral;

    private CraftingBookCategory category;
    private String group;

    public CoralRehydrationRecipeBuilder setItem(Material coral) {
        this.coral = new ItemStack(coral);
        return this;
    }

    public CoralRehydrationRecipeBuilder setItem(ItemStack coral) {
        this.coral = coral;
        return this;
    }

    public CoralRehydrationRecipeBuilder setItem(DFMaterial coral) {
        this.coral = coral.toItemStack();
        return this;
    }

    public CoralRehydrationRecipeBuilder setMaterial(DFMaterial deadCoral) {
        this.deadCoral = new RecipeChoice.ExactChoice(deadCoral.toItemStack());
        return this;
    }

    public CoralRehydrationRecipeBuilder setMaterial(ItemStack deadCoral) {
        this.deadCoral = new RecipeChoice.ExactChoice(deadCoral);
        return this;
    }

    public CoralRehydrationRecipeBuilder setMaterial(Material deadCoral) {
        this.deadCoral = new RecipeChoice.ExactChoice(new ItemStack(deadCoral));
        return this;
    }

    public CoralRehydrationRecipeBuilder setMaterial(RecipeChoice deadCoral) {
        this.deadCoral = deadCoral;
        return this;
    }

    public CoralRehydrationRecipeBuilder setCategory(@Nullable CraftingBookCategory category) {
        this.category = category;
        return this;
    }

    public CoralRehydrationRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Recipe build(RecipeTemplateInfo info, String extra) {
        return new ShapedRecipeBuilder()
                .setOutput(coral)
                .setGroup(this.group)
                .setCategory(this.category)
                .build(info, extra)
                .shape("W", "C")
                .setIngredient('W', Material.WATER_BUCKET)
                .setIngredient('C', deadCoral);
    }

    @Override
    public Recipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
