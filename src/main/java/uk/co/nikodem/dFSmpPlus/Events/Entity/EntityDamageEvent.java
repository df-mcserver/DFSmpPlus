package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Entities.EnderDragon;

public class EntityDamageEvent implements Listener {
    @EventHandler
    public void EntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        EnderDragon.onDamaged(event);
        if (!event.getEntity().isDead()) {
            event.getEntity().getPersistentDataContainer().remove(Keys.bluebellsarDeath);
        }
    }
}
