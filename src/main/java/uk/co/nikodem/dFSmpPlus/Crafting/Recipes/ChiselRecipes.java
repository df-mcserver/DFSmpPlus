package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Etc.DirectConversionRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairAnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.SmithingTableRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithIngredientReplace;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class ChiselRecipes extends CraftingTemplate {
    public ChiselRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addAllChisels(recipesToAdd);

        return recipesToAdd;
    }

    public void addAllChisels(List<Recipe> recipesToAdd) {
        RecipeRemover.addQuery(new RecipeWithIngredientReplace()
                .setReplacementIngredient(new RecipeChoice.ExactChoice(new ItemStack(Material.STICK)))
                .setIngredient(Material.STICK));


        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(new RecipeChoice.ExactChoice(ItemStack.of(Material.STICK)))
                        .setOutput(DFMaterial.PointyStick)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "PointyStick")
        );

        new ItemRepairAnvilRecipeBuilder() // idk why you'd ever want this
                .setItem(DFMaterial.PointyStick)
                .setAddition(Material.STICK)
                .assign();

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(DFMaterial.LooseStone)
                        .setOutput(DFMaterial.SharpStone)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "SharpStone")
        );

        new ItemRepairAnvilRecipeBuilder() // idk why you'd ever want this
                .setItem(DFMaterial.SharpStone)
                .setAddition(DFMaterial.LooseStone)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "CopperChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.CopperNugget.toItemStack()))
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CopperChisel)
                .setAddition(DFMaterial.CopperNugget)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.IronChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "IronChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_NUGGET)))
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.IronChisel)
                .setAddition(Material.IRON_NUGGET)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FiridiumChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "FiridiumChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FiridiumNugget.toItemStack()))
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FiridiumChisel)
                .setAddition(DFMaterial.FiridiumNugget)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "GoldChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.GOLD_NUGGET)
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.GoldChisel)
                .setAddition(Material.GOLD_NUGGET)
                .assign();

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.DIAMOND)
                        .setOutput(DFMaterial.DiamondChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "DiamondChisel")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.DiamondChisel)
                .setAddition(Material.DIAMOND)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(DFMaterial.DiamondChisel))
                        .setTemplate(new CustomItemRepresentation(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .setAddition(new CustomItemRepresentation(Material.NETHERITE_INGOT))
                        .setResult(DFMaterial.NetheriteChisel)
                        .build(getInfo(), "NetheriteChisel")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.NetheriteChisel)
                .setAddition(Material.NETHERITE_SCRAP)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(DFMaterial.NetheriteChisel))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianChisel)
                        .build(getInfo(), "ObsidianChisel")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianChisel)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();
    }

    @Override
    public String getRecipesID() {
        return "chisel";
    }
}
