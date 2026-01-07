package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface AccessoryMeta {
    default void RunPerSecond(Player plr, ItemStack accessory, AccessoryInformation info) {};
}
