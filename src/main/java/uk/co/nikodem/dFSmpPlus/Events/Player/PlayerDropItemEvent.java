package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.SetBonuses.SetBonusText;

public class PlayerDropItemEvent implements Listener {
    @EventHandler
    public void PlayerDropItemEvent(org.bukkit.event.player.PlayerDropItemEvent event) {
        SetBonusText.onItemDrop(event);
        DFMaterialEvents.ItemDrop(event);
    }
}
