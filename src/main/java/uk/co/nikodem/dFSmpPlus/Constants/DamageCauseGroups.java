package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class DamageCauseGroups {
    public static List<EntityDamageEvent.DamageCause> hotDamageCauses = List.of(EntityDamageEvent.DamageCause.CAMPFIRE, EntityDamageEvent.DamageCause.FIRE, EntityDamageEvent.DamageCause.FIRE_TICK, EntityDamageEvent.DamageCause.HOT_FLOOR, EntityDamageEvent.DamageCause.LAVA);
}
