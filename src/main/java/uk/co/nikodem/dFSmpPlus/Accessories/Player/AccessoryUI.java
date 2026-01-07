package uk.co.nikodem.dFSmpPlus.Accessories.Player;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

public class AccessoryUI {
    public static int slot1Index = 10;
    public static int slot1ConfigIndex = slot1Index+9;
    public static int slot2Index = 12;
    public static int slot2ConfigIndex = slot2Index+9;
    public static int slot3Index = 14;
    public static int slot3ConfigIndex = slot3Index+9;
    public static int slot4Index = 16;
    public static int slot4ConfigIndex = slot4Index+9;
    public static int slot5Index = 31;
    public static int slot5ConfigIndex = slot5Index+9;

    public static void open(Player plr) {
        AccessoryInventory ui = new AccessoryInventory();
        plr.openInventory(ui.getInventory());
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if (inv.getHolder() instanceof AccessoryInventory) {
            Inventory clickedInventory = event.getClickedInventory();
            if (clickedInventory == null) return;

            if (clickedInventory.getHolder() instanceof AccessoryInventory) {
                if (!isAccessorySlot(event.getSlot())) event.setCancelled(true);

                event.getView().getPlayer().sendMessage(String.valueOf(event.getSlot()));
            }
        }
    }

    public static void onInventoryOpen(InventoryOpenEvent event) {
        InventoryView view = event.getView();
        view.setItem(slot1Index, ItemStack.of(Material.OAK_PLANKS));
        view.setItem(slot1ConfigIndex, ItemStack.of(Material.OAK_HANGING_SIGN));

        view.setItem(slot2Index, ItemStack.of(Material.DARK_OAK_BOAT));
        view.setItem(slot2ConfigIndex, ItemStack.of(Material.DARK_OAK_BUTTON));

        view.setItem(slot3Index, ItemStack.of(Material.SPRUCE_LOG));
        view.setItem(slot3ConfigIndex, ItemStack.of(Material.SPRUCE_FENCE_GATE));

        view.setItem(slot4Index, ItemStack.of(Material.ACACIA_BOAT));
        view.setItem(slot4ConfigIndex, ItemStack.of(Material.ACACIA_DOOR));

        view.setItem(slot5Index, ItemStack.of(Material.ORANGE_BED));
        view.setItem(slot5ConfigIndex, ItemStack.of(Material.ORANGE_STAINED_GLASS_PANE));

    }

    public static boolean isAccessorySlot(int index) {
        return index == slot1Index || index == slot2Index || index == slot3Index || index == slot4Index || index == slot5Index;
    }

    public static boolean isAccessoryConfigSlot(int index) {
        return index == slot1ConfigIndex || index == slot2ConfigIndex || index == slot3ConfigIndex || index == slot4ConfigIndex || index == slot5ConfigIndex;
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
