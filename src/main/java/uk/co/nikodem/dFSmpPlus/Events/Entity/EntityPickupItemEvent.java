package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Entities.OnEntityPickUpItem;

public class EntityPickupItemEvent implements Listener {
    @EventHandler
    public void EntityPickupItemEvent(org.bukkit.event.entity.EntityPickupItemEvent event) {
        OnEntityPickUpItem.OnEntityPickUpItem(event);
    }
}
