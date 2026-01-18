package uk.co.nikodem.dFSmpPlus.Items.Metas.VanillaItems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class FireChargeMeta implements DFMaterialMeta {
    @Override
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        if (item == null) return;
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
            Vector direction = plr.getLocation().getDirection();
            plr.launchProjectile(Fireball.class, direction);
            item.setAmount(item.getAmount() - 1);
            plr.setCooldown(item.getType(), 20);
            event.setCancelled(true);
        }
    }
}
