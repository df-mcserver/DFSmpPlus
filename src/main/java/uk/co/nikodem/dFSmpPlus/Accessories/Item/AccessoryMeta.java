package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public interface AccessoryMeta {
    default void RunPerSecond(Player plr, ItemStack accessory, AccessoryInformation info) {};
}
