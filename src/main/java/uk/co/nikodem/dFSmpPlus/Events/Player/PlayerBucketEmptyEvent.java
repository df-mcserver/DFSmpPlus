package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class PlayerBucketEmptyEvent implements Listener {
    @EventHandler
    public void PlayerBucketEmptyEvent(org.bukkit.event.player.PlayerBucketEmptyEvent event) {
        DFMaterialEvents.BucketEmptyEvent(event);
    }
}
