package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

public class EntityDamageEvent implements Listener {
    @EventHandler
    public void EntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        if (!event.getEntity().isDead()) {
            event.getEntity().getPersistentDataContainer().remove(Keys.bluebellsarDeath);
        }
    }
}
