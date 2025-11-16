package uk.co.nikodem.dFSmpPlus.Player.Combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatLoggingManager {
    public static final int COMBAT_LENGTH = 600; // in ticks
    public static final HashMap<UUID, CombatInformation> combatInformation = new HashMap<>();

    public static void combatUpdate(Player victim, Player attacker) {
        combatUpdate(victim.getUniqueId(), attacker.getUniqueId());
    }

    public static void combatUpdate(UUID victim, UUID attacker) {
        int currentTick = Bukkit.getCurrentTick();

        CombatInformation victimInfo = getCombatInformation(victim);
        victimInfo.setLastAttacker(attacker);
        victimInfo.setStartTick(currentTick);

        CombatInformation attackerInfo = getCombatInformation(attacker);
        attackerInfo.setStartTick(currentTick);
    }

    public static CombatInformation getCombatInformation(Player plr) {
        return getCombatInformation(plr.getUniqueId());
    }

    public static CombatInformation getCombatInformation(UUID plr) {
        if (combatInformation.containsKey(plr)) return combatInformation.get(plr);
        else {
            CombatInformation info = new CombatInformation();
            combatInformation.put(plr, info);
            return info;
        }
    }

    public static void removeCombat(Player plr) {
        removeCombat(plr.getUniqueId());
    }
    public static void removeCombat(UUID plr) {
        getCombatInformation(plr).reset();
    }

    public static boolean isInCombat(Player plr) {
        return isInCombat(plr.getUniqueId());
    }
    public static boolean isInCombat(UUID plr) {
        if (!combatInformation.containsKey(plr)) return false;
        return getCombatInformation(plr).getStartTick() != null;
    }
}
