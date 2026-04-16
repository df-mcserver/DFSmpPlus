package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairAnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class OtherCustomItemRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addMagicMirror(recipesToAdd);
        addEntityBucket(recipesToAdd);
        doWartChanges(recipesToAdd);
        addBluebellsarStick(recipesToAdd);
        addVampireSword(recipesToAdd);
        addLocatorCompassAndModules(recipesToAdd);
        addPestleAndMortar(recipesToAdd);
        addCustomFoodRecipes(recipesToAdd);
        addCustomBuckets(recipesToAdd);
        addEmptyingEntityBuckets(recipesToAdd);
        addAncientDebrisFragmentRecipe(recipesToAdd);
        addShears(recipesToAdd);

        return recipesToAdd;
    }

    public void addEmptyingEntityBuckets(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.CopperEntityBucket)
                        .build(getInfo(), "ccbucketempty")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.CopperCleaningEntityBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.CopperEntityBucket)
                        .build(getInfo(), "csbucketempty")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.CopperStoringEntityBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.EntityBucket)
                        .build(getInfo(), "icbucketempty")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.CleaningEntityBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.EntityBucket)
                        .build(getInfo(), "isbucketempty")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.StoringEntityBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.GoldEntityBucket)
                        .build(getInfo(), "gcbucketempty")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.GoldCleaningEntityBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.GoldEntityBucket)
                        .build(getInfo(), "gsbucketempty")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.GoldStoringEntityBucket.toItemStack()))
        );
    }

    public void addCustomBuckets(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperBucket)
                        .build(getInfo(), "cbucket")
                        .shape("X X", " X ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.COPPER_INGOT)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldBucket)
                        .build(getInfo(), "gbucket")
                        .shape("X X", " X ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.GOLD_INGOT)))
        );
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
                        .setOutput(DFMaterial.CopperEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EB-NoBucketCopper")
                        .shape("BXB", " B ")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(ItemStack.of(Material.COPPER_INGOT)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EB-BucketCopper")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.CopperBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperCleaningEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "CEBCopper")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.CopperWaterBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperStoringEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "SEBCopper")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.CopperLavaBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.EntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EB-NoBucket")
                        .shape("BXB", " B ")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_INGOT)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.EntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EB-Bucket")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(ItemStack.of(Material.BUCKET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CleaningEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "CEB")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(ItemStack.of(Material.WATER_BUCKET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.StoringEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "SEB")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(ItemStack.of(Material.LAVA_BUCKET)))
        );

        //

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EB-NoBucketGold")
                        .shape("BXB", " B ")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(ItemStack.of(Material.GOLD_INGOT)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EB-BucketGold")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.GoldBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldCleaningEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "CEBGold")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.GoldWaterBucket.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldStoringEntityBucket)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "SEBGold")
                        .shape("X", "B")
                        .setIngredient('X', Material.COBWEB)
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.GoldLavaBucket.toItemStack()))
        );
    }

    public void addBluebellsarStick(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.BluebellsarStick)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "bluebellsarstick")
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

    public void addPestleAndMortar(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.FlowerPestleAndMortar)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "FlowerPM")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.EmptyPestleAndMortar.toItemStack()))
                        .addIngredient(new RecipeChoice.MaterialChoice(Tag.ITEMS_FLOWERS))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.EmptyPestleAndMortar)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "EmptyPM")
                        .shape("XIX", " X ")
                        .setIngredient('X', new RecipeChoice.MaterialChoice(Tag.ITEMS_STONE_TOOL_MATERIALS))
                        .setIngredient('I', new RecipeChoice.ExactChoice(DFMaterial.PointyStick.toItemStack()))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.RottenFleshPestleAndMortar)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "RottenPM")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.EmptyPestleAndMortar.toItemStack()))
                        .addIngredient(Material.ROTTEN_FLESH)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.GravelPestleAndMortar)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "GravelPM")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.EmptyPestleAndMortar.toItemStack()))
                        .addIngredient(Material.GRAVEL)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.AmethystPestleAndMortar)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "AmethystPM")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.EmptyPestleAndMortar.toItemStack()))
                        .addIngredient(Material.AMETHYST_BLOCK)
        );
    }

    public void addCustomFoodRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new CampfireRecipeBuilder()
                        .setOutput(DFMaterial.FriedEgg)
                        .setCookingTime(30*20)
                        .setExperience(0)
                        .setCategory(CookingBookCategory.FOOD)
                        .setSource(Material.EGG)
                        .build(getInfo(), "friedeggoncampfire")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setOutput(DFMaterial.FriedEgg)
                        .setCookingTime(10*20)
                        .setExperience(0)
                        .setCategory(CookingBookCategory.FOOD)
                        .setSource(Material.EGG)
                        .build(getInfo(), "friedegginfurnace")
        );

        recipesToAdd.add(
                new SmokerRecipeBuilder()
                        .setOutput(DFMaterial.FriedEgg)
                        .setCookingTime(5*20)
                        .setExperience(0)
                        .setCategory(CookingBookCategory.FOOD)
                        .setSource(Material.EGG)
                        .build(getInfo(), "friedegginsmoker")
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.EggSandwich)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "eggsandwich")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.FriedEgg.toItemStack()))
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.FriedEgg.toItemStack()))
                        .addIngredient(Material.BREAD)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.ChickenBurger)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "dcburger")
                        .addIngredient(Material.COOKED_CHICKEN)
                        .addIngredient(Material.COOKED_CHICKEN)
                        .addIngredient(Material.BREAD)
        );
    }

    public void addAncientDebrisFragmentRecipe(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.AncientDebrisChunk)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "fragtochunk")
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.AncientDebrisFragment.toItemStack()))
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.AncientDebrisFragment.toItemStack()))
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.AncientDebrisFragment.toItemStack()))
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.AncientDebrisFragment.toItemStack()))
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setOutput(Material.NETHERITE_SCRAP)
                        .setCategory(CookingBookCategory.BLOCKS)
                        .setSource(DFMaterial.AncientDebrisChunk)
                        .build(getInfo(), "chunktoscrap")
        );

        recipesToAdd.add(
                new BlastFurnaceRecipeBuilder()
                        .setOutput(Material.NETHERITE_SCRAP)
                        .setCategory(CookingBookCategory.BLOCKS)
                        .setSource(DFMaterial.AncientDebrisChunk)
                        .build(getInfo(), "chunktoscrap-blasting")
        );
    }

    public void addShears(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperShears)
                        .build(getInfo(), "coppershears")
                        .shape(" X", "X ")
                        .setIngredient('X', Material.COPPER_INGOT)
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.CopperShears)
                .setAddition(Material.COPPER_INGOT)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FiridiumShears)
                        .build(getInfo(), "firidiumshears")
                        .shape(" X", "X ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FiridiumIngot.toItemStack()))
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.FiridiumShears)
                .setAddition(DFMaterial.FiridiumIngot)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldShears)
                        .build(getInfo(), "goldshears")
                        .shape(" X", "X ")
                        .setIngredient('X', Material.GOLD_INGOT)
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.GoldShears)
                .setAddition(Material.GOLD_INGOT)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.DiamondShears)
                        .build(getInfo(), "diamondshears")
                        .shape(" X", "X ")
                        .setIngredient('X', Material.DIAMOND)
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.DiamondShears)
                .setAddition(Material.DIAMOND)
                .assign();

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.NetheriteShears)
                .setAddition(Material.NETHERITE_INGOT)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(DFMaterial.DiamondShears))
                        .setTemplate(new CustomItemRepresentation(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .setAddition(new CustomItemRepresentation(Material.NETHERITE_INGOT))
                        .setResult(DFMaterial.NetheriteShears)
                        .build(getInfo(), "NetheriteShears")
        );

        new ItemRepairAnvilRecipeBuilder()
                .setItem(DFMaterial.ObsidianShears)
                .setAddition(Material.CRYING_OBSIDIAN)
                .assign();

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(DFMaterial.NetheriteShears))
                        .setTemplate(new CustomItemRepresentation(DFMaterial.ObsidianUpgradeTemplate))
                        .setAddition(new CustomItemRepresentation(Material.CRYING_OBSIDIAN))
                        .setResult(DFMaterial.ObsidianShears)
                        .build(getInfo(), "ObsidianShears")
        );
    }

    @Override
    public String getRecipesID() {
        return "othercustom";
    }
}
