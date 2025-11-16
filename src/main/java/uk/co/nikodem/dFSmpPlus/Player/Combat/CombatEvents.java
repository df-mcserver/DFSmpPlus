package uk.co.nikodem.dFSmpPlus.Player.Combat;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.DoublingDown;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;

import java.util.HashMap;
import java.util.Map;

import static uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager.COMBAT_LENGTH;

public class CombatEvents {

    public static final HashMap<Player, Map.Entry<BossBar, Integer>> combatBar = new HashMap<>();

    public static void onAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player victim && event.getDamageSource().getCausingEntity() instanceof Player attacker) {
            formallyAnnounceCombat(victim, attacker);
            CombatLoggingManager.combatUpdate(victim, attacker);

            Integer startTickVictim = CombatLoggingManager.getLastTimestamp(victim);
            if (startTickVictim != null) updateCombatBar(startTickVictim, victim);

            Integer startTickAttacker = CombatLoggingManager.getLastTimestamp(attacker);
            if (startTickAttacker != null) updateCombatBar(startTickAttacker, attacker);
        }
    }

    public static void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        Player attacker = CombatLoggingManager.getLastAttackerPlayer(victim);
        if (attacker != null) {
            Component deathMessage = event.deathMessage();
            if (deathMessage != null) {
                if (!PlainTextComponentSerializer.plainText().serialize(deathMessage).contains(PlainTextComponentSerializer.plainText().serialize(attacker.displayName())))
                    event.deathMessage(deathMessage.append(Component.text(" whilst in combat with ").append(attacker.name())));
            }

            incrementKills(attacker);
            Integer startTick = CombatLoggingManager.getLastTimestamp(attacker);
            if (startTick == null) return;

            updateCombatBar(startTick, attacker);
        }

        if (event.getDamageSource().getDamageType() == DamageType.OUT_OF_WORLD) {
            if (victim.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING
                    || victim.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
                DFAdvancementsHandler.grantAdvancement(victim, DoublingDown.class);
            }

            if (attacker != null) {
                for (ItemStack item : victim.getInventory().getContents()) {
                    if (item != null) attacker.getWorld().dropItem(attacker.getLocation(), item);
                }
            } else {
                event.setKeepInventory(true);
            }
        }

        CombatLoggingManager.removeCombat(victim);
        hideCombatBar(victim);
    }

    public static void perSecond(Player plr) {
        if (CombatLoggingManager.isInCombat(plr)) {
            Integer startTick = CombatLoggingManager.getLastTimestamp(plr);
            if (startTick == null) return;

            updateCombatBar(startTick, plr);

            if (startTick + COMBAT_LENGTH < Bukkit.getCurrentTick()) {
                formallyRemoveCombat(plr);
            }
        }
    }

    public static void formallyRemoveCombat(Player plr) {
        CombatLoggingManager.removeCombat(plr);
        plr.sendActionBar(MiniMessage.miniMessage().deserialize("<green>You are no longer in combat!"));

        hideCombatBar(plr);
    }

    public static void formallyAnnounceCombat(Player victim, Player attacker) {
        // java doesn't support new lines in action bars, it looks nicer, so if possible use a new line.
        Component msg = MiniMessage.miniMessage().deserialize("<red>You are now in combat! Leaving the server in combat will instantly kill you!");
        Component bedrockMsg = MiniMessage.miniMessage().deserialize("<red>You are now in combat!<newline>Leaving the server in combat will instantly kill you!");

        BossBar bar = BossBar.bossBar(MiniMessage.miniMessage().deserialize("<red>Combat"), 1f, BossBar.Color.RED, BossBar.Overlay.PROGRESS);

        if (!CombatLoggingManager.isInCombat(victim)) {
            Boolean bedrock = BedrockPlayers.isBedrock(victim);
            victim.sendActionBar((bedrock == null || !bedrock) ? msg : bedrockMsg);
            if (!combatBar.containsKey(victim)) combatBar.put(victim, Map.entry(bar, 0));
            victim.showBossBar(bar);
        }
        if (!CombatLoggingManager.isInCombat(attacker)) {
            Boolean bedrock = BedrockPlayers.isBedrock(attacker);
            attacker.sendActionBar((bedrock == null || !bedrock) ? msg : bedrockMsg);
            if (!combatBar.containsKey(attacker)) combatBar.put(attacker, Map.entry(bar, 0));
            attacker.showBossBar(bar);
        }
    }

    public static void hideCombatBar(Player plr) {
        Map.Entry<BossBar, Integer> bar = combatBar.get(plr);
        if (bar != null) {
            plr.hideBossBar(bar.getKey());
            combatBar.remove(plr);
        }
    }

    public static void updateCombatBar(Integer startTick, Player plr) {
        Map.Entry<BossBar, Integer> bar = combatBar.get(plr);
        if (bar != null) {
            float progress = 1f - (float) (Bukkit.getCurrentTick() - startTick) / COMBAT_LENGTH;
            int kills = bar.getValue() == null ? 0 : bar.getValue();
            bar.getKey().progress(Math.clamp(progress, 0f, 1f));
            bar.getKey().name(MiniMessage.miniMessage().deserialize("<red>Combat"+(kills > 0 ? " ("+kills+")" : "")));
        }
    }

    public static void incrementKills(Player plr) {
        Map.Entry<BossBar, Integer> bar = combatBar.get(plr);
        if (bar != null) {
            int kills = (bar.getValue() == null ? 0 : bar.getValue()) + 1;
            combatBar.replace(plr, Map.entry(bar.getKey(), kills));
        }
    }
}
