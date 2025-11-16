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

import static uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager.COMBAT_LENGTH;

public class CombatEvents {
    public static void onAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player victim && event.getDamageSource().getCausingEntity() instanceof Player attacker) {
            formallyAnnounceCombat(victim, attacker);
            CombatLoggingManager.combatUpdate(victim, attacker);

            updateCombatBar(victim);
            updateCombatBar(attacker);
        }
    }

    public static void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        Player attacker = CombatLoggingManager.getCombatInformation(victim).getLastAttackerPlayer();
        if (attacker != null) {
            Component deathMessage = event.deathMessage();
            if (deathMessage != null) {
                if (!PlainTextComponentSerializer.plainText().serialize(deathMessage).contains(PlainTextComponentSerializer.plainText().serialize(attacker.displayName())))
                    event.deathMessage(deathMessage.append(Component.text(" whilst in combat with ").append(attacker.name())));
            }

            incrementKills(attacker);
            updateCombatBar(attacker);
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
            Integer startTick = CombatLoggingManager.getCombatInformation(plr).getStartTick();
            if (startTick == null) {
                CombatLoggingManager.getCombatInformation(plr).reset();
                return;
            }

            updateCombatBar(plr);
            plr.sendMessage(String.valueOf(startTick));
            plr.sendMessage(String.valueOf(CombatLoggingManager.getCombatInformation(plr).getLastAttackerUUID()));

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
            CombatLoggingManager.getCombatInformation(victim).setBar(bar);
            victim.showBossBar(bar);
        }
        if (!CombatLoggingManager.isInCombat(attacker)) {
            Boolean bedrock = BedrockPlayers.isBedrock(attacker);
            attacker.sendActionBar((bedrock == null || !bedrock) ? msg : bedrockMsg);
            CombatLoggingManager.getCombatInformation(attacker).setBar(bar);
            attacker.showBossBar(bar);
        }
    }

    public static void hideCombatBar(Player plr) {
        CombatInformation info = CombatLoggingManager.getCombatInformation(plr);
        if (info.getBar() != null) {
            plr.hideBossBar(info.getBar());
            info.setBar(null);
        }
    }

    public static void updateCombatBar(Player plr) {
        CombatInformation info = CombatLoggingManager.getCombatInformation(plr);
        if (info.getBar() != null) {
            Integer startTick = info.getStartTick();
            if (startTick == null) return;
            float progress = 1f - (float) (Bukkit.getCurrentTick() - startTick) / COMBAT_LENGTH;
            int kills = info.getKills() == null ? 0 : info.getKills();
            info.getBar().progress(Math.clamp(progress, 0f, 1f));
            info.getBar().name(MiniMessage.miniMessage().deserialize("<red>Combat"+(kills > 0 ? " ("+kills+")" : "")));
        }
    }

    public static void incrementKills(Player plr) {
        CombatInformation info = CombatLoggingManager.getCombatInformation(plr);
        if (info.getKills() != null) info.setKills(info.getKills() + 1);
        else info.setKills(1);
    }
}
