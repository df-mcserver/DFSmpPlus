package uk.co.nikodem.dFSmpPlus.Events.Block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.SetBonuses.SetBonusText;

public class BlockDispenseArmorEvent implements Listener {
    @EventHandler
    public void BlockDispenseArmorEvent(org.bukkit.event.block.BlockDispenseArmorEvent event) {
        SetBonusText.onArmourDispensed(event);
    }
}
