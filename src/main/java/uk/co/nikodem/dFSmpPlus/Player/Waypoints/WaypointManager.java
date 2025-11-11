package uk.co.nikodem.dFSmpPlus.Player.Waypoints;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaypointManager {
    public static final int MAX_WAYPOINTS = 10;

    public static final HashMap<Player, List<ArmorStand>> activeWaypoints = new HashMap<>();

    @Nullable
    public static ArmorStand CreateWaypointEntity(Location location) {
        Entity e = location.getWorld().spawnEntity(
                location, EntityType.ARMOR_STAND
        );

        if (e instanceof ArmorStand waypointEntity) {
            waypointEntity.setGravity(false);
            waypointEntity.setCustomNameVisible(false);
            waypointEntity.setInvulnerable(true);
            waypointEntity.setMarker(true);
            waypointEntity.setInvisible(true);

            return waypointEntity;
        } else return null;
    }

    public static WaypointCreationResult CreateWaypoint(Player plr, Location location, String id) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        if (data.waypoints.containsKey(id)) return WaypointCreationResult.FAILED_ALREADY_EXISTS;
        if (data.waypoints.size() >= MAX_WAYPOINTS) return WaypointCreationResult.FAILED_REACHED_MAXIMUM;

        ArmorStand waypointEntity = CreateWaypointEntity(location);
        if (waypointEntity == null) return WaypointCreationResult.FAILED_CREATING_WAYPOINT;
        DFSmpPlus.hidingUtils.MakeEntityExclusiveToPlayer(plr, waypointEntity);

        // make sure that the waypoint transmission is set after is invisible to other players
        AttributeInstance transmit_range = waypointEntity.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);
        if (transmit_range != null) transmit_range.setBaseValue(5000D);

        WaypointInformation info = new WaypointInformation();
        info.x = location.getX();
        info.y = location.getY();
        info.z = location.getZ();
        info.yaw = location.getYaw();
        info.pitch = location.getPitch();
        data.waypoints.put(id, info);

        DFSmpPlus.playerDataHandler.writePlayerData(plr, data);

        List<ArmorStand> waypoints = activeWaypoints.computeIfAbsent(plr, k -> new ArrayList<>());
        waypoints.add(waypointEntity);
        activeWaypoints.replace(plr, waypoints);

        return WaypointCreationResult.SUCCESS;
    }

    public static void CleanupOnLeave(Player plr) {
        List<ArmorStand> waypoints = activeWaypoints.get(plr);
        if (waypoints == null) return;
        for (ArmorStand waypointEntity : waypoints) {
            waypointEntity.remove();
        }

        activeWaypoints.remove(plr);
    }

    public static void CreateOnJoin(Player plr) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        for (Map.Entry<String, WaypointInformation> waypointEntry : data.waypoints.entrySet()) {
            String id = waypointEntry.getKey();
            WaypointInformation info = waypointEntry.getValue();

            Location location = new Location(Bukkit.getWorld(info.worldName), info.x, info.y, info.z, info.yaw, info.pitch);
            CreateWaypoint(plr, location, id);
        }
    }
}
