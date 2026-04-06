package uk.co.nikodem.dFSmpPlus.Constants;

import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ChiseledBookshelfInventory;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.IITNIG;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.NetheriteTech;
import uk.co.nikodem.dFSmpPlus.Entities.CustomDrops.DFCustomDropCheck;
import uk.co.nikodem.dFSmpPlus.Entities.CustomDrops.DFCustomDrops;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import javax.annotation.Nullable;
import java.util.*;

public class ChiselBlockData {
    // please don't touch lol
    public final static List<ChiselBlockData> ChiselBlockDataIndex = new ArrayList<>();

    // Chisel Blocks
    public static void createChiselBlockData() {
        new Builder(Material.STONE).setReplacement(Material.GRAVEL).setDrop(DFMaterial.LooseStone).create();
        new Builder(Material.COBBLESTONE).setReplacement(Material.GRAVEL).setDrop(DFMaterial.LooseStone).create();
        new Builder(Material.DEEPSLATE).setReplacement(Material.GRAVEL).setDrop(DFMaterial.LooseStone).create();
        new Builder(Material.COBBLED_DEEPSLATE).setReplacement(Material.GRAVEL).setDrop(DFMaterial.LooseStone).create();
        new Builder(Material.MOSSY_COBBLESTONE).setReplacement(Material.COBBLESTONE).setDrop(Material.VINE).create();
        new Builder(Material.COARSE_DIRT).setReplacement(Material.DIRT).setDrop(Material.GRAVEL).create();
        new Builder(Material.GRAVEL).setReplacement(Material.AIR).setDrop(Material.FLINT).setSpeedMultiplayer(0.25f).create();

        new Builder(Material.COAL_ORE).setReplacement(Material.STONE).setDrop(Material.COAL).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.IRON_ORE).setReplacement(Material.STONE).setDrop(Material.RAW_IRON).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.GOLD_ORE).setReplacement(Material.STONE).setDrop(Material.RAW_GOLD).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.COPPER_ORE).setReplacement(Material.STONE).setDrop(Material.RAW_COPPER).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.EMERALD_ORE).setReplacement(Material.STONE).setDrop(Material.EMERALD_ORE).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.REDSTONE_ORE).setReplacement(Material.STONE).setDrop(Material.REDSTONE).setMinimumTool(Enums.ToolLevel.IRON).create();
        new Builder(Material.DIAMOND_ORE).setReplacement(Material.STONE).setDrop(Material.DIAMOND).setMinimumTool(Enums.ToolLevel.IRON).create();

        new Builder(Material.DEEPSLATE_COAL_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.COAL).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.DEEPSLATE_IRON_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.RAW_IRON).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.DEEPSLATE_GOLD_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.RAW_GOLD).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.DEEPSLATE_COPPER_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.RAW_COPPER).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.DEEPSLATE_EMERALD_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.EMERALD_ORE).setMinimumTool(Enums.ToolLevel.STONE).create();
        new Builder(Material.DEEPSLATE_REDSTONE_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.REDSTONE).setMinimumTool(Enums.ToolLevel.IRON).create();
        new Builder(Material.DEEPSLATE_DIAMOND_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.DIAMOND).setMinimumTool(Enums.ToolLevel.IRON).create();

        new Builder(Material.ANCIENT_DEBRIS).setReplacement(Material.AIR).setDrop(DFMaterial.AncientDebrisFragment, 5).setMinimumTool(Enums.ToolLevel.DIAMOND).setAdvancement(NetheriteTech.class).create();

        new Builder(Material.BOOKSHELF).setReplacement(Material.CHISELED_BOOKSHELF).setAdvancement(IITNIG.class)
                .setBlockModification((plr, block, face ) -> {
                    BlockData blockData = block.getBlockData();
                    if (blockData instanceof ChiseledBookshelf bookshelfData) {
                        if (face.getModY() == 0) bookshelfData.setFacing(face);
                        block.setBlockData(bookshelfData);
                        List<Integer> indexes = new ArrayList<>();

                        org.bukkit.block.ChiseledBookshelf state = (org.bukkit.block.ChiseledBookshelf) block.getState();
                        ChiseledBookshelfInventory inv = state.getInventory();

                        for (int i=0; i<inv.getSize(); i++) indexes.add(i);
                        Collections.shuffle(indexes);

                        for (int i=0; i<3; i++) {
                            inv.setItem(indexes.get(i), ItemStack.of(Material.BOOK));
                            state.setLastInteractedSlot(indexes.get(i));
                        }
                    }
                })
                .create();

        new Builder(Material.BLACK_CONCRETE).setReplacement(Material.BLACK_CONCRETE_POWDER).create();
        new Builder(Material.BLUE_CONCRETE).setReplacement(Material.BLUE_CONCRETE_POWDER).create();
        new Builder(Material.BROWN_CONCRETE).setReplacement(Material.BROWN_CONCRETE_POWDER).create();
        new Builder(Material.CYAN_CONCRETE).setReplacement(Material.CYAN_CONCRETE_POWDER).create();
        new Builder(Material.GRAY_CONCRETE).setReplacement(Material.GRAY_CONCRETE_POWDER).create();
        new Builder(Material.GREEN_CONCRETE).setReplacement(Material.GREEN_CONCRETE_POWDER).create();
        new Builder(Material.LIGHT_BLUE_CONCRETE).setReplacement(Material.LIGHT_BLUE_CONCRETE_POWDER).create();
        new Builder(Material.LIGHT_GRAY_CONCRETE).setReplacement(Material.LIGHT_GRAY_CONCRETE_POWDER).create();
        new Builder(Material.LIME_CONCRETE).setReplacement(Material.LIME_CONCRETE_POWDER).create();
        new Builder(Material.MAGENTA_CONCRETE).setReplacement(Material.MAGENTA_CONCRETE_POWDER).create();
        new Builder(Material.ORANGE_CONCRETE).setReplacement(Material.ORANGE_CONCRETE_POWDER).create();
        new Builder(Material.PINK_CONCRETE).setReplacement(Material.PINK_CONCRETE_POWDER).create();
        new Builder(Material.PURPLE_CONCRETE).setReplacement(Material.PURPLE_CONCRETE_POWDER).create();
        new Builder(Material.RED_CONCRETE).setReplacement(Material.RED_CONCRETE_POWDER).create();
        new Builder(Material.WHITE_CONCRETE).setReplacement(Material.WHITE_CONCRETE_POWDER).create();
        new Builder(Material.YELLOW_CONCRETE).setReplacement(Material.YELLOW_CONCRETE_POWDER).create();

        // every copper thing ever
        new Builder(Material.WAXED_COPPER_BLOCK).setReplacement(Material.COPPER_BLOCK).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_CHISELED_COPPER).setReplacement(Material.CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_COPPER_BULB).setReplacement(Material.COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_COPPER_DOOR).setReplacement(Material.COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_COPPER_GRATE).setReplacement(Material.COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_COPPER_TRAPDOOR).setReplacement(Material.COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_CUT_COPPER).setReplacement(Material.CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_CUT_COPPER_SLAB).setReplacement(Material.CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_CUT_COPPER_STAIRS).setReplacement(Material.CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new Builder(Material.WAXED_EXPOSED_COPPER).setReplacement(Material.EXPOSED_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_CHISELED_COPPER).setReplacement(Material.EXPOSED_CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_COPPER_BULB).setReplacement(Material.EXPOSED_COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_COPPER_DOOR).setReplacement(Material.EXPOSED_COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_COPPER_GRATE).setReplacement(Material.EXPOSED_COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_COPPER_TRAPDOOR).setReplacement(Material.EXPOSED_COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_CUT_COPPER).setReplacement(Material.EXPOSED_CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_CUT_COPPER_SLAB).setReplacement(Material.EXPOSED_CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_EXPOSED_CUT_COPPER_STAIRS).setReplacement(Material.EXPOSED_CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new Builder(Material.EXPOSED_COPPER).setReplacement(Material.COPPER_BLOCK).create();
        new Builder(Material.EXPOSED_CHISELED_COPPER).setReplacement(Material.CHISELED_COPPER).create();
        new Builder(Material.EXPOSED_COPPER_BULB).setReplacement(Material.COPPER_BULB).create();
        new Builder(Material.EXPOSED_COPPER_DOOR).setReplacement(Material.COPPER_DOOR).create();
        new Builder(Material.EXPOSED_COPPER_GRATE).setReplacement(Material.COPPER_GRATE).create();
        new Builder(Material.EXPOSED_COPPER_TRAPDOOR).setReplacement(Material.COPPER_TRAPDOOR).create();
        new Builder(Material.EXPOSED_CUT_COPPER).setReplacement(Material.CUT_COPPER).create();
        new Builder(Material.EXPOSED_CUT_COPPER_SLAB).setReplacement(Material.CUT_COPPER_SLAB).create();
        new Builder(Material.EXPOSED_CUT_COPPER_STAIRS).setReplacement(Material.CUT_COPPER_STAIRS).create();

        new Builder(Material.WAXED_WEATHERED_COPPER).setReplacement(Material.WEATHERED_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_CHISELED_COPPER).setReplacement(Material.WEATHERED_CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_COPPER_BULB).setReplacement(Material.WEATHERED_COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_COPPER_DOOR).setReplacement(Material.WEATHERED_COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_COPPER_GRATE).setReplacement(Material.WEATHERED_COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_COPPER_TRAPDOOR).setReplacement(Material.WEATHERED_COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_CUT_COPPER).setReplacement(Material.WEATHERED_CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_CUT_COPPER_SLAB).setReplacement(Material.WEATHERED_CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_WEATHERED_CUT_COPPER_STAIRS).setReplacement(Material.WEATHERED_CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new Builder(Material.WEATHERED_COPPER).setReplacement(Material.EXPOSED_COPPER).create();
        new Builder(Material.WEATHERED_CHISELED_COPPER).setReplacement(Material.EXPOSED_CHISELED_COPPER).create();
        new Builder(Material.WEATHERED_COPPER_BULB).setReplacement(Material.EXPOSED_COPPER_BULB).create();
        new Builder(Material.WEATHERED_COPPER_DOOR).setReplacement(Material.EXPOSED_COPPER_DOOR).create();
        new Builder(Material.WEATHERED_COPPER_GRATE).setReplacement(Material.EXPOSED_COPPER_GRATE).create();
        new Builder(Material.WEATHERED_COPPER_TRAPDOOR).setReplacement(Material.EXPOSED_COPPER_TRAPDOOR).create();
        new Builder(Material.WEATHERED_CUT_COPPER).setReplacement(Material.EXPOSED_CUT_COPPER).create();
        new Builder(Material.WEATHERED_CUT_COPPER_SLAB).setReplacement(Material.EXPOSED_CUT_COPPER_SLAB).create();
        new Builder(Material.WEATHERED_CUT_COPPER_STAIRS).setReplacement(Material.EXPOSED_CUT_COPPER_STAIRS).create();

        new Builder(Material.WAXED_OXIDIZED_COPPER).setReplacement(Material.OXIDIZED_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_CHISELED_COPPER).setReplacement(Material.OXIDIZED_CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_COPPER_BULB).setReplacement(Material.OXIDIZED_COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_COPPER_DOOR).setReplacement(Material.OXIDIZED_COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_COPPER_GRATE).setReplacement(Material.OXIDIZED_COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_COPPER_TRAPDOOR).setReplacement(Material.OXIDIZED_COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_CUT_COPPER).setReplacement(Material.OXIDIZED_CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB).setReplacement(Material.OXIDIZED_CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new Builder(Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS).setReplacement(Material.OXIDIZED_CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new Builder(Material.OXIDIZED_COPPER).setReplacement(Material.WEATHERED_COPPER).create();
        new Builder(Material.OXIDIZED_CHISELED_COPPER).setReplacement(Material.WEATHERED_CHISELED_COPPER).create();
        new Builder(Material.OXIDIZED_COPPER_BULB).setReplacement(Material.WEATHERED_COPPER_BULB).create();
        new Builder(Material.OXIDIZED_COPPER_DOOR).setReplacement(Material.WEATHERED_COPPER_DOOR).create();
        new Builder(Material.OXIDIZED_COPPER_GRATE).setReplacement(Material.WEATHERED_COPPER_GRATE).create();
        new Builder(Material.OXIDIZED_COPPER_TRAPDOOR).setReplacement(Material.WEATHERED_COPPER_TRAPDOOR).create();
        new Builder(Material.OXIDIZED_CUT_COPPER).setReplacement(Material.WEATHERED_CUT_COPPER).create();
        new Builder(Material.OXIDIZED_CUT_COPPER_SLAB).setReplacement(Material.WEATHERED_CUT_COPPER_SLAB).create();
        new Builder(Material.OXIDIZED_CUT_COPPER_STAIRS).setReplacement(Material.WEATHERED_CUT_COPPER_STAIRS).create();

        new Builder(Material.BLACK_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.BLACK_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.BLUE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.BLUE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.BROWN_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.BROWN_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.CYAN_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.CYAN_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.GRAY_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.GRAY_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.GREEN_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.GREEN_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.LIGHT_GRAY_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.LIGHT_GRAY_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.LIGHT_BLUE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.LIGHT_BLUE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.LIME_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.LIME_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.MAGENTA_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.MAGENTA_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.ORANGE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.ORANGE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.PINK_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.PINK_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.PURPLE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.PURPLE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.RED_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.RED_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.YELLOW_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.YELLOW_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();

        new Builder(Material.BLACK_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.BLACK_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.BLUE_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.BLUE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.BROWN_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.BROWN_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.CYAN_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.CYAN_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.GRAY_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.GRAY_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.GREEN_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.GREEN_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.LIGHT_GRAY_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.LIGHT_GRAY_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.LIGHT_BLUE_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.LIGHT_BLUE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.LIME_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.LIME_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.MAGENTA_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.MAGENTA_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.ORANGE_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.ORANGE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.PINK_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.PINK_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.PURPLE_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.PURPLE_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.RED_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.RED_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();
        new Builder(Material.YELLOW_CARPET).setReplacement(Material.WHITE_CARPET).setDrop(Material.YELLOW_DYE).setSpeedMultiplayer(0.5f).setSoundData(Sounds.WoolChisel).create();

        new Builder(Material.CHORUS_FLOWER).setReplacement(Material.CHORUS_PLANT).setDrop(Material.POPPED_CHORUS_FRUIT).create();
        new Builder(Material.COBWEB).setReplacement(Material.STRING).setDrop(Material.SLIME_BALL, 1).setSpeedMultiplayer(0.5f).setSoundData(Sounds.CobwebBreak).create();

        new Builder(Material.OAK_LEAVES).setReplacement(Material.AIR).setDrop(Material.OAK_SAPLING, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.BIRCH_LEAVES).setReplacement(Material.AIR).setDrop(Material.BIRCH_SAPLING, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.SPRUCE_LEAVES).setReplacement(Material.AIR).setDrop(Material.SPRUCE_SAPLING, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.JUNGLE_LEAVES).setReplacement(Material.AIR).setDrop(Material.JUNGLE_LEAVES, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.ACACIA_LEAVES).setReplacement(Material.AIR).setDrop(Material.ACACIA_SAPLING, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.DARK_OAK_LEAVES).setReplacement(Material.AIR).setDrop(Material.DARK_OAK_LEAVES, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.MANGROVE_LEAVES).setReplacement(Material.AIR).setDrop(Material.MANGROVE_PROPAGULE, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.CHERRY_LEAVES).setReplacement(Material.AIR).setDrop(Material.CHERRY_SAPLING, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.PALE_OAK_LEAVES).setReplacement(Material.AIR).setDrop(Material.PALE_OAK_LEAVES, 1).setSpeedMultiplayer(0.1f).setSoundData(Sounds.WoodCrash).create();

        new Builder(Material.OAK_LOG).setReplacement(Material.STRIPPED_OAK_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.ACACIA_LOG).setReplacement(Material.STRIPPED_ACACIA_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.BIRCH_LOG).setReplacement(Material.STRIPPED_BIRCH_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.CHERRY_LOG).setReplacement(Material.STRIPPED_CHERRY_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.DARK_OAK_LOG).setReplacement(Material.STRIPPED_DARK_OAK_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.JUNGLE_LOG).setReplacement(Material.STRIPPED_JUNGLE_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.MANGROVE_LOG).setReplacement(Material.STRIPPED_MANGROVE_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.SPRUCE_LOG).setReplacement(Material.STRIPPED_SPRUCE_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.PALE_OAK_LOG).setReplacement(Material.STRIPPED_PALE_OAK_LOG).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.WARPED_STEM).setReplacement(Material.STRIPPED_WARPED_STEM).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.CRIMSON_STEM).setReplacement(Material.STRIPPED_CRIMSON_STEM).setDrop(Material.STICK, 2).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();

        new Builder(Material.STRIPPED_OAK_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_ACACIA_LOG).setReplacement(Material.AIR).setDrop(Material.ACACIA_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_BIRCH_LOG).setReplacement(Material.AIR).setDrop(Material.BIRCH_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_CHERRY_LOG).setReplacement(Material.AIR).setDrop(Material.CHERRY_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_DARK_OAK_LOG).setReplacement(Material.AIR).setDrop(Material.DARK_OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_JUNGLE_LOG).setReplacement(Material.AIR).setDrop(Material.JUNGLE_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_MANGROVE_LOG).setReplacement(Material.AIR).setDrop(Material.MANGROVE_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_SPRUCE_LOG).setReplacement(Material.AIR).setDrop(Material.SPRUCE_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_PALE_OAK_LOG).setReplacement(Material.AIR).setDrop(Material.PALE_OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_WARPED_STEM).setReplacement(Material.AIR).setDrop(Material.WARPED_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
        new Builder(Material.STRIPPED_CRIMSON_STEM).setReplacement(Material.AIR).setDrop(Material.CRIMSON_PLANKS, 4).setSpeedMultiplayer(0.25f).setSoundData(Sounds.WoodCrash).create();
    }

    // chisel block data
    private final Enums.ToolLevel minimumToolLevel;
    private final Material block;
    private final Material convertInto;
    private final ItemStack drop;
    private final float speedMultiplier;
    private final PresetSoundData soundData;
    private final @Nullable Class<? extends BaseAdvancement> advancementOnChisel;
    private final @Nullable ChiselBlockModify blockModification;

    public ChiselBlockData(
            Enums.ToolLevel minimumToolLevel,
            Material block,
            Material convertInto,
            ItemStack drop,
            float speedMultiplier,
            PresetSoundData soundData,
            Class<? extends BaseAdvancement> advancementOnChisel,
            ChiselBlockModify blockModification
    ) {
        this.minimumToolLevel = minimumToolLevel;
        this.block = block;
        this.convertInto = convertInto;
        this.drop = drop;
        this.speedMultiplier = speedMultiplier;
        this.soundData = soundData;
        this.advancementOnChisel = advancementOnChisel;
        this.blockModification = blockModification;
    }

    public Enums.ToolLevel getMinimumToolLevel() {
        return this.minimumToolLevel;
    }

    public Material getBlockMaterial() {
        return this.block;
    }

    public Material getReplacingMaterial() {
        return this.convertInto;
    }

    public ItemStack getDrop() {
        return this.drop;
    }

    public float getSpeedMultiplier() {
        return this.speedMultiplier;
    }

    public PresetSoundData getSoundData() {
        return this.soundData;
    }

    public void runBlockModifications(Player plr, Block block, BlockFace face) {
        if (blockModification == null) return;
        this.blockModification.modifyBlock(plr, block, face);
    }

    @Nullable
    public Class<? extends BaseAdvancement> getAdvancementOnChisel() {
        return this.advancementOnChisel;
    }

    @Nullable
    public static ChiselBlockData getChiselBlockData(Block block) {
        if (block == null) return null;

        for (ChiselBlockData data : ChiselBlockDataIndex) {
            if (Objects.equals(data.getBlockMaterial(), block.getType())) {
                return data;
            }
        }

        return null;
    }

    public static class Builder {
        private Enums.ToolLevel minimumToolLevel;
        private final Material block;
        private Material convertInto;
        private ItemStack drop;
        private Float speedMultiplier;
        private PresetSoundData soundData;
        private @Nullable Class<? extends BaseAdvancement> advancementOnChisel;
        private ChiselBlockModify blockModification = null;

        public Builder(Material block) {
            this.block = block;
        }

        public Builder setAdvancement(Class<? extends BaseAdvancement> clazz) {
            this.advancementOnChisel = clazz;
            return this;
        }

        public Builder setReplacement(Material replacement) {
            this.convertInto = replacement;
            return this;
        }

        public Builder setDrop(Material drop) {
            this.drop = ItemStack.of(drop);
            return this;
        }

        public Builder setDrop(Material drop, int amnt) {
            this.drop = ItemStack.of(drop, amnt);
            return this;
        }

        public Builder setDrop(ItemStack drop) {
            this.drop = drop;
            return this;
        }

        public Builder setDrop(DFMaterial drop) {
            this.drop = drop.toItemStack();
            return this;
        }

        public Builder setDrop(DFMaterial drop, int amnt) {
            this.drop = drop.toItemStack(amnt);
            return this;
        }

        public Builder setSpeedMultiplayer(float speedMultiplier) {
            this.speedMultiplier = speedMultiplier;
            return this;
        }

        public Builder setMinimumTool (Enums.ToolLevel level) {
            this.minimumToolLevel = level;
            return this;
        }

        public Builder setSoundData(PresetSoundData soundData) {
            this.soundData = soundData;
            return this;
        }

        public Builder setBlockModification(ChiselBlockModify blockModification) {
            this.blockModification = blockModification;
            return this;
        }

        public ChiselBlockData create() {
            ChiselBlockData data = new ChiselBlockData(
                    minimumToolLevel == null ? Enums.ToolLevel.WOODEN : minimumToolLevel,
                    block,
                    convertInto == null ? Material.AIR : convertInto,
                    drop == null ? ItemStack.of(Material.AIR) : drop,
                    speedMultiplier == null ? 1.5F : speedMultiplier,
                    soundData == null ? Sounds.StoneClank : soundData,
                    advancementOnChisel,
                    blockModification
            );
            ChiselBlockDataIndex.add(data);
            return data;
        }
    }

}
