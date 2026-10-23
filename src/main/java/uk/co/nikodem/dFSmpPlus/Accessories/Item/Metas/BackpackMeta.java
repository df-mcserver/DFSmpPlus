package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.GlobalData;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.Types.BackpackItemStorage;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.List;
import java.util.Map;

public class BackpackMeta implements AccessoryMeta {
    private final int slots;

    public BackpackMeta(int slots) {
        this.slots = slots;
    }

    public void openBackpack(Player plr, ItemStack item) {
        String uuid = DFItemUtils.getUUID(item);
        if (uuid == null) return;

        GlobalData globalData = DFSmpPlus.globalDataHandler.getGlobalData();
        BackpackItemStorage backpackItemStorage = globalData.backpackMappings.getOrDefault(uuid, new BackpackItemStorage(uuid, MiniMessage.miniMessage().serialize(item.effectiveName()), slots, InventoryType.BARREL));
        backpackItemStorage.maxSlots = slots;
        backpackItemStorage.title = MiniMessage.miniMessage().serialize(item.effectiveName());

        Inventory inv = backpackItemStorage.getInventory();

        for (Map.Entry<Integer, ItemStack> entry : backpackItemStorage.items.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue());
        }

        plr.openInventory(inv);
        globalData.backpackMappings.replace(uuid, backpackItemStorage);
    }

    public List<AccessoryAction> GetAccessoryActions() {
        return List.of(
                new AccessoryAction.Builder()
                        .setChooseItemStackicon((plr, accessoryItem, accessoryInformation) -> {
                            ItemStack clickToEnable = ItemStack.of(Material.ORANGE_STAINED_GLASS_PANE);
                            ItemMeta meta = clickToEnable.getItemMeta();
                            meta.displayName(MiniMessage.miniMessage().deserialize("Open "+MiniMessage.miniMessage().serialize(accessoryItem.effectiveName())));
                            clickToEnable.setItemMeta(meta);
                            return clickToEnable;

                        })
                        .setOnClickAction((plr, accessoryItem, accessoryInformation,inventoryClickEvent) -> {
                            openBackpack(plr, accessoryItem);
                        })
                        .create()
        );
    };

    public static void onInventoryClose(InventoryCloseEvent event) {
        Player plr = (Player) event.getPlayer();
        Inventory inv = event.getInventory();

        if (inv.getHolder() instanceof BackpackItemStorage backpackItemStorage) {
            int i = 0;

            for (ItemStack item : inv.getContents()) {
                if (item == null) {
                    backpackItemStorage.items.replace(i, null);
                    i++;
                    continue;
                };
                if (item.isEmpty()) {
                    backpackItemStorage.items.replace(i, null);
                    i++;
                    continue;
                }

                if (!canBeStoredInBackpack(item)) {
                    backpackItemStorage.items.remove(i);
                    plr.dropItem(item);
                } else {
                    backpackItemStorage.items.put(i, item);
                }

                i++;
            }

            GlobalData globalData = DFSmpPlus.globalDataHandler.getGlobalData();
            globalData.backpackMappings.put(backpackItemStorage.uuid, backpackItemStorage);
            DFSmpPlus.globalDataHandler.writeGlobalData(globalData);
        }
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        Player plr = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        Inventory clickedInventory = event.getClickedInventory();

        if (inv.getHolder() instanceof BackpackItemStorage backpackItemStorage) {
            if (clickedInventory != null) {
                if (clickedInventory.getHolder() instanceof BackpackItemStorage && !canBeStoredInBackpack(event.getCursor())) {
                    event.setCancelled(true);
                    return;
                }

                if (clickedInventory instanceof PlayerInventory && event.isShiftClick() && !canBeStoredInBackpack(event.getCurrentItem())) {
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getAction().equals(InventoryAction.HOTBAR_SWAP)) {
                int hotbar_index = event.getHotbarButton();
                ItemStack hotbar_item = plr.getInventory().getItem(hotbar_index);

                if (hotbar_item == null) return;

                if (!canBeStoredInBackpack(hotbar_item)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void onItemDestroy(EntityDamageEvent event) {
        if (event.getEntity() instanceof Item item) {
            ItemStack itemStack = item.getItemStack();

            AccessoryInformation info = DFItemUtils.getAccessoryInformation(itemStack);
            if (info == null) return;

            if (!info.hasMeta()) return;
            for (AccessoryMeta meta : info.getMeta()) {
                if (meta.getClass().equals(BackpackMeta.class)) {
                    String uuid = DFItemUtils.getUUID(itemStack);
                    if (uuid == null) return;

                    GlobalData globalData = DFSmpPlus.globalDataHandler.getGlobalData();
                    globalData.backpackMappings.remove(uuid);

                    DFSmpPlus.globalDataHandler.writeGlobalData(globalData);
                    return;
                }
            }
        }
    }

    public static boolean canBeStoredInBackpack(ItemStack item) {
        if (Tag.SHULKER_BOXES.isTagged(item.getType())) return false;
        if (Tag.ITEMS_BUNDLES.isTagged(item.getType())) return false;
        if (item.getType().equals(Material.DRAGON_EGG)) return false;

        AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
        if (info == null) return true;

        if (!info.hasMeta()) return true;
        for (AccessoryMeta meta : info.getMeta()) {
            if (meta.getClass().equals(BackpackMeta.class)) return false;
        }

        return true;
    }

    public static boolean openPlayerBackpack(Player plr) {
        PlayerAccessoryData data = AccessoryManager.getPlayerAccessoryData(plr);
        for (ItemStack accessoryItem : data.slots) {
            if (accessoryItem == null) continue;
            if (accessoryItem.isEmpty()) continue;
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) continue;

            for (AccessoryMeta meta : info.getMeta()) {
                if (meta instanceof BackpackMeta backpackMeta) {
                    backpackMeta.openBackpack(plr, accessoryItem);
                    return true;
                }
            }
        }

        return false;
    }

    public static void onDropItem(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        ItemStack itemStack = item.getItemStack();

        String uuid = DFItemUtils.getUUID(itemStack);
        if (uuid == null) return;

        Player plr = event.getPlayer();

        InventoryView view = plr.getOpenInventory();
        if (view.getTopInventory().getHolder() instanceof BackpackItemStorage backpackItemStorage) {
            if (backpackItemStorage.uuid.equals(uuid)) {
                plr.closeInventory(InventoryCloseEvent.Reason.CANT_USE);
            }
        }
    }

    public static void onDeath(PlayerDeathEvent event) {
        Player plr = event.getPlayer();

        InventoryView view = plr.getOpenInventory();
        if (view.getTopInventory().getHolder() instanceof BackpackItemStorage) {
            plr.closeInventory(InventoryCloseEvent.Reason.CANT_USE);
        }
    }
}
