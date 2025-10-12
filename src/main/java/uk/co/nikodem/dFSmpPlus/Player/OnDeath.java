package uk.co.nikodem.dFSmpPlus.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

public class OnDeath implements Listener {
    @EventHandler
    public void OnDeath(PlayerDeathEvent e) {
        if (e.getPlayer().getPersistentDataContainer().has(Keys.comicallyLarge)) {
            String blockName = e.getPlayer().getPersistentDataContainer().get(Keys.comicallyLarge, PersistentDataType.STRING);
            e.deathMessage(Component.text(e.getPlayer().getName()+" had a heart attack whilst mining ").append(Component.translatable(blockName)));
        }
    }
}
