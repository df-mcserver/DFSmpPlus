package uk.co.nikodem.dFSmpPlus.Events.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

public class PlayerDeathEvent implements Listener {
    @EventHandler
    public void PlayerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent e) {
        if (e.getPlayer().getPersistentDataContainer().has(Keys.comicallyLarge)) {
            String blockName = e.getPlayer().getPersistentDataContainer().get(Keys.comicallyLarge, PersistentDataType.STRING);
            if (blockName == null) e.deathMessage(Component.text(e.getPlayer().getName()+" had a heart attack whilst mining a block"));
            else e.deathMessage(Component.text(e.getPlayer().getName()+" had a heart attack whilst mining ").append(Component.translatable(blockName)));

            e.getPlayer().getPersistentDataContainer().remove(Keys.comicallyLarge);
        }
    }
}
