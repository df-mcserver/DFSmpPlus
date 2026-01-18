package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;

public class EntityTargetEvent implements Listener {
    @EventHandler
    public void EntityTargetEvent(org.bukkit.event.entity.EntityTargetEvent event) {
        AccessoryEvents.UserTargetted(event);
    }
}
