package uk.co.nikodem.dFSmpPlus.Player.Combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatLoggingManager {
    public static final int COMBAT_LENGTH = 600; // in ticks
    public static final HashMap<UUID, Map.Entry<Integer, String>> combatInformation = new HashMap<>();

    public static void combatUpdate(Player victim, Player attacker) {
        combatUpdate(victim.getUniqueId(), attacker.getUniqueId());
    }

    public static void combatUpdate(UUID victim, UUID attacker) {
        int currentTick = Bukkit.getCurrentTick();

        Map.Entry<Integer, String> victimNewInformation = Map.entry(currentTick, attacker.toString());
        if (!combatInformation.containsKey(victim)) combatInformation.put(victim, victimNewInformation);
        else combatInformation.replace(victim, victimNewInformation);

        Map.Entry<Integer, String> attackerCurrentInformation = combatInformation.get(attacker);
        if (attackerCurrentInformation != null) combatInformation.replace(attacker, Map.entry(currentTick, attackerCurrentInformation.getValue()));
        else combatInformation.put(attacker, Map.entry(currentTick, ""));
    }

    @Nullable
    public static UUID getLastAttackerUUID(Player victim) {
        return getLastAttackerUUID(victim.getUniqueId());
    }

    @Nullable
    public static UUID getLastAttackerUUID(UUID victim) {
        Map.Entry<Integer, String> info = combatInformation.get(victim);
        if (info == null) return null;

        String uuidString = info.getValue();
        try {
            return UUID.fromString(uuidString);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Nullable
    public static Player getLastAttackerPlayer(Player victim) {
        return getLastAttackerPlayer(victim.getUniqueId());
    }

    @Nullable
    public static Player getLastAttackerPlayer(UUID victim) {
        UUID uuid = getLastAttackerUUID(victim);
        if (uuid == null) return null;

        return Bukkit.getServer().getOnlinePlayers().stream().filter(player -> player.getUniqueId() == uuid).findFirst().orElse(null);
    }

    @Nullable
    public static Integer getLastTimestamp(Player victim) {
        return getLastTimestamp(victim.getUniqueId());
    }

    @Nullable
    public static Integer getLastTimestamp(UUID victim) {
        Map.Entry<Integer, String> info = combatInformation.get(victim);
        if (info == null) return null;

        return info.getKey();
    }

    @Nullable
    public static void removeCombat(Player victim) {
        removeCombat(victim.getUniqueId());
    }

    @Nullable
    public static void removeCombat(UUID victim) {
        combatInformation.remove(victim);
    }

    public static boolean isInCombat(Player victim) {
        return isInCombat(victim.getUniqueId());
    }

    public static boolean isInCombat(UUID victim) {
        return combatInformation.containsKey(victim);
    }
}
