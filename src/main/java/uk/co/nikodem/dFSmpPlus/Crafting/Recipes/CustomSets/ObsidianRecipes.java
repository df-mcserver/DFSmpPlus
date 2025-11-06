package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairAnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.SmithingTableRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class ObsidianRecipes extends CraftingTemplate {
    public ObsidianRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addTemplateDuplication(recipesToAdd);
        addTools(recipesToAdd);
        addArmour(recipesToAdd);

        return recipesToAdd;
    }

    public void addTemplateDuplication(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ObsidianUpgradeTemplate, 2)
                        .build(getInfo())
                        .shape("XYX", "XOX", "XXX")
                        .setIngredient('X', Material.DIAMOND)
                        .setIngredient('O', Material.CRYING_OBSIDIAN)
                        .setIngredient('Y', new RecipeChoice.ExactChoice(DFMaterial.ObsidianUpgradeTemplate.toItemStack()))
        );
    }

    public void addTools(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_SWORD))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianSword)
                        .build(getInfo(), "ObsidianSword")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianSword)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_AXE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianAxe)
                        .build(getInfo(), "ObsidianAxe")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianAxe)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_PICKAXE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianPickaxe)
                        .build(getInfo(), "ObsidianPickaxe")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianPickaxe)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_SHOVEL))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianShovel)
                        .build(getInfo(), "ObsidianShovel")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianShovel)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_HOE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianHoe)
                        .build(getInfo(), "ObsidianHoe")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianHoe)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();
    }

    public void addArmour(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_HELMET))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianHelmet)
                        .build(getInfo(), "ObsidianHelmet")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianHelmet)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_CHESTPLATE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianChestplate)
                        .build(getInfo(), "ObsidianChestplate")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianChestplate)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_LEGGINGS))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianLeggings)
                        .build(getInfo(), "ObsidianLeggings")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianLeggings)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_BOOTS))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianBoots)
                        .build(getInfo(), "ObsidianBoots")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianBoots)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();
    }

    @Override
    public String getRecipesID() {
        return "obsidian-customset";
    }
}
