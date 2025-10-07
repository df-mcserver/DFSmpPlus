package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Constants.PointyStick;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Map;

public class PointyStickMeta implements DFMaterialMeta {
    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {

        Block block = event.getBlock();
        Location loc = block.getLocation();

        for (Map.Entry<Material, Map.Entry<Material, Material>> potential : PointyStick.PointyStick.entrySet()) {
            if (potential.getKey() == block.getType()) {
                event.setCancelled(true);
                block.setType(potential.getValue().getKey());
                block.getWorld().dropItemNaturally(loc, ItemStack.of(potential.getValue().getValue()));
                loc.getWorld().playSound(loc, Sound.BLOCK_ANVIL_PLACE, 1F, 1.75F);
                tool.setAmount(tool.getAmount() - 1);
                break;
            }
        }
    }
}
