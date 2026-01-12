package uk.co.nikodem.dFSmpPlus.Accessories.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.Arrays;

public class AccessoryUI {
    public static int[] slots = {10, 12, 14, 16, 31};
    public static int[] configSlots = {19, 21, 23, 25, 40};

    public static void open(Player plr) {
        AccessoryInventory ui = new AccessoryInventory();
        plr.openInventory(ui.getInventory());
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player plr = (Player) event.getWhoClicked();
        if (inv.getHolder() instanceof AccessoryInventory) {
            Inventory clickedInventory = event.getClickedInventory();
            if (clickedInventory == null) return;

            if (clickedInventory.getHolder() instanceof AccessoryInventory) {
                if (!isAccessorySlot(event.getSlot())) event.setCancelled(true);

                event.getView().getPlayer().sendMessage(String.valueOf(event.getSlot()));
            } else if (clickedInventory instanceof PlayerInventory plrInv) {
                if (event.isShiftClick()) {
                    PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
                    ItemStack itemClicked = event.getCurrentItem();
                    if (itemClicked == null || itemClicked.getType() == Material.AIR) {
                        event.setCancelled(true);
                        return;
                    }
                    for (int i = 0; i < accessoryData.slots.length; i++) {
                        ItemStack itemInAccessorySlot = accessoryData.slots[i];
                        if (itemInAccessorySlot == null || itemInAccessorySlot.getType().equals(Material.AIR)) {
                            accessoryData.slots[i] = itemClicked.clone();

                            plr.sendMessage(itemClicked.displayName());
                            plr.sendMessage(String.valueOf(i));

                            AccessoryManager.updatePlayerData(plr, accessoryData);
                            return;
                        }
                    }

                    event.setCancelled(true);
                    // going into a slot
                }
            }
        }
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
