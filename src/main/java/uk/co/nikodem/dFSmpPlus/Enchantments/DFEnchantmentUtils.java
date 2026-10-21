package uk.co.nikodem.dFSmpPlus.Enchantments;

import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.Nullable;

public class DFEnchantmentUtils {
    public static int getEnchantmentLevelOfItem(ItemStack item, DFEnchantment enchantment) {
        return item.getEnchantmentLevel(enchantment.getEnchantment());
    }
}
