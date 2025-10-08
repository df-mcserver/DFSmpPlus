package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipes.SmithingTable.SmithingTableItem;
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
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.PointyStick)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "PointyStick")
                        .addIngredient(new RecipeChoice.ExactChoice(ItemStack.of(Material.STICK)))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.SharpStone)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "SharpStone")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.LooseStone.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "CopperChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.CopperNugget.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.IronChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "IronChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_NUGGET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FiridiumChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "FiridiumChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FiridiumNugget.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.IronChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "GoldChisel")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.GOLD_NUGGET)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.DiamondChisel)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "DiamondChisel")
                        .addIngredient(Material.DIAMOND)
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(DFMaterial.DiamondChisel))
                        .setTemplate(new SmithingTableItem(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .setAddition(new SmithingTableItem(Material.NETHERITE_INGOT))
                        .setResult(DFMaterial.NetheriteChisel)
                        .build(getInfo(), "NetheriteChisel")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new SmithingTableItem(DFMaterial.NetheriteChisel))
                        .setTemplate(new SmithingTableItem(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new SmithingTableItem(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianChisel)
                        .build(getInfo(), "ObsidianChisel")
        );
    }

    @Override
    public String getRecipesID() {
        return "chisel";
    }
}
