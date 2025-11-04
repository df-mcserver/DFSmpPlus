package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory.Crafting;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.MergingCraftingTable.CraftingTableEvents;

public class PrepareItemCraftEvent implements Listener {
    @EventHandler
    public void PrepareItemCraftEvent(org.bukkit.event.inventory.PrepareItemCraftEvent event) {
        CraftingTableEvents.onCraftingTableEvent(event);
    }
}
