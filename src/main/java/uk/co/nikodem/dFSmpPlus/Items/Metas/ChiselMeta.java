package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import uk.co.nikodem.dFSmpPlus.Constants.Chisel.ChiselBlockData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class ChiselMeta implements DFMaterialMeta {
    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {

        Block block = event.getBlock();
        Location loc = block.getLocation();

        for (ChiselBlockData data : ChiselBlockData.ChiselBlockDataIndex) {
            if (data.getBlockMaterial() == block.getType()) {
                event.setCancelled(true);
                block.setType(data.getReplacingMaterial());
                if (DFItemUtils.isLevelOrAbove(tool, data.getMinimumToolLevel())) block.getWorld().dropItemNaturally(loc.add(new Vector(0.5, 0.5, 0.5)), data.getDrop());
                loc.getWorld().playSound(loc, Sound.BLOCK_ANVIL_PLACE, 1F, 1.75F);
                DFItemUtils.reduceDurability(tool, 1);
                break;
            }
        }
    }
}
