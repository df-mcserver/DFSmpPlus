package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Grindstone.GrindstoneEvents;

public class PrepareGrindstoneEvent implements Listener {
    @EventHandler
    public void PrepareGrindstoneEvent(org.bukkit.event.inventory.PrepareGrindstoneEvent event) {
        GrindstoneEvents.onGrindstoneEvent(event);
    }
}
