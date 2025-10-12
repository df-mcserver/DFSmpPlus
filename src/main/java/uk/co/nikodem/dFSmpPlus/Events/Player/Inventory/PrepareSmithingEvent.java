package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.SmithingTableEvents;

public class PrepareSmithingEvent implements Listener {
    @EventHandler
    public void PrepareSmithingEvent(org.bukkit.event.inventory.PrepareSmithingEvent event) {
        SmithingTableEvents.onSmithingTableEvent(event);
    }
}
