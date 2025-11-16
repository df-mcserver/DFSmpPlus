package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.Items.Metas.TargetDummyMeta;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatEvents;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetEvents;

public class EntityDamageByEntityEvent implements Listener {
    @EventHandler
    public void EntityDamageByEntityEvent(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
        DFMaterialEvents.ItemAttack(event);
        DFArmourSetEvents.PlayerAttack(event);
        CombatEvents.onAttack(event);

        if (event.getDamager().getType() == EntityType.ZOMBIE && event.getDamager() instanceof Ageable entity) {
            if (!entity.isAdult()) event.setDamage(event.getDamage() * 0.5);
        } else if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
            if (event.getEntity().getPersistentDataContainer().has(Keys.targetDummy))
                TargetDummyMeta.TargetDummyHit(event);
        }
    }
}
