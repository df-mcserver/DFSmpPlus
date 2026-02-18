package uk.co.nikodem.dFSmpPlus.Enchantments.Metas;

import org.bukkit.Material;
import uk.co.nikodem.dFSmpPlus.Enchantments.DFEnchantmentMeta;

import java.util.List;

public class HarvestMeta implements DFEnchantmentMeta {
    public static List<Material> harvestable = List.of(
            Material.WHEAT,
            Material.BEETROOT,
            Material.POTATO,
            Material.CARROT,
            Material.PUMPKIN,
            Material.MELON,
            Material.NETHER_WART
    );
}
