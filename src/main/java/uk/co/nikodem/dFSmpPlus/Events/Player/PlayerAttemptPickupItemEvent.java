package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class PlayerAttemptPickupItemEvent implements Listener {
    @EventHandler
    public void PlayerAttemptPickupItemEvent(org.bukkit.event.player.PlayerAttemptPickupItemEvent event) {
        AccessoryEvents.ItemPickup(event);
        DFMaterialEvents.ItemPickup(event);
    }
}
