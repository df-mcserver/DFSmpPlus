package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.Material;

import java.util.Map;

import static java.util.Map.entry;

public class AutoSmeltable {
    public final static Map<Material, Material> AutosmeltablePickaxe = Map.ofEntries(
            entry(Material.IRON_ORE, Material.IRON_INGOT),
            entry(Material.GOLD_ORE, Material.GOLD_INGOT),
            entry(Material.COPPER_ORE, Material.COPPER_INGOT),
            entry(Material.DEEPSLATE_IRON_ORE, Material.IRON_INGOT),
            entry(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_INGOT),
            entry(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_INGOT),

            entry(Material.COBBLESTONE, Material.STONE),
            entry(Material.COBBLESTONE_SLAB, Material.STONE_SLAB),
            entry(Material.COBBLESTONE_STAIRS, Material.STONE_STAIRS),

            entry(Material.STONE, Material.SMOOTH_STONE),
            entry(Material.STONE_SLAB, Material.SMOOTH_STONE_SLAB),

            entry(Material.COBBLED_DEEPSLATE, Material.DEEPSLATE)
    );

    public final static Map<Material, Material> AutosmeltableShovel = Map.ofEntries(
            entry(Material.SAND, Material.GLASS),
            entry(Material.RED_SAND, Material.GLASS),
            entry(Material.GRAVEL, Material.FLINT)
    );

    public final static Map<Material, Material> AutosmeltableAxe = Map.ofEntries(
            entry(Material.ACACIA_LOG, Material.CHARCOAL),
            entry(Material.BIRCH_LOG, Material.CHARCOAL),
            entry(Material.CHERRY_LOG, Material.CHARCOAL),
            entry(Material.JUNGLE_LOG, Material.CHARCOAL),
            entry(Material.DARK_OAK_LOG, Material.CHARCOAL),
            entry(Material.MANGROVE_LOG, Material.CHARCOAL),
            entry(Material.OAK_LOG, Material.CHARCOAL),
            entry(Material.SPRUCE_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_ACACIA_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_BIRCH_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_CHERRY_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_JUNGLE_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_DARK_OAK_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_MANGROVE_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_OAK_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_SPRUCE_LOG, Material.CHARCOAL),
            entry(Material.STRIPPED_CRIMSON_STEM, Material.CHARCOAL),
            entry(Material.STRIPPED_WARPED_STEM, Material.CHARCOAL),
            entry(Material.WARPED_STEM, Material.CHARCOAL),
            entry(Material.CRIMSON_STEM, Material.CHARCOAL)
    );
}
