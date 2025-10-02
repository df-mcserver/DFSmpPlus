package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

public class HungerlessMeta implements DFArmourSetMeta {
    @Override
    public void PlayerHunger(Player plr, DFArmourSet armourSet, FoodLevelChangeEvent event) {
        if (event.getItem() == null) event.setCancelled(true);
    }
}
