package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

public class SculkArmourMeta implements DFArmourSetMeta {
    public void PlayerHit(Player victim, DFArmourSet armourSet, EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player attacker) {
            Sounds.SculkArmourActivate.playSound(victim);
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20*10, 2));
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*3, 0));
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20*3, 0));
            victim.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*5, 0));
            victim.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*5, 0));
        }
    }
}
