package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;

public class PhantomIgnoresMeta implements AccessoryMeta {
    @Override
    public void UserTargetted(Player plr, ItemStack accessory, AccessoryInformation info, EntityTargetEvent event) {
        if (event.getEntity() instanceof Phantom) {
            event.setCancelled(true);
        }
    }
}
