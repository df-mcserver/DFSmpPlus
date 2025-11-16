package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EnderDragon {
    public static void onHitByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.EnderDragon dragon) {
            event.setDamage(event.getDamage() / 3);
        } else if (event.getDamager() instanceof org.bukkit.entity.EnderDragon dragon) {
            event.setDamage(event.getDamage() * 3);
        }
    }

    public static void onDamaged(EntityDamageEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            if (event.getDamageSource().getCausingEntity() == null) {
                event.setCancelled(true);
            }
        }
    }
}
