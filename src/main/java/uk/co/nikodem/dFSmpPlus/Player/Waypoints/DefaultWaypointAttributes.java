package uk.co.nikodem.dFSmpPlus.Player.Waypoints;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;

public class DefaultWaypointAttributes {
    public static void applyDefaults(Player plr) {
        AttributeInstance transmitRange = plr.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);
        if (transmitRange != null) transmitRange.setBaseValue(0D); // doesn't broadcast a waypoint on the locator bar

        updateLocatorBar(plr);
    }

    public static void updateLocatorBar(Player plr) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        AttributeInstance receiveRange = plr.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE);
        if (receiveRange != null) {
            receiveRange.setBaseValue(data.locatorBarEnabled ? WaypointManager.MAX_DISTANCE : 0D);
        }
    }
}
