package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;

public class OnLeave {
    public static void OnLeave(PlayerQuitEvent e) {
        Player plr = e.getPlayer();
        WaypointManager.CleanupOnLeave(plr);

        BedrockPlayers.onLeave(plr);

        if (CombatLoggingManager.isInCombat(plr)) {
            if (!plr.getGameMode().isInvulnerable()) plr.setHealth(0D);
        }
    }
}
