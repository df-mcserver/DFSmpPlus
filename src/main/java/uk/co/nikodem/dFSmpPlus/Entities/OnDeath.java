package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Entities.CustomDrops.DFCustomDrops;

public class OnDeath implements Listener {
    private DFSmpPlus plugin;

    public OnDeath(DFSmpPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnDeath(EntityDeathEvent event) {
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
