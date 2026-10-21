package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class EnderDragon {
    public static void onHitByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.EnderDragon dragon) {
            event.setDamage(event.getDamage() / 3);
        } else if (event.getDamager() instanceof org.bukkit.entity.EnderDragon dragon) {
            event.setDamage(event.getDamage() * 3);
        }
    }

    public static void onDamaged(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EnderDragon) {
            if (event.getDamageSource().getCausingEntity() == null) {
                event.setCancelled(true);
            }
        } else if (entity instanceof EnderCrystal) {
            if (!entity.getLocation().getWorld().getEnvironment().equals(World.Environment.THE_END)) return;
            List<Entity> entities = entity.getLocation().getWorld().getEntities();
            if (entities.stream().anyMatch(e -> e.getType().equals(EntityType.ENDER_DRAGON))) {
                Location location = event.getEntity().getLocation().add(0, 25, 0);
                location.getWorld().spawnEntity(location, EntityType.PHANTOM);
            }
        }
    }
}
