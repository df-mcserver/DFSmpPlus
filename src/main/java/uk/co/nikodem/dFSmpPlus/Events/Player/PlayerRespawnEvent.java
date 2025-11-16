package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.Combat.WorldRecordAdvancementHandler;

public class PlayerRespawnEvent implements Listener {
    @EventHandler
    public void PlayerRespawnEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        WorldRecordAdvancementHandler.onSpawn(event);
    }
}
