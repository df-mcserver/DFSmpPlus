package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.OnJoin;

public class PlayerChannelEvent implements Listener {
    @EventHandler
    public void PlayerChannelEvent(org.bukkit.event.player.PlayerChannelEvent event) {
        OnJoin.OnInitChannels(event);
    }
}
