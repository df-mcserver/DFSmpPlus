package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.VacuumAccessoryMeta;

public class EntityDropItemEvent implements Listener {
    @EventHandler
    public void EntityDropItemEvent(org.bukkit.event.entity.EntityDropItemEvent event) {
        VacuumAccessoryMeta.ItemSpawned(event);
    }
}
