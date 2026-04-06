package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Objects;

public class MilkCustomBucketMeta implements DFMaterialMeta {
    private final String emptyBucketNamedId;

    public MilkCustomBucketMeta(String emptyBucketNamedId) {
        this.emptyBucketNamedId = emptyBucketNamedId;
    }

    public void ItemConsumed(Player plr, DFMaterial material, ItemStack item, PlayerItemConsumeEvent event) {
        DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(emptyBucketNamedId);
        event.setReplacement(newMaterial == null ? null : newMaterial.toItemStack());
    };
}
