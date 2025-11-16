package uk.co.nikodem.dFSmpPlus.Player.Combat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager.COMBAT_LENGTH;

public class CombatEvents {
    public static void onAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player victim && event.getDamager() instanceof Player attacker) {
            formallyAnnounceCombat(victim, attacker);
            CombatLoggingManager.combatUpdate(victim, attacker);
        }
    }

    public static void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        Player attacker = CombatLoggingManager.getLastAttackerPlayer(victim);
        if (attacker != null) {
            victim.sendMessage(attacker.displayName());
            Component deathMessage = event.deathMessage();
            if (deathMessage != null) {
                if (!PlainTextComponentSerializer.plainText().serialize(deathMessage).contains(PlainTextComponentSerializer.plainText().serialize(attacker.displayName())))
                    event.deathMessage(deathMessage.append(Component.text(" whilst in combat with ").append(attacker.name())));
            }
        }

        CombatLoggingManager.removeCombat(victim);
    }

    public static void perSecond(Player plr) {
        if (CombatLoggingManager.isInCombat(plr)) {
            Integer startTick = CombatLoggingManager.getLastTimestamp(plr);
            if (startTick == null) return;

            if (startTick + COMBAT_LENGTH < Bukkit.getCurrentTick()) {
                formallyRemoveCombat(plr);
            }
        }
    }

    public static void formallyRemoveCombat(Player plr) {
        CombatLoggingManager.removeCombat(plr);
        plr.sendMessage(MiniMessage.miniMessage().deserialize("<green>You are no longer in combat!"));
    }

    public static void formallyAnnounceCombat(Player victim, Player attacker) {
        Component msg = MiniMessage.miniMessage().deserialize("<red>You are now in combat!<newline>Leaving the server in combat will instantly kill you!");
        if (!CombatLoggingManager.isInCombat(victim)) victim.sendMessage(msg);
        if (!CombatLoggingManager.isInCombat(victim)) attacker.sendMessage(msg);
    }
}
