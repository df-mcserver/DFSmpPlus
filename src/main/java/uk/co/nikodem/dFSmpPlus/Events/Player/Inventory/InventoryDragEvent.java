package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.EnderEggStoragePreventer;

public class InventoryDragEvent implements Listener {
    @EventHandler
    public void InventoryDragEvent(org.bukkit.event.inventory.InventoryDragEvent event) {
        EnderEggStoragePreventer.onInventoryDrag(event);
    }
}
