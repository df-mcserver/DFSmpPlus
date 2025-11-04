package uk.co.nikodem.dFSmpPlus.Events.World;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.World.PopulateChests;

public class LootGenerateEvent implements Listener {
    @EventHandler
    public void LootGenerateEvent(org.bukkit.event.world.LootGenerateEvent event) {
        PopulateChests.PopulateChests(event);
    }
}
