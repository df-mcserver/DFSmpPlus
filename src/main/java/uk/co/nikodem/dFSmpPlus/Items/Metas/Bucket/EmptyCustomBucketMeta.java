package uk.co.nikodem.dFSmpPlus.Items.Metas.Bucket;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class EmptyCustomBucketMeta extends CustomBucketCanFillMeta {
    private final String namedIdPrefix;

    public EmptyCustomBucketMeta(String namedIdPrefix) {
        super(namedIdPrefix);
        this.namedIdPrefix = namedIdPrefix;
    }

    public void BucketFillEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {
        ItemStack vanillaItemStack = event.getItemStack();
        if (vanillaItemStack == null) return;
        String nameToLookFor = namedIdPrefix + vanillaItemStack.getType().key().value();
        DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(nameToLookFor);
        event.setItemStack(newMaterial == null ? null : newMaterial.toItemStack());
    };
}
