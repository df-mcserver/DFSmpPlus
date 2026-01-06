package uk.co.nikodem.dFSmpPlus.Blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.Metas.AutoSmeltingMeta;

public class CustomDrops {
    public static void onBlockBreak(BlockBreakEvent event) {
        Player plr = event.getPlayer();
        Block origin = event.getBlock();
        if (origin.getType() == Material.GRAVEL) {
            DFMaterial material = DFItemUtils.getDFMaterial(plr.getInventory().getItemInMainHand());
            boolean hasAutosmeltItem = false;
            if (material != null) hasAutosmeltItem = DFItemUtils.containsMeta(material, AutoSmeltingMeta.class);

            if (!hasAutosmeltItem) {
                event.setDropItems(false);
                plr.getWorld().dropItemNaturally(origin.getLocation().add(new Location(origin.getWorld(), 0.5, 0.5, 0.5)), new ItemStack(Material.GRAVEL));
            }
        }
    }
}
