package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.BlastFurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.FurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardBootsRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardChestplateRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardHelmetRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardLeggingsRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairAnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Tools.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class FloralRecipes extends CraftingTemplate {
    public FloralRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addPestleAndMortarRecipes(recipesToAdd);
        addIngredientRecipes(recipesToAdd);
        addTools(recipesToAdd);
        addArmour(recipesToAdd);
        addFurnaceRecipes(recipesToAdd);

        return recipesToAdd;
    }

    public void addPestleAndMortarRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.FlowerPestleAndMortar)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "FlowerPM")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.EmptyPestleAndMortar.toItemStack()))
                        .addIngredient(new RecipeChoice.MaterialChoice(Tag.ITEMS_FLOWERS))
        );
    }

    public void addIngredientRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "FloralIngot-Powder")
                        .shape("XO", "OX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FlowerPowder.toItemStack()))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_INGOT)))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.FloralNugget, 9)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "FloralNugget")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.FloralIngot.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "FloralIngot")
                        .shape("XXX", "XXX", "XXX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FloralNugget.toItemStack()))
        );
    }

    public void addTools(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new SwordRecipeBuilder()
                        .setItem(DFMaterial.FloralSword)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralSword)
                .setAddition(DFMaterial.FloralIngot)
                .assign();

        recipesToAdd.add(
                new LeftAxeRecipeBuilder()
                        .setItem(DFMaterial.FloralAxe)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("FloralAxe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightAxeRecipeBuilder()
                        .setItem(DFMaterial.FloralAxe)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("FloralAxe")
                        .build(getInfo(), "Right")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralAxe)
                .setAddition(DFMaterial.FloralIngot)
                .assign();

        recipesToAdd.add(
                new PickaxeRecipeBuilder()
                        .setItem(DFMaterial.FloralPickaxe)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralPickaxe)
                .setAddition(DFMaterial.FloralIngot)
                .assign();

        recipesToAdd.add(
                new ShovelRecipeBuilder()
                        .setItem(DFMaterial.FloralShovel)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralShovel)
                .setAddition(DFMaterial.FloralIngot)
                .assign();

        recipesToAdd.add(
                new LeftHoeRecipeBuilder()
                        .setItem(DFMaterial.FloralHoe)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("FloralHoe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightHoeRecipeBuilder()
                        .setItem(DFMaterial.FloralHoe)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("FloralHoe")
                        .build(getInfo(), "Right")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralHoe)
                .setAddition(DFMaterial.FloralIngot)
                .assign();
    }

    public void addArmour(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new StandardHelmetRecipeBuilder()
                        .setItem(DFMaterial.FloralHelmet)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralHelmet)
                .setAddition(DFMaterial.FloralIngot)
                .assign();

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(DFMaterial.FloralChestplate)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralChestplate)
                .setAddition(DFMaterial.FloralIngot)
                .assign();

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(DFMaterial.FloralLeggings)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralLeggings)
                .setAddition(DFMaterial.FloralIngot)
                .assign();

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(DFMaterial.FloralBoots)
                        .setMaterial(DFMaterial.FloralIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FloralBoots)
                .setAddition(DFMaterial.FloralIngot)
                .assign();
    }

    public void addFurnaceRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralIngot)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltIngot")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralSword)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltSword")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralAxe)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltAxe")
        );
        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralPickaxe)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltPickaxe")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralHoe)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltHoe")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralShovel)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltShovel")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralHelmet)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltHelmet")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralChestplate)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltChestplate")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralLeggings)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltLeggings")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralBoots)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltBoots")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralIngot)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltIngotBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralSword)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltSwordBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralAxe)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltAxeBlast")
        );
        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralPickaxe)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltPickaxeBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralHoe)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltHoeBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralShovel)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltShovelBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralHelmet)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltHelmetBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralChestplate)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltChestplateBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralLeggings)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltLeggingsBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FloralBoots)
                        .setOutput(DFMaterial.FlowerPowder)
                        .build(getInfo(), "SmeltBootsBlast")
        );
    }

    @Override
    public String getRecipesID() {
        return "floral-customset";
    }
}
