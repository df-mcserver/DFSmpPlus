package uk.co.nikodem.dFSmpPlus.Events.Block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Blocks.CustomDrops;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class BlockBreakEvent implements Listener {
    @EventHandler
    public void BlockBreakEvent(org.bukkit.event.block.BlockBreakEvent event) {
        AccessoryEvents.BlockMined(event);
        DFMaterialEvents.ItemMine(event);
        CustomDrops.onBlockBreak(event);
    }
}
