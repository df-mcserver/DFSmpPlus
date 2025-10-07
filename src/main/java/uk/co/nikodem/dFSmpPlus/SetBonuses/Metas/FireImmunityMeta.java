package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

public class FireImmunityMeta implements DFArmourSetMeta {
    @Override
    public void RunPerSecond(Player plr, DFArmourSet armourSet) {
        plr.addPotionEffect(new PotionEffect(
                PotionEffectType.FIRE_RESISTANCE, 40, 1, true, false
        ));
    };
}
