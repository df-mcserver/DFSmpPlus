package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

import static uk.co.nikodem.dFSmpPlus.Player.Waypoints.DefaultWaypointAttributes.updateLocatorBar;

// is practically useless at the moment, used to prevent waypoints but they are now fully functioning
public class BedrockPlayers {
    public static HashMap<UUID, Boolean> checkedPlayers = new HashMap<>();

    @Nullable
    public static Boolean isBedrock(Player plr) {
        return checkedPlayers.get(plr.getUniqueId());
    }

    public static boolean hasChecked(Player plr) {
        return checkedPlayers.containsKey(plr.getUniqueId());
    }

    public static void onCheck(Player plr, boolean isBedrock) {
        updateLocatorBar(plr, isBedrock);
    }

    public static void doCheck(Player plr, boolean isBedrock) {
        checkedPlayers.put(plr.getUniqueId(), isBedrock);

        onCheck(plr, isBedrock);
    }

    public static void onLeave(Player plr) {
        checkedPlayers.remove(plr.getUniqueId());
    }
}
