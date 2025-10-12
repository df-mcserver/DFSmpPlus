package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.DFUpdates.OnOpenContainer;

public class InventoryOpenEvent implements Listener {
    public void InventoryOpenEvent(org.bukkit.event.inventory.InventoryOpenEvent event) {
        OnOpenContainer.OnOpenContainer(event);
    }
}
