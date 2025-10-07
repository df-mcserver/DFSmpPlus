package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DFMaterialEvents implements Listener {
    @EventHandler
    public void ItemPickup(PlayerAttemptPickupItemEvent e) {
        Item itemEntity = e.getItem();
        ItemStack item = itemEntity.getItemStack();

        DFMaterial material = DFItemUtils.getDFMaterial(item);

        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemPickup(e.getPlayer(), material, item, e);
            }
        }
    }

    @EventHandler
    public void ItemAttack(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();

        if (damager instanceof Player plr) {
            ItemStack weapon = plr.getInventory().getItemInMainHand();
            DFMaterial material = DFItemUtils.getDFMaterial(weapon);

            if (material == null) return;

            if (material.hasMeta()) {
                for (DFMaterialMeta meta : material.getMeta()) {
                    meta.ItemAttack(plr, material, weapon, e);
                }
            }
        }
    }

    @EventHandler
    public void ItemDrop(PlayerDropItemEvent e) {
        Player plr = e.getPlayer();

        ItemStack item = e.getItemDrop().getItemStack();
        DFMaterial material = DFItemUtils.getDFMaterial(item);

        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemDrop(plr, material, item, e);
            }
        }
    }

    @EventHandler
    public void ItemUse(PlayerInteractEvent e) {
        Player plr = e.getPlayer();
        ItemStack item = e.getItem();

        DFMaterial material = DFItemUtils.getDFMaterial(item);

        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemUse(plr, material, item, e);
            }
        }
    }

    @EventHandler
    public void ItemUseOnEntity(PlayerInteractEntityEvent e) {
        Player plr = e.getPlayer();
        ItemStack item = plr.getInventory().getItemInMainHand();

        DFMaterial material = DFItemUtils.getDFMaterial(item);

        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemUseOnEntity(plr, material, item, e);
            }
        }
    }

    @EventHandler
    public void ItemMine(BlockBreakEvent e) {
        Player plr = e.getPlayer();
        ItemStack tool = plr.getInventory().getItemInMainHand();

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemMine(plr, material, tool, e);
            }
        }
    }

    @EventHandler
    public void ItemStartMining(BlockBreakProgressUpdateEvent e) {
        Entity entity = e.getEntity();

        if (entity instanceof Player plr) {
            ItemStack tool = plr.getInventory().getItemInMainHand();

            DFMaterial material = DFItemUtils.getDFMaterial(tool);

            if (material == null) return;

            if (material.hasMeta()) {
                for (DFMaterialMeta meta : material.getMeta()) {
                    meta.ItemStartMine(plr, material, tool, e);
                }
            }
        }
    }

    @EventHandler
    public void ItemKillEntity(EntityDeathEvent e) {
        Entity target = e.getEntity();
        DamageSource source = e.getDamageSource();

        Entity causer = source.getCausingEntity(); // james causer
        if (causer == null) return;

        if (causer instanceof Player plr) {
            ItemStack tool = plr.getInventory().getItemInMainHand();

            DFMaterial material = DFItemUtils.getDFMaterial(tool);

            if (material == null) return;

            if (material.hasMeta()) {
                for (DFMaterialMeta meta : material.getMeta()) {
                    meta.ItemKilledEntity(plr, material, target, e);
                }
            }
        }
    }
}
