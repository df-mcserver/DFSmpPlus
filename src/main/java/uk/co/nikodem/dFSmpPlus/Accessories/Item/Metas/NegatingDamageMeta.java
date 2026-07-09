package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;

import java.util.Arrays;
import java.util.List;

public class NegatingDamageMeta implements AccessoryMeta {
    public final List<DamageType> damagePreventionTypes;

    public NegatingDamageMeta(DamageType... types) {
        this.damagePreventionTypes = Arrays.stream(types).toList();
    }

    @Override
    public void UserDamaged(Player plr, ItemStack accessory, AccessoryInformation info, EntityDamageEvent event) {
        if (damagePreventionTypes.contains(event.getDamageSource().getDamageType())) {
            event.setCancelled(true);
        }
    };
}
