package uk.co.nikodem.dFSmpPlus.Accessories.Action.Operations;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;

@FunctionalInterface
public interface ChooseItemStackIcon {
    ItemStack doActionIcon(Player plr, ItemStack accessoryitem, AccessoryInformation info);
}
