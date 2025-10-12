package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.Metas.TargetDummyMeta;

public class PlayerInteractAtEntityEvent implements Listener {
    @EventHandler
    public void PlayerInteractAtEntityEvent(org.bukkit.event.player.PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
            if (event.getRightClicked().getPersistentDataContainer().has(Keys.targetDummy))
                TargetDummyMeta.TargetDummyInteracted(event);
        }
    }
}
