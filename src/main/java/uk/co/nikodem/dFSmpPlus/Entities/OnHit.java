package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.Metas.TargetDummyMeta;

public class OnHit implements Listener {
    @EventHandler
    public void OnHit(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
            if (event.getEntity().getPersistentDataContainer().has(Keys.targetDummy))
                TargetDummyMeta.TargetDummyHit(event);
        }
    }
}
