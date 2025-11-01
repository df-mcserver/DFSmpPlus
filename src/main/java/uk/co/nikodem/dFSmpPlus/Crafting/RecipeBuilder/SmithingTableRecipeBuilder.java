package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingTransformRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.SmithingTableEvents;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.SmithingTableRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.function.Function;

public class SmithingTableRecipeBuilder extends RecipeBuilder {

    private ItemStack result;
    private CustomItemRepresentation base;
    private CustomItemRepresentation template;
    private CustomItemRepresentation addition;
    private Function<SmithingTableRecipe, ItemStack> transformer;

    public SmithingTableRecipeBuilder setResult(ItemStack result) {
        this.result = result;
        return this;
    }

    public SmithingTableRecipeBuilder setResult(Material result) {
        this.result = new ItemStack(result);
        return this;
    }

    public SmithingTableRecipeBuilder setResult(DFMaterial result) {
        this.result = result.toItemStack();
        return this;
    }

    public SmithingTableRecipeBuilder setBase(CustomItemRepresentation base) {
        this.base = base;
        return this;
    }

    public SmithingTableRecipeBuilder setBaseStrict(boolean strict) {
        if (base == null) return this;
        base.setStrict(strict);
        return this;
    }

    public SmithingTableRecipeBuilder setBaseCheck(Function<ItemStack, Boolean> check) {
        if (base == null) return this;
        base.setCheck(check);
        return this;
    }

    public SmithingTableRecipeBuilder setTemplate(ItemStack template) {
        this.template = new CustomItemRepresentation(template);
        return this;
    }

    public SmithingTableRecipeBuilder setTemplate(Material template) {
        this.template = new CustomItemRepresentation(template);
        return this;
    }

    public SmithingTableRecipeBuilder setTemplate(DFMaterial template) {
        this.template = new CustomItemRepresentation(template);
        return this;
    }

    public SmithingTableRecipeBuilder setTemplate(CustomItemRepresentation template) {
        this.template = template;
        return this;
    }

    public SmithingTableRecipeBuilder setTemplateStrict(boolean strict) {
        if (template == null) return this;
        template.setStrict(strict);
        return this;
    }

    public SmithingTableRecipeBuilder setTemplateCheck(Function<ItemStack, Boolean> check) {
        if (template == null) return this;
        template.setCheck(check);
        return this;
    }

    public SmithingTableRecipeBuilder setAddition(ItemStack addition) {
        this.addition = new CustomItemRepresentation(addition);
        return this;
    }

    public SmithingTableRecipeBuilder setAddition(Material addition) {
        this.addition = new CustomItemRepresentation(addition);
        return this;
    }

    public SmithingTableRecipeBuilder setAddition(DFMaterial addition) {
        this.addition = new CustomItemRepresentation(addition);
        return this;
    }

    public SmithingTableRecipeBuilder setAddition(CustomItemRepresentation addition) {
        this.addition = addition;
        return this;
    }

    public SmithingTableRecipeBuilder setAdditionStrict(boolean strict) {
        if (addition == null) return this;
        addition.setStrict(strict);
        return this;
    }

    public SmithingTableRecipeBuilder setAdditionCheck(Function<ItemStack, Boolean> check) {
        if (addition == null) return this;
        addition.setCheck(check);
        return this;
    }

    public SmithingTableRecipeBuilder setTransformer(Function<SmithingTableRecipe, ItemStack> transformer) {
        this.transformer = transformer;
        return this;
    }

    @Override
    public SmithingTransformRecipe build(RecipeTemplateInfo info, String extra) {

        SmithingTableRecipe realRecipe = transformer == null ?
                new SmithingTableRecipe(result, template, base, addition) :
                new SmithingTableRecipe(result, template, base, addition, transformer);

        SmithingTableEvents.recipes.add(realRecipe); // add the real recipe

        SmithingTransformRecipe dummyRecipe = new SmithingTransformRecipe( // make a dummy recipe so that the recipes work properly :)
                MakeNamespacedKey(
                        MakeId(info, result, extra)
                ),
                new ItemStack(Material.DIRT), // dummy output, so that if the recipe fails you don't get a false item
                new RecipeChoice.MaterialChoice(template.getItem().getType()), // basic template check
                new RecipeChoice.MaterialChoice(base.getItem().getType()), // basic base check
                new RecipeChoice.MaterialChoice(addition.getItem().getType()), // basic addition check
                true // copy components
        );
        info.addKey(dummyRecipe.getKey());
        return dummyRecipe;
    }

    @Override
    public SmithingTransformRecipe build(RecipeTemplateInfo info) {
        return this.build(info, "");
    }
}
