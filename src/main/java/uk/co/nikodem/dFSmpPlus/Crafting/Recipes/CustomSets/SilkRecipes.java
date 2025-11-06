package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairAnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Tools.*;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class SilkRecipes extends CraftingTemplate {
    public SilkRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        recipesToAdd.add(
                new SwordRecipeBuilder()
                        .setItem(DFMaterial.SilkSword)
                        .setMaterial(Material.STRING)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.SilkSword)
                .setAddition(Material.STRING)
                .assign();

        recipesToAdd.add(
                new LeftAxeRecipeBuilder()
                        .setItem(DFMaterial.SilkAxe)
                        .setMaterial(Material.STRING)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("SilkAxe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightAxeRecipeBuilder()
                        .setItem(DFMaterial.SilkAxe)
                        .setMaterial(Material.STRING)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("SilkAxe")
                        .build(getInfo(), "Right")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.SilkAxe)
                .setAddition(Material.STRING)
                .assign();

        recipesToAdd.add(
                new PickaxeRecipeBuilder()
                        .setItem(DFMaterial.SilkPickaxe)
                        .setMaterial(Material.STRING)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.SilkPickaxe)
                .setAddition(Material.STRING)
                .assign();

        recipesToAdd.add(
                new ShovelRecipeBuilder()
                        .setItem(DFMaterial.SilkShovel)
                        .setMaterial(Material.STRING)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.SilkShovel)
                .setAddition(Material.STRING)
                .assign();

        recipesToAdd.add(
                new LeftHoeRecipeBuilder()
                        .setItem(DFMaterial.SilkHoe)
                        .setMaterial(Material.STRING)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("SilkHoe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightHoeRecipeBuilder()
                        .setItem(DFMaterial.SilkHoe)
                        .setMaterial(Material.STRING)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("SilkHoe")
                        .build(getInfo(), "Right")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.SilkHoe)
                .setAddition(Material.STRING)
                .assign();

        return recipesToAdd;
    }

    @Override
    public String getRecipesID() {
        return "silk-customset";
    }
}
