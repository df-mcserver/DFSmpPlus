package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.BlastFurnaceRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithResultRemoval;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.ArrayList;
import java.util.List;

public class VanillaRecipes extends CraftingTemplate {

    public VanillaRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        doOtherRecipes(recipesToAdd);
        doChainmailRecipes(recipesToAdd);
        doBlastFurnaceRecipes(recipesToAdd);
        doUnobtainableRecipes(recipesToAdd);
        doHorseArmourRecipes(recipesToAdd);
        doDispenserChange(recipesToAdd);
        doSlimeblockChange(recipesToAdd);

        return recipesToAdd;
    }

    private void doDispenserChange(List<Recipe> recipesToAdd) {
        RecipeRemover.addQuery(
                new RecipeWithResultRemoval()
                        .setResult(Material.DISPENSER)
                        .onlyUseMinecraftNamespace()
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DISPENSER)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("CCC", "CIC", "CSC")
                        .setIngredient('C', new RecipeChoice.MaterialChoice(Tag.ITEMS_STONE_TOOL_MATERIALS))
                        .setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)))
                        .setIngredient('S', Material.REDSTONE)
        );
    }

    private void doSlimeblockChange(List<Recipe> recipesToAdd) {
        RecipeRemover.addQuery(
                new RecipeWithResultRemoval()
                        .setResult(Material.SLIME_BLOCK)
                        .onlyUseMinecraftNamespace()
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.SLIME_BLOCK)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("XX", "XX")
                        .setIngredient('X', Material.SLIME_BALL)
        );
    }

    private void doOtherRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.FLINT)
                        .setOutput(Material.GRAVEL)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.NETHER_WART_BLOCK)
                        .setOutput(Material.NETHER_WART)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.AMETHYST_BLOCK)
                        .setOutput(Material.AMETHYST_SHARD)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(Material.COBWEB)
                        .build(getInfo())
                        .addIngredient(2, Material.STRING)
        );
    }

    private void doUnobtainableRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.SADDLE)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("III", "X X")
                        .setIngredient('I', Material.LEATHER)
                        .setIngredient('X', Material.CHAIN)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NAME_TAG)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape(" SS", "PIS", "PPP")
                        .setIngredient('P', Material.PAPER)
                        .setIngredient('I', Material.INK_SAC)
                        .setIngredient('S', Material.STRING)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.TRIDENT)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("DID", "DSD", " S ")
                        .setIngredient('D', Material.DIAMOND)
                        .setIngredient('S', Material.STICK)
                        .setIngredient('I', Material.IRON_NUGGET)
        );
    }

    private void doHorseArmourRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.LEATHER_HORSE_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("  L", "LWL", "LLL")
                        .setIngredient('L', Material.LEATHER)
                        .setIngredient('W', Material.SADDLE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.IRON_HORSE_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("  L", "LWL", "LLL")
                        .setIngredient('L', Material.IRON_INGOT)
                        .setIngredient('W', Material.SADDLE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.GOLDEN_HORSE_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("  L", "LWL", "LLL")
                        .setIngredient('L', Material.GOLD_INGOT)
                        .setIngredient('W', Material.SADDLE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DIAMOND_HORSE_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("  L", "LWL", "LLL")
                        .setIngredient('L', Material.DIAMOND)
                        .setIngredient('W', Material.SADDLE)
        );
    }

    private void doBlastFurnaceRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(Material.SAND)
                        .setOutput(Material.GLASS)
                        .setCategory(CookingBookCategory.BLOCKS)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(Material.COBBLESTONE)
                        .setOutput(Material.STONE)
                        .setCategory(CookingBookCategory.BLOCKS)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setSource(Material.STONE)
                        .setOutput(Material.SMOOTH_STONE)
                        .setCategory(CookingBookCategory.BLOCKS)
                        .build(getInfo())
        );
    }

    private void doChainmailRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new StandardHelmetRecipeBuilder()
                        .setItem(Material.CHAINMAIL_HELMET)
                        .setMaterial(Material.CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(Material.CHAINMAIL_CHESTPLATE)
                        .setMaterial(Material.CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(Material.CHAINMAIL_LEGGINGS)
                        .setMaterial(Material.CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(Material.CHAINMAIL_BOOTS)
                        .setMaterial(Material.CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );
    }

    @Override
    public String getRecipesID() {
        return "vanilla";
    }
}
