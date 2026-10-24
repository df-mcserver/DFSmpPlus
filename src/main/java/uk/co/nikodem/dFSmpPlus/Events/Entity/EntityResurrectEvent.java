package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

public class EntityResurrectEvent implements Listener {
    @EventHandler
    public void EntityResurrectEvent(org.bukkit.event.entity.EntityResurrectEvent event) {
        Entity entity = event.getEntity();
        
        if (entity instanceof Player plr) {
            if (plr.getPersistentDataContainer().has(Keys.comicallyLarge)) {
                plr.getPersistentDataContainer().remove(Keys.comicallyLarge);
            }
            if (plr.getPersistentDataContainer().has(Keys.bluebellsarDeath)) {
                plr.getPersistentDataContainer().remove(Keys.bluebellsarDeath);
            }
            if (plr.getPersistentDataContainer().has(Keys.uranium)) {
                plr.getPersistentDataContainer().remove(Keys.uranium);
            }
        }
    }
}
