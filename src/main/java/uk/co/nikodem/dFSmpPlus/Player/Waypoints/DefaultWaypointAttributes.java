package uk.co.nikodem.dFSmpPlus.Player.Waypoints;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class DefaultWaypointAttributes {
    public static void applyDefaults(Player plr) {
        AttributeInstance transmitRange = plr.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);
        if (transmitRange != null) transmitRange.setBaseValue(0D);

        AttributeInstance receiveRange = plr.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE);
        if (receiveRange != null) receiveRange.setBaseValue(5000D);
    }
}
