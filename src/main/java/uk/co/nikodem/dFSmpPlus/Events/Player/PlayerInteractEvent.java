package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.Player.EnderEggStoragePreventer;
import uk.co.nikodem.dFSmpPlus.SetBonuses.SetBonusText;

public class PlayerInteractEvent implements Listener {
    @EventHandler
    public void PlayerInteractEvent(org.bukkit.event.player.PlayerInteractEvent event) {
        EnderEggStoragePreventer.onBlockPrevention(event);
        SetBonusText.onItemUsed(event);
        AccessoryEvents.UserInteract(event);
        DFMaterialEvents.ItemUse(event);
    }
}
