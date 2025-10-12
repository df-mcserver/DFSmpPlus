package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.*;
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

        recipesToAdd.add(
                new PickaxeRecipeBuilder()
                        .setItem(DFMaterial.CalcitePickaxe)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new ShovelRecipeBuilder()
                        .setItem(DFMaterial.CalciteShovel)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

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
    }

    public void addArmour(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new StandardHelmetRecipeBuilder()
                        .setItem(DFMaterial.CalciteHelmet)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(DFMaterial.CalciteChestplate)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(DFMaterial.CalciteLeggings)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(DFMaterial.CalciteBoots)
                        .setMaterial(Material.CALCITE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );
    }

    @Override
    public String getRecipesID() {
        return "calcite-customset";
    }
}
