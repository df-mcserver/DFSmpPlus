package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Constants.DamageCauseGroups;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

import java.util.List;

public class FireImmunityMeta implements DFArmourSetMeta {
    public void PlayerTakeDamage(Player plr, DFArmourSet armourSet, EntityDamageEvent event) {
        if (DamageCauseGroups.hotDamageCauses.contains(event.getCause())) event.setCancelled(true);
        plr.setFireTicks(0);
    };
}
