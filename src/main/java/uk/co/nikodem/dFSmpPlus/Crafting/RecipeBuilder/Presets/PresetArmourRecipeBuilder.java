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

public abstract class PresetArmourRecipeBuilder extends RecipeBuilder {
    private ItemStack item;
    private ItemStack material;

    private CraftingBookCategory category;
    private String group;

    abstract List<String> getShape();

    public PresetArmourRecipeBuilder setItem(Material armour) {
        this.item = new ItemStack(armour);
        return this;
    }

    public PresetArmourRecipeBuilder setItem(ItemStack armour) {
        this.item = armour;
        return this;
    }

    public PresetArmourRecipeBuilder setItem(DFMaterial armour) {
        this.item = armour.toItemStack();
        return this;
    }

    public PresetArmourRecipeBuilder setMaterial(DFMaterial material) {
        this.material = material.toItemStack();
        return this;
    }

    public PresetArmourRecipeBuilder setMaterial(ItemStack material) {
        this.material = material;
        return this;
    }

    public PresetArmourRecipeBuilder setMaterial(Material material) {
        this.material = new ItemStack(material);
        return this;
    }

    public PresetArmourRecipeBuilder setCategory(@Nullable CraftingBookCategory category) {
        this.category = category;
        return this;
    }

    public PresetArmourRecipeBuilder setGroup(@Nullable String group) {
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
                .setIngredient('X', new RecipeChoice.ExactChoice(material));
    }

    @Override
    public Recipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
