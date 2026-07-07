package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;

public class PhantomIgnoresMeta implements AccessoryMeta {
    @Override
    public void UserTargetted(Player plr, ItemStack accessory, AccessoryInformation info, EntityTargetEvent event) {
        if (event.getEntity() instanceof Phantom) {
            event.setCancelled(true);
        }
    }

    @Override
    public void UserDamaged(Player plr, ItemStack accessory, AccessoryInformation info, EntityDamageEvent event) {
        // if somehow the phantom even manages to hit you
        if (event.getEntity() instanceof Phantom phantom) {
            phantom.addPotionEffect(PotionEffectType.POISON.createEffect(60, 1));
        }
    };
}
