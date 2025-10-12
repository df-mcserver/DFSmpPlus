package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Crafting.OnCraft;

public class CraftItemEvent implements Listener {
    @EventHandler
    public void CraftItemEvent(org.bukkit.event.inventory.CraftItemEvent event) {
        OnCraft.OnCraft(event);
    }
}
