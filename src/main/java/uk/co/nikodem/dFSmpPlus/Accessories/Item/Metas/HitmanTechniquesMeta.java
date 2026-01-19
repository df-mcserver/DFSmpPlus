package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HitmanTechniquesMeta implements AccessoryMeta {
    public static Map<UUID, Long> silencedTimestamps = new HashMap<>();

    @Override
    public void UserAttacking(Player plr, ItemStack accessory, AccessoryInformation info, EntityDamageByEntityEvent event) {
        event.setDamage(event.getDamage() * 0.95);
    };

    @Override
    public void UserKilledPlayer(Player plr, ItemStack accessory, AccessoryInformation info, PlayerDeathEvent event) {
        Component silencedDeathMessage = event.deathMessage() == null ? Component.text("") : MiniMessage.miniMessage().deserialize("<grey><i>["+MiniMessage.miniMessage().serialize(event.deathMessage())+"]");
        event.deathMessage(null);

        Player victim = event.getPlayer();
        plr.sendMessage(silencedDeathMessage);
        victim.sendMessage(silencedDeathMessage);

        victim.sendMessage(MiniMessage.miniMessage().deserialize("<red>You are now silenced for 5 minutes!"));
        silencedTimestamps.remove(victim.getUniqueId());
        silencedTimestamps.put(victim.getUniqueId(), new Date().getTime());
    };

    public static boolean isSilenced(Player plr) {
        Long silencedTimestamp = silencedTimestamps.get(plr.getUniqueId());
        if (silencedTimestamp == null) return false;

        if ((silencedTimestamp + TimeUnit.MINUTES.toMillis(5)) < new Date().getTime()) {
            silencedTimestamps.remove(plr.getUniqueId());
            return false;
        } else {
            return true;
        }
    }
}
