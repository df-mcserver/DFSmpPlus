package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

public class FiridiumMeta implements DFArmourSetMeta {
    @Override
    public boolean AdditionalQualification(Player plr, DFArmourSet armourSet, ItemStack equippedHelmet, ItemStack equippedChestplate, ItemStack equippedLeggings, ItemStack equippedBoots) {
        return DFItemUtils.hasFireAspect(equippedHelmet) && DFItemUtils.hasFireAspect(equippedChestplate) && DFItemUtils.hasFireAspect(equippedLeggings) && DFItemUtils.hasFireAspect(equippedBoots);
    };

    @Override
    public void PlayerHit(Player plr, DFArmourSet armourSet, EntityDamageByEntityEvent event) {
        event.getDamager().setFireTicks(60); // set on fire
    };
}
