package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

import java.util.Map;

public class KnockbackResistenceMeta implements AccessoryMeta {
    @Override
    public Map<Attribute, AttributeModifier> AddAdditionalAttributeModifiers(Player plr, ItemStack accessory, AccessoryInformation info) {
        return Map.of(
                Attribute.KNOCKBACK_RESISTANCE,
                new AttributeModifier(Keys.cobaltShieldKb, 0.5f, AttributeModifier.Operation.ADD_NUMBER)
        );
    };
}
