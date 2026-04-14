package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.event.entity.EntityPickupItemEvent;
import uk.co.nikodem.dFSmpPlus.Player.DFUpdates.DFUpdateEvents;

public class OnEntityPickUpItem {
    public static void OnEntityPickUpItem(EntityPickupItemEvent e) {
        DFUpdateEvents.onItemPickup(e);
    }
}
