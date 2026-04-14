package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.AccessoryUI;
import uk.co.nikodem.dFSmpPlus.Player.DFUpdates.DFUpdateEvents;

public class InventoryOpenEvent implements Listener {
    @EventHandler
    public void InventoryOpenEvent(org.bukkit.event.inventory.InventoryOpenEvent event) {
        AccessoryUI.onInventoryOpen(event);
        DFUpdateEvents.onOpenContainer(event);
    }
}
