package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.Metas.TargetDummyMeta;

public class OnInteract implements Listener {
    @EventHandler
    public void OnInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
            if (event.getRightClicked().getPersistentDataContainer().has(Keys.targetDummy))
                TargetDummyMeta.TargetDummyInteracted(event);
        }
    }
}
