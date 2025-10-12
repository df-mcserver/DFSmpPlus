package uk.co.nikodem.dFSmpPlus.Events.Player.Block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class BlockBreakProgressUpdateEvent implements Listener {
    @EventHandler
    public void BlockBreakProgressUpdateEvent(io.papermc.paper.event.block.BlockBreakProgressUpdateEvent event) {
        DFMaterialEvents.ItemStartMining(event);
    }
}
