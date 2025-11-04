package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.BlastFurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.FurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithIngredientReplace;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class FiridiumRecipes extends CraftingTemplate {
    public FiridiumRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        doRemovals();
        addIngotRecipes(recipesToAdd);
        addTools(recipesToAdd);
        addArmour(recipesToAdd);
        addFurnaceRecipes(recipesToAdd);

        return recipesToAdd;
    }

    public void doRemovals() {
        RecipeRemover.addQuery(new RecipeWithIngredientReplace()
                .setReplacementIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)))
                .setIngredient(Material.IRON_INGOT));
    }

    public void addIngotRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(Material.IRON_INGOT)
                        .setOutput(DFMaterial.FiridiumIngot)
                        .setCategory(CookingBookCategory.MISC)
                        .build(getInfo(), "FiridiumIngot")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(Material.IRON_NUGGET)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .setCategory(CookingBookCategory.MISC)
                        .build(getInfo(), "FiridiumNugget")
        );
    }

    public void addTools(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new SwordRecipeBuilder()
                        .setItem(DFMaterial.FiridiumSword)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumSword)
                        .build(getInfo(), "FiridiumSword")
        );

        recipesToAdd.add(
                new LeftAxeRecipeBuilder()
                        .setItem(DFMaterial.FiridiumAxe)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setGroup("FiridiumAxe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Left-Firidium")
        );

        recipesToAdd.add(
                new RightAxeRecipeBuilder()
                        .setItem(DFMaterial.FiridiumAxe)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setGroup("FiridiumAxe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Right-Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumAxe)
                        .build(getInfo(), "FiridiumAxe")
        );

        recipesToAdd.add(
                new PickaxeRecipeBuilder()
                        .setItem(DFMaterial.FiridiumPickaxe)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumPickaxe)
                        .build(getInfo(), "FiridiumPickaxe")
        );

        recipesToAdd.add(
                new ShovelRecipeBuilder()
                        .setItem(DFMaterial.FiridiumShovel)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumShovel)
                        .build(getInfo(), "FiridiumShovel")
        );

        recipesToAdd.add(
                new LeftHoeRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHoe)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setGroup("FiridiumHoe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Left-Firidium")
        );

        recipesToAdd.add(
                new RightHoeRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHoe)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setGroup("FiridiumHoe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Right-Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHoe)
                        .build(getInfo(), "FiridiumHoe")
        );
    }

    public void addArmour(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new StandardHelmetRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHelmet)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHelmet)
                        .build(getInfo(), "FiridiumHelmet")
        );

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(DFMaterial.FiridiumChestplate)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumChestplate)
                        .build(getInfo(), "FiridiumChestplate")
        );

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(DFMaterial.FiridiumLeggings)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumLeggings)
                        .build(getInfo(), "FiridiumLeggings")
        );

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(DFMaterial.FiridiumBoots)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.FiridiumBoots)
                        .build(getInfo(), "FiridiumBoots")
        );
    }

    public void addFurnaceRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumIngot)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltIngot")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumSword)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltSword")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumAxe)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltAxe")
        );
        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumPickaxe)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltPickaxe")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumShovel)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltShovel")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumHoe)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltHoe")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumHelmet)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltHelmet")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumChestplate)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltChestplate")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumLeggings)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltLeggings")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumBoots)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltBoots")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumChisel)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltChisel")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumIngot)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltIngotBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumSword)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltSwordBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumAxe)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltAxeBlast")
        );
        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumPickaxe)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltPickaxeBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumShovel)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltShovelBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumHoe)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltHoeBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumHelmet)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltHelmetBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumChestplate)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltChestplateBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumLeggings)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltLeggingsBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumBoots)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltBootsBlast")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(DFMaterial.FiridiumChisel)
                        .setOutput(DFMaterial.FiridiumNugget)
                        .build(getInfo(), "FSmeltChiselBlast")
        );
    }

    @Override
    public String getRecipesID() {
        return "firidium-customset";
    }
}
