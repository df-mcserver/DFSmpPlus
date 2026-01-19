package uk.co.nikodem.dFSmpPlus.Events.Block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;

public class BlockPlaceEvent implements Listener {
    @EventHandler
    public void BlockPlaceEvent(org.bukkit.event.block.BlockPlaceEvent event) {
        DFMaterialEvents.ItemPlaced(event);
        TelemetryUtils.increaseBlocks(event);
    }
}
