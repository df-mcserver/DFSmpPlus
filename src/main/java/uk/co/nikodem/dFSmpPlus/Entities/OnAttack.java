package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnAttack implements Listener {
    @EventHandler
    public void OnAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.ZOMBIE && event.getDamager() instanceof Ageable entity) {
            if (!entity.isAdult()) event.setDamage(event.getDamage() * 0.5);
        }
    }
}
