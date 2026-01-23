package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class HarvesterItemMeta implements DFMaterialMeta {
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {
        if (plr.isSneaking()) return;

        Block block = event.getBlock();
        if (block.getState().getBlockData() instanceof Ageable ageable) {
            if (ageable.getAge() < ageable.getMaximumAge()) event.setCancelled(true);
        }
    };
}
