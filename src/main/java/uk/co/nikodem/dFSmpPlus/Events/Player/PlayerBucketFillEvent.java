package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class PlayerBucketFillEvent implements Listener {
    @EventHandler
    public void PlayerBucketFillEvent(org.bukkit.event.player.PlayerBucketFillEvent event) {
        DFMaterialEvents.BucketUseEvent(event);
    }
}
