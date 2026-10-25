package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.DialogManager;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointDialog;

public class PlayerCustomClickEvent implements Listener {
    @EventHandler
    public void PlayerCustomClickEvent(io.papermc.paper.event.player.PlayerCustomClickEvent event) {
        DialogManager.onClick(event);
        WaypointDialog.onClick(event);
    }
}
