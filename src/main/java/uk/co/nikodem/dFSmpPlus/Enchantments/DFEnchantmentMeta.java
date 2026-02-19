package uk.co.nikodem.dFSmpPlus.Enchantments;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;

import java.util.Map;

public interface DFEnchantmentMeta {
    // TODO figure out how i'd implement this
    default void BlockMined(Player plr, ItemStack item, DFEnchantment enchantment, BlockBreakEvent event) {};
    default Map<Attribute, AttributeModifier> AddAttributeModifiers(Player plr, ItemStack accessory, AccessoryInformation info) {
        return Map.of();
    };
}
