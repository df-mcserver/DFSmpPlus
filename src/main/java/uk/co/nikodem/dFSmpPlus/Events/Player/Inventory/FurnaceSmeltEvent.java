package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;

public class FurnaceSmeltEvent implements Listener {
    @EventHandler
    public void FurnaceSmeltEvent(org.bukkit.event.inventory.FurnaceSmeltEvent event) {
        TelemetryUtils.increaseSmelting(event);
    }
}
