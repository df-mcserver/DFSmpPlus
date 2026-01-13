package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.List;

public interface AccessoryMeta {
    default void RunPerSecond(Player plr, ItemStack accessory, AccessoryInformation info) {};
    default void AccessoryEquipped(Player plr, ItemStack accessory, AccessoryInformation info) {};
    default void AccessoryUnequipped(Player plr, ItemStack accessory, AccessoryInformation info) {};
}
