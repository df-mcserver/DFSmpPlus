package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.RightClickPassthrough;

public class PlayerOpenSignEvent implements Listener {
    @EventHandler
    public void PlayerOpenSignEvent(io.papermc.paper.event.player.PlayerOpenSignEvent event) {
        RightClickPassthrough.onSignOpen(event);
    }
}
