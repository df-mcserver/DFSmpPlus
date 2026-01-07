package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;

public class SpeedMeta implements AccessoryMeta {
    @Override
    public void RunPerSecond(Player plr, ItemStack accessory, AccessoryInformation info) {
        plr.addPotionEffect(
                new PotionEffect(PotionEffectType.SPEED, 20, 5)
        );
    };
}
