package uk.co.nikodem.dFSmpPlus.Player.DFUpdates;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryEvents;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Commands.BasicCommands.DFMaterialView;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static uk.co.nikodem.dFSmpPlus.Constants.Enums.UpdateResult.*;
import static uk.co.nikodem.dFSmpPlus.Constants.Enums.UpdateResult.UPDATE_FAILED;

public class DFUpdateEvents {
    public static void onJoin(PlayerJoinEvent event) {
        Player plr = event.getPlayer();

        boolean atleastOneDeletion = false;
        boolean atleastOneFailure = false;

        for (int i = 0; i < plr.getInventory().getContents().length; i++) {
            ItemStack item = plr.getInventory().getContents()[i];
            if (item == null) continue;

            if (item.getType() == Material.BUNDLE) {
                BundleMeta meta = (BundleMeta) item.getItemMeta();
                List<ItemStack> newitems = new ArrayList<>();
                for (ItemStack itemInBundle : meta.getItems()) {
                    Map.Entry<Enums.UpdateResult, ItemStack> res = DFMaterialUpdater.doUpdate(itemInBundle);
                    Enums.UpdateResult result = res.getKey();
                    ItemStack replacedItemIfExists = res.getValue();
                    if (!Objects.equals(result, DELETED)) {
                        if (replacedItemIfExists.isEmpty()) {
                            newitems.add(itemInBundle);
                        } else {
                            newitems.add(replacedItemIfExists);
                        }
                    }

                    if (Objects.equals(result, DELETED)) atleastOneDeletion = true;
                    if (Objects.equals(result, UPDATE_FAILED)) atleastOneFailure = true;
                }
                meta.setItems(newitems);
                item.setItemMeta(meta);
            } else {
                Map.Entry<Enums.UpdateResult, ItemStack> res = DFMaterialUpdater.doUpdate(item);
                Enums.UpdateResult result = res.getKey();
                ItemStack replacedItemIfExists = res.getValue();

                if (Objects.equals(result, DELETED)) atleastOneDeletion = true;
                if (Objects.equals(result, UPDATE_FAILED)) atleastOneFailure = true;

                if (!replacedItemIfExists.isEmpty()) plr.getInventory().setItem(i, replacedItemIfExists);
            }
        }

        PlayerAccessoryData data = AccessoryManager.getPlayerAccessoryData(plr);
        for (int i = 0; i < data.slots.length; i++) {
            ItemStack item = data.slots[i];
            if (item == null) continue;
            Map.Entry<Enums.UpdateResult, ItemStack> res = DFMaterialUpdater.doUpdate(item);
            Enums.UpdateResult result = res.getKey();
            ItemStack replacedItemIfExists = res.getValue();

            if (Objects.equals(result, DELETED)) atleastOneDeletion = true;
            if (Objects.equals(result, UPDATE_FAILED)) atleastOneFailure = true;

            if (!replacedItemIfExists.isEmpty()) data.slots[i] = replacedItemIfExists;
        }

        AccessoryManager.updatePlayerData(plr, data);

        boolean removedInvalidAccessories = AccessoryEvents.RefreshAccessoryAttributes(plr);

        if (atleastOneDeletion || removedInvalidAccessories) {
            Component msg = MiniMessage.miniMessage().deserialize("<red>Some items in your inventory have been removed due to invalid data!");
            plr.sendMessage(msg);
            Sounds.Notification.playSoundLocally(plr);
        }

        if (atleastOneFailure) {
            Component msg = MiniMessage.miniMessage().deserialize("<red>Some items in your inventory have failed to update! Please contact the developer about this issue!");
            plr.sendMessage(msg);
            Sounds.Notification.playSoundLocally(plr);
        }
    }

    public static void onItemPickup(EntityPickupItemEvent event) {
        ItemStack i = event.getItem().getItemStack();

        if (i.getType() == Material.BUNDLE) {
            BundleMeta meta = (BundleMeta) i.getItemMeta();
            List<ItemStack> newitems = new ArrayList<>();
            for (ItemStack item : meta.getItems()) {
                Map.Entry<Enums.UpdateResult, ItemStack> res = DFMaterialUpdater.doUpdate(item);
                Enums.UpdateResult result = res.getKey();
                ItemStack replacedItemIfExists = res.getValue();
                if (!Objects.equals(result, DELETED)) {
                    if (replacedItemIfExists.isEmpty()) {
                        newitems.add(item);
                    } else {
                        newitems.add(replacedItemIfExists);
                    }
                }
            }
            meta.setItems(newitems);
            i.setItemMeta(meta);
        } else {
            Map.Entry<Enums.UpdateResult, ItemStack> res = DFMaterialUpdater.doUpdate(i);
            Enums.UpdateResult result = res.getKey();
            ItemStack replacedItemIfExists = res.getValue();

            if (result.equals(DELETED)) {
                event.getItem().remove();
                event.setCancelled(true);
                return;
            }

            if (!replacedItemIfExists.isEmpty()) {
                event.getItem().setItemStack(replacedItemIfExists);
            } else {
                event.getItem().setItemStack(i);
            }
        }
    }

    public static void onOpenContainer(InventoryOpenEvent event) {
        Inventory inv = event.getInventory();

        if (inv.getHolder() instanceof DFMaterialView.DFMaterialViewInventory) return;

        for (int i = 0; i < inv.getContents().length; i++) {
            ItemStack item = inv.getContents()[i];
            if (item == null) continue;

            if (item.getType() == Material.BUNDLE) {
                BundleMeta meta = (BundleMeta) item.getItemMeta();
                List<ItemStack> newitems = new ArrayList<>();
                for (ItemStack itemInBundle : meta.getItems()) {
                    Map.Entry<Enums.UpdateResult, ItemStack> res = DFMaterialUpdater.doUpdate(itemInBundle);
                    Enums.UpdateResult result = res.getKey();
                    ItemStack replacedItemIfExists = res.getValue();
                    if (!Objects.equals(result, DELETED)) {
                        if (replacedItemIfExists.isEmpty()) {
                            newitems.add(itemInBundle);
                        } else {
                            newitems.add(replacedItemIfExists);
                        }
                    }
                }
                meta.setItems(newitems);
                item.setItemMeta(meta);
            } else {
                Map.Entry<Enums.UpdateResult, ItemStack> res = DFMaterialUpdater.doUpdate(item);
                ItemStack replacedItemIfExists = res.getValue();

                if (!replacedItemIfExists.isEmpty()) inv.setItem(i, replacedItemIfExists);
            }
        }
    }
}
