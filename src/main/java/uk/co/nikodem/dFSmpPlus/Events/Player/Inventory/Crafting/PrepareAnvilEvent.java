package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.AnvilEvents;

public class PrepareAnvilEvent implements Listener {
    @EventHandler
    public void PrepareAnvilEvent(org.bukkit.event.inventory.PrepareAnvilEvent event) {
        AnvilEvents.onAnvilEvent(event);
    }
}
