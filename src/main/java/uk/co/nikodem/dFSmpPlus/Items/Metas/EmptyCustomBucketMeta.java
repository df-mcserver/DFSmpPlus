package uk.co.nikodem.dFSmpPlus.Items.Metas;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Objects;

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
