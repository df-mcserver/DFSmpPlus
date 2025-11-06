package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.Material;

public class VeinMineable {
    public final static Material[] VeinOres = new Material[] {
            Material.COAL_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.IRON_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.COPPER_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.GOLD_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.REDSTONE_ORE,
            Material.DEEPSLATE_REDSTONE_ORE,
            Material.EMERALD_ORE,
            Material.DEEPSLATE_EMERALD_ORE,
            Material.LAPIS_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.DIAMOND_ORE,
            Material.DEEPSLATE_DIAMOND_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.AMETHYST_BLOCK,
    };

    public final static Material[] VeinLogs = new Material[] {
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.CHERRY_LOG,
            Material.JUNGLE_LOG,
            Material.DARK_OAK_LOG,
            Material.MANGROVE_LOG,
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.STRIPPED_ACACIA_LOG,
            Material.STRIPPED_BIRCH_LOG,
            Material.STRIPPED_CHERRY_LOG,
            Material.STRIPPED_JUNGLE_LOG,
            Material.STRIPPED_DARK_OAK_LOG,
            Material.STRIPPED_MANGROVE_LOG,
            Material.STRIPPED_OAK_LOG,
            Material.STRIPPED_SPRUCE_LOG,
            Material.WARPED_STEM,
            Material.CRIMSON_STEM,
            Material.STRIPPED_WARPED_STEM,
            Material.STRIPPED_CRIMSON_STEM
    };

    public static boolean isVeinOre(Material material) {
        for (Material potential : VeinOres) {
            if (potential == material) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVeinLog(Material material) {
        for (Material potential : VeinLogs) {
            if (potential == material) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInArray(Material material, Material[] array) {
        for (Material potential : array) {
            if (potential == material) {
                return true;
            }
        }
        return false;
    }
}
