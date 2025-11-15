package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.entity.Player;

import static uk.co.nikodem.dFSmpPlus.Player.Waypoints.DefaultWaypointAttributes.updateLocatorBar;

public class BedrockPlayers {
    public static boolean isBedrock(Player plr) {
        // TODO: request the proxy server on join, store this value and return it here
        return true;
    }

    public static boolean hasChecked(Player plr) {
        // TODO: proxy has been requested and a value is stored
        return true;
    }

    public static void onCheck(Player plr, boolean isBedrock) {
        updateLocatorBar(plr, isBedrock);
    }
}
