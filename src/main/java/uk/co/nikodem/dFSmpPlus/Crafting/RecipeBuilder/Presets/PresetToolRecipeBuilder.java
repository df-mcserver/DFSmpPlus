package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets;

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
import java.util.List;

public abstract class PresetToolRecipeBuilder extends RecipeBuilder {
    private ItemStack item;
    private RecipeChoice material;
    private RecipeChoice handle = new RecipeChoice.ExactChoice(new ItemStack(Material.STICK));

    private CraftingBookCategory category;
    private String group;

    abstract List<String> getShape();

    public PresetToolRecipeBuilder setItem(Material tool) {
        this.item = new ItemStack(tool);
        return this;
    }

    public PresetToolRecipeBuilder setItem(ItemStack tool) {
        this.item = tool;
        return this;
    }

    public PresetToolRecipeBuilder setItem(DFMaterial tool) {
        this.item = tool.toItemStack();
        return this;
    }

    public PresetToolRecipeBuilder setMaterial(DFMaterial material) {
        this.material = new RecipeChoice.ExactChoice(material.toItemStack());
        return this;
    }

    public PresetToolRecipeBuilder setMaterial(ItemStack material) {
        this.material = new RecipeChoice.ExactChoice(material);
        return this;
    }

    public PresetToolRecipeBuilder setMaterial(Material material) {
        this.material = new RecipeChoice.ExactChoice(new ItemStack(material));
        return this;
    }

    public PresetToolRecipeBuilder setMaterial(RecipeChoice material) {
        this.material = material;
        return this;
    }

    public PresetToolRecipeBuilder setHandle(Material handle) {
        this.handle = new RecipeChoice.ExactChoice(new ItemStack(handle));
        return this;
    }

    public PresetToolRecipeBuilder setHandle(ItemStack handle) {
        this.handle = new RecipeChoice.ExactChoice(handle);
        return this;
    }

    public PresetToolRecipeBuilder setHandle(DFMaterial handle) {
        this.handle = new RecipeChoice.ExactChoice(handle.toItemStack());
        return this;
    }

    public PresetToolRecipeBuilder setHandle(RecipeChoice handle) {
        this.handle = handle;
        return this;
    }

    public PresetToolRecipeBuilder setCategory(@Nullable CraftingBookCategory category) {
        this.category = category;
        return this;
    }

    public PresetToolRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Recipe build(RecipeTemplateInfo info, String extra) {
        return new ShapedRecipeBuilder()
                .setOutput(item)
                .setGroup(this.group)
                .setCategory(this.category)
                .build(info, extra)
                .shape(getShape().toArray(new String[0]))
                .setIngredient('X', material)
                .setIngredient('I', handle);
    }

    @Override
    public Recipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
