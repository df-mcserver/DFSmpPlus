package uk.co.nikodem.dFSmpPlus.Items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class VanillaItems {
    public static void onItemUse(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        Player plr = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null) return;
        if (item.getType() == Material.FIRE_CHARGE) {
            boolean shoot = true;
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.getClickedBlock();
                if (b != null) {
                    if (b.getType().isInteractable()
                            || b.getType().equals(Material.OBSIDIAN)) {
                        shoot = false;
                    }
                }
            }
            if (plr.hasCooldown(item.getType())) shoot = false;
            if (shoot) {
                Location loc = plr.getEyeLocation();
                Vector direction = plr.getLocation().getDirection();
                Location infront = loc.add(direction);
                plr.getWorld().spawnEntity(infront, EntityType.FIREBALL);
                item.setAmount(item.getAmount() - 1);
                plr.setCooldown(item.getType(), 20);
                event.setCancelled(true);
            }
        }
    }
}
