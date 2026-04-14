package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DFMaterialMeta {
    default void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {};
    default void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {};
    default void ItemPickup(Player plr, DFMaterial material, ItemStack item, PlayerAttemptPickupItemEvent event) {};
    default void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {};
    default void ItemAttackWhileOffhand(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {};
    default void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {};
    default void ItemMinedBlockDropItem(Player plr, DFMaterial material, ItemStack tool, BlockDropItemEvent event) {};
    default void ItemStartMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakProgressUpdateEvent event) {};
    default void ItemDrop(Player plr, DFMaterial material, ItemStack item, PlayerDropItemEvent event) {};
    default void ItemCrafted(DFMaterial material, ItemStack item, Player plr, @Nullable CraftItemEvent event) {};
    default void ItemCreated(DFMaterial material, ItemStack item) {};
    default void ItemKilledEntity(Player plr, DFMaterial material, Entity target, EntityDeathEvent event) {};
    default void ItemConsumed(Player plr, DFMaterial material, ItemStack item, PlayerItemConsumeEvent event) {};
    default void ItemPlaced(Player plr, DFMaterial material, ItemStack item, BlockPlaceEvent event) {};
    default void BucketFillEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {};
    default void BucketEmptyEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEmptyEvent event) {};
    default void BucketEntityEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEntityEvent event) {};

    default List<TextComponent> AddAdditionalLore(DFMaterial material) {return List.of();};
    default void ItemUpdated(DFMaterial material, ItemStack item) { };
}
