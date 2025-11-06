package uk.co.nikodem.dFSmpPlus.Events.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.BulliedByBluebellsar;
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
        if (e.getPlayer().getPersistentDataContainer().has(Keys.bluebellsarDeath)) {
            e.deathMessage(Component.text(e.getPlayer().getName()+" got a taste of their own medicine."));
            DFAdvancementsHandler.grantAdvancement(e.getPlayer(), BulliedByBluebellsar.class);

            e.getPlayer().getPersistentDataContainer().remove(Keys.bluebellsarDeath);
        }
    }
}
