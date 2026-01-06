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
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        addLooseStoneRecipes(recipesToAdd);

        addStonecuttingRecipes(recipesToAdd);

        addOreRecipes(recipesToAdd);
        addSlabRecipes(recipesToAdd);

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
                Material.NETHERRACK // this one doesn't make sense but its for qol
        );

        int i = 0;
        for (Material musicDisc : allStones) {
            for (Material subMusicDisc : allStones) {
                recipesToAdd.add(
                        new StonecutterRecipeBuilder()
                                .setSource(musicDisc)
                                .setOutput(subMusicDisc)
                                .build(getInfo(), "stones"+i)
                );
                i++;
            }
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

    @Override
    public String getRecipesID() {
        return "vanilla";
    }
}
