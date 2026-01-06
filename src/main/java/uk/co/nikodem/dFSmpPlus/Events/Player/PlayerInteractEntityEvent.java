package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.Player.EnderEggStoragePreventer;

public class PlayerInteractEntityEvent implements Listener {
    @EventHandler
    public void PlayerInteractEntityEvent(org.bukkit.event.player.PlayerInteractEntityEvent event) {
        EnderEggStoragePreventer.onItemFramePrevention(event);
        DFMaterialEvents.ItemUseOnEntity(event);
    }
}
