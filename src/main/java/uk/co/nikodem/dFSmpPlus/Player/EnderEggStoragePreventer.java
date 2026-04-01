package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Shelf;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.BundleMeta;

public class EnderEggStoragePreventer {
    public static void onInventoryClick(InventoryClickEvent event) {
        Inventory clicked = event.getClickedInventory();
        if (event.getInventory() instanceof AnvilInventory || event.getInventory() instanceof CraftingInventory || event.getInventory() instanceof PlayerInventory) return;
        if (event.getClick().isShiftClick()) {
            if (clicked == event.getWhoClicked().getInventory()) {
                ItemStack clickedOn = event.getCurrentItem();

                if (clickedOn != null && (isForbiddenItem(clickedOn))) {
                    event.setCancelled(true);
                }
            }
        } else {
            if (clicked != event.getWhoClicked().getInventory()) {
                // The cursor item is going into the top inventory
                ItemStack onCursor = event.getCursor();

                if (isForbiddenItem(onCursor)){
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void onInventoryDrag(InventoryDragEvent event) {
        ItemStack dragged = event.getOldCursor(); // This is the item that is being dragged

        if (isForbiddenItem(dragged)) {
            int inventorySize = event.getInventory().getSize(); // The size of the inventory, for reference

            for (int i : event.getRawSlots()) {
                if (i < inventorySize) {
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }

    public static void onInventoryPickupItem(InventoryPickupItemEvent event) {
        if (event.getInventory().getType() == InventoryType.HOPPER) {
            if (isForbiddenItem(event.getItem().getItemStack())) event.setCancelled(true);
        }
    }

    public static void onBlockPrevention(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getBlockData() instanceof Shelf) {
            Player plr = event.getPlayer();
            if (plr.isSneaking()) return;
            if (isForbiddenItem(event.getPlayer().getInventory().getItemInMainHand())) {
                event.setCancelled(true);
            }
        }
    }

    public static void onItemFramePrevention(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame || event.getRightClicked() instanceof GlowItemFrame) {
            if (isForbiddenItem(event.getPlayer().getInventory().getItemInMainHand())) {
                event.setCancelled(true);
            }
        }
    }

    public static boolean isForbiddenItem(ItemStack item) {
        if (item.getType() == Material.DRAGON_EGG) return true;
        else if (item.getItemMeta() instanceof BundleMeta bundleMeta) {
            for (ItemStack bundleitem : bundleMeta.getItems()) {
                if (bundleitem.getType() == Material.DRAGON_EGG) {
                    return true;
                }
            }
        }
        return false;
    }
}
