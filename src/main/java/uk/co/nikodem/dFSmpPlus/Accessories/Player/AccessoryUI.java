package uk.co.nikodem.dFSmpPlus.Accessories.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.Arrays;
import java.util.List;

public class AccessoryUI {
    public static void open(Player plr) {
        AccessoryInventory ui = new AccessoryInventory();
        plr.openInventory(ui.getInventory());

        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        accessoryData.accessoryInsertLock = false;
    }

    public static void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof AccessoryActionMultipleChoiceInventory) {
            Bukkit.getScheduler().runTaskLater(DFSmpPlus.getProvidingPlugin(DFSmpPlus.class), () -> {
                open((Player) event.getPlayer());
            }, 1L);
        }
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player plr = (Player) event.getWhoClicked();
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        if (inv.getHolder() instanceof AccessoryInventory) {
            Inventory clickedInventory = event.getClickedInventory();
            if (clickedInventory == null) return;

            if (clickedInventory.getHolder() instanceof AccessoryInventory) {
                if (isAccessoryConfigSlot(accessoryData, event.getSlot())) {
                    event.setCancelled(true);
                    ItemStack clickedOption = event.getCurrentItem();
                    if (clickedOption == null) return;
                    Integer realAccessorySlotIndex = convertInventorySlotIntoAccessoryConfigSlot(accessoryData, event.getSlot());
                    if (realAccessorySlotIndex == null) return;
                    int accessorySlot = getSlots(accessoryData)[realAccessorySlotIndex];
                    ItemStack accessory = inv.getItem(accessorySlot);
                    if (accessory == null) return;

                    AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessory);
                    if (info == null) return;
                    if (clickedOption.isSimilar(getMultichoice())) {
                        AccessoryActionMultipleChoiceInventory optionUI = new AccessoryActionMultipleChoiceInventory(PlainTextComponentSerializer.plainText().serialize(accessory.displayName()), realAccessorySlotIndex);
                        plr.openInventory(optionUI.getInventory());
                        return;
                    }
                    for (AccessoryMeta meta : info.getMeta()) {
                        for (AccessoryAction action : meta.GetAccessoryActions()) {
                            action.doOnClick(plr, accessory, info, event);
                            if (action.shouldUpdateIconOnClick()) {
                                ItemStack itemUpdate = action.getItemStackIcon(plr, accessory, info);
                                event.getInventory().setItem(event.getSlot(), itemUpdate);
                            }
                        }
                    }

                    return;
                }
                if (!isAccessorySlot(accessoryData, event.getSlot())) {
                    event.setCancelled(true);
                    return;
                }

                if (accessoryData.accessoryInsertLock) {
                    event.setCancelled(true);
                    return;
                }
                accessoryData.accessoryInsertLock = true;

                Integer realAccessorySlotIndex = convertInventorySlotIntoAccessorySlot(accessoryData, event.getSlot());
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
                } else if (Boolean.TRUE.equals(successfullyUnequipped)) {
                    int configSlot = getConfigSlots(accessoryData)[realAccessorySlotIndex];
                    inv.setItem(configSlot, getBlankSlot());
                }

                Boolean successfullyEquipped = insertItemIntoAccessorySlot(plr, itemInCursor, realAccessorySlotIndex);
                if (Boolean.TRUE.equals(successfullyEquipped)) {
                    event.setCancelled(true);
                    ItemStack newItem = itemInCursor.clone();
                    clickedInventory.setItem(event.getSlot(), newItem);
                    if (itemClicked != null) plr.setItemOnCursor(itemClicked.clone());
                    else itemInCursor.setAmount(0);

                    AccessoryInformation info = DFItemUtils.getAccessoryInformation(newItem);
                    int configSlot = getConfigSlots(accessoryData)[realAccessorySlotIndex];
                    if (info != null) {
                        ItemStack configItem = getConfigItemStack(plr, newItem, info);
                        if (configItem != null) inv.setItem(configSlot, configItem);
                    }
                } else if (Boolean.FALSE.equals(successfullyEquipped)) {
                    event.setCancelled(true);
                }

                accessoryData.accessoryInsertLock = false;
            } else if (clickedInventory instanceof PlayerInventory plrInv) {
                if (event.isShiftClick()) {
                    if (accessoryData.accessoryInsertLock) {
                        event.setCancelled(true);
                        return;
                    }
                    accessoryData.accessoryInsertLock = true;
                    ItemStack item = event.getCurrentItem();
                    for (int i = 0; i <= 4; i ++) {
                        Boolean result = (insertItemIntoAccessorySlot(plr, item, i));
                        if (Boolean.TRUE.equals(result)) {
                            AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
                            int configSlot = getConfigSlots(accessoryData)[i];
                            if (info != null) {
                                ItemStack configItem = getConfigItemStack(plr, item, info);
                                if (configItem != null) inv.setItem(configSlot, configItem);
                            }

                            accessoryData.accessoryInsertLock = false;
                            return;
                        }
                    }

                    event.setCancelled(true);

                    accessoryData.accessoryInsertLock = false;
                }
            }
        } else if (inv.getHolder() instanceof AccessoryActionMultipleChoiceInventory multipleChoiceInventory) {
            int accessorySlotIndex = multipleChoiceInventory.getAccessorySlotIndex();
            event.setCancelled(true);

            ItemStack accessoryItem = accessoryData.slots[accessorySlotIndex];
            if (accessoryItem == null) {
                event.setCancelled(true);
                return;
            }

            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) {
                event.setCancelled(true);
                return;
            }

            int i = 0;
            for (AccessoryMeta meta : info.getMeta()) {
                for (AccessoryAction action : meta.GetAccessoryActions()) {
                    if (i == event.getSlot()) action.doOnClick(plr, accessoryItem, info, event);
                    i++;
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
        if (accessoryData.hasConflictingAccessoryEquipped(info)) return false;

        for (int i : indexesToCheck) {
            if (i > accessoryData.getAccessoryCapIndex()) break;
            ItemStack itemInAccessorySlot = accessoryData.slots[i];
            if (itemInAccessorySlot == null || itemInAccessorySlot.getType().equals(Material.AIR)) {
                ItemStack newItem = item.clone();
                accessoryData.slots[i] = newItem;
                AccessoryEvents.AccessoryEquipped(plr, newItem, info);
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

    @Nullable
    public static ItemStack getConfigItemStack(Player plr, ItemStack item, AccessoryInformation info) {
        ItemStack lastChosenItemStack = null;
        for (AccessoryMeta meta : info.getMeta()) {
            List<AccessoryAction> actions = meta.GetAccessoryActions();
            if (actions.isEmpty()) continue;
            for (AccessoryAction action : actions) {
                ItemStack chosenItem = action.getItemStackIcon(plr, item, info);
                if (chosenItem != null && lastChosenItemStack != null) {
                    return getMultichoice();
                }

                lastChosenItemStack = chosenItem;
            }
        }

        return lastChosenItemStack;
    }

    public static void onInventoryOpen(InventoryOpenEvent event) {
        Inventory inv = event.getInventory();
        Player plr = (Player) event.getPlayer();
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        if (inv.getHolder() instanceof AccessoryInventory) {
            InventoryView view = event.getView();

            int i = 0;
            for (int slot : getSlots(accessoryData)) {
                ItemStack item = accessoryData.slots[i];
                if (item == null) item = ItemStack.of(Material.AIR);

                if (i > accessoryData.getAccessoryCapIndex()) item = getBlankSlot();

                if (i <= accessoryData.getAccessoryCapIndex()) {
                    AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
                    if (info != null) {
                        int configSlot = getConfigSlots(accessoryData)[i];
                        ItemStack configItem = getConfigItemStack(plr, item, info);
                        if (configItem != null) {
                            view.setItem(configSlot, configItem);
                        }
                    }
                }

                view.setItem(slot, item);
                i++;
            }
        } else if (inv.getHolder() instanceof AccessoryActionMultipleChoiceInventory multipleChoiceInventory) {
            InventoryView view = event.getView();
            int accessorySlotIndex = multipleChoiceInventory.getAccessorySlotIndex();

            ItemStack accessoryItem = accessoryData.slots[accessorySlotIndex];
            if (accessoryItem == null) {
                event.setCancelled(true);
                return;
            }

            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) {
                event.setCancelled(true);
                return;
            }

            int i = 0;
            for (AccessoryMeta meta : info.getMeta()) {
                for (AccessoryAction action : meta.GetAccessoryActions()) {
                    view.setItem(i, action.getItemStackIcon(plr, accessoryItem, info));
                    i++;
                }
            }
        }
    }

    public static boolean isAccessorySlot(PlayerAccessoryData accessoryData, int index) {
        return Arrays.binarySearch(getSlots(accessoryData), index) >= 0;
    }

    public static boolean isAccessoryConfigSlot(PlayerAccessoryData accessoryData, int index) {
        return Arrays.binarySearch(getConfigSlots(accessoryData), index) >= 0;
    }

    @Nullable
    public static Integer convertInventorySlotIntoAccessorySlot(PlayerAccessoryData accessoryData, int index) {
        return doIndexSearch(getSlots(accessoryData), index);
    }

    @Nullable
    public static Integer convertInventorySlotIntoAccessoryConfigSlot(PlayerAccessoryData accessoryData, int index) {
        return doIndexSearch(getConfigSlots(accessoryData), index);
    }

    @Nullable
    public static Integer doIndexSearch(int[] array, int index) {
        int result = Arrays.binarySearch(array, index);
        if (result < 0) return null;
        else return result;
    }

    public static int[] getSlots(PlayerAccessoryData accessoryData) {
        int slotCount = accessoryData.getAccessoryCapIndex()+1;
        int[] slots = new int[slotCount];
        int xOffset = (int) Math.ceil((double) (9 - slotCount) / 2);
        for (int i = 0; i < slotCount; i++) {
            slots[i] = i+xOffset+9;
        }

        return slots;
    }

    public static int[] getConfigSlots(PlayerAccessoryData accessoryData) {
        int[] slots = getSlots(accessoryData);
        for (int i = 0; i < slots.length; i++) {
            slots[i] = slots[i] + 9;
        }
        return slots;
    }

    public static ItemStack getBlankSlot() {
        ItemStack blankSlot = ItemStack.of(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = blankSlot.getItemMeta();
        meta.setMaxStackSize(1);
        meta.setItemModel(Keys.emptySlotModel);
        meta.displayName(Component.text().build());
        blankSlot.setItemMeta(meta);

        return blankSlot;
    }

    public static ItemStack getMultichoice() {
        ItemStack multichoice = ItemStack.of(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta itemmeta = multichoice.getItemMeta();
        itemmeta.displayName(Component.text("<multiple accessory actions>"));
        PersistentDataContainer container = itemmeta.getPersistentDataContainer();
        container.set(Keys.multiconfigOption, PersistentDataType.BOOLEAN, true);
        multichoice.setItemMeta(itemmeta);

        return multichoice;
    }

    public static class AccessoryInventory implements InventoryHolder {
        private final Inventory baseInventory;

        public AccessoryInventory() {
            baseInventory = DFSmpPlus.getPlugin(DFSmpPlus.class).getServer().createInventory(this, 9*3, Component.text("Accessories"));

            ItemStack blankSlot = getBlankSlot();
            for (int i = 0; i < baseInventory.getSize(); i++) {
                baseInventory.setItem(i, blankSlot);
            }
        }

        @Override
        public @NotNull Inventory getInventory() {
            return baseInventory;
        }
    }

    public static class AccessoryActionMultipleChoiceInventory implements InventoryHolder {
        private final Inventory baseInventory;
        private final int accessorySlotIndex;
        private final String accessoryName;

        public AccessoryActionMultipleChoiceInventory(String accessoryName, int accessorySlotIndex) {
            baseInventory = DFSmpPlus.getPlugin(DFSmpPlus.class).getServer().createInventory(this, 9*3, Component.text("Accessory options for "+accessoryName));

            ItemStack blankSlot = getBlankSlot();
            for (int i = 0; i < baseInventory.getSize(); i++) {
                baseInventory.setItem(i, blankSlot);
            }

            this.accessoryName = accessoryName;
            this.accessorySlotIndex = accessorySlotIndex;
        }

        public int getAccessorySlotIndex() {
            return accessorySlotIndex;
        }

        public String getAccessoryName() {
            return accessoryName;
        }

        @Override
        public @NotNull Inventory getInventory() {
            return baseInventory;
        }
    }
}
