package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.List;
import java.util.Map;

public interface AccessoryMeta {
    default void RunPerSecond(Player plr, ItemStack accessory, AccessoryInformation info) {};
    default void AccessoryEquipped(Player plr, ItemStack accessory, AccessoryInformation info) {};
    default void AccessoryUnequipped(Player plr, ItemStack accessory, AccessoryInformation info) {};

    default Map<Attribute, AttributeModifier> AddAdditionalAttributeModifiers(Player plr, ItemStack accessory, AccessoryInformation info) {
        return Map.of();
    };
}
