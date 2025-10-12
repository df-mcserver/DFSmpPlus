package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithResultRemoval;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class OtherCustomItemRecipes extends CraftingTemplate {
    public OtherCustomItemRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addMagicMirror(recipesToAdd);
        addEntityBucket(recipesToAdd);
        doWartChanges(recipesToAdd);
        addBluebellsarStick(recipesToAdd);
        addVampireSword(recipesToAdd);
        addLocatorCompassAndModules(recipesToAdd);

        return recipesToAdd;
    }

    public void doWartChanges(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.WarpedWart)
                        .build(getInfo(), "wwbtoww")
                        .addIngredient(Material.WARPED_WART_BLOCK)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.WARPED_WART_BLOCK)
                        .build(getInfo(), "wwtowwb")
                        .shape("XXX", "XXX", "XXX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.WarpedWart.toItemStack()))
        );

        RecipeRemover.addQuery(new RecipeWithResultRemoval()
                .onlyUseMinecraftNamespace()
                .setResult(Material.NETHER_WART_BLOCK));

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NETHER_WART_BLOCK)
                        .build(getInfo(), "nwtonwb")
                        .shape("XXX", "XXX", "XXX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHER_WART)))
        );
    }

    public void addMagicMirror(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.MagicMirror)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("XiX", "iOi", "XiX")
                        .setIngredient('X', Material.GLASS_PANE)
                        .setIngredient('i', Material.IRON_NUGGET)
                        .setIngredient('O', Material.DIAMOND)
        );
    }

    public void addEntityBucket(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.EntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("BXB", " B ")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', Material.IRON_INGOT)
        );
    }

    public void addBluebellsarStick(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.BluebellsarStick)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("AdA", "dXd", "AdA")
                        .setIngredient('A', Material.AMETHYST_SHARD)
                        .setIngredient('d', Material.DIAMOND)
                        .setIngredient('X', Material.STICK)
        );
    }

    public void addVampireSword(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VampireSword)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("B", "F", "S")
                        .setIngredient('B', Material.BREEZE_ROD)
                        .setIngredient('F', Material.FLINT)
                        .setIngredient('S', Material.STICK)
        );
    }

    public void addLocatorCompassAndModules(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.LocatorCompass)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "LocatorCompass")
                        .shape(" D ", "AXA", " D ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.COMPASS)))
                        .setIngredient('A', new RecipeChoice.ExactChoice(ItemStack.of(Material.DIAMOND)))
                        .setIngredient('D', new RecipeChoice.ExactChoice(ItemStack.of(Material.AMETHYST_SHARD)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.LocatorCompassModule)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "LocatorCompassModule")
                        .shape(" I ", "SCS", " I ")
                        .setIngredient('I', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_INGOT)))
                        .setIngredient('S', new RecipeChoice.ExactChoice(ItemStack.of(Material.STRING)))
                        .setIngredient('C', new RecipeChoice.ExactChoice(ItemStack.of(Material.COAL)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.EndLocatorCompassModule)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EndLocatorCompassModule")
                        .shape("XXX", "XOX", "XXX")
                        .setIngredient('O', new RecipeChoice.ExactChoice(DFMaterial.LocatorCompassModule.toItemStack()))
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.ENDER_EYE)))
        );
    }

    @Override
    public String getRecipesID() {
        return "othercustom";
    }
}
