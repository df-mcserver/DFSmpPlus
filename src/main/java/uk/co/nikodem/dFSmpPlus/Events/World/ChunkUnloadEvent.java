package uk.co.nikodem.dFSmpPlus.Events.World;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointEventHandler;

public class ChunkUnloadEvent implements Listener {
    @EventHandler
    public void ChunkUnloadEvent(org.bukkit.event.world.ChunkUnloadEvent event) {
        WaypointEventHandler.onChunkUnload(event);
    }
}
