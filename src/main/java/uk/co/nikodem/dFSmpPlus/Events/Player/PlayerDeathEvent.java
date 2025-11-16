package uk.co.nikodem.dFSmpPlus.Events.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.BulliedByBluebellsar;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatEvents;

public class PlayerDeathEvent implements Listener {
    @EventHandler
    public void PlayerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
        CombatEvents.onDeath(event);

        if (event.getPlayer().getPersistentDataContainer().has(Keys.comicallyLarge)) {
            String blockName = event.getPlayer().getPersistentDataContainer().get(Keys.comicallyLarge, PersistentDataType.STRING);
            if (blockName == null) event.deathMessage(Component.text(event.getPlayer().getName()+" had a heart attack whilst mining a block"));
            else event.deathMessage(Component.text(event.getPlayer().getName()+" had a heart attack whilst mining ").append(Component.translatable(blockName)));

            event.getPlayer().getPersistentDataContainer().remove(Keys.comicallyLarge);
        }
        if (event.getPlayer().getPersistentDataContainer().has(Keys.bluebellsarDeath)) {
            event.deathMessage(Component.text(event.getPlayer().getName()+" got a taste of their own medicine."));
            DFAdvancementsHandler.grantAdvancement(event.getPlayer(), BulliedByBluebellsar.class);

            event.getPlayer().getPersistentDataContainer().remove(Keys.bluebellsarDeath);
        }
    }
}
