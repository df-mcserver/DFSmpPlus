package uk.co.nikodem.dFSmpPlus.Events.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.BackpackMeta;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.BulliedByBluebellsar;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatEvents;
import uk.co.nikodem.dFSmpPlus.Player.Combat.WorldRecordAdvancementHandler;
import uk.co.nikodem.dFSmpPlus.SetBonuses.SetBonusText;

public class PlayerDeathEvent implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
        BackpackMeta.onDeath(event);
        CombatEvents.onDeath(event);
        AccessoryEvents.UserKilledPlayer(event);
        WorldRecordAdvancementHandler.onDeath(event);
        SetBonusText.onDeath(event);

        Player plr = event.getPlayer();

        if (plr.getPersistentDataContainer().has(Keys.comicallyLarge)) {
            String blockName = plr.getPersistentDataContainer().get(Keys.comicallyLarge, PersistentDataType.STRING);
            if (blockName == null) event.deathMessage(Component.text(plr.getName()+" had a heart attack whilst mining a block"));
            else event.deathMessage(Component.text(plr.getName()+" had a heart attack whilst mining ").append(Component.translatable(blockName)));

            plr.getPersistentDataContainer().remove(Keys.comicallyLarge);
        }
        if (plr.getPersistentDataContainer().has(Keys.bluebellsarDeath)) {
            event.deathMessage(Component.text(plr.getName()+" got a taste of their own medicine."));
            DFAdvancementsHandler.grantAdvancement(plr, BulliedByBluebellsar.class);

            plr.getPersistentDataContainer().remove(Keys.bluebellsarDeath);
        }
        if (plr.getPersistentDataContainer().has(Keys.uranium)) {
            event.deathMessage(Component.text(plr.getName()+" had too many calories."));

            plr.getPersistentDataContainer().remove(Keys.uranium);
        }
    }
}
