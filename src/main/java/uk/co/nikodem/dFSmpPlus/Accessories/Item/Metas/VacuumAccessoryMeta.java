package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VacuumAccessoryMeta implements AccessoryMeta {
    public List<AccessoryAction> GetAccessoryActions() {
        return List.of(
                new AccessoryAction.Builder()
                        .setChooseItemStackicon((plr, accessoryItem, accessoryInformation) -> {
                            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                            if (data.hasVacuumSoundEnabled) {
                                ItemStack clickToDisable = ItemStack.of(Material.GREEN_STAINED_GLASS_PANE);
                                ItemMeta meta = clickToDisable.getItemMeta();
                                meta.displayName(MiniMessage.miniMessage().deserialize("<green>Vacuum sound enabled"));
                                meta.lore(List.of(MiniMessage.miniMessage().deserialize("Click to deactivate pickup sound")));
                                clickToDisable.setItemMeta(meta);
                                return clickToDisable;
                            } else {
                                ItemStack clickToEnable = ItemStack.of(Material.RED_STAINED_GLASS_PANE);
                                ItemMeta meta = clickToEnable.getItemMeta();
                                meta.displayName(MiniMessage.miniMessage().deserialize("<red>Vacuum sound disabled"));
                                meta.lore(List.of(MiniMessage.miniMessage().deserialize("Click to activate pickup sound")));
                                clickToEnable.setItemMeta(meta);
                                return clickToEnable;
                            }
                        })
                        .setOnClickAction((plr, accessoryItem, accessoryInformation,inventoryClickEvent) -> {
                            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                            data.hasVacuumSoundEnabled = !data.hasVacuumSoundEnabled;
                            DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
                        })
                        .create()
        );
    };

    public static void ItemSpawned(EntityDropItemEvent event) {
        if (event.getEntity() instanceof Player plr) {
            Item item = event.getItemDrop();
            ItemStack itemStack = item.getItemStack();
            item.remove();
            for (ItemStack drop : VacuumAccessoryMeta.giveItemsToPlayerViaVacuum(plr, List.of(itemStack))) {
                item.getWorld().dropItemNaturally(item.getLocation(), drop);
            }
        }
    }

    public static List<ItemStack> giveItemEntitiesToPlayerViaVacuum(Player plr, List<Item> newDrops) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        boolean hasVacuum = accessoryData.hasAccessoryWithMetaEquipped(VacuumAccessoryMeta.class);
        boolean hasVacuumedItem = false;

        List<ItemStack> items = new ArrayList<>();
        for (Item item : newDrops) {
            items.add(item.getItemStack());
            if (hasVacuum) {
                item.setItemStack(ItemStack.of(Material.AIR));
                hasVacuumedItem = true;
            }
        }

        if (hasVacuumedItem && data.hasVacuumSoundEnabled) Sounds.VacuumPickupItem.playSound(plr);

        return giveItemsToPlayerViaVacuum(plr, items);
    }

    public static List<ItemStack> giveItemsToPlayerViaVacuum(Player plr, List<ItemStack> newDrops) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        boolean hasVacuum = accessoryData.hasAccessoryWithMetaEquipped(VacuumAccessoryMeta.class);

        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);

        List<ItemStack> overflowItems = new ArrayList<>();

        for (ItemStack drop : newDrops) {
            if (hasVacuum) {
                boolean hasVacuumedItem = false;
                PlayerInventory inv = plr.getInventory();
                if (drop.isSimilar(inv.getItemInOffHand())) {
                    int stackSize = drop.getMaxStackSize();
                    int totalAmount = drop.getAmount() + inv.getItemInOffHand().getAmount();
                    if (totalAmount <= stackSize) {
                        inv.getItemInOffHand().setAmount(totalAmount);
                        hasVacuumedItem = true;
                        continue;
                    } else {
                        hasVacuumedItem = true;
                        inv.getItemInOffHand().setAmount(stackSize);
                        drop.setAmount(totalAmount - stackSize);
                    }
                }
                Map<Integer, ItemStack> overflow = inv.addItem(drop);
                if (overflow.isEmpty()) hasVacuumedItem = true;
                if (hasVacuumedItem && data.hasVacuumSoundEnabled) Sounds.VacuumPickupItem.playSound(plr);
                for (Map.Entry<Integer, ItemStack> item : overflow.entrySet()) {
                    overflowItems.add(item.getValue());
                }
            } else {
                overflowItems.add(drop);
            }
        }

        return overflowItems;
    }
}
