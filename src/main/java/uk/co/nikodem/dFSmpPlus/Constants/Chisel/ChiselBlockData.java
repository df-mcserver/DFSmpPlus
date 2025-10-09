package uk.co.nikodem.dFSmpPlus.Constants.Chisel;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChiselBlockData {
    // please don't touch lol
    public final static List<ChiselBlockData> ChiselBlockDataIndex = new ArrayList<>();

    // Chisel Blocks
    public static void createChiselBlockData() {
        new ChiselBlockDataBuilder(Material.STONE).setReplacement(Material.GRAVEL).setDrop(DFMaterial.LooseStone).create();
        new ChiselBlockDataBuilder(Material.GRAVEL).setReplacement(Material.AIR).setDrop(Material.FLINT).setSpeedMultiplayer(0.25f).create();

        // TODO: autosmelt
        new ChiselBlockDataBuilder(Material.COAL_ORE).setReplacement(Material.STONE).setDrop(Material.COAL).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.IRON_ORE).setReplacement(Material.STONE).setDrop(Material.RAW_IRON).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.GOLD_ORE).setReplacement(Material.STONE).setDrop(Material.RAW_GOLD).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.COPPER_ORE).setReplacement(Material.STONE).setDrop(Material.RAW_COPPER).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.EMERALD_ORE).setReplacement(Material.STONE).setDrop(Material.EMERALD_ORE).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.REDSTONE_ORE).setReplacement(Material.STONE).setDrop(Material.REDSTONE).setMinimumTool(Enums.ToolLevel.IRON).create();
        new ChiselBlockDataBuilder(Material.DIAMOND_ORE).setReplacement(Material.STONE).setDrop(Material.DIAMOND).setMinimumTool(Enums.ToolLevel.IRON).create();

        new ChiselBlockDataBuilder(Material.DEEPSLATE_COAL_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.COAL).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.DEEPSLATE_IRON_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.RAW_IRON).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.DEEPSLATE_GOLD_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.RAW_GOLD).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.DEEPSLATE_COPPER_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.RAW_COPPER).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.DEEPSLATE_EMERALD_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.EMERALD_ORE).setMinimumTool(Enums.ToolLevel.STONE).create();
        new ChiselBlockDataBuilder(Material.DEEPSLATE_REDSTONE_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.REDSTONE).setMinimumTool(Enums.ToolLevel.IRON).create();
        new ChiselBlockDataBuilder(Material.DEEPSLATE_DIAMOND_ORE).setReplacement(Material.DEEPSLATE).setDrop(Material.DIAMOND).setMinimumTool(Enums.ToolLevel.IRON).create();

        new ChiselBlockDataBuilder(Material.BLACK_CONCRETE).setReplacement(Material.BLACK_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.BLUE_CONCRETE).setReplacement(Material.BLUE_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.BROWN_CONCRETE).setReplacement(Material.BROWN_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.CYAN_CONCRETE).setReplacement(Material.CYAN_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.GRAY_CONCRETE).setReplacement(Material.GRAY_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.GREEN_CONCRETE).setReplacement(Material.GREEN_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.LIGHT_BLUE_CONCRETE).setReplacement(Material.LIGHT_BLUE_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.LIGHT_GRAY_CONCRETE).setReplacement(Material.LIGHT_GRAY_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.LIME_CONCRETE).setReplacement(Material.LIME_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.MAGENTA_CONCRETE).setReplacement(Material.MAGENTA_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.ORANGE_CONCRETE).setReplacement(Material.ORANGE_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.PINK_CONCRETE).setReplacement(Material.PINK_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.PURPLE_CONCRETE).setReplacement(Material.PURPLE_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.RED_CONCRETE).setReplacement(Material.RED_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.WHITE_CONCRETE).setReplacement(Material.WHITE_CONCRETE_POWDER).create();
        new ChiselBlockDataBuilder(Material.YELLOW_CONCRETE).setReplacement(Material.YELLOW_CONCRETE_POWDER).create();

        // every copper thing ever
        new ChiselBlockDataBuilder(Material.WAXED_COPPER_BLOCK).setReplacement(Material.COPPER_BLOCK).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_CHISELED_COPPER).setReplacement(Material.CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_COPPER_BULB).setReplacement(Material.COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_COPPER_DOOR).setReplacement(Material.COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_COPPER_GRATE).setReplacement(Material.COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_COPPER_TRAPDOOR).setReplacement(Material.COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_CUT_COPPER).setReplacement(Material.CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_CUT_COPPER_SLAB).setReplacement(Material.CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_CUT_COPPER_STAIRS).setReplacement(Material.CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_COPPER).setReplacement(Material.EXPOSED_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_CHISELED_COPPER).setReplacement(Material.EXPOSED_CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_COPPER_BULB).setReplacement(Material.EXPOSED_COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_COPPER_DOOR).setReplacement(Material.EXPOSED_COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_COPPER_GRATE).setReplacement(Material.EXPOSED_COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_COPPER_TRAPDOOR).setReplacement(Material.EXPOSED_COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_CUT_COPPER).setReplacement(Material.EXPOSED_CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_CUT_COPPER_SLAB).setReplacement(Material.EXPOSED_CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_EXPOSED_CUT_COPPER_STAIRS).setReplacement(Material.EXPOSED_CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new ChiselBlockDataBuilder(Material.EXPOSED_COPPER).setReplacement(Material.COPPER_BLOCK).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_CHISELED_COPPER).setReplacement(Material.CHISELED_COPPER).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_COPPER_BULB).setReplacement(Material.COPPER_BULB).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_COPPER_DOOR).setReplacement(Material.COPPER_DOOR).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_COPPER_GRATE).setReplacement(Material.COPPER_GRATE).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_COPPER_TRAPDOOR).setReplacement(Material.COPPER_TRAPDOOR).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_CUT_COPPER).setReplacement(Material.CUT_COPPER).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_CUT_COPPER_SLAB).setReplacement(Material.CUT_COPPER_SLAB).create();
        new ChiselBlockDataBuilder(Material.EXPOSED_CUT_COPPER_STAIRS).setReplacement(Material.CUT_COPPER_STAIRS).create();

        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_COPPER).setReplacement(Material.WEATHERED_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_CHISELED_COPPER).setReplacement(Material.WEATHERED_CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_COPPER_BULB).setReplacement(Material.WEATHERED_COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_COPPER_DOOR).setReplacement(Material.WEATHERED_COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_COPPER_GRATE).setReplacement(Material.WEATHERED_COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_COPPER_TRAPDOOR).setReplacement(Material.WEATHERED_COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_CUT_COPPER).setReplacement(Material.WEATHERED_CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_CUT_COPPER_SLAB).setReplacement(Material.WEATHERED_CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_WEATHERED_CUT_COPPER_STAIRS).setReplacement(Material.WEATHERED_CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new ChiselBlockDataBuilder(Material.WEATHERED_COPPER).setReplacement(Material.EXPOSED_COPPER).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_CHISELED_COPPER).setReplacement(Material.EXPOSED_CHISELED_COPPER).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_COPPER_BULB).setReplacement(Material.EXPOSED_COPPER_BULB).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_COPPER_DOOR).setReplacement(Material.EXPOSED_COPPER_DOOR).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_COPPER_GRATE).setReplacement(Material.EXPOSED_COPPER_GRATE).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_COPPER_TRAPDOOR).setReplacement(Material.EXPOSED_COPPER_TRAPDOOR).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_CUT_COPPER).setReplacement(Material.EXPOSED_CUT_COPPER).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_CUT_COPPER_SLAB).setReplacement(Material.EXPOSED_CUT_COPPER_SLAB).create();
        new ChiselBlockDataBuilder(Material.WEATHERED_CUT_COPPER_STAIRS).setReplacement(Material.EXPOSED_CUT_COPPER_STAIRS).create();

        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_COPPER).setReplacement(Material.OXIDIZED_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_CHISELED_COPPER).setReplacement(Material.OXIDIZED_CHISELED_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_COPPER_BULB).setReplacement(Material.OXIDIZED_COPPER_BULB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_COPPER_DOOR).setReplacement(Material.OXIDIZED_COPPER_DOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_COPPER_GRATE).setReplacement(Material.OXIDIZED_COPPER_GRATE).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_COPPER_TRAPDOOR).setReplacement(Material.OXIDIZED_COPPER_TRAPDOOR).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_CUT_COPPER).setReplacement(Material.OXIDIZED_CUT_COPPER).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB).setReplacement(Material.OXIDIZED_CUT_COPPER_SLAB).setDrop(Material.HONEYCOMB).create();
        new ChiselBlockDataBuilder(Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS).setReplacement(Material.OXIDIZED_CUT_COPPER_STAIRS).setDrop(Material.HONEYCOMB).create();

        new ChiselBlockDataBuilder(Material.OXIDIZED_COPPER).setReplacement(Material.WEATHERED_COPPER).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_CHISELED_COPPER).setReplacement(Material.WEATHERED_CHISELED_COPPER).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_COPPER_BULB).setReplacement(Material.WEATHERED_COPPER_BULB).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_COPPER_DOOR).setReplacement(Material.WEATHERED_COPPER_DOOR).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_COPPER_GRATE).setReplacement(Material.WEATHERED_COPPER_GRATE).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_COPPER_TRAPDOOR).setReplacement(Material.WEATHERED_COPPER_TRAPDOOR).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_CUT_COPPER).setReplacement(Material.WEATHERED_CUT_COPPER).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_CUT_COPPER_SLAB).setReplacement(Material.WEATHERED_CUT_COPPER_SLAB).create();
        new ChiselBlockDataBuilder(Material.OXIDIZED_CUT_COPPER_STAIRS).setReplacement(Material.WEATHERED_CUT_COPPER_STAIRS).create();

        new ChiselBlockDataBuilder(Material.BOOKSHELF).setReplacement(Material.CHISELED_BOOKSHELF).setDrop(Material.BOOK, 3).create();

        new ChiselBlockDataBuilder(Material.BLACK_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.BLACK_DYE).create();
        new ChiselBlockDataBuilder(Material.BLUE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.BLUE_DYE).create();
        new ChiselBlockDataBuilder(Material.BROWN_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.BROWN_DYE).create();
        new ChiselBlockDataBuilder(Material.CYAN_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.CYAN_DYE).create();
        new ChiselBlockDataBuilder(Material.GRAY_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.GRAY_DYE).create();
        new ChiselBlockDataBuilder(Material.GREEN_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.GREEN_DYE).create();
        new ChiselBlockDataBuilder(Material.LIGHT_GRAY_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.LIGHT_GRAY_DYE).create();
        new ChiselBlockDataBuilder(Material.LIGHT_BLUE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.LIGHT_BLUE_DYE).create();
        new ChiselBlockDataBuilder(Material.LIME_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.LIME_DYE).create();
        new ChiselBlockDataBuilder(Material.MAGENTA_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.MAGENTA_DYE).create();
        new ChiselBlockDataBuilder(Material.ORANGE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.ORANGE_DYE).create();
        new ChiselBlockDataBuilder(Material.PINK_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.PINK_DYE).create();
        new ChiselBlockDataBuilder(Material.PURPLE_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.PURPLE_DYE).create();
        new ChiselBlockDataBuilder(Material.RED_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.RED_DYE).create();
        new ChiselBlockDataBuilder(Material.YELLOW_WOOL).setReplacement(Material.WHITE_WOOL).setDrop(Material.YELLOW_DYE).create();

        new ChiselBlockDataBuilder(Material.OAK_LOG).setReplacement(Material.STRIPPED_OAK_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.ACACIA_LOG).setReplacement(Material.STRIPPED_ACACIA_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.BIRCH_LOG).setReplacement(Material.STRIPPED_BIRCH_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.CHERRY_LOG).setReplacement(Material.STRIPPED_CHERRY_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.DARK_OAK_LOG).setReplacement(Material.STRIPPED_DARK_OAK_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.JUNGLE_LOG).setReplacement(Material.STRIPPED_JUNGLE_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.MANGROVE_LOG).setReplacement(Material.STRIPPED_MANGROVE_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.SPRUCE_LOG).setReplacement(Material.STRIPPED_SPRUCE_LOG).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.WARPED_STEM).setReplacement(Material.STRIPPED_WARPED_STEM).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.CRIMSON_STEM).setReplacement(Material.STRIPPED_CRIMSON_STEM).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();

        new ChiselBlockDataBuilder(Material.STRIPPED_OAK_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_ACACIA_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_BIRCH_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_CHERRY_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_DARK_OAK_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_JUNGLE_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_MANGROVE_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_SPRUCE_LOG).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_WARPED_STEM).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
        new ChiselBlockDataBuilder(Material.STRIPPED_CRIMSON_STEM).setReplacement(Material.AIR).setDrop(Material.OAK_PLANKS, 4).setSpeedMultiplayer(0.25f).setSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR).setPitch(1f).create();
    }

    // chisel block data
    private final Enums.ToolLevel minimumToolLevel;
    private final Material block;
    private final Material convertInto;
    private final ItemStack drop;
    private final float speedMultiplier;
    private final PresetSoundData soundData;

    public ChiselBlockData(
            Enums.ToolLevel minimumToolLevel,
            Material block,
            Material convertInto,
            ItemStack drop,
            float speedMultiplier,
            PresetSoundData soundData
    ) {
        this.minimumToolLevel = minimumToolLevel;
        this.block = block;
        this.convertInto = convertInto;
        this.drop = drop;
        this.speedMultiplier = speedMultiplier;
        this.soundData = soundData;
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
}
