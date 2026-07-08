package uk.co.nikodem.dFSmpPlus.Events.Entity;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityZapEvent implements Listener {
    @EventHandler
    public void EntityZapEvent(com.destroystokyo.paper.event.entity.EntityZapEvent event) {

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Zapp!"));

        // prevent villagers which have been traded with to be turned into witches
        if (event.getTransformReason().equals(org.bukkit.event.entity.EntityTransformEvent.TransformReason.LIGHTNING)) {
            if (event.getEntity() instanceof Villager villager) {
                if (villager.getVillagerLevel() > 1) {
                    villager.setFireTicks(0);
                    Block b = villager.getLocation().getBlock();
                    if (b.getType() == Material.FIRE) {
                        b.setType(Material.AIR);
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
