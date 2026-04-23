package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Entities.ItemFrameRightClickOnChests;

public class PlayerItemFrameChangeEvent implements Listener {
    @EventHandler
    public void PlayerItemFrameChangeEvent(io.papermc.paper.event.player.PlayerItemFrameChangeEvent event) {
        ItemFrameRightClickOnChests.onInteractWithEntity(event);
    }
}
