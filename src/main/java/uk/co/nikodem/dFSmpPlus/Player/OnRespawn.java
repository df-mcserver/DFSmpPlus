package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnRespawn implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        FishPlayer.oxygenAmounts.put(event.getPlayer().getUniqueId(), 300);

        if (FishPlayer.isFishPlayer(event.getPlayer())) FishPlayer.onFishRespawn(event.getPlayer());
    }
}
