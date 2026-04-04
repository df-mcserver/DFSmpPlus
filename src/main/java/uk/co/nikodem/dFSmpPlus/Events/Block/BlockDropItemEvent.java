package uk.co.nikodem.dFSmpPlus.Events.Block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Blocks.CustomDrops;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;

public class BlockDropItemEvent implements Listener {
    @EventHandler
    public void BlockDropItemEvent(org.bukkit.event.block.BlockDropItemEvent event) {
        DFMaterialEvents.ItemMinedBlockDropItem(event);
        AccessoryEvents.MinedBlockDropItem(event);
        CustomDrops.onBlockDropEvent(event);
    }
}
