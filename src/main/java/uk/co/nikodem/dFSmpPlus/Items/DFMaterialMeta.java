package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;

public interface DFMaterialMeta {
    default void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {};
    default void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {};
    default void ItemPickup(Player plr, DFMaterial material, ItemStack item, PlayerAttemptPickupItemEvent event) {};
    default void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {};
    default void ItemAttackWhileOffhand(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {};
    default void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {};
    default void ItemStartMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakProgressUpdateEvent event) {};
    default void ItemDrop(Player plr, DFMaterial material, ItemStack item, PlayerDropItemEvent event) {};
    default void ItemCrafted(DFMaterial material, ItemStack item, CraftItemEvent event) {};
    default void ItemCreated(DFMaterial material, ItemStack item) {};
    default void ItemKilledEntity(Player plr, DFMaterial material, Entity target, EntityDeathEvent event) {};
    default void BucketUseEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {};

    default Enums.UpdateResult ItemUpdated(DFMaterial material, ItemStack item) { return Enums.UpdateResult.NULL; };
}
