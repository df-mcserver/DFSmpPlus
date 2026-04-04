package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class DFMaterialEvents {
    public static void ItemPickup(PlayerAttemptPickupItemEvent e) {
        Item itemEntity = e.getItem();
        ItemStack item = itemEntity.getItemStack();

        DFMaterial material = DFItemUtils.getDFMaterial(item);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (item.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemPickup(e.getPlayer(), null, item, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemPickup(e.getPlayer(), material, item, e);
            }
        }
    }

    static void doOffhand(Player plr, EntityDamageByEntityEvent e) {
        ItemStack offhandItem = plr.getInventory().getItemInOffHand();
        DFMaterial material = DFItemUtils.getDFMaterial(offhandItem);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (offhandItem.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemAttackWhileOffhand(plr, null, offhandItem, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemAttackWhileOffhand(plr, material, offhandItem, e);
            }
        }
    }

    public static void ItemAttack(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (damager instanceof Player plr) {
            ItemStack weapon = plr.getInventory().getItemInMainHand();
            DFMaterial material = DFItemUtils.getDFMaterial(weapon);

            if (material == null) {
                for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                    if (weapon.getType().equals(entry.getKey())) {
                        for (DFMaterialMeta meta : entry.getValue()) {
                            meta.ItemAttack(plr, null, weapon, e);
                        }
                    }
                }
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

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (item.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemDrop(plr, null, item, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemDrop(plr, material, item, e);
            }
        }
    }

    public static void ItemUse(PlayerInteractEvent e) {
        Player plr = e.getPlayer();
        ItemStack item = e.getItem();
        if (item == null) return;

        DFMaterial material = DFItemUtils.getDFMaterial(item);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (item.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemUse(plr, null, item, e);
                    }
                }
            }
            return;
        }

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

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (item.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemUseOnEntity(e.getPlayer(), null, item, e);
                    }
                }
            }
            return;
        }

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

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (tool.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemMine(plr, null, tool, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemMine(plr, material, tool, e);
            }
        }
    }

    public static void ItemMinedBlockDropItem(BlockDropItemEvent e) {
        Player plr = e.getPlayer();
        ItemStack tool = plr.getInventory().getItemInMainHand();

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (tool.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemMinedBlockDropItem(plr, null, tool, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemMinedBlockDropItem(plr, material, tool, e);
            }
        }
    }

    public static void ItemStartMining(BlockBreakProgressUpdateEvent e) {
        Entity entity = e.getEntity();

        if (entity instanceof Player plr) {
            ItemStack tool = plr.getInventory().getItemInMainHand();

            DFMaterial material = DFItemUtils.getDFMaterial(tool);

            if (material == null) {
                for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                    if (tool.getType().equals(entry.getKey())) {
                        for (DFMaterialMeta meta : entry.getValue()) {
                            meta.ItemStartMine(plr, null, tool, e);
                        }
                    }
                }
                return;
            }

            if (material.hasMeta()) {
                for (DFMaterialMeta meta : material.getMeta()) {
                    meta.ItemStartMine(plr, material, tool, e);
                }
            }
        }
    }

    public static void BucketFillEvent(PlayerBucketFillEvent e) {
        Player plr = e.getPlayer();
        ItemStack tool = plr.getInventory().getItem(e.getHand());

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (tool.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.BucketFillEvent(plr, null, tool, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.BucketFillEvent(plr, material, tool, e);
            }
        }
    }

    public static void BucketEmptyEvent(PlayerBucketEmptyEvent e) {
        Player plr = e.getPlayer();
        ItemStack tool = plr.getInventory().getItem(e.getHand());

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (tool.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.BucketEmptyEvent(plr, null, tool, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.BucketEmptyEvent(plr, material, tool, e);
            }
        }
    }

    public static void BucketEntityEvent(PlayerBucketEntityEvent e) {
        Player plr = e.getPlayer();

        ItemStack tool = plr.getInventory().getItem(e.getHand());

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (tool.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.BucketEntityEvent(plr, null, tool, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.BucketEntityEvent(plr, material, tool, e);
            }
        }
    }

    public static void ItemConsumed(PlayerItemConsumeEvent e) {
        Player plr = e.getPlayer();

        ItemStack tool = plr.getInventory().getItem(e.getHand());

        DFMaterial material = DFItemUtils.getDFMaterial(tool);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (tool.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemConsumed(plr, null, tool, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemConsumed(plr, material, tool, e);
            }
        }
    }

    public static void ItemPlaced(BlockPlaceEvent e) {
        Player plr = e.getPlayer();
        ItemStack item = e.getItemInHand();

        DFMaterial material = DFItemUtils.getDFMaterial(item);

        if (material == null) {
            for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                if (item.getType().equals(entry.getKey())) {
                    for (DFMaterialMeta meta : entry.getValue()) {
                        meta.ItemPlaced(plr, null, item, e);
                    }
                }
            }
            return;
        }

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemPlaced(plr, material, item, e);
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

            if (material == null) {
                for (Map.Entry<Material, List<DFMaterialMeta>> entry : VanillaItems.vanillaItemMetas.entrySet()) {
                    if (tool.getType().equals(entry.getKey())) {
                        for (DFMaterialMeta meta : entry.getValue()) {
                            meta.ItemKilledEntity(plr, null, target, e);
                        }
                    }
                }
                return;
            }

            if (material.hasMeta()) {
                for (DFMaterialMeta meta : material.getMeta()) {
                    meta.ItemKilledEntity(plr, material, target, e);
                }
            }
        }
    }
}
