package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.Material;

import java.util.Map;

import static java.util.Map.entry;

public class PointyStick {
    public final static Map<Material, Map.Entry<Material, Material>> PointyStick = Map.ofEntries(
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
            entry(Material.DEEPSLATE_LAPIS_ORE, entry(Material.DEEPSLATE, Material.AIR))
    );

}
