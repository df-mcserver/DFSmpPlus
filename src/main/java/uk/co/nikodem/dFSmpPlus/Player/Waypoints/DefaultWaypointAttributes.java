package uk.co.nikodem.dFSmpPlus.Player.Waypoints;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;

public class DefaultWaypointAttributes {
    public static void applyDefaults(Player plr) {
        AttributeInstance transmitRange = plr.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);
        if (transmitRange != null) transmitRange.setBaseValue(0D); // doesn't broadcast a waypoint on the locator bar

        AttributeInstance receiveRange = plr.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE);
        if (receiveRange != null) receiveRange.setBaseValue(0D);

        updateLocatorBar(plr);
    }

    public static void updateLocatorBar(Player plr) {
        // not show locator bar if they're on bedrock or not yet checked
        updateLocatorBar(plr, !BedrockPlayers.hasChecked(plr) || BedrockPlayers.isBedrock(plr));
    }

    public static void updateLocatorBar(Player plr, boolean isBedrock) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        AttributeInstance receiveRange = plr.getAttribute(Attribute.WAYPOINT_RECEIVE_RANGE);
        if (receiveRange != null) {
            receiveRange.setBaseValue(data.locatorBarEnabled && !isBedrock ? WaypointManager.MAX_DISTANCE : 0D);
        }
    }
}
