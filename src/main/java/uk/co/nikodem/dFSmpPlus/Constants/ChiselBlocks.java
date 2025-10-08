package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.Material;

import java.util.Map;

import static java.util.Map.entry;

public class ChiselBlocks {
    public final static Map<Material, Map.Entry<Material, Material>> blockMappings = Map.ofEntries(
            // btw reference
            entry(Material.STONE, entry(Material.GRAVEL, Material.GRAVEL)),
            entry(Material.GRAVEL, entry(Material.PALE_OAK_LEAVES, Material.FLINT)),

            // trolling
            entry(Material.COAL_ORE, entry(Material.STONE, Material.AIR)),
            entry(Material.IRON_ORE, entry(Material.STONE, Material.AIR)),
            entry(Material.REDSTONE_ORE, entry(Material.STONE, Material.AIR)),
            entry(Material.EMERALD_ORE, entry(Material.STONE, Material.AIR)),
            entry(Material.COPPER_ORE, entry(Material.STONE, Material.AIR)),
            entry(Material.GOLD_ORE, entry(Material.STONE, Material.AIR)),
            entry(Material.DIAMOND_ORE, entry(Material.STONE, Material.AIR)),
            entry(Material.LAPIS_ORE, entry(Material.STONE, Material.AIR)),

            entry(Material.DEEPSLATE_COAL_ORE, entry(Material.DEEPSLATE, Material.AIR)),
            entry(Material.DEEPSLATE_IRON_ORE, entry(Material.DEEPSLATE, Material.AIR)),
            entry(Material.DEEPSLATE_REDSTONE_ORE, entry(Material.DEEPSLATE, Material.AIR)),
            entry(Material.DEEPSLATE_EMERALD_ORE, entry(Material.DEEPSLATE, Material.AIR)),
            entry(Material.DEEPSLATE_COPPER_ORE, entry(Material.DEEPSLATE, Material.AIR)),
            entry(Material.DEEPSLATE_GOLD_ORE, entry(Material.DEEPSLATE, Material.AIR)),
            entry(Material.DEEPSLATE_DIAMOND_ORE, entry(Material.DEEPSLATE, Material.AIR)),
            entry(Material.DEEPSLATE_LAPIS_ORE, entry(Material.DEEPSLATE, Material.AIR)),

            // actually useful
            entry(Material.BLACK_CONCRETE, entry(Material.BLACK_CONCRETE_POWDER, Material.AIR)),
            entry(Material.BLUE_CONCRETE, entry(Material.BLUE_CONCRETE_POWDER, Material.AIR)),
            entry(Material.BROWN_CONCRETE, entry(Material.BROWN_CONCRETE_POWDER, Material.AIR)),
            entry(Material.CYAN_CONCRETE, entry(Material.CYAN_CONCRETE_POWDER, Material.AIR)),
            entry(Material.GRAY_CONCRETE, entry(Material.GRAY_CONCRETE_POWDER, Material.AIR)),
            entry(Material.GREEN_CONCRETE, entry(Material.GREEN_CONCRETE_POWDER, Material.AIR)),
            entry(Material.LIGHT_BLUE_CONCRETE, entry(Material.LIGHT_BLUE_CONCRETE_POWDER, Material.AIR)),
            entry(Material.LIGHT_GRAY_CONCRETE, entry(Material.LIGHT_GRAY_CONCRETE_POWDER, Material.AIR)),
            entry(Material.LIME_CONCRETE, entry(Material.LIME_CONCRETE_POWDER, Material.AIR)),
            entry(Material.MAGENTA_CONCRETE, entry(Material.MAGENTA_CONCRETE_POWDER, Material.AIR)),
            entry(Material.ORANGE_CONCRETE, entry(Material.ORANGE_CONCRETE_POWDER, Material.AIR)),
            entry(Material.PINK_CONCRETE, entry(Material.PINK_CONCRETE_POWDER, Material.AIR)),
            entry(Material.PURPLE_CONCRETE, entry(Material.PURPLE_CONCRETE_POWDER, Material.AIR)),
            entry(Material.RED_CONCRETE, entry(Material.RED_CONCRETE_POWDER, Material.AIR)),
            entry(Material.WHITE_CONCRETE, entry(Material.WHITE_CONCRETE_POWDER, Material.AIR)),
            entry(Material.YELLOW_CONCRETE, entry(Material.YELLOW_CONCRETE_POWDER, Material.AIR))
    );
}
