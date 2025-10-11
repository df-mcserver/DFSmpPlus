package uk.co.nikodem.dFSmpPlus.Entities.CustomDrops;

import org.bukkit.event.entity.EntityDeathEvent;

public interface DFCustomDropCheck {
    boolean runCheck(EntityDeathEvent event);
}
