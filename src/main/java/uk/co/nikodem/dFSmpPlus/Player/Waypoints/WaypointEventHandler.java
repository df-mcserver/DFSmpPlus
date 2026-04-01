package uk.co.nikodem.dFSmpPlus.Player.Waypoints;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WaypointEventHandler {
    public static void onChunkUnload(ChunkUnloadEvent event) {
        World world = event.getWorld();
        Chunk chunk = event.getChunk();

        boolean containsWaypoints = WaypointManager.killGhostWaypointsInChunk(chunk);

        if (containsWaypoints) {
            debugmsg("<red>Add chunk "+chunk.getChunkKey()+" to list");
            List<Long> chunks = WaypointManager.unloadedChunksWithWaypoints.getOrDefault(world.getKey(), new ArrayList<>());
            chunks.add(chunk.getChunkKey());
            if (WaypointManager.unloadedChunksWithWaypoints.containsKey(world.getKey())) WaypointManager.unloadedChunksWithWaypoints.replace(world.getKey(), chunks);
            else WaypointManager.unloadedChunksWithWaypoints.put(world.getKey(), chunks);
        }
    }

    public static void onChunkLoad(ChunkLoadEvent event) {
        World world = event.getWorld();
        Chunk chunk = event.getChunk();

        WaypointManager.killGhostWaypointsInChunk(chunk);

        List<Long> chunks = WaypointManager.unloadedChunksWithWaypoints.get(world.getKey());
        if (chunks == null) return;
        chunks.remove(chunk.getChunkKey());
        WaypointManager.unloadedChunksWithWaypoints.replace(world.getKey(), chunks);
        debugmsg("<green>Loaded chunk "+chunk.getChunkKey());
    }

    public static void onPerSecond() {
        for (Map.Entry<NamespacedKey, List<Long>> entry : WaypointManager.unloadedChunksWithWaypoints.entrySet()) {
            World world = Bukkit.getWorld(entry.getKey());
            if (world == null) continue;
            for (Long chunkKey : entry.getValue()) {
                debugmsg("<cyan>Attempting to load chunk "+chunkKey);
                Chunk chunk = world.getChunkAt(chunkKey);
                world.loadChunk(chunk);
            }
        }
    }

    public static void debugmsg(String msg) {
        Bukkit.getOnlinePlayers().forEach((plr) -> plr.sendMessage(MiniMessage.miniMessage().deserialize(msg)));
        DFSmpPlus.getPlugin().getLogger().info(msg);
    }
}
