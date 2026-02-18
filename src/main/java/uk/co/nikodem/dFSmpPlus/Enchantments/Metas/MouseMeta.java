package uk.co.nikodem.dFSmpPlus.Enchantments.Metas;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Enchantments.DFEnchantmentMeta;

import java.util.Map;

public class MouseMeta implements DFEnchantmentMeta {
    public Map<Attribute, AttributeModifier> AddAttributeModifiers(Player plr, ItemStack accessory, AccessoryInformation info) {
        return Map.of(
                Attribute.SCALE,
                new AttributeModifier(Keys.mouse, 1.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1)
        );
    };
}
