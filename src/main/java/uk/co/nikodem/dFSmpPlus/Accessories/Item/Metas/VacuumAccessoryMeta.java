package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VacuumAccessoryMeta implements AccessoryMeta {
    public static List<ItemStack> giveItemsToPlayerViaVacuum(Player plr, List<ItemStack> newDrops) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        boolean hasVacuum = accessoryData.hasAccessoryWithMetaEquipped(VacuumAccessoryMeta.class);

        List<ItemStack> overflowItems = new ArrayList<>();

        for (ItemStack drop : newDrops) {
            if (hasVacuum) {
                PlayerInventory inv = plr.getInventory();
                Map<Integer, ItemStack> overflow = inv.addItem(drop);
                if (overflow.isEmpty()) Sounds.VacuumPickupItem.playSound(plr);
                for (Map.Entry<Integer, ItemStack> item : overflow.entrySet()) {
                    overflowItems.add(item.getValue());
                }
            } else {
                overflowItems.add(drop);
            }
        }

        return overflowItems;
    }
}
