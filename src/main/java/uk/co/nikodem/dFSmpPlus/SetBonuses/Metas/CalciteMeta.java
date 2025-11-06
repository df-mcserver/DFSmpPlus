package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

public class CalciteMeta implements DFArmourSetMeta {
    @Override
    public void RunPerSecond(Player plr, DFArmourSet armourSet) {
        plr.addPotionEffect(new PotionEffect(
                PotionEffectType.SPEED, 40, 1, true, false
        ));
        plr.addPotionEffect(new PotionEffect(
                PotionEffectType.HEALTH_BOOST, 40, 0, true, false
        ));
    };
}
