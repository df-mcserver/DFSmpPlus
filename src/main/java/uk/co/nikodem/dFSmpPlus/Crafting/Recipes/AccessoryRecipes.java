package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.AnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class AccessoryRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.LuckyHorseshoe)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "luckyhorseshoe")
                        .shape("FGN", "GIG", "NGF")
                        .setIngredient('G', Material.GOLD_INGOT)
                        .setIngredient('N', Material.GOLD_NUGGET)
                        .setIngredient('I', Material.BREEZE_ROD)
                        .setIngredient('F', Material.FEATHER)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ElytraBraces)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "elytrabraces")
                        .shape("NI", "IN")
                        .setIngredient('N', Material.IRON_BARS)
                        .setIngredient('I', Material.IRON_NUGGET)
        );

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.LuckyHorseshoe)
                .setAddition(DFMaterial.ElytraBraces)
                .setResult(DFMaterial.BracedHorseshoe)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.ElytraBraces)
                .setAddition(DFMaterial.LuckyHorseshoe)
                .setResult(DFMaterial.BracedHorseshoe)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ContaminatedMembrane)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "contaminatedmembrane")
                        .shape("PMP", " C ")
                        .setIngredient('M', Material.PHANTOM_MEMBRANE)
                        .setIngredient('P', Material.POISONOUS_POTATO)
                        .setIngredient('C', Material.FIRE_CHARGE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CobaltShield)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "cobaltshield")
                        .shape("NLN", "LGL", " N ")
                        .setIngredient('L', Material.LAPIS_LAZULI)
                        .setIngredient('G', Material.GOLD_INGOT)
                        .setIngredient('N', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_NUGGET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinMinerEssence)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "veinessence")
                        .shape("AAA", "XAP", "AAA")
                        .setIngredient('A', Material.AMETHYST_SHARD)
                        .setIngredient('P', new RecipeChoice.ExactChoice(DFMaterial.VeinPickaxe.toItemStack()))
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.VeinAxe.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FiridiumEssence)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "autosmeltessence")
                        .shape("NAN", "NNN", "PNS")
                        .setIngredient('N', new RecipeChoice.ExactChoice(DFMaterial.FiridiumNugget.toItemStack()))
                        .setIngredient('P', new RecipeChoice.ExactChoice(DFMaterial.FiridiumPickaxe.toItemStack()))
                        .setIngredient('A', new RecipeChoice.ExactChoice(DFMaterial.FiridiumAxe.toItemStack()))
                        .setIngredient('S', new RecipeChoice.ExactChoice(DFMaterial.FiridiumShovel.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.SplitEssence)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "splitessence")
                        .shape(" A ", "LEL", " B ")
                        .setIngredient('A', new RecipeChoice.ExactChoice(DFMaterial.FiridiumEssence.toItemStack()))
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.VeinMinerEssence.toItemStack()))
                        .setIngredient('E', new RecipeChoice.ExactChoice(ItemStack.of(Material.ENDER_EYE)))
                        .setIngredient('L', new RecipeChoice.ExactChoice(ItemStack.of(Material.LAPIS_LAZULI)))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.BootsOfSwiftness)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "hermesboots")
                        .addIngredient(Material.LEATHER_BOOTS)
                        .addIngredient(Material.FEATHER)
                        .addIngredient(Material.CACTUS)
                        .addIngredient(Material.STRING)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FlowerBoots)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "flowerboots")
                        .shape(" O ", "OXO", " O ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FloralBoots.toItemStack()))
                        .setIngredient('O', new RecipeChoice.ExactChoice(DFMaterial.FlowerPowder.toItemStack()))
        );

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.BootsOfSwiftness)
                .setAddition(DFMaterial.FlowerBoots)
                .setResult(DFMaterial.FlowerBootsOfSwiftness)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.FlowerBoots)
                .setAddition(DFMaterial.BootsOfSwiftness)
                .setResult(DFMaterial.FlowerBootsOfSwiftness)
                .assign();

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.HitmanTechniquesBook)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "htbook")
                        .addIngredient(Material.BOOK)
                        .addIngredient(Material.TOTEM_OF_UNDYING)
                        .addIngredient(Material.TNT)
                        .addIngredient(Material.SHIELD)
                        .addIngredient(Material.SPECTRAL_ARROW)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VacuumAccessory)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "vacuum")
                        .shape(" X ", "XOX", "IOI")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_INGOT)))
                        .setIngredient('O', Material.WIND_CHARGE)
                        .setIngredient('I', Material.IRON_BARS)
        );

        return recipesToAdd;
    }

    @Override
    public String getRecipesID() {
        return "accessory";
    }
}
