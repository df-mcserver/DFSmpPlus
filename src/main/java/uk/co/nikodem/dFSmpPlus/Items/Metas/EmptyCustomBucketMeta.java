package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Objects;

public class EmptyCustomBucketMeta implements DFMaterialMeta {
    private final String namedIdPrefix;

    public EmptyCustomBucketMeta(String namedIdPrefix) {
        this.namedIdPrefix = namedIdPrefix;
    }

    public void BucketFillEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {
        ItemStack vanillaItemStack = event.getItemStack();
        if (vanillaItemStack == null) return;
        String nameToLookFor = namedIdPrefix + vanillaItemStack.getType().key().value();
        for (DFMaterial potentialMaterial : DFMaterial.DFMaterialIndex) {
            if (Objects.equals(potentialMaterial.getNamedId(), nameToLookFor)) {
                event.setItemStack(potentialMaterial.toItemStack());
                return;
            };
        }
    };
}
