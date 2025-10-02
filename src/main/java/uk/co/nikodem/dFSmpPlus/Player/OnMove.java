package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnMove implements Listener {
    @EventHandler
    public void OnMove(PlayerMoveEvent e) {
        if (FishPlayer.isFishPlayer(e.getPlayer())) {
            FishPlayer.onMove((e.getPlayer()));
        }
    }
}
