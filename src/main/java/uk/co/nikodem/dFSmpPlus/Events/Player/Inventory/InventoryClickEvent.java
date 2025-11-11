package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.AnvilEvents;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.SmithingTableEvents;
import uk.co.nikodem.dFSmpPlus.SetBonuses.SetBonusText;

public class InventoryClickEvent implements Listener {
    @EventHandler
    public void InventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent event) {
        SetBonusText.onInventoryClick(event);
        AnvilEvents.onAnvilInventoryClick(event);
        SmithingTableEvents.onSmithingTableCraft(event);
    }
}
