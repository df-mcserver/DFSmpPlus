package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.CustomSets;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.ItemRepairCombineRecipeBuilder;
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

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianSword)
                        .build(getInfo(), "ObsidianSword")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_AXE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianAxe)
                        .build(getInfo(), "ObsidianAxe")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianAxe)
                        .build(getInfo(), "ObsidianAxe")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_PICKAXE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianPickaxe)
                        .build(getInfo(), "ObsidianPickaxe")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianPickaxe)
                        .build(getInfo(), "ObsidianPickaxe")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_SHOVEL))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianShovel)
                        .build(getInfo(), "ObsidianShovel")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianShovel)
                        .build(getInfo(), "ObsidianShovel")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_HOE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianHoe)
                        .build(getInfo(), "ObsidianHoe")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianHoe)
                        .build(getInfo(), "ObsidianHoe")
        );
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

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianHelmet)
                        .build(getInfo(), "ObsidianHelmet")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_CHESTPLATE))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianChestplate)
                        .build(getInfo(), "ObsidianChestplate")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianChestplate)
                        .build(getInfo(), "ObsidianChestplate")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_LEGGINGS))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianLeggings)
                        .build(getInfo(), "ObsidianLeggings")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianLeggings)
                        .build(getInfo(), "ObsidianLeggings")
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(Material.NETHERITE_BOOTS))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianBoots)
                        .build(getInfo(), "ObsidianBoots")
        );

        recipesToAdd.add(
                new ItemRepairCombineRecipeBuilder()
                        .setItem(DFMaterial.ObsidianBoots)
                        .build(getInfo(), "ObsidianBoots")
        );
    }

    @Override
    public String getRecipesID() {
        return "obsidian-customset";
    }
}
