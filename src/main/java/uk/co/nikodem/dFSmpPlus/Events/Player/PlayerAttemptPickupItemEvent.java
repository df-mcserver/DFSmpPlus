package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.Player.DFUpdates.OnPlayerPickUpItem;

public class PlayerAttemptPickupItemEvent implements Listener {
    @EventHandler
    public void PlayerAttemptPickupItemEvent(org.bukkit.event.player.PlayerAttemptPickupItemEvent event) {
        DFMaterialEvents.ItemPickup(event);
        OnPlayerPickUpItem.OnPlayerPickUpItem(event);
    }
}
