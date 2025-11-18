package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class PlayerItemConsumeEvent implements Listener {
    @EventHandler
    public void PlayerItemConsumeEvent(org.bukkit.event.player.PlayerItemConsumeEvent event) {
        DFMaterialEvents.ItemConsumed(event);
    }
}
