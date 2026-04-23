package uk.co.nikodem.dFSmpPlus.Entities;

import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ItemFrameRightClickOnChests {
    public static void onInteractWithEntity(PlayerItemFrameChangeEvent event) {
        Player plr = event.getPlayer();
        ItemFrame frame = event.getItemFrame();

        ItemStack item = frame.getItem();
        if (item.isEmpty()) return;
        if (plr.isSneaking()) return;
        if (event.getAction().equals(PlayerItemFrameChangeEvent.ItemFrameChangeAction.REMOVE)) return;
        BlockFace face = frame.getFacing().getOppositeFace();
        Vector direction = face.getDirection();
        Block block = direction.add(frame.getLocation().toVector()).toLocation(frame.getWorld()).getBlock();

        if (block.getState() instanceof Container container) {
            Inventory inv = container.getInventory();
            plr.openInventory(inv);
            event.setCancelled(true);
        }
    }
}