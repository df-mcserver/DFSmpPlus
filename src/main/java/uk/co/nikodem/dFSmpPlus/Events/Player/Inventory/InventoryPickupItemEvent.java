package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.EnderEggStoragePreventer;

public class InventoryPickupItemEvent implements Listener {
    @EventHandler
    public void InventoryPickupItemEvent (org.bukkit.event.inventory.InventoryPickupItemEvent event) {
        EnderEggStoragePreventer.onInventoryPickupItem(event);
    }
}
