package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.AnvilEvents;

public class InventoryClickEvent implements Listener {
    @EventHandler
    public void InventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent event) {
        AnvilEvents.onAnvilInventoryClick(event);
    }
}
