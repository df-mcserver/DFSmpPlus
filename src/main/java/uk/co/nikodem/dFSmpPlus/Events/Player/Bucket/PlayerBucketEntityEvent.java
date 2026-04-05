package uk.co.nikodem.dFSmpPlus.Events.Player.Bucket;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class PlayerBucketEntityEvent implements Listener {
    @EventHandler
    public void PlayerBucketEntityEvent(org.bukkit.event.player.PlayerBucketEntityEvent event) {
        DFMaterialEvents.BucketEntityEvent(event);
    }
}
