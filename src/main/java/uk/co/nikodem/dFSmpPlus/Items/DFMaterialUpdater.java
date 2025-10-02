package uk.co.nikodem.dFSmpPlus.Items;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

import javax.annotation.Nullable;
import java.util.Map;

import static uk.co.nikodem.dFSmpPlus.Constants.Enums.UpdateResult.*;
import static uk.co.nikodem.dFSmpPlus.Items.DFItemUtils.getCurrentVersion;
import static uk.co.nikodem.dFSmpPlus.Items.DFItemUtils.getDFMaterial;

public class DFMaterialUpdater {

    @Nullable
    public static Enums.UpdateResult updateItem(ItemStack item, DFMaterial material) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return NULL;

        ItemStack reference = material.toItemStack();
        ItemMeta refMeta = reference.getItemMeta();
        if (refMeta == null) return NULL;
        meta.setAttributeModifiers(null);
        if (refMeta.hasAttributeModifiers()) {
            for (Map.Entry<Attribute, AttributeModifier> mod : refMeta.getAttributeModifiers().entries()) {
                Attribute attribute = mod.getKey();
                AttributeModifier modifier = mod.getValue();

                meta.addAttributeModifier(attribute, modifier);
            };
        }
        for (ItemFlag flag : meta.getItemFlags()) {
            meta.removeItemFlags(flag);
        }
        for (ItemFlag flag : refMeta.getItemFlags()) {
            meta.addItemFlags(flag);
        }

        PersistentDataContainer cont = meta.getPersistentDataContainer();
        cont.set(Keys.dfmaterialVersion, PersistentDataType.INTEGER, material.getVersion());

        meta.lore(refMeta.hasLore() ? refMeta.lore() : null);

        if (refMeta.hasEquippable()) meta.setEquippable(refMeta.getEquippable());

        if (refMeta.hasTool()) meta.setTool(refMeta.getTool());

        meta.setCustomModelData(refMeta.hasCustomModelData() ? refMeta.getCustomModelData() : null);

        item.setItemMeta(meta);

        if (material.hasMeta()) {
            Enums.UpdateResult res = null;
            for (DFMaterialMeta materialMeta : material.getMeta()) {

                if (materialMeta != null) res = materialMeta.ItemUpdated(material, item);
                if (res == NULL || res == UPDATED) {
                    return UPDATED;
                } else if (res == DELETED) {
                    return DELETED;
                }
            }
        }

        return UPDATED;
    }

    @Nullable
    public static Enums.UpdateResult doUpdate(ItemStack item, boolean destructive) {
        DFMaterial dfMaterial = getDFMaterial(item);
        Integer currentVersion = getCurrentVersion(item);

        if (dfMaterial == null) return null;
        if (currentVersion == null) {
            // df material isn't invalid, but current version is
            // this means that the df material no longer exists

            item.setAmount(0);
            return DELETED;
        }

        if (dfMaterial.getVersion() > currentVersion) {
            return updateItem(item, dfMaterial) == UPDATED ? UPDATED : NULL;
        } else if (dfMaterial.getVersion() < currentVersion) {
            if (destructive) item.setAmount(0);
            return DELETED;
        } else {
            return null;
        }
    }

    @Nullable
    public static Enums.UpdateResult doUpdate(ItemStack item) {
        return doUpdate(item, true);
    }
}
