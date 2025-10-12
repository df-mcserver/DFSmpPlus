package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.OnJoin;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void PlayerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        OnJoin.OnJoin(event);
    }
}
