package uk.co.nikodem.dFSmpPlus.WorldGen;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.List;
import java.util.Random;

public class PopulateChests {

    private static final Random rand = new Random();

    public static void PopulateChests(LootGenerateEvent e) {
        InventoryHolder holder = e.getInventoryHolder();
        if (holder instanceof Chest chest) {
            // chest inv
            List<ItemStack> loot = e.getLoot();

            if (
                    // target bastions, nether fortresses and ruined portals
                    // and probably some other stuff
                    lootContains(loot,
                            Material.ANCIENT_DEBRIS,
                            Material.NETHERITE_INGOT,
                            Material.NETHERITE_SCRAP,
                            Material.FLINT_AND_STEEL,
                            Material.NETHER_WART,
                            Material.GOLDEN_SWORD,
                            Material.GLISTERING_MELON_SLICE,
                            Material.LODESTONE)

                    && !lootContains(loot, Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
            ) {

                int chance = rand.nextInt(0, 4);

                if (chance == 0) {
                    loot.add(DFMaterial.ObsidianUpgradeTemplate.toItemStack());
                }
            }

            // if biome is warped forest
            // and nether wart is in the chest
            // use warped wart instead

            int i = 0;
            for (ItemStack item : loot) {
                if (item.getType() == Material.NETHER_WART) {

                    Block b = chest.getBlock();
                    Biome biome = b.getWorld().getBiome(b.getLocation());

                    if (biome == Biome.WARPED_FOREST) {
                        loot.set(i, DFMaterial.WarpedWart.toItemStack());
                    }
                }
                i++;
            }
        }
    }

    public static boolean lootContains(List<ItemStack> loot, Material... materials) {
        for (ItemStack item : loot) {
            for (Material material : materials) {
                if (item.getType() == material) {
                    return true;
                }
            }
        }
        return false;
    }
}
