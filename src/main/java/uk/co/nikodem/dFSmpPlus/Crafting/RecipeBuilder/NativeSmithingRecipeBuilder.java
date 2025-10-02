package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingTransformRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.function.Function;

public class NativeSmithingRecipeBuilder extends RecipeBuilder {
    private ItemStack output;

    private RecipeChoice template;
    private RecipeChoice base;
    private RecipeChoice addition;
    private boolean copy = true;

    public NativeSmithingRecipeBuilder disableCopying() {
        this.copy = false;
        return this;
    }

    public NativeSmithingRecipeBuilder setCopy(boolean copy) {
        this.copy = copy;
        return this;
    }

    public NativeSmithingRecipeBuilder setOutput(Material output) {
        this.output = new ItemStack(output);
        return this;
    }

    public NativeSmithingRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public NativeSmithingRecipeBuilder setOutput(DFMaterial output) {
        this.output = output.toItemStack();
        return this;
    }

    public NativeSmithingRecipeBuilder setTemplate(DFMaterial template) {
        this.template = new RecipeChoice.ExactChoice(template.toItemStack());
        return this;
    }

    public NativeSmithingRecipeBuilder setTemplate(Material template) {
        this.template = new RecipeChoice.ExactChoice(new ItemStack(template));
        return this;
    }

    public NativeSmithingRecipeBuilder setTemplate(ItemStack template) {
        this.template = new RecipeChoice.ExactChoice(template);
        return this;
    }

    public NativeSmithingRecipeBuilder setTemplate(RecipeChoice template) {
        this.template = template;
        return this;
    }

    public NativeSmithingRecipeBuilder setBase(DFMaterial base) {
        this.base = new RecipeChoice.ExactChoice(base.toItemStack());
        return this;
    }

    public NativeSmithingRecipeBuilder setBase(Material base) {
        this.base = new RecipeChoice.ExactChoice(new ItemStack(base));
        return this;
    }

    public NativeSmithingRecipeBuilder setBase(ItemStack base) {
        this.base = new RecipeChoice.ExactChoice(base);
        return this;
    }

    public NativeSmithingRecipeBuilder setBase(RecipeChoice base) {
        this.base = base;
        return this;
    }

    public NativeSmithingRecipeBuilder setAddition(DFMaterial addition) {
        this.addition = new RecipeChoice.ExactChoice(addition.toItemStack());
        return this;
    }

    public NativeSmithingRecipeBuilder setAddition(Material addition) {
        this.addition = new RecipeChoice.ExactChoice(new ItemStack(addition));
        return this;
    }

    public NativeSmithingRecipeBuilder setAddition(ItemStack addition) {
        this.addition = new RecipeChoice.ExactChoice(addition);
        return this;
    }

    public NativeSmithingRecipeBuilder setAddition(RecipeChoice addition) {
        this.addition = addition;
        return this;
    }

    public NativeSmithingRecipeBuilder setOutputChange(Function<RecipeChoice, ItemStack> run) {
        this.output = run.apply(base);
        return this;
    }

    @Override
    public SmithingTransformRecipe build(RecipeTemplateInfo info, String extra) {
        SmithingTransformRecipe recipe =  new SmithingTransformRecipe(
                MakeNamespacedKey(
                        MakeId(info, output, extra)
                ),
                output,
                template,
                base,
                addition,
                copy
        );
        info.addKey(recipe.getKey());
        return recipe;
    }

    @Override
    public SmithingTransformRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
