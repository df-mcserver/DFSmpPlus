package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.BlastFurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.FurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class CopperRecipes extends CraftingTemplate {
    public CopperRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addIngotRecipes(recipesToAdd);
        addTools(recipesToAdd);
        addArmour(recipesToAdd);
        addFurnaceRecipes(recipesToAdd);

        return recipesToAdd;
    }

    public void addIngotRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.CopperNugget)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "CopperNugget")
                        .addIngredient(Material.COPPER_INGOT)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "CopperIngot")
                        .shape("XXX", "XXX", "XXX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.CopperNugget.toItemStack()))
        );
    }

    public void addTools(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new SwordRecipeBuilder()
                        .setItem(DFMaterial.CopperSword)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new LeftAxeRecipeBuilder()
                        .setItem(DFMaterial.CopperAxe)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CopperAxe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightAxeRecipeBuilder()
                        .setItem(DFMaterial.CopperAxe)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CopperAxe")
                        .build(getInfo(), "Right")
        );

        recipesToAdd.add(
                new PickaxeRecipeBuilder()
                        .setItem(DFMaterial.CopperPickaxe)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new ShovelRecipeBuilder()
                        .setItem(DFMaterial.CopperShovel)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new LeftHoeRecipeBuilder()
                        .setItem(DFMaterial.CopperHoe)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CopperHoe")
                        .build(getInfo(), "Left")
        );

        recipesToAdd.add(
                new RightHoeRecipeBuilder()
                        .setItem(DFMaterial.CopperHoe)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("CopperHoe")
                        .build(getInfo(), "Right")
        );
    }

    public void addArmour(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new StandardHelmetRecipeBuilder()
                        .setItem(DFMaterial.CopperHelmet)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(DFMaterial.CopperChestplate)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(DFMaterial.CopperLeggings)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(DFMaterial.CopperBoots)
                        .setMaterial(Material.COPPER_INGOT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );
    }

    public void addFurnaceRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(Material.COPPER_INGOT)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltIngot")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperSword)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltSword")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperAxe)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltAxe")
        );
        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperPickaxe)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltPickaxe")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperHoe)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltHoe")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperShovel)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltShovel")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperHelmet)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltHelmet")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperChestplate)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltChestplate")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperLeggings)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltLeggings")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperBoots)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltBoots")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperChisel)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltChisel")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(Material.COPPER_INGOT)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltIngotBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperSword)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltSwordBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperAxe)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltAxeBlast")
        );
        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperPickaxe)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltPickaxeBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperHoe)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltHoeBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperShovel)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltShovelBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperHelmet)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltHelmetBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperChestplate)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltChestplateBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperLeggings)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltLeggingsBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperBoots)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltBootsBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.CopperChisel)
                        .setOutput(DFMaterial.CopperNugget)
                        .build(getInfo(), "SmeltChiselBlast")
        );
    }

    @Override
    public String getRecipesID() {
        return "copper-customset";
    }
}
