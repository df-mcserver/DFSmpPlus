package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.TpaPreventionDuringCombat;

public class PlayerCommandPreprocessEvent implements Listener {
    @EventHandler
    public void PlayerCommandPreprocessEvent(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
        TpaPreventionDuringCombat.onRunCommand(event);
    }
}
