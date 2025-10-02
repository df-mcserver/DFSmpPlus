package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Map;

public class AutoSmeltingMeta implements DFMaterialMeta {
    public final Map<Material, Material> list;

    public AutoSmeltingMeta(Map<Material, Material> list) {
        this.list = list;
    }

    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {
        Block b = event.getBlock();

        if (list.containsKey(b.getType())) {
            if (!DFItemUtils.hasFireAspect(tool)) return; // if you remove fireaspect, autosmelting no work
            // is auto smeltable
            Material drop = list.get(b.getType());

            event.setDropItems(false);

            b.getWorld().playSound(plr, Sound.BLOCK_FIRE_EXTINGUISH, 0.3F, 1F);

            for (ItemStack a : b.getDrops(tool)) {
                ItemStack newDrop = new ItemStack(drop);
                newDrop.setAmount(a.getAmount());
                plr.getWorld().dropItemNaturally(b.getLocation().add(new Location(b.getWorld(), 0.5, 0.5, 0.5)), newDrop);
            }
        }
    }
}
