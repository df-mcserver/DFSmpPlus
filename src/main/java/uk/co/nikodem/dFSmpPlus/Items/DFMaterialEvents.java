package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class DFMaterialEvents {
    public static void ItemPickup(PlayerAttemptPickupItemEvent e) {
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

    static void doOffhand(Player plr, EntityDamageByEntityEvent e) {
        ItemStack offhandItem = plr.getInventory().getItemInOffHand();
        DFMaterial material2 = DFItemUtils.getDFMaterial(offhandItem);

        if (material2 == null) return;

        if (material2.hasMeta()) {
            for (DFMaterialMeta meta : material2.getMeta()) {
                meta.ItemAttackWhileOffhand(plr, material2, offhandItem, e);
            }
        }
    }

    public static void ItemAttack(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (damager instanceof Player plr) {
            ItemStack weapon = plr.getInventory().getItemInMainHand();
            DFMaterial material = DFItemUtils.getDFMaterial(weapon);

            if (material == null) {
                doOffhand(plr, e);
                return;
            }

            if (material.hasMeta()) {
                for (DFMaterialMeta meta : material.getMeta()) {
                    meta.ItemAttack(plr, material, weapon, e);
                }
            }

            doOffhand(plr, e);
        }
    }

    public static void ItemDrop(PlayerDropItemEvent e) {
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

    public static void ItemUse(PlayerInteractEvent e) {
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

    public static void ItemUseOnEntity(PlayerInteractEntityEvent e) {
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

    public static void ItemMine(BlockBreakEvent e) {
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

    public static void ItemStartMining(BlockBreakProgressUpdateEvent e) {
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

    public static void BucketUseEvent(PlayerBucketFillEvent e) {
        Player plr = e.getPlayer();

        ItemStack tool = plr.getInventory().getItemInMainHand();

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.BucketUseEvent(plr, material, tool, e);
            }
        }
    }

    public static void ItemConsumed(PlayerItemConsumeEvent e) {
        Player plr = e.getPlayer();

        ItemStack tool = plr.getInventory().getItemInMainHand();

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemConsumed(plr, material, tool, e);
            }
        }
    }

    public static void ItemKillEntity(EntityDeathEvent e) {
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
