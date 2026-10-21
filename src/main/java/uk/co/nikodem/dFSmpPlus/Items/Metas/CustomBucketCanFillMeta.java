package uk.co.nikodem.dFSmpPlus.Items.Metas;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class CustomBucketCanFillMeta implements DFMaterialMeta {
    private final String namedIdPrefix;

    public CustomBucketCanFillMeta(String namedIdPrefix) {
        this.namedIdPrefix = namedIdPrefix;
    }

    public void BucketEntityEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEntityEvent event) {
        ItemStack vanillaItemStack = event.getEntityBucket();
        String nameToLookFor = namedIdPrefix + vanillaItemStack.getType().key().value();

        // TODO: SULFUR CUBE BUCKET TEMPORARY STOPGAP
        // wait until https://github.com/PaperMC/Paper/pull/14150 is merged, because otherwise it causes issues with converting back into copper/gold buckets
        // for now, just stop custom buckets from bucketting sulfur cubes (the likelihood is this won't even happen in normal gameplay :p)
        if (nameToLookFor.contains("sulfur")) {
            event.setCancelled(true);
            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Cannot bucket sulfur cube with this bucket!<br><white>This will be fixed, just waiting on a paper update, sorry :p"));
        }

        DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(nameToLookFor);
        if (newMaterial != null) {
            ItemMeta currentMeta = vanillaItemStack.getItemMeta();
            ItemMeta baseMeta = newMaterial.toItemStack().getItemMeta();

            currentMeta.setItemModel(baseMeta.getItemModel());
            if (!currentMeta.hasCustomName()) item.setData(DataComponentTypes.ITEM_NAME, newMaterial.getDisplayName());
            String dfupdateid = baseMeta.getPersistentDataContainer().get(Keys.dfUpdateId, PersistentDataType.STRING);
            if (dfupdateid != null) currentMeta.getPersistentDataContainer().set(Keys.dfUpdateId, PersistentDataType.STRING, dfupdateid);
            currentMeta.getPersistentDataContainer().set(Keys.dfmaterial, PersistentDataType.STRING, nameToLookFor);

            Boolean markeduuid = baseMeta.getPersistentDataContainer().get(Keys.markedForUUID, PersistentDataType.BOOLEAN);
            if (markeduuid != null) currentMeta.getPersistentDataContainer().set(Keys.markedForUUID, PersistentDataType.BOOLEAN, markeduuid);

            vanillaItemStack.setItemMeta(currentMeta);
        }
    };
}
