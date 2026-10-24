package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetEvents;

public class PlayerMoveEvent implements Listener {
    @EventHandler
    public void PlayerMoveEvent(org.bukkit.event.player.PlayerMoveEvent event) {
        AccessoryEvents.UserMove(event);
        DFArmourSetEvents.UserInteract(event);
    }
}
