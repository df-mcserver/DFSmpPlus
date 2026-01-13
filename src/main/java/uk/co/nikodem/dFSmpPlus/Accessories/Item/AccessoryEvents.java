package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

public class AccessoryEvents {
    public static void ApplyRunPerSecond(Player plr) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

        for (ItemStack a : accessoryData.slots) {
            if (a == null) continue;
            plr.sendMessage(a.displayName());
        }

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
        for (AccessoryMeta meta : info.getMeta()) {
            meta.AccessoryEquipped(plr, item, info);
        }
    }

    public static void AccessoryUnequipped(Player plr, ItemStack item, AccessoryInformation info) {
        for (AccessoryMeta meta : info.getMeta()) {
            meta.AccessoryUnequipped(plr, item, info);
        }
    }
}
