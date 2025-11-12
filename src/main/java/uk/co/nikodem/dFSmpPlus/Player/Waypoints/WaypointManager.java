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
import java.util.*;

public class WaypointManager {
    public static final int MAX_WAYPOINTS = 10;

    public static final HashMap<UUID, List<ArmorStand>> activeWaypoints = new HashMap<>();

    public static WaypointCreationResult CreateNewWaypoint(Player plr, Location location, String id, long colour) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        if (data.waypoints.containsKey(id)) return WaypointCreationResult.FAILED_ALREADY_EXISTS;
        if (data.waypoints.size() >= MAX_WAYPOINTS) return WaypointCreationResult.FAILED_REACHED_MAXIMUM;

        WaypointInformation info = new WaypointInformation();
        info.worldName = location.getWorld().getName();
        info.x = location.getX();
        info.y = location.getY();
        info.z = location.getZ();
        info.yaw = location.getYaw();
        info.pitch = location.getPitch();
        info.colour = colour;
        data.waypoints.put(id, info);

        DFSmpPlus.playerDataHandler.writePlayerData(plr, data);

        ArmorStand waypointEntity = CreateWaypoint(plr, info);
        if (waypointEntity == null) return WaypointCreationResult.FAILED_CREATING_WAYPOINT;

        List<ArmorStand> waypoints = activeWaypoints.computeIfAbsent(plr.getUniqueId(), k -> new ArrayList<>());
        waypoints.add(waypointEntity);
        activeWaypoints.replace(plr.getUniqueId(), waypoints);

        return WaypointCreationResult.SUCCESS;
    }

    @Nullable
    public static ArmorStand CreateWaypoint(Player plr, WaypointInformation info) {
        Location location = new Location(Bukkit.getWorld(info.worldName), info.x, info.y, info.z, info.yaw, info.pitch);

        Entity e = location.getWorld().spawnEntity(
                location, EntityType.ARMOR_STAND
        );

        if (e instanceof ArmorStand waypointEntity) {
            waypointEntity.setGravity(false);
            waypointEntity.setCustomNameVisible(false);
            waypointEntity.setInvulnerable(true);
            waypointEntity.setMarker(true);
            waypointEntity.setInvisible(true);

            DFSmpPlus.hidingUtils.MakeEntityExclusiveToPlayer(plr, waypointEntity);

            // make sure that the waypoint transmission is set after is invisible to other players
            AttributeInstance transmit_range = waypointEntity.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);
            if (transmit_range != null) transmit_range.setBaseValue(5000D);

            // set colour of waypoint
            // this puts a message in the console, but tbh i cba to fix it
            // nor does it really impact anything
            // but there's no other way to change waypoint colours yet on paper
            // TODO: https://github.com/PaperMC/Paper/pull/12964
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    String.format("waypoint modify %s color hex %s", waypointEntity.getUniqueId(), String.format("%06X", info.colour))
            );

            return waypointEntity;
        } else return null;
    }

    public static void CleanupOnLeave(Player plr) {
        List<ArmorStand> waypoints = activeWaypoints.get(plr.getUniqueId());
        if (waypoints == null) return;
        for (ArmorStand waypointEntity : waypoints) {
            waypointEntity.remove();
        }

        activeWaypoints.remove(plr.getUniqueId());
    }

    public static void CreateOnJoin(Player plr) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        for (Map.Entry<String, WaypointInformation> waypointEntry : data.waypoints.entrySet()) {
            String id = waypointEntry.getKey();
            WaypointInformation info = waypointEntry.getValue();
            DFSmpPlus.getProvidingPlugin(DFSmpPlus.class).getLogger().info(Objects.requireNonNull(CreateWaypoint(plr, info)).toString());
        }
    }
}
