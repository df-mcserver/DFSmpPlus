package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.Map;

public class AccessoryEvents {
    public static void ApplyRunPerSecond(Player plr) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

        for (int i = 0; i < accessoryData.slots.length; i++) {
            if ((i + 1) == accessoryData.slots.length && !accessoryData.canUseFinalSlot) return;

            ItemStack accessoryItem = accessoryData.slots[i];
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) return;

            for (AccessoryMeta meta : info.getMeta()) {
                meta.RunPerSecond(plr, accessoryItem, info);
            }
        }
    }

    public static void AccessoryEquipped(Player plr, ItemStack item, AccessoryInformation info) {
        AttributeInstance instance = plr.getAttribute(Attribute.ARMOR);
        if (instance != null) {
            AttributeModifier alreadyExistingModifier = instance.getModifier(info.getNamespacedKey());
            if (alreadyExistingModifier != null) instance.removeModifier(info.getNamespacedKey());

            AttributeModifier newModifier = new AttributeModifier(info.getNamespacedKey(), info.getArmourPoints(), AttributeModifier.Operation.ADD_NUMBER);
            instance.addModifier(newModifier);
        }

        info.getEquipSound().playSound(plr);

        for (AccessoryMeta meta : info.getMeta()) {
            meta.AccessoryEquipped(plr, item, info);

            Map<Attribute, AttributeModifier> extraModifiers = meta.AddAdditionalAttributeModifiers(plr, item, info);
            if (!extraModifiers.isEmpty()) {
                for (Map.Entry<Attribute, AttributeModifier> extraModifier : extraModifiers.entrySet()) {
                    AttributeInstance extraInstance = plr.getAttribute(extraModifier.getKey());
                    if (extraInstance != null) {
                        AttributeModifier alreadyExistingModifier = extraInstance.getModifier(extraModifier.getValue().getKey());
                        if (alreadyExistingModifier != null) extraInstance.removeModifier(extraModifier.getValue().getKey());

                        extraInstance.addModifier(extraModifier.getValue());
                    }
                }
            }
        }
    }

    public static void AccessoryUnequipped(Player plr, ItemStack item, AccessoryInformation info) {
        AttributeInstance instance = plr.getAttribute(Attribute.ARMOR);
        if (instance != null) {
            AttributeModifier alreadyExistingModifier = instance.getModifier(info.getNamespacedKey());
            if (alreadyExistingModifier != null) instance.removeModifier(info.getNamespacedKey());
        }

        for (AccessoryMeta meta : info.getMeta()) {
            meta.AccessoryUnequipped(plr, item, info);

            Map<Attribute, AttributeModifier> extraModifiers = meta.AddAdditionalAttributeModifiers(plr, item, info);
            if (!extraModifiers.isEmpty()) {
                for (Map.Entry<Attribute, AttributeModifier> extraModifier : extraModifiers.entrySet()) {
                    AttributeInstance extraInstance = plr.getAttribute(extraModifier.getKey());
                    if (extraInstance != null) {
                        AttributeModifier alreadyExistingModifier = extraInstance.getModifier(extraModifier.getValue().getKey());
                        if (alreadyExistingModifier != null) extraInstance.removeModifier(extraModifier.getValue().getKey());
                    }
                }
            }
        }
    }
}
