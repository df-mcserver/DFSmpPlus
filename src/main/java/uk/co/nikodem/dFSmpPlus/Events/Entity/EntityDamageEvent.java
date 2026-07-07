package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Constants.DamageCauseGroups;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Entities.EnderDragon;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetEvents;
import uk.co.nikodem.dFSmpPlus.SetBonuses.Metas.FireImmunityMeta;

public class EntityDamageEvent implements Listener {
    @EventHandler
    public void EntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        EnderDragon.onDamaged(event);
        AccessoryEvents.UserDamaged(event);
        DFArmourSetEvents.PlayerDamaged(event);
        if (!event.getEntity().isDead()) {
            event.getEntity().getPersistentDataContainer().remove(Keys.bluebellsarDeath);
        }

        if (event.getEntity() instanceof Item item) {
            ItemStack base = item.getItemStack();
            DFMaterial material = DFItemUtils.getDFMaterial(base);

            if (material != null) {
                if (material.canSurviveLava() && DamageCauseGroups.hotDamageCauses.contains(event.getCause())) {
                    if (material.getNamedId().equals(DFMaterial.NetheriteTintedElytra.getNamedId())
                    || material.getNamedId().equals(DFMaterial.ObsidianTintedElytra.getNamedId())) {
                        // breaks the tinted elytras but does not remove the item
                        Damageable damageable = (Damageable) base.getItemMeta();
                        damageable.setDamage(damageable.getMaxDamage());

                        base.setItemMeta(damageable);
                        item.setItemStack(base);
                    }

                    event.setCancelled(true);
                    item.setFireTicks(0);
                }
            }
        }
    }
}
