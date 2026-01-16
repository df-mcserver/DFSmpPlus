package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryActionBuilder;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.List;
import java.util.Map;

public class TestMeta implements AccessoryMeta {
    @Override
    public void RunPerSecond(Player plr, ItemStack accessory, AccessoryInformation info) {
        plr.addPotionEffect(
                new PotionEffect(PotionEffectType.SPEED, 20, 5)
        );
    };

    public Map<Attribute, AttributeModifier> AddAdditionalAttributeModifiers(Player plr, ItemStack accessory, AccessoryInformation info) {
        return Map.of(
                Attribute.ATTACK_SPEED,
                new AttributeModifier(Keys.createAccessoryKey("testing-speedattribute"), 5D, AttributeModifier.Operation.ADD_NUMBER)
        );
    };

    public List<AccessoryAction> GetAccessoryActions() {
        return List.of(new AccessoryActionBuilder()
                .setChooseItemStackicon(((plr, accessoryitem, info) -> DFMaterial.BluebellsarStick.toItemStack()))
                .setOnClickAction((plr, accessoryitem, info, event) -> {
                    plr.sendMessage("YOO LETS GO WE CLICKED IT !!! WSP BRO111!11!1111 "+accessoryitem.displayName());
                })
                .build());
    };
}
