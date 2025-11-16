package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Entities.CustomDrops.DFCustomDrops;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialEvents;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatEvents;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;

public class EntityDeathEvent implements Listener {
    private final DFSmpPlus plugin;

    public EntityDeathEvent(DFSmpPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void EntityDeathEvent(org.bukkit.event.entity.EntityDeathEvent event) {
        DFMaterialEvents.ItemKillEntity(event);
        TelemetryUtils.increaseDeath(event.getEntity(), event.getDamageSource().getCausingEntity());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (DFCustomDrops drops : DFCustomDrops.CustomDropsMap) {
                LivingEntity entity = event.getEntity();

                if (drops.getType() != event.getEntityType()) continue;

                if (!drops.runCheck(event)) continue;
                if (!drops.runRandomCheck()) continue;

                ItemStack item = drops.getItem();
                item.setAmount((int) (Math.random() * drops.getMaximum() + drops.getMinimum()));
                entity.getWorld().dropItem(entity.getLocation(), item);
            }
        }, 1L);
    }
}
