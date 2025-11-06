package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

public class CopperMeta implements DFArmourSetMeta {
    @Override
    public void PlayerAttack(Player plr, DFArmourSet armourSet, ItemStack weapon, EntityDamageByEntityEvent event) {
        if (!DFItemUtils.isTool(weapon)) return;
        if (event.isCritical() && !plr.getWorld().isClearWeather()) {
            int random = DFItemUtils.isCopper(weapon) ? (int) (Math.random() * 10 + 1) : (int) (Math.random() * 25 + 1);
            if (random == 2) {
                Entity target = event.getEntity();
                plr.getWorld().strikeLightning(target.getLocation());
                event.setDamage(event.getDamage() * 1.5);
            }
        }
    }
}
