package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.StonecuttingRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.annotation.Nullable;

public class StonecutterRecipeBuilder extends RecipeBuilder {

    private RecipeChoice.ExactChoice source;
    private ItemStack output;

    private String group;

    public StonecutterRecipeBuilder setSource(RecipeChoice.ExactChoice source) {
        this.source = source;
        return this;
    }

    public StonecutterRecipeBuilder setSource(ItemStack source) {
        this.source = new RecipeChoice.ExactChoice(source);
        return this;
    }

    public StonecutterRecipeBuilder setSource(DFMaterial source) {
        this.source = new RecipeChoice.ExactChoice(source.toItemStack());
        return this;
    }

    public StonecutterRecipeBuilder setSource(Material source) {
        this.source = new RecipeChoice.ExactChoice(new ItemStack(source));
        return this;
    }

    public StonecutterRecipeBuilder setOutput(Material output) {
        this.output = new ItemStack(output);
        return this;
    }

    public StonecutterRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public StonecutterRecipeBuilder setOutput(DFMaterial output) {
        this.output = output.toItemStack();
        return this;
    }

    public StonecutterRecipeBuilder setOutput(Material output, int amount) {
        this.output = new ItemStack(output, amount);
        return this;
    }

    public StonecutterRecipeBuilder setOutput(DFMaterial output, int amount) {
        this.output = output.toItemStack(amount);
        return this;
    }

    public StonecutterRecipeBuilder setGroup(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public StonecuttingRecipe build(RecipeTemplateInfo info, String extra) {
        StonecuttingRecipe recipe =  new StonecuttingRecipe(
                MakeNamespacedKey(
                        MakeId(info, output, extra)
                ),
                output,
                source
        );
        if (this.group != null) recipe.setGroup(this.group);
        info.addKey(recipe.getKey());
        return recipe;
    }

    @Override
    public StonecuttingRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
