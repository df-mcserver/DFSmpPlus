package uk.co.nikodem.dFSmpPlus.Accessories.Action.Operations;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;

@FunctionalInterface
public interface OnClickAction {
    void onClick(Player plr, ItemStack accessoryItem, AccessoryInformation info, InventoryClickEvent event);
}
