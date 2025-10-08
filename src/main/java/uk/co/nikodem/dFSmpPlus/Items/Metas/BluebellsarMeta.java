package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class BluebellsarMeta implements DFMaterialMeta {
    public void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER
        || event.getEntity().getType() == EntityType.ENDER_DRAGON
        || event.getEntity().getType() == EntityType.WITHER
        || event.getEntity().getType() == EntityType.WARDEN) {
            event.setDamage(0);
        } else {
            event.setDamage(event.getDamage() * 10);
        }
    }
}
