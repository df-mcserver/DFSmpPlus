package uk.co.nikodem.dFSmpPlus.Events.Entity.Food;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetEvents;

public class FoodLevelChangeEvent implements Listener {
    @EventHandler
    public void FoodLevelChangeEvent(org.bukkit.event.entity.FoodLevelChangeEvent event) {
        DFArmourSetEvents.PlayerHunger(event);
    }
}
