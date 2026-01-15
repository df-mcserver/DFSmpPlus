package uk.co.nikodem.dFSmpPlus.Accessories.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import javax.print.DocFlavor;
import java.util.Arrays;

public class AccessoryUI {
    public static int[] slots = {10, 12, 14, 16, 31};
    public static int[] configSlots = {19, 21, 23, 25, 40};

    public static void open(Player plr) {
        AccessoryInventory ui = new AccessoryInventory();
        plr.openInventory(ui.getInventory());

        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        accessoryData.accessoryInsertLock = false;
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player plr = (Player) event.getWhoClicked();
        if (inv.getHolder() instanceof AccessoryInventory) {
            Inventory clickedInventory = event.getClickedInventory();
            if (clickedInventory == null) return;

            if (clickedInventory.getHolder() instanceof AccessoryInventory) {
                if (!isAccessorySlot(event.getSlot())) {
                    event.setCancelled(true);
                    return;
                }
                PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
                if (accessoryData.accessoryInsertLock) {
                    event.setCancelled(true);
                    return;
                }
                accessoryData.accessoryInsertLock = true;

                Integer realAccessorySlotIndex = convertInventorySlotIntoAccessorySlot(event.getSlot());
                if (realAccessorySlotIndex == null) {
                    event.setCancelled(true);
                    accessoryData.accessoryInsertLock = false;
                    return;
                }

                ItemStack itemClicked = event.getCurrentItem();
                ItemStack itemInCursor = event.getCursor();

                Boolean successfullyUnequipped = takeItemOutOfAccessorySlot(plr, itemClicked, realAccessorySlotIndex);
                if (Boolean.FALSE.equals(successfullyUnequipped)) {
                    event.setCancelled(true);
                    accessoryData.accessoryInsertLock = false;
                    return;
                }

                Boolean successfullyEquipped = insertItemIntoAccessorySlot(plr, itemInCursor, realAccessorySlotIndex);
                if (Boolean.TRUE.equals(successfullyEquipped)) {
                    event.setCancelled(true);
                    clickedInventory.setItem(event.getSlot(), itemInCursor.clone());
                    if (itemClicked != null) plr.setItemOnCursor(itemClicked.clone());
                    else itemInCursor.setAmount(0);
                } else if (Boolean.FALSE.equals(successfullyEquipped)) {
                    event.setCancelled(true);
                }

                accessoryData.accessoryInsertLock = false;
            } else if (clickedInventory instanceof PlayerInventory plrInv) {
                if (event.isShiftClick()) {
                    PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
                    if (accessoryData.accessoryInsertLock) {
                        event.setCancelled(true);
                        return;
                    }
                    accessoryData.accessoryInsertLock = true;
                    event.setCancelled(Boolean.FALSE.equals(insertItemIntoAccessorySlot(plr, event.getCurrentItem(), 0, 1, 2, 3, 4)));
                    accessoryData.accessoryInsertLock = false;
                }
            }
        }
    }

    public static Boolean insertItemIntoAccessorySlot(Player plr, ItemStack item, int... indexesToCheck) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }

        AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
        if (info == null) {
            return false;
        }

        if (accessoryData.isAccessoryEquipped(info)) return false;

        for (int i : indexesToCheck) {
            ItemStack itemInAccessorySlot = accessoryData.slots[i];
            if (itemInAccessorySlot == null || itemInAccessorySlot.getType().equals(Material.AIR)) {
                accessoryData.slots[i] = item.clone();
                AccessoryEvents.AccessoryEquipped(plr, item, info);
                AccessoryManager.updatePlayerData(plr, accessoryData);
                return true;
            }
        }

        return false;
    }

    public static Boolean takeItemOutOfAccessorySlot(Player plr, ItemStack item, int... indexesToCheck) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }

        AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
        if (info == null) {
            return false;
        }

        for (int i : indexesToCheck) {
            ItemStack itemInAccessorySlot = accessoryData.slots[i];
            if (itemInAccessorySlot == null) continue;
            if (itemInAccessorySlot.equals(item)) {
                accessoryData.slots[i] = null;
                AccessoryEvents.AccessoryUnequipped(plr, item, info);
                AccessoryManager.updatePlayerData(plr, accessoryData);
                return true;
            }
        }

        return false;
    }

    public static void onInventoryOpen(InventoryOpenEvent event) {
        Inventory inv = event.getInventory();
        Player plr = (Player) event.getPlayer();
        if (inv.getHolder() instanceof AccessoryInventory) {
            InventoryView view = event.getView();

            PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
            int i = 0;
            for (int slot : slots) {
                ItemStack item = accessoryData.slots[i];
                if (item == null) item = ItemStack.of(Material.AIR);

                view.setItem(slot, item);
                i++;
            }
        }
    }

    public static boolean isAccessorySlot(int index) {
        return Arrays.binarySearch(slots, index) >= 0;
    }

    @Nullable
    public static Integer convertInventorySlotIntoAccessorySlot(int index) {
        int result = Arrays.binarySearch(slots, index);
        if (result < 0) return null;
        else return result;
    }

    public static boolean isAccessoryConfigSlot(int index) {
        return Arrays.binarySearch(configSlots, index) >= 0;
    }

    public static class AccessoryInventory implements InventoryHolder {
        private final Inventory baseInventory;

        public AccessoryInventory() {
            baseInventory = DFSmpPlus.getPlugin(DFSmpPlus.class).getServer().createInventory(this, 9*6, Component.text("Accessories"));

            ItemStack blankSlot = ItemStack.of(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
            ItemMeta meta = blankSlot.getItemMeta();
            meta.setMaxStackSize(1);
            meta.setItemModel(Keys.emptySlotModel);
            meta.displayName(Component.text().build());
            blankSlot.setItemMeta(meta);
            for (int i = 0; i < baseInventory.getSize(); i++) {
                baseInventory.setItem(i, blankSlot);
            }
        }

        @Override
        public @NotNull Inventory getInventory() {
            return baseInventory;
        }
    }
}
