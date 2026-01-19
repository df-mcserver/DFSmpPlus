package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class PreventPlacingMeta implements DFMaterialMeta {
    @Override
    public void ItemPlaced(Player plr, DFMaterial material, ItemStack item, BlockPlaceEvent event) {
        event.setCancelled(true);
    };
}
