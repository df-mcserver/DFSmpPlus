package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Objects;

public class EmptyEntityBucketMeta implements DFMaterialMeta {
    private final String cleaningBucketNamedId;
    private final String storingBucketNamedId;

    public EmptyEntityBucketMeta(String cleaningBucketNamedId, String storingBucketNamedId) {
        this.cleaningBucketNamedId = cleaningBucketNamedId;
        this.storingBucketNamedId = storingBucketNamedId;
    }

    @Override
    public void BucketFillEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {
        Block block = event.getBlockClicked();
        if (block.getType() == Material.WATER) {
            DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(cleaningBucketNamedId);
            event.setItemStack(newMaterial == null ? null : newMaterial.toItemStack());
        } else if (block.getType() == Material.LAVA) {
            if (storingBucketNamedId == null) {
                event.setItemStack(ItemStack.of(Material.COPPER_INGOT));
                plr.getLocation().getBlock().setType(Material.LAVA);
            } else {
                DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(storingBucketNamedId);
                event.setItemStack(newMaterial == null ? null : newMaterial.toItemStack());
            }
        } else {
            event.setCancelled(true);
        }
    }
}
