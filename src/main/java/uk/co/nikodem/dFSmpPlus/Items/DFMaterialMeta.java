package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;

public interface DFMaterialMeta {
    public default void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {};
    public default void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {};
    public default void ItemPickup(Player plr, DFMaterial material, ItemStack item, PlayerAttemptPickupItemEvent event) {};
    public default void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {};
    public default void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {};
    public default void ItemStartMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakProgressUpdateEvent event) {};
    public default void ItemDrop(Player plr, DFMaterial material, ItemStack item, PlayerDropItemEvent event) {};
    public default void ItemCrafted(DFMaterial material, ItemStack item, CraftItemEvent event) {};
    public default void ItemCreated(DFMaterial material, ItemStack item) {};

    public default Enums.UpdateResult ItemUpdated(DFMaterial material, ItemStack item) { return Enums.UpdateResult.NULL; };
}
