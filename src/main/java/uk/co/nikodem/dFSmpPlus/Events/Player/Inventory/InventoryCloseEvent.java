package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.BackpackMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.AccessoryUI;
import uk.co.nikodem.dFSmpPlus.Commands.BasicCommands.BinCommand;

public class InventoryCloseEvent implements Listener {
    @EventHandler
    public void InventoryCloseEvent(org.bukkit.event.inventory.InventoryCloseEvent event) {
        AccessoryUI.onInventoryClose(event);
        BackpackMeta.onInventoryClose(event);
        BinCommand.onInventoryClose(event);
    }
}
