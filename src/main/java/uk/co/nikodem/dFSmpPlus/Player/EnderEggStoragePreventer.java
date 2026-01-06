package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.Material;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnderEggStoragePreventer {
    public static void onInventoryClick(InventoryClickEvent event) {
        Inventory clicked = event.getClickedInventory();
        if (event.getClick().isShiftClick()) {
            if (clicked == event.getWhoClicked().getInventory()) {
                // The item is being shift clicked from the bottom to the top
                ItemStack clickedOn = event.getCurrentItem();

                if (clickedOn != null && (clickedOn.getType() == Material.DRAGON_EGG)) {
                    event.setCancelled(true);
                }
            }
        } else {
            if (clicked != event.getWhoClicked().getInventory()) { // Note: !=
                // The cursor item is going into the top inventory
                ItemStack onCursor = event.getCursor();

                if (onCursor.getType() == Material.DRAGON_EGG){
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void onInventoryDrag(InventoryDragEvent event) {
        ItemStack dragged = event.getOldCursor(); // This is the item that is being dragged

        if (dragged.getType() == Material.DRAGON_EGG) {
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
            if (event.getItem().getItemStack().getType() == Material.DRAGON_EGG) event.setCancelled(true);
        }
    }

    public static void onItemFramePrevention(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame || event.getRightClicked() instanceof GlowItemFrame) {
            if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.DRAGON_EGG) {
                event.setCancelled(true);
            }
        }
    }
}
