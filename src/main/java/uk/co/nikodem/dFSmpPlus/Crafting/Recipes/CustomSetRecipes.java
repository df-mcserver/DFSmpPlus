package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipes.SmithingTable.SmithingTableItem;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.BlastFurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.SmithingTableRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithIngredientReplace;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class CustomSetRecipes extends CraftingTemplate {
    public CustomSetRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addCopper(recipesToAdd);
        addVein(recipesToAdd);
        addFiridium(recipesToAdd);
        addObsidian(recipesToAdd);

        return recipesToAdd;
    }

    public void addFiridium(List<Recipe> recipesToAdd) {
        RecipeRemover.addQuery(new RecipeWithIngredientReplace()
                .setReplacementIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)))
                .setIngredient(Material.IRON_INGOT));

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

        recipesToAdd.add(
                new SwordRecipeBuilder()
                        .setItem(DFMaterial.FiridiumSword)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
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
                new PickaxeRecipeBuilder()
                        .setItem(DFMaterial.FiridiumPickaxe)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setHandle(Material.IRON_BARS)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
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
                new StandardHelmetRecipeBuilder()
                        .setItem(DFMaterial.FiridiumHelmet)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(DFMaterial.FiridiumChestplate)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(DFMaterial.FiridiumLeggings)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(DFMaterial.FiridiumBoots)
                        .setMaterial(DFMaterial.FiridiumIngot)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Firidium")
        );
    }

    public void addObsidian(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ObsidianUpgradeTemplate, 2)
                        .build(getInfo())
                        .shape("XYX", "XOX", "XXX")
                        .setIngredient('X', Material.DIAMOND)
                        .setIngredient('O', Material.CRYING_OBSIDIAN)
                        .setIngredient('Y', new RecipeChoice.ExactChoice(DFMaterial.ObsidianUpgradeTemplate.toItemStack()))
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_SWORD))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianSword)
                        .build(getInfo(), "ObsidianSword")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_AXE))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianAxe)
                        .build(getInfo(), "ObsidianAxe")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_PICKAXE))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianPickaxe)
                        .build(getInfo(), "ObsidianPickaxe")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_SHOVEL))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianShovel)
                        .build(getInfo(), "ObsidianShovel")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_HOE))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianHoe)
                        .build(getInfo(), "ObsidianHoe")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_HELMET))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianHelmet)
                        .build(getInfo(), "ObsidianHelmet")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_CHESTPLATE))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianChestplate)
                        .build(getInfo(), "ObsidianChestplate")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_LEGGINGS))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianLeggings)
                        .build(getInfo(), "ObsidianLeggings")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(Material.NETHERITE_BOOTS))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianBoots)
                        .build(getInfo(), "ObsidianBoots")
        );
    }

    public void addVein(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinAxe)
                        .setGroup("VeinAxe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Left-Vein")
                        .shape("XXX", "XI ", " I ")
                        .setIngredient('X', Material.AMETHYST_SHARD)
                        .setIngredient('I', Material.STICK)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinAxe)
                        .setGroup("VeinAxe")
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Right-Vein")
                        .shape("XXX", " IX", " I ")
                        .setIngredient('X', Material.AMETHYST_SHARD)
                        .setIngredient('I', Material.STICK)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinPickaxe)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "Vein")
                        .shape("XXX", "XIX", " I ")
                        .setIngredient('X', Material.AMETHYST_SHARD)
                        .setIngredient('I', Material.STICK)
        );
    }

    public void addCopper(List<Recipe> recipesToAdd) {
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

    @Override
    public String getRecipesID() {
        return "customset";
    }
}
