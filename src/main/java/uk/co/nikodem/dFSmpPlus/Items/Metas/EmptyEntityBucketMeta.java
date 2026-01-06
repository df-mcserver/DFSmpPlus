package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class EmptyEntityBucketMeta implements DFMaterialMeta {
    @Override
    public void BucketFillEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {
        Block block = event.getBlockClicked();
        if (block.getType() == Material.WATER) {
            event.setItemStack(DFMaterial.CleaningEntityBucket.toItemStack());
//            DFMaterial convertingMaterial = DFItemUtils.getDFMaterial("cleaning_entity_bucket");
//            if (convertingMaterial == null) event.setCancelled(true);
//            else event.setItemStack(convertingMaterial.toItemStack());
        } else if (block.getType() == Material.LAVA) {
            event.setItemStack(DFMaterial.StoringEntityBucket.toItemStack());
        } else {
            event.setCancelled(true);
        }
    }
}
