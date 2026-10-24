package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairCombineRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.Metas.Tool.VampireSwordMeta;

import java.util.ArrayList;
import java.util.List;

public class HiddenRepairRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        doVampireRepair(recipesToAdd);
        doChiselRecipes(recipesToAdd);
        doShearsRecipes(recipesToAdd);
        doElytraRecipes(recipesToAdd);

        doCalciteRecipes(recipesToAdd);
        doFiridiumRecipes(recipesToAdd);
        doObsidianRecipes(recipesToAdd);
        doSculkRecipes(recipesToAdd);
        doSilkRecipes(recipesToAdd);
        doVeinRecipes(recipesToAdd);

        return recipesToAdd;
    }

    public void doVampireRepair(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.VampireSword)
                        .transformOutput((data -> {
                            ItemStack swordA = null;
                            ItemStack swordB = null;
                            for (ItemStack item : data.getValue().getInventory().getMatrix()) {
                                if (item == null) continue;
                                if (item.isEmpty()) continue;
                                DFMaterial material = DFItemUtils.getDFMaterial(item);
                                if (material == null) return null;

                                if (material.equals(DFMaterial.VampireSword)) {
                                    if (swordA == null) swordA = item;
                                    else {
                                        swordB = item;
                                        break;
                                    }
                                }
                            }

                            if (swordA == null || swordB == null) return null;
                            ItemMeta swordAMeta = swordA.getItemMeta();
                            ItemMeta swordBMeta = swordB.getItemMeta();
                            if (swordAMeta == null || swordBMeta == null) return null;
                            Integer stageA = swordAMeta.getPersistentDataContainer().get(Keys.vampireSwordStage, PersistentDataType.INTEGER);
                            Integer stageB = swordBMeta.getPersistentDataContainer().get(Keys.vampireSwordStage, PersistentDataType.INTEGER);
                            if (stageA == null || stageB == null) return null;

                            int newStage = Math.max(Math.max(stageA, stageB) - 1, 0);
                            ItemStack newSword = DFMaterial.VampireSword.toItemStack();
                            DFItemUtils.set(
                                    newSword,
                                    Keys.vampireSwordStage,
                                    PersistentDataType.INTEGER,
                                    newStage
                            );

                            DFItemUtils.set(
                                    newSword,
                                    Keys.vampireSwordWasRepaired,
                                    PersistentDataType.BOOLEAN,
                                    true
                            );

                            VampireSwordMeta meta = new VampireSwordMeta();
                            meta.updateLore(newSword, newStage);
                            meta.updateModel(newSword, newStage);

                            return newSword;
                        }))
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "repairbycombine")
                        .addIngredient(RecipeChoice.predicateChoice(x -> {
                            if (x.isEmpty()) return false;
                            DFMaterial material = DFItemUtils.getDFMaterial(x);
                            if (material == null) return false;
                            return material.equals(DFMaterial.VampireSword);
                        }, DFMaterial.VampireSword.toItemStack()))
                        .addIngredient(RecipeChoice.predicateChoice(x -> {
                            if (x.isEmpty()) return false;
                            DFMaterial material = DFItemUtils.getDFMaterial(x);
                            if (material == null) return false;
                            return material.equals(DFMaterial.VampireSword);
                        }, DFMaterial.VampireSword.toItemStack()))
        );
    }

    public void doElytraRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.DiamondTintedElytra)
                        .build(getInfo(), "DiamondTintedElytra")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.NetheriteTintedElytra)
                        .build(getInfo(), "NetheriteTintedElytra")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianTintedElytra)
                        .build(getInfo(), "ObsidianTintedElytra")
        );
    }

    public void doShearsRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CopperShears)
                        .build(getInfo(), "CopperShears")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumShears)
                        .build(getInfo(), "FiridiumShears")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.GoldShears)
                        .build(getInfo(), "GoldShears")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.DiamondShears)
                        .build(getInfo(), "DiamondShears")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.NetheriteShears)
                        .build(getInfo(), "NetheriteShears")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianShears)
                        .build(getInfo(), "ObsidianShears")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FloralShears)
                        .build(getInfo(), "FloralShears")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteShears)
                        .build(getInfo(), "CalciteShears")
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
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FloralChisel)
                        .build(getInfo(), "FloralChisel")
        );
        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.CalciteChisel)
                        .build(getInfo(), "CalciteChisel")
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
