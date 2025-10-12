package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class PlayerInteractEvent implements Listener {
    @EventHandler
    public void PlayerInteractEvent(org.bukkit.event.player.PlayerInteractEvent event) {
        DFMaterialEvents.ItemUse(event);
    }
}
