package uk.co.nikodem.dFSmpPlus.Blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class CustomDrops {
    public static void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!event.isDropItems()) return;
        Player plr = event.getPlayer();
        Block origin = event.getBlock();
        if (origin.getType() == Material.GRAVEL) {
            event.setDropItems(false);
            plr.getWorld().dropItemNaturally(origin.getLocation().add(new Location(origin.getWorld(), 0.5, 0.5, 0.5)), new ItemStack(Material.GRAVEL));
        }
    }
}
