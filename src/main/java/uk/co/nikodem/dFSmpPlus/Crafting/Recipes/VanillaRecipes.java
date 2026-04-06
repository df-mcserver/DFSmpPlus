package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardBootsRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardChestplateRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardHelmetRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour.StandardLeggingsRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Etc.DirectConversionRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair.ItemRepairAnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.RecipeWithResultRemoval;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.RecipeRemover;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VanillaRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        doOtherRecipes(recipesToAdd);
        doChainmailRecipes(recipesToAdd);
        doBlastFurnaceRecipes(recipesToAdd);
        doUnobtainableRecipes(recipesToAdd);
        doHorseArmourRecipes(recipesToAdd);
        doNautilusArmourRecipes(recipesToAdd);
        doVanillaRecipeChanges(recipesToAdd);
        doSlimeblockChange(recipesToAdd);
        doPoppedChorusRecipeReadditions(recipesToAdd);

        addLooseStoneRecipes(recipesToAdd);

        addStonecuttingRecipes(recipesToAdd);

        addOreRecipes(recipesToAdd);
        addSlabRecipes(recipesToAdd);
        addGlowInkSacRecipe(recipesToAdd);
        addCakeRecipe(recipesToAdd);

        return recipesToAdd;
    }

    private void doPoppedChorusRecipeReadditions(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.END_ROD, 4)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("I", "X")
                        .setIngredient('I', Material.BLAZE_ROD)
                        .setIngredient('X', new RecipeChoice.ExactChoice(new ItemStack(Material.POPPED_CHORUS_FRUIT)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.PURPUR_BLOCK, 4)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("XX", "XX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(new ItemStack(Material.POPPED_CHORUS_FRUIT)))
        );
    }

    private void doVanillaRecipeChanges(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DISPENSER)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo(), "w/iron")
                        .shape("CCC", "CIC", "CSC")
                        .setIngredient('C', new RecipeChoice.MaterialChoice(Tag.ITEMS_STONE_TOOL_MATERIALS))
                        .setIngredient('I', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)))
                        .setIngredient('S', Material.REDSTONE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DISPENSER)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("CCC", "CIC", "CSC")
                        .setIngredient('C', new RecipeChoice.MaterialChoice(Tag.ITEMS_STONE_TOOL_MATERIALS))
                        .setIngredient('I', Material.BOW)
                        .setIngredient('S', Material.REDSTONE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.LANTERN, 4)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo())
                        .shape("XXX", "XIX", "XXX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_NUGGET)))
                        .setIngredient('I', Material.TORCH)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.SOUL_LANTERN, 4)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo())
                        .shape("XXX", "XIX", "XXX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_NUGGET)))
                        .setIngredient('I', Material.SOUL_TORCH)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.COPPER_LANTERN, 4)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo())
                        .shape("XXX", "XIX", "XXX")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.COPPER_NUGGET)))
                        .setIngredient('I', Material.COPPER_TORCH)
        );
    }

    private void doSlimeblockChange(List<Recipe> recipesToAdd) {
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
                new FurnaceRecipeBuilder()
                        .setOutput(Material.FLINT)
                        .setSource(Material.GRAVEL)
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
                        .addIngredient(1, Material.SLIME_BALL)
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setOutput(Material.GREEN_DYE)
                        .setSource(Material.KELP)
                        .build(getInfo(), "kelp")
        );

        recipesToAdd.add(
                new FurnaceRecipeBuilder()
                        .setOutput(Material.GREEN_DYE)
                        .setSource(Material.BAMBOO)
                        .build(getInfo(), "bamboo")
        );
    }

    private void doUnobtainableRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NAME_TAG)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("nametag")
                        .build(getInfo(), "ironnugget")
                        .shape(" X", "P ")
                        .setIngredient('P', Material.PAPER)
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.IRON_NUGGET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NAME_TAG)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("nametag")
                        .build(getInfo(), "goldnugget")
                        .shape(" X", "P ")
                        .setIngredient('P', Material.PAPER)
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.GOLD_NUGGET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NAME_TAG)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("nametag")
                        .build(getInfo(), "coppernugget")
                        .shape(" X", "P ")
                        .setIngredient('P', Material.PAPER)
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.COPPER_NUGGET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NAME_TAG)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("nametag")
                        .build(getInfo(), "firidiumnugget")
                        .shape(" X", "P ")
                        .setIngredient('P', Material.PAPER)
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FiridiumNugget.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NAME_TAG)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .setGroup("nametag")
                        .build(getInfo(), "floralnugget")
                        .shape(" X", "P ")
                        .setIngredient('P', Material.PAPER)
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.FloralNugget.toItemStack()))
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

        new ItemRepairAnvilRecipeBuilder()
                .setItem(Material.TRIDENT)
                .setAddition(Material.DIAMOND)
                .assign();
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
                        .setOutput(Material.COPPER_HORSE_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("  L", "LWL", "LLL")
                        .setIngredient('L', Material.COPPER_INGOT)
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

    private void doNautilusArmourRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.COPPER_NAUTILUS_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("LWL", "L L", "LL ")
                        .setIngredient('L', Material.COPPER_INGOT)
                        .setIngredient('W', Material.SADDLE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.IRON_NAUTILUS_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("LWL", "L L", "LL ")
                        .setIngredient('L', Material.IRON_INGOT)
                        .setIngredient('W', Material.SADDLE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.GOLDEN_NAUTILUS_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("LWL", "L L", "LL ")
                        .setIngredient('L', Material.GOLD_INGOT)
                        .setIngredient('W', Material.SADDLE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DIAMOND_NAUTILUS_ARMOR)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
                        .shape("LWL", "L L", "LL ")
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
                        .setMaterial(Material.IRON_CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardChestplateRecipeBuilder()
                        .setItem(Material.CHAINMAIL_CHESTPLATE)
                        .setMaterial(Material.IRON_CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardLeggingsRecipeBuilder()
                        .setItem(Material.CHAINMAIL_LEGGINGS)
                        .setMaterial(Material.IRON_CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );

        recipesToAdd.add(
                new StandardBootsRecipeBuilder()
                        .setItem(Material.CHAINMAIL_BOOTS)
                        .setMaterial(Material.IRON_CHAIN)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo())
        );
    }

    private void addLooseStoneRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(Material.STONE_AXE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.LooseStone.toItemStack()))
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.LooseStone.toItemStack()))
                        .addIngredient(Material.STICK)
                        .addIngredient(Material.STRING)
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(Material.STONE_SHOVEL)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .addIngredient(new RecipeChoice.ExactChoice(DFMaterial.LooseStone.toItemStack()))
                        .addIngredient(Material.STICK)
                        .addIngredient(Material.STRING)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.STONE_PICKAXE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .setGroup("StonePickaxeLS")
                        .build(getInfo(), "LEFT")
                        .shape("XXX", "SI ", " I ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.LooseStone.toItemStack()))
                        .setIngredient('I', Material.STICK)
                        .setIngredient('S', Material.STRING)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.STONE_PICKAXE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .setGroup("StonePickaxeLS")
                        .build(getInfo(), "RIGHT")
                        .shape("XXX", " IS", " I ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(DFMaterial.LooseStone.toItemStack()))
                        .setIngredient('I', Material.STICK)
                        .setIngredient('S', Material.STRING)
        );
    }

    private void addStonecuttingRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new StonecutterRecipeBuilder()
                        .setOutput(Material.LEATHER)
                        .setSource(Material.RABBIT_HIDE)
                        .build(getInfo(), "h-l")
        );

        recipesToAdd.add(
                new StonecutterRecipeBuilder()
                        .setOutput(Material.RABBIT_HIDE)
                        .setSource(Material.LEATHER)
                        .build(getInfo(), "l-h")
        );

        List<Material> allStones = List.of(
                Material.COBBLESTONE,
                Material.COBBLED_DEEPSLATE,
                Material.ANDESITE,
                Material.DIORITE,
                Material.GRANITE,
                Material.TUFF,
                // these don't make sense but it's for qol
                Material.NETHERRACK,
                Material.END_STONE
        );

        int i = 0;
        for (Material stone : allStones) {
            for (Material substone : allStones) {
                recipesToAdd.add(
                        new StonecutterRecipeBuilder()
                                .setSource(stone)
                                .setOutput(substone)
                                .build(getInfo(), "stones"+i)
                );
                i++;
            }
        }

        i = 0;
        Tag<Material> allWool = Tag.WOOL;
        for (Material wool : allWool.getValues()) {
            recipesToAdd.add(
                    new StonecutterRecipeBuilder()
                            .setSource(wool)
                            .setOutput(ItemStack.of(Material.STRING, 2))
                            .build(getInfo(), "wools"+i)
            );
            i++;
        }

        recipesToAdd.add(
                new StonecutterRecipeBuilder()
                        .setSource(Material.STONE)
                        .setOutput(Material.COBBLESTONE)
                        .build(getInfo(), "stone-to-cobble")
        );

        recipesToAdd.add(
                new StonecutterRecipeBuilder()
                        .setSource(Material.DEEPSLATE)
                        .setOutput(Material.COBBLED_DEEPSLATE)
                        .build(getInfo(), "deep-to-cobble")
        );

        Map<Material, Material> allLogsToStrippedLogs = Map.ofEntries(
                Map.entry(Material.OAK_LOG, Material.STRIPPED_OAK_LOG),
                Map.entry(Material.ACACIA_LOG, Material.STRIPPED_ACACIA_LOG),
                Map.entry(Material.BIRCH_LOG, Material.STRIPPED_BIRCH_LOG),
                Map.entry(Material.CHERRY_LOG, Material.STRIPPED_CHERRY_LOG),
                Map.entry(Material.DARK_OAK_LOG, Material.STRIPPED_DARK_OAK_LOG),
                Map.entry(Material.JUNGLE_LOG, Material.STRIPPED_JUNGLE_LOG),
                Map.entry(Material.MANGROVE_LOG, Material.STRIPPED_MANGROVE_LOG),
                Map.entry(Material.SPRUCE_LOG, Material.STRIPPED_SPRUCE_LOG),
                Map.entry(Material.PALE_OAK_LOG, Material.STRIPPED_PALE_OAK_LOG),
                Map.entry(Material.WARPED_STEM, Material.STRIPPED_WARPED_STEM),
                Map.entry(Material.CRIMSON_STEM, Material.STRIPPED_CRIMSON_STEM)
        );

        for (Map.Entry<Material, Material> entry : allLogsToStrippedLogs.entrySet()) {
            recipesToAdd.add(
                    new StonecutterRecipeBuilder()
                            .setSource(entry.getKey())
                            .setOutput(entry.getValue())
                            .build(getInfo(), entry.getKey().name())
            );
            recipesToAdd.add(
                    new StonecutterRecipeBuilder()
                            .setSource(entry.getValue())
                            .setOutput(entry.getKey())
                            .build(getInfo(), entry.getValue().name())
            );
        }
    }

    public void addSlabRecipes(List<Recipe> recipesToAdd) {
        Map<Material, Material> slabToFullblockMapping = Map.ofEntries(
                Map.entry(Material.OAK_SLAB, Material.OAK_PLANKS),
                Map.entry(Material.SPRUCE_SLAB, Material.SPRUCE_PLANKS),
                Map.entry(Material.BIRCH_SLAB, Material.BIRCH_PLANKS),
                Map.entry(Material.JUNGLE_SLAB, Material.JUNGLE_PLANKS),
                Map.entry(Material.ACACIA_SLAB, Material.ACACIA_PLANKS),
                Map.entry(Material.DARK_OAK_SLAB, Material.DARK_OAK_PLANKS),
                Map.entry(Material.MANGROVE_SLAB, Material.MANGROVE_PLANKS),
                Map.entry(Material.CHERRY_SLAB, Material.CHERRY_PLANKS),
                Map.entry(Material.PALE_OAK_SLAB, Material.PALE_OAK_PLANKS),
                Map.entry(Material.BAMBOO_SLAB, Material.BAMBOO_PLANKS),
                Map.entry(Material.BAMBOO_MOSAIC_SLAB, Material.BAMBOO_MOSAIC),
                Map.entry(Material.CRIMSON_SLAB, Material.CRIMSON_PLANKS),
                Map.entry(Material.WARPED_SLAB, Material.WARPED_PLANKS),
                Map.entry(Material.STONE_SLAB, Material.STONE),
                Map.entry(Material.COBBLESTONE_SLAB, Material.COBBLESTONE),
                Map.entry(Material.MOSSY_COBBLESTONE_SLAB, Material.MOSSY_COBBLESTONE),
                Map.entry(Material.SMOOTH_STONE_SLAB, Material.SMOOTH_STONE),
                Map.entry(Material.STONE_BRICK_SLAB, Material.STONE_BRICKS),
                Map.entry(Material.MOSSY_STONE_BRICK_SLAB, Material.MOSSY_STONE_BRICKS),
                Map.entry(Material.GRANITE_SLAB, Material.GRANITE),
                Map.entry(Material.POLISHED_GRANITE_SLAB, Material.POLISHED_GRANITE),
                Map.entry(Material.DIORITE_SLAB, Material.DIORITE),
                Map.entry(Material.POLISHED_DIORITE_SLAB, Material.POLISHED_DIORITE),
                Map.entry(Material.ANDESITE_SLAB, Material.ANDESITE),
                Map.entry(Material.POLISHED_ANDESITE_SLAB, Material.POLISHED_ANDESITE),
                Map.entry(Material.COBBLED_DEEPSLATE_SLAB, Material.COBBLED_DEEPSLATE),
                Map.entry(Material.POLISHED_DEEPSLATE_SLAB, Material.POLISHED_DEEPSLATE),
                Map.entry(Material.DEEPSLATE_BRICK_SLAB, Material.DEEPSLATE_BRICKS),
                Map.entry(Material.DEEPSLATE_TILE_SLAB, Material.DEEPSLATE_TILES),
                Map.entry(Material.TUFF_SLAB, Material.TUFF),
                Map.entry(Material.POLISHED_TUFF_SLAB, Material.POLISHED_TUFF),
                Map.entry(Material.BRICK_SLAB, Material.BRICKS),
                Map.entry(Material.MUD_BRICK_SLAB, Material.MUD_BRICKS),
                Map.entry(Material.RESIN_BRICK_SLAB, Material.RESIN_BRICKS),
                Map.entry(Material.SANDSTONE_SLAB, Material.SANDSTONE),
                Map.entry(Material.SMOOTH_SANDSTONE_SLAB, Material.SMOOTH_SANDSTONE),
                Map.entry(Material.CUT_SANDSTONE_SLAB, Material.CUT_SANDSTONE),
                Map.entry(Material.RED_SANDSTONE_SLAB, Material.RED_SANDSTONE),
                Map.entry(Material.SMOOTH_RED_SANDSTONE_SLAB, Material.SMOOTH_RED_SANDSTONE),
                Map.entry(Material.PRISMARINE_SLAB, Material.PRISMARINE),
                Map.entry(Material.PRISMARINE_BRICK_SLAB, Material.PRISMARINE_BRICKS),
                Map.entry(Material.DARK_PRISMARINE_SLAB, Material.DARK_PRISMARINE),
                Map.entry(Material.NETHER_BRICK_SLAB, Material.NETHER_BRICKS),
                Map.entry(Material.RED_NETHER_BRICK_SLAB, Material.RED_NETHER_BRICKS),
                Map.entry(Material.BLACKSTONE_SLAB, Material.BLACKSTONE),
                Map.entry(Material.POLISHED_BLACKSTONE_SLAB, Material.POLISHED_BLACKSTONE),
                Map.entry(Material.POLISHED_BLACKSTONE_BRICK_SLAB, Material.POLISHED_BLACKSTONE_BRICKS),
                Map.entry(Material.END_STONE_BRICK_SLAB, Material.END_STONE_BRICKS),
                Map.entry(Material.PURPUR_SLAB, Material.PURPUR_BLOCK),
                Map.entry(Material.QUARTZ_SLAB, Material.QUARTZ_BLOCK),
                Map.entry(Material.SMOOTH_QUARTZ_SLAB, Material.SMOOTH_QUARTZ),
                Map.entry(Material.CUT_COPPER_SLAB, Material.CUT_COPPER),
                Map.entry(Material.EXPOSED_CUT_COPPER_SLAB, Material.EXPOSED_CUT_COPPER),
                Map.entry(Material.WEATHERED_CUT_COPPER_SLAB, Material.WEATHERED_CUT_COPPER),
                Map.entry(Material.OXIDIZED_CUT_COPPER_SLAB, Material.OXIDIZED_CUT_COPPER),
                Map.entry(Material.WAXED_CUT_COPPER_SLAB, Material.WAXED_CUT_COPPER),
                Map.entry(Material.WAXED_EXPOSED_CUT_COPPER_SLAB, Material.WAXED_EXPOSED_CUT_COPPER),
                Map.entry(Material.WAXED_WEATHERED_CUT_COPPER_SLAB, Material.WAXED_WEATHERED_CUT_COPPER),
                Map.entry(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB, Material.WAXED_OXIDIZED_CUT_COPPER)
        );

        for (Map.Entry<Material, Material> entry : slabToFullblockMapping.entrySet()) {
            recipesToAdd.add(
                    new ShapedRecipeBuilder()
                            .setOutput(entry.getValue())
                            .setCategory(CraftingBookCategory.BUILDING)
                            .build(getInfo(), "stob")
                            .shape("X", "X")
                            .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(entry.getKey())))
            );
        }
    }

    public void addOreRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.COAL_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.COAL)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.COPPER_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.RAW_COPPER)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.IRON_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.RAW_IRON)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.GOLD_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.RAW_GOLD)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DIAMOND_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.DIAMOND)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.EMERALD_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.EMERALD)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.REDSTONE_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.REDSTONE)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.LAPIS_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape(" O ", "OXO", " O ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.STONE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.LAPIS_BLOCK)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NETHER_GOLD_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OXO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.NETHERRACK)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.GOLD_INGOT)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.NETHER_QUARTZ_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.NETHERRACK)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.QUARTZ)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_COAL_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.COAL)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_COPPER_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.RAW_COPPER)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_IRON_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.RAW_IRON)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_GOLD_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.RAW_GOLD)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_DIAMOND_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.DIAMOND)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_EMERALD_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.EMERALD)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_LAPIS_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape(" O ", "OXO", " O ")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.LAPIS_BLOCK)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.DEEPSLATE_REDSTONE_ORE)
                        .setCategory(CraftingBookCategory.BUILDING)
                        .build(getInfo())
                        .shape("OOO", "OXO", "OOO")
                        .setIngredient('X', new RecipeChoice.ExactChoice(ItemStack.of(Material.DEEPSLATE)))
                        .setIngredient('O', new RecipeChoice.ExactChoice(ItemStack.of(Material.REDSTONE)))
        );
    }

    public void addGlowInkSacRecipe(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(Material.GLOW_INK_SAC)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo())
                        .addIngredient(Material.INK_SAC)
                        .addIngredient(Material.GLOWSTONE_DUST)
                        .addIngredient(Material.GLOWSTONE_DUST)
                        .addIngredient(Material.GLOWSTONE_DUST)
        );
    }

    public void addCakeRecipe(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.CAKE)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "iron")
                        .shape("BBB", "XYX", "WWW")
                        .setIngredient('B', new RecipeChoice.ExactChoice(ItemStack.of(Material.MILK_BUCKET)))
                        .setIngredient('X', Material.SUGAR)
                        .setIngredient('Y', new RecipeChoice.MaterialChoice(Tag.ITEMS_EGGS))
                        .setIngredient('W', Material.WHEAT)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.CAKE)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "copper")
                        .shape("BBB", "XYX", "WWW")
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.CopperMilkBucket.toItemStack()))
                        .setIngredient('X', Material.SUGAR)
                        .setIngredient('Y', new RecipeChoice.MaterialChoice(Tag.ITEMS_EGGS))
                        .setIngredient('W', Material.WHEAT)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.CAKE)
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "gold")
                        .shape("BBB", "XYX", "WWW")
                        .setIngredient('B', new RecipeChoice.ExactChoice(DFMaterial.GoldMilkBucket.toItemStack()))
                        .setIngredient('X', Material.SUGAR)
                        .setIngredient('Y', new RecipeChoice.MaterialChoice(Tag.ITEMS_EGGS))
                        .setIngredient('W', Material.WHEAT)
        );
    }

    @Override
    public String getRecipesID() {
        return "vanilla";
    }
}
