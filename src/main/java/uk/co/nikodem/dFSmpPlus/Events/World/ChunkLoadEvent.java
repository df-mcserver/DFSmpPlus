package uk.co.nikodem.dFSmpPlus.Events.World;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointEventHandler;

public class ChunkLoadEvent implements Listener {
    @EventHandler
    public void ChunkLoadEvent(org.bukkit.event.world.ChunkLoadEvent event) {
        WaypointEventHandler.onChunkLoad(event);
    }
}
