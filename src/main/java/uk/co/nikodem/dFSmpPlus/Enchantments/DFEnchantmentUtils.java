package uk.co.nikodem.dFSmpPlus.Enchantments;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class DFEnchantmentUtils {
    public static int getEnchantmentLevelOfItem(ItemStack item, DFEnchantment enchantment) {
        return item.getEnchantmentLevel(enchantment.getEnchantment());
    }
}
