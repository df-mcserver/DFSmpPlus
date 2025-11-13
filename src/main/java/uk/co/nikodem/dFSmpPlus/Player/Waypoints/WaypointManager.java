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
    public static final int MAX_WAYPOINTS = 5;

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

        ArmorStand waypointEntity = CreateWaypoint(plr, info.colour, location);
        if (waypointEntity == null) return WaypointCreationResult.FAILED_CREATING_WAYPOINT;

        return WaypointCreationResult.SUCCESS;
    }

    @Nullable
    public static ArmorStand CreateWaypoint(Player plr, long colour, Location location) {

        // TODO: if a waypoint api comes out, adapt this code to use that
        // this is quite possibly the hackiest code ever

        if (!location.isChunkLoaded()) {
            // TODO: manage these chunks so that we don't end up with force loaded chunks with no waypoints
            location.getChunk().setForceLoaded(true); // make sure the player gets sent the chunk or whatever
            location.getChunk().load(); // make sure to load the chunk so that the waypoint can spawn
        }

        Entity e = location.getWorld().spawnEntity(
                location, EntityType.ARMOR_STAND
        );

        try {
            if (e instanceof ArmorStand waypointEntity) {
                waypointEntity.setGravity(false);
                waypointEntity.setCustomNameVisible(false);
                waypointEntity.setInvulnerable(true);
                waypointEntity.setMarker(true);
                waypointEntity.setInvisible(true);

                waypointEntity.addScoreboardTag("waypoint");

                DFSmpPlus.hidingUtils.MakeEntityExclusiveToPlayer(plr, waypointEntity);

                // set colour of waypoint
                // this puts a message in the console, but tbh i cba to fix it
                // nor does it really impact anything
                // but there's no other way to change waypoint colours yet on paper
                // TODO: https://github.com/PaperMC/Paper/pull/12964
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getConsoleSender(),
                        String.format("minecraft:waypoint modify %s color hex %s", waypointEntity.getUniqueId(), String.format("%06X", colour))
                );

                // make sure that the waypoint transmission is set after is invisible to other players
                AttributeInstance transmit_range = waypointEntity.getAttribute(Attribute.WAYPOINT_TRANSMIT_RANGE);
                if (transmit_range != null) transmit_range.setBaseValue(5000D);

                List<ArmorStand> waypoints = activeWaypoints.computeIfAbsent(plr.getUniqueId(), k -> new ArrayList<>());
                waypoints.add(waypointEntity);
                activeWaypoints.replace(plr.getUniqueId(), waypoints);

                return waypointEntity;
            } else return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            e.remove();
            return null;
        }
    }

    public static void CleanupOnShutdown() {
        for (Map.Entry<UUID, List<ArmorStand>> entry : activeWaypoints.entrySet()) {
            List<ArmorStand> waypoints = entry.getValue();
            if (waypoints == null) return;
            for (ArmorStand waypointEntity : waypoints) {
                waypointEntity.remove();
            }
        }

        // just in case of desync lol
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                "kill @e[tag=waypoint]"
        );
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
            Location location = new Location(Bukkit.getWorld(info.worldName), info.x, info.y, info.z, info.yaw, info.pitch);
            CreateWaypoint(plr, info.colour, location);
        }
    }
}
