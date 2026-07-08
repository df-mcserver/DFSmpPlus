package uk.co.nikodem.dFSmpPlus.Accessories.Player;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

public class PlayerAccessoryData {
    public ItemStack[] slots = new ItemStack[7];
    public int accessoryCapIndexAddition = 0;
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

    public boolean hasAccessoryWithMetaEquipped(Class<? extends AccessoryMeta> metaClass) {
        for (ItemStack item : slots) {
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
            if (info == null) continue;

            for (AccessoryMeta accessoryMeta : info.getMeta()) {
                if (accessoryMeta.getClass().equals(metaClass)) return true;
                else if (accessoryMeta.getClass().getSuperclass() != null) if (accessoryMeta.getClass().getSuperclass().equals(metaClass)) return true;
            }
        }
        return false;
    }

    public int getAccessoryCapIndex() {
        return 4 + accessoryCapIndexAddition;
    }
}
