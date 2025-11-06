package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardBootsRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardChestplateRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardHelmetRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardLeggingsRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairAnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Tools.*;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class CalciteRecipes extends CraftingTemplate {
    public CalciteRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addTools(recipesToAdd);
        addArmour(recipesToAdd);

        return recipesToAdd;
    }

    public void addTools(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new SwordRecipeBuilder()
                        .setItem(DFMaterial.CalciteSword)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteSword)
                .setAddition(Material.CALCITE)
                .assign();

        recipesToAdd.add(
                new LeftAxeRecipeBuilder()
                        .setItem(DFMaterial.CalciteAxe)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CalciteAxe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightAxeRecipeBuilder()
                        .setItem(DFMaterial.CalciteAxe)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CalciteAxe")
                        .build(getInfo(), "Right")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteAxe)
                .setAddition(Material.CALCITE)
                .assign();

        recipesToAdd.add(
                new PickaxeRecipeBuilder()
                        .setItem(DFMaterial.CalcitePickaxe)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalcitePickaxe)
                .setAddition(Material.CALCITE)
                .assign();

        recipesToAdd.add(
                new ShovelRecipeBuilder()
                        .setItem(DFMaterial.CalciteShovel)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteShovel)
                .setAddition(Material.CALCITE)
                .assign();

        recipesToAdd.add(
                new LeftHoeRecipeBuilder()
                        .setItem(DFMaterial.CalciteHoe)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CalciteHoe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightHoeRecipeBuilder()
                        .setItem(DFMaterial.CalciteHoe)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CalciteHoe")
                        .build(getInfo(), "Right")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteHoe)
                .setAddition(Material.CALCITE)
                .assign();
    }

    public void addArmour(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new StandardHelmetRecipeBuilder()
                        .setItem(DFMaterial.CalciteHelmet)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteHelmet)
                .setAddition(Material.CALCITE)
                .assign();

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(DFMaterial.CalciteChestplate)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteChestplate)
                .setAddition(Material.CALCITE)
                .assign();

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(DFMaterial.CalciteLeggings)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteLeggings)
                .setAddition(Material.CALCITE)
                .assign();

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(DFMaterial.CalciteBoots)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CalciteLeggings)
                .setAddition(Material.CALCITE)
                .assign();
    }

    @Override
    public String getRecipesID() {
        return "calcite-customset";
    }
}
