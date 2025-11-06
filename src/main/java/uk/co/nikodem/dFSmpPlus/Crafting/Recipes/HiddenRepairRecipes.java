package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ControlledShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.ItemRepairCombineRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class HiddenRepairRecipes extends CraftingTemplate {
    public HiddenRepairRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        doVampireSwordRemoval(recipesToAdd);
        doChiselRecipes(recipesToAdd);

        doCalciteRecipes(recipesToAdd);
        doCopperRecipes(recipesToAdd);
        doFiridiumRecipes(recipesToAdd);
        doObsidianRecipes(recipesToAdd);
        doSculkRecipes(recipesToAdd);
        doSilkRecipes(recipesToAdd);
        doVeinRecipes(recipesToAdd);

        return recipesToAdd;
    }

    public void doVampireSwordRemoval(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ControlledShapelessRecipeBuilder()
                        .addIngredient(DFMaterial.VampireSword)
                        .addIngredient(DFMaterial.VampireSword)
                        .setResult(Material.STICK)
                        .setTransformer((data) -> ItemStack.of(Material.AIR))
                        .build(getInfo(), "VampSwordCombine")
        );
    }

    public void doChiselRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.PointyStick)
                        .build(getInfo(), "PointyStick")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SharpStone)
                        .build(getInfo(), "SharpStone")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperChisel)
                        .build(getInfo(), "CopperChisel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.IronChisel)
                        .build(getInfo(), "IronChisel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumChisel)
                        .build(getInfo(), "FiridiumChisel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.GoldChisel)
                        .build(getInfo(), "GoldChisel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.DiamondChisel)
                        .build(getInfo(), "DiamondChisel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.NetheriteChisel)
                        .build(getInfo(), "NetheriteChisel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianChisel)
                        .build(getInfo(), "ObsidianChisel")
        );
    }

    public void doCalciteRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteSword)
                        .build(getInfo(), "CalciteSword")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteAxe)
                        .build(getInfo(), "CalciteAxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalcitePickaxe)
                        .build(getInfo(), "CalcitePickaxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteShovel)
                        .build(getInfo(), "CalciteShovel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteHoe)
                        .build(getInfo(), "CalciteHoe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteHelmet)
                        .build(getInfo(), "CalciteHelmet")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteChestplate)
                        .build(getInfo(), "CalciteChestplate")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteLeggings)
                        .build(getInfo(), "CalciteLeggings")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteBoots)
                        .build(getInfo(), "CalciteBoots")
        );
    }

    public void doCopperRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperSword)
                        .build(getInfo(), "CopperSword")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperAxe)
                        .build(getInfo(), "CopperAxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperPickaxe)
                        .build(getInfo(), "CopperPickaxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperShovel)
                        .build(getInfo(), "CopperShovel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperHoe)
                        .build(getInfo(), "CopperHoe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperHelmet)
                        .build(getInfo(), "CopperHelmet")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperChestplate)
                        .build(getInfo(), "CopperChestplate")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperLeggings)
                        .build(getInfo(), "CopperLeggings")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperBoots)
                        .build(getInfo(), "CopperBoots")
        );
    }

    public void doFiridiumRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumSword)
                        .build(getInfo(), "FiridiumSword")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumAxe)
                        .build(getInfo(), "FiridiumAxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumPickaxe)
                        .build(getInfo(), "FiridiumPickaxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumShovel)
                        .build(getInfo(), "FiridiumShovel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHoe)
                        .build(getInfo(), "FiridiumHoe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHelmet)
                        .build(getInfo(), "FiridiumHelmet")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumChestplate)
                        .build(getInfo(), "FiridiumChestplate")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumLeggings)
                        .build(getInfo(), "FiridiumLeggings")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumBoots)
                        .build(getInfo(), "FiridiumBoots")
        );
    }

    public void doObsidianRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianSword)
                        .build(getInfo(), "ObsidianSword")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianAxe)
                        .build(getInfo(), "ObsidianAxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianPickaxe)
                        .build(getInfo(), "ObsidianPickaxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianShovel)
                        .build(getInfo(), "ObsidianShovel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianHoe)
                        .build(getInfo(), "ObsidianHoe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianHelmet)
                        .build(getInfo(), "ObsidianHelmet")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianChestplate)
                        .build(getInfo(), "ObsidianChestplate")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianLeggings)
                        .build(getInfo(), "ObsidianLeggings")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianBoots)
                        .build(getInfo(), "ObsidianBoots")
        );
    }

    public void doSculkRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkHelmet)
                        .build(getInfo(), "SculkHelmet")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkChestplate)
                        .build(getInfo(), "SculkChestplate")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkLeggings)
                        .build(getInfo(), "SculkLeggings")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SculkBoots)
                        .build(getInfo(), "SculkBoots")
        );
    }

    public void doSilkRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SilkSword)
                        .build(getInfo(), "SilkSword")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SilkAxe)
                        .build(getInfo(), "SilkAxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SilkPickaxe)
                        .build(getInfo(), "SilkPickaxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SilkShovel)
                        .build(getInfo(), "SilkShovel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.SilkHoe)
                        .build(getInfo(), "SilkHoe")
        );
    }

    public void doVeinRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.VeinAxe)
                        .build(getInfo(), "VeinAxe")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.VeinPickaxe)
                        .build(getInfo(), "VeinPickaxe")
        );
    }

    @Override
    public String getRecipesID() {
        return "hiddenrepairrecipes";
    }
}
