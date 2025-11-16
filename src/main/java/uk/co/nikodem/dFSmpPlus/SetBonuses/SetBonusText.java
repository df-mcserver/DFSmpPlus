package uk.co.nikodem.dFSmpPlus.SetBonuses;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import javax.annotation.Nullable;
import java.util.*;

public class SetBonusText {
    // TODO: Use plain serialiser (adventure API)
    public static final String SET_BONUS_PREFIX = "<underlined><dark_gray>Set Bonus: ";

    public static void onItemDrop(PlayerDropItemEvent event) {
        removeSetBonusText(event.getItemDrop().getItemStack());
    }

    public static void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory().getType() != InventoryType.PLAYER) return;
        PlayerInventory inv = (PlayerInventory) event.getClickedInventory();
        Player plr = (Player) event.getWhoClicked();

        // run task 1 tick later to let all changes apply
        Bukkit.getScheduler().runTaskLater(DFSmpPlus.getProvidingPlugin(DFSmpPlus.class), () -> {
            boolean allArmourEquipped = true;
            for (ItemStack piece : inv.getArmorContents()) {
                if (piece == null || piece.getType() == Material.AIR) allArmourEquipped = false;
                updateItem(plr, piece);
            }

            updateItem(plr, plr.getItemOnCursor());
            updateItem(plr, event.getCurrentItem());

            if (event.getClick().isShiftClick()) {
                // the shift clicking is pretty weird
                // looping through them and removing the set bonus is probably
                // the easiest method
                if (!allArmourEquipped && (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR))) {
                    for (ItemStack item : inv.getContents()) {
                        removeSetBonusText(item);
                    }
                }
            }
        }, 1L);
    }

    public static void onArmourDispensed(BlockDispenseArmorEvent event) {
        if (event.getTargetEntity().getType() != EntityType.PLAYER) return;
        Player plr = (Player) event.getTargetEntity();

        Bukkit.getScheduler().runTaskLater(DFSmpPlus.getProvidingPlugin(DFSmpPlus.class), () -> {
            PlayerInventory inv = plr.getInventory();

            for (ItemStack piece : inv.getArmorContents()) {
                updateItem(plr, piece);
            }
        }, 1L);
    }

    public static void onItemUsed(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL || event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (!event.hasItem() || event.getItem() == null) return;

        ItemStack item = event.getItem();
        EquipmentSlot equipmentSlot = item.getType().getEquipmentSlot();
        if (equipmentSlot.ordinal() < 2) return;

        Player plr = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(DFSmpPlus.getProvidingPlugin(DFSmpPlus.class), () -> {
            PlayerInventory inv = plr.getInventory();

            for (ItemStack piece : inv.getArmorContents()) {
                updateItem(plr, piece);
            }

            removeSetBonusText(inv.getItemInMainHand());
        }, 1L);
    }

    public static void updateItem(Player plr, ItemStack item) {
        DFArmourSet set = DFArmourSet.getArmourSetEquipped(plr);
        if (set == null) {
            removeSetBonusText(item);
            return;
        }
        if (set.itemInSet(item)) {
            applySetBonusText(item, getSetBonusText(item));
        } else {
            removeSetBonusText(item);
        }
    }

    public static void applySetBonusText(ItemStack item, String setBonus) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
        assert lore != null;

        for (Component line : lore) {
            MiniMessage mm = MiniMessage.miniMessage();
            String string = mm.serialize(line);
            if (string.startsWith(SET_BONUS_PREFIX)) return;
        }

        lore.add(MiniMessage.miniMessage().deserialize(SET_BONUS_PREFIX+setBonus));
        meta.lore(lore);
        item.setItemMeta(meta);
    }

    public static void removeSetBonusText(ItemStack item) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        if (!meta.hasLore()) return;
        List<Component> lore = meta.lore();
        assert lore != null;

        Iterator<Component> it = lore.iterator();
        while (it.hasNext()) {
            Component line = it.next();
            MiniMessage mm = MiniMessage.miniMessage();
            String string = mm.serialize(line);
            if (string.startsWith(SET_BONUS_PREFIX)) it.remove();
        }

        meta.lore(lore);
        item.setItemMeta(meta);
    }

    @Nullable
    public static String getSetBonusText(ItemStack item) {
        for (DFArmourSet set : DFArmourSet.DFArmourSetIndex) {
            if (set.itemInSet(item)) return set.getSetBonusText();
        }
        return null;
    }
}
