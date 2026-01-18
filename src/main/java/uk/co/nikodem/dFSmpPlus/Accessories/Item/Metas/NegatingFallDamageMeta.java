package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;

public class NegatingFallDamageMeta implements AccessoryMeta {
    @Override
    public void UserDamaged(Player plr, ItemStack accessory, AccessoryInformation info, EntityDamageEvent event) {
        if (event.getDamageSource().getDamageType() == DamageType.FALL) {
            event.setCancelled(true);
        }
    };
}
