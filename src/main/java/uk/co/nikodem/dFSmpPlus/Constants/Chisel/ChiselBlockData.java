package uk.co.nikodem.dFSmpPlus.Constants.Chisel;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class ChiselBlockData {
    // please don't touch lol
    public final static List<ChiselBlockData> ChiselBlockDataIndex = new ArrayList<>();

    // Chisel Blocks
    public static void createChiselBlockData() {
        new ChiselBlockDataBuilder(Material.STONE).setReplacement(Material.GRAVEL).setDrop(DFMaterial.LooseStone).create();

        new ChiselBlockDataBuilder(Material.GRAVEL).setReplacement(Material.PALE_OAK_LEAVES).setDrop(Material.FLINT).create();

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
    }

    // chisel block data
    private final Enums.ToolLevel minimumToolLevel;
    private final Material block;
    private final Material convertInto;
    private final ItemStack drop;

    public ChiselBlockData(
            Enums.ToolLevel minimumToolLevel,
            Material block,
            Material convertInto,
            ItemStack drop
    ) {
        this.minimumToolLevel = minimumToolLevel;
        this.block = block;
        this.convertInto= convertInto;
        this.drop = drop;
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
}
