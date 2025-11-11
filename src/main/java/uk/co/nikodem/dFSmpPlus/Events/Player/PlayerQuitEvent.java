package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.OnLeave;

public class PlayerQuitEvent implements Listener {
    @EventHandler
    public void PlayerQuitEvent(org.bukkit.event.player.PlayerQuitEvent event) {
        OnLeave.OnLeave(event);
    }
}
