package uk.co.nikodem.dFSmpPlus.Accessories.Player;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

public class PlayerAccessoryData {
    public ItemStack[] slots = new ItemStack[5];
    public int accessoryCapIndex = 3;
    public boolean accessoryInsertLock = false;

    public boolean isAccessoryEquipped(NamespacedKey id) {
        for (ItemStack item : slots) {
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
            if (info == null) continue;

            if (info.getNamespacedKey().equals(id)) return true;
        }
        return false;
    }

    public boolean isAccessoryEquipped(AccessoryInformation info) {
        return isAccessoryEquipped(info.getNamespacedKey());
    }
}
