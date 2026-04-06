package uk.co.nikodem.dFSmpPlus.Items.Metas;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Objects;

public class FilledCustomBucketMeta implements DFMaterialMeta {
    private final String namedIdPrefix;

    public FilledCustomBucketMeta(String namedIdPrefix) {
        this.namedIdPrefix = namedIdPrefix;
    }

    public void BucketEmptyEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEmptyEvent event) {
        DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(namedIdPrefix+"bucket");
        if (newMaterial != null) {
            ItemMeta meta = item.getItemMeta();
            // make sure that the name is reset, so any entities summoned don't take the name of the item
            Component customName = meta.customName();
            if (customName != null) if (customName.equals(material.getDisplayName())) meta.customName(null);
            item.setItemMeta(meta);
            event.setItemStack(newMaterial.toItemStack());
        }
    }

    public void BucketEntityEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEntityEvent event) {
        ItemStack vanillaItemStack = event.getEntityBucket();
        String nameToLookFor = namedIdPrefix + vanillaItemStack.getType().key().value();

        DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(nameToLookFor);
        if (newMaterial != null) {
            ItemMeta currentMeta = vanillaItemStack.getItemMeta();
            ItemMeta baseMeta = newMaterial.toItemStack().getItemMeta();

            currentMeta.setItemModel(baseMeta.getItemModel());
            if (!currentMeta.hasCustomName()) currentMeta.customName(baseMeta.customName());
            Integer dfmaterialversion = baseMeta.getPersistentDataContainer().get(Keys.dfmaterialVersion, PersistentDataType.INTEGER);
            if (dfmaterialversion != null) currentMeta.getPersistentDataContainer().set(Keys.dfmaterialVersion, PersistentDataType.INTEGER, dfmaterialversion);

            currentMeta.getPersistentDataContainer().set(Keys.dfmaterial, PersistentDataType.STRING, nameToLookFor);

            Boolean markeduuid = baseMeta.getPersistentDataContainer().get(Keys.markedForUUID, PersistentDataType.BOOLEAN);
            if (markeduuid != null) currentMeta.getPersistentDataContainer().set(Keys.markedForUUID, PersistentDataType.BOOLEAN, markeduuid);

            vanillaItemStack.setItemMeta(currentMeta);
            return;
        }
    };
}
