package uk.co.nikodem.dFSmpPlus.Player.DFUpdates;

import io.papermc.paper.datacomponent.DataComponentType;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.*;

import static uk.co.nikodem.dFSmpPlus.Constants.Enums.UpdateResult.*;
import static uk.co.nikodem.dFSmpPlus.Items.DFItemUtils.*;

public class DFMaterialUpdater {
    public static final HashMap<String, String> legacyMappings;

    static {
        legacyMappings = new HashMap<>();
        legacyMappings.put("hermesboots", "boots_of_swiftness");
        legacyMappings.put("autosmelt_essence", "firidium_essence");
    }

    public static Map.Entry<Enums.UpdateResult, ItemStack> updateItem(ItemStack item, DFMaterial material, List<Enums.UpdateType> updatesToPerform) {
        ItemStack reference = material.toItemStack();

        ItemStack operatingItem = item;

        boolean itemShouldBeReplaced = false;

        ItemMeta meta = operatingItem.getItemMeta();
        if (meta == null) return Map.entry(UPDATE_FAILED, ItemStack.empty());

        if (updatesToPerform.contains(Enums.UpdateType.ITEM_TYPE)) {
            // change item type, which is harder bc of paper api
            ItemStack newItem = ItemStack.of(reference.getType());
            newItem.setAmount(operatingItem.getAmount());
            newItem.setItemMeta(meta);
            operatingItem = newItem;
            itemShouldBeReplaced = true;
        }

        if (updatesToPerform.contains(Enums.UpdateType.CUSTOM_NAME)) meta.customName(material.getDisplayName());

        if (updatesToPerform.contains(Enums.UpdateType.DATA_COMPONENTS)) operatingItem.copyDataFrom(reference, reference.getDataTypes()::contains);

        ItemMeta refMeta = reference.getItemMeta();
        if (refMeta == null) return Map.entry(UPDATE_FAILED, ItemStack.empty());
        meta.setAttributeModifiers(null);
        if (refMeta.hasAttributeModifiers()) {
            for (Map.Entry<Attribute, AttributeModifier> mod : Objects.requireNonNull(refMeta.getAttributeModifiers()).entries()) {
                Attribute attribute = mod.getKey();
                AttributeModifier modifier = mod.getValue();

                meta.addAttributeModifier(attribute, modifier);
            };
        }

        if (updatesToPerform.contains(Enums.UpdateType.ITEM_MODEL)) meta.setItemModel(refMeta.getItemModel());

        if (updatesToPerform.contains(Enums.UpdateType.ITEM_FLAGS)) {
            for (ItemFlag flag : meta.getItemFlags()) {
                meta.removeItemFlags(flag);
            }
            for (ItemFlag flag : refMeta.getItemFlags()) {
                meta.addItemFlags(flag);
            }
        }

//        if (updatesToPerform.contains(Enums.UpdateType.LORE)) meta.lore(refMeta.hasLore() ? refMeta.lore() : null);

        if (updatesToPerform.contains(Enums.UpdateType.UPDATE_ID)) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(Keys.dfUpdateId, PersistentDataType.STRING, material.getUpdateId());
        }

        operatingItem.setItemMeta(meta);

        if (material.hasMeta()) {
            for (DFMaterialMeta materialMeta : material.getMeta()) {
                if (materialMeta != null) materialMeta.ItemUpdated(material, operatingItem);
            }
        }

        return Map.entry(UPDATED, itemShouldBeReplaced ? operatingItem : ItemStack.empty());
    }

    public static Map.Entry<Enums.UpdateResult, ItemStack> doUpdate(ItemStack item) {
        DFMaterial dfMaterial = getDFMaterial(item);
        Integer currentVersion = getLegacyDFVersion(item);
        String updateId = getDFUpdateId(item);
        if (item == null) return Map.entry(NULL, ItemStack.empty());

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return Map.entry(NULL, ItemStack.empty());

        List<Enums.UpdateType> updatesToPerform = new ArrayList<>();

        if (currentVersion != null) {
            // older version of items, use custom mappings to convert from old named_ids to newer ones

            if (dfMaterial == null) {
                // try again with a mapped id

                String legacydfMaterialId = meta.getPersistentDataContainer().get(
                        Keys.dfmaterial,
                        PersistentDataType.STRING
                );

                String mappedId = legacyMappings.get(legacydfMaterialId);
                dfMaterial = getDFMaterial(mappedId);
                if (dfMaterial != null) {
                    meta.getPersistentDataContainer().set(Keys.dfmaterial, PersistentDataType.STRING, mappedId);
                }
            }

            meta.getPersistentDataContainer().remove(Keys.legacy_dfmaterialVersion);
            item.setItemMeta(meta);
        }

        if (dfMaterial == null) {
            item.setAmount(0);
            return Map.entry(DELETED, ItemStack.empty());
        }
        boolean isSameUpdateId = false;

        if (updateId != null && updateId.equals(dfMaterial.getUpdateId())) isSameUpdateId = true;
        else {
            updatesToPerform.add(Enums.UpdateType.UPDATE_ID);
        }

        ItemMeta refMeta = dfMaterial.toItemStack().getItemMeta();
        if (refMeta == null) return Map.entry(NULL, ItemStack.empty());

        if ((refMeta.hasItemModel() != meta.hasItemModel()) || (!Objects.equals(refMeta.getItemModel(),meta.getItemModel()))) {
            updatesToPerform.add(Enums.UpdateType.ITEM_MODEL);
        }
        if (refMeta.hasCustomName() && meta.hasCustomName()) {
            String name = MiniMessage.miniMessage().serialize(Objects.requireNonNull(meta.customName()));
            if (!name.startsWith("<!italic>")) updatesToPerform.add(Enums.UpdateType.CUSTOM_NAME);
        }
        if (!isSameUpdateId) if (!dfMaterial.toItemStack().getType().equals(item.getType())) {
            updatesToPerform.add(Enums.UpdateType.ITEM_TYPE);
        }
        if (!isSameUpdateId) if (((!refMeta.getItemFlags().isEmpty() && !meta.getItemFlags().isEmpty()) && (refMeta.getItemFlags() != meta.getItemFlags())) || refMeta.getItemFlags().isEmpty() != meta.getItemFlags().isEmpty()) {
            updatesToPerform.add(Enums.UpdateType.ITEM_FLAGS);
        }
        if (!isSameUpdateId) if (((refMeta.hasAttributeModifiers() && meta.hasAttributeModifiers()) && (refMeta.getAttributeModifiers() != meta.getAttributeModifiers())) || refMeta.hasAttributeModifiers() != meta.hasAttributeModifiers()) {
            updatesToPerform.add(Enums.UpdateType.ATTRIBUTES);
        }
        ItemStack reference = dfMaterial.toItemStack();
        if (!isSameUpdateId) for (@NotNull DataComponentType type : reference.getDataTypes()) {
            if (type instanceof DataComponentType.Valued<?> valued) {
                if (!(reference.getData(valued).equals(item.getData(valued)))) {
                    updatesToPerform.add(Enums.UpdateType.DATA_COMPONENTS);
                    break;
                }
            }
        }

        if (!updatesToPerform.isEmpty()) return updateItem(item, dfMaterial, updatesToPerform);
        return Map.entry(NULL, ItemStack.empty());
    }
}
