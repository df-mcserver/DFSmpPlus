package uk.co.nikodem.dFSmpPlus.SetBonuses;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

public class DFArmourSetEvents {
    public static void PlayerAttack(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();

        boolean isVictim = false;

        Player validPlr = null;
        if (damager instanceof Player d) validPlr = d;
        if (victim instanceof Player v) {
            validPlr = v;
            isVictim = true;
        };

        if (validPlr != null) {
            ItemStack weapon = validPlr.getInventory().getItemInMainHand();
            DFArmourSet armourSet = DFArmourSetUtils.getPlayersArmourSet(validPlr);

            if (armourSet == null) return;

            if (armourSet.hasMeta()) {
                for (DFArmourSetMeta meta : armourSet.getMeta()) {
                    if (isVictim) meta.PlayerHit(validPlr, armourSet, e);
                    else meta.PlayerAttack(validPlr, armourSet, weapon, e);
                }
            }
        }
    }

    public static void PlayerHunger(FoodLevelChangeEvent e) {
        Player plr = (Player) e.getEntity();

        DFArmourSet armourSet = DFArmourSetUtils.getPlayersArmourSet(plr);

        if (armourSet == null) return;

        if (armourSet.hasMeta()) {
            for (DFArmourSetMeta meta : armourSet.getMeta()) {
                meta.PlayerHunger(plr, armourSet, e);
            }
        }
    }

    public static void ApplyRunPerSecond(Player plr) {
        DFArmourSet armourSet = DFArmourSetUtils.getPlayersArmourSet(plr);

        if (armourSet == null) return;

        if (armourSet.hasMeta()) {
            for (DFArmourSetMeta meta : armourSet.getMeta()) {
                meta.RunPerSecond(plr, armourSet);
            }
        }
    }
}
