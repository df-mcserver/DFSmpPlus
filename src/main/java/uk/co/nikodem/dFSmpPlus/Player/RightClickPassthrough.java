package uk.co.nikodem.dFSmpPlus.Player;

import com.destroystokyo.paper.MaterialTags;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import io.papermc.paper.event.player.PlayerOpenSignEvent;
import org.bukkit.FeatureFlag;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class RightClickPassthrough {
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

    public static void onSignOpen(PlayerOpenSignEvent event) {
        Player plr = event.getPlayer();
        Sign sign = event.getSign();

        if (sign instanceof Directional directional) {
            if (plr.isSneaking() || !sign.isPlaced()) return;
            BlockFace facing = directional.getFacing();
            Block restingBlock = facing.getOppositeFace().getDirection().add(event.getSign().getLocation().toVector()).toLocation(sign.getWorld()).getBlock();

            if (restingBlock.getState() instanceof Container container) {
                Inventory inv = container.getInventory();
                plr.openInventory(inv);
                event.setCancelled(true);
            }
        }
    }
}