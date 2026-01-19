package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.List;
import java.util.Map;

public interface AccessoryMeta {
    default void RunPerSecond(Player plr, ItemStack accessory, AccessoryInformation info) {};
    default void AccessoryEquipped(Player plr, ItemStack accessory, AccessoryInformation info) {};
    default void AccessoryUnequipped(Player plr, ItemStack accessory, AccessoryInformation info) {};
    default void UserDamaged(Player plr, ItemStack accessory, AccessoryInformation info, EntityDamageEvent event) {};
    default void UserTargetted(Player plr, ItemStack accessory, AccessoryInformation info, EntityTargetEvent event) {};
    default void BlockMined(Player plr, ItemStack accessory, AccessoryInformation info, BlockBreakEvent event) {};
    default void UserAttacking(Player plr, ItemStack accessory, AccessoryInformation info, EntityDamageByEntityEvent event) {};
    default void UserKilledPlayer(Player plr, ItemStack accessory, AccessoryInformation info, PlayerDeathEvent event) {};
    default void UserInteract(Player plr, ItemStack accessory, AccessoryInformation info, PlayerInteractEvent event) {};
    default void UserMove(Player plr, ItemStack accessory, AccessoryInformation info, PlayerMoveEvent event) {};

    default Map<Attribute, AttributeModifier> AddAdditionalAttributeModifiers(Player plr, ItemStack accessory, AccessoryInformation info) {
        return Map.of();
    };
    default List<AccessoryAction> GetAccessoryActions() {
        return List.of();
    };
}
