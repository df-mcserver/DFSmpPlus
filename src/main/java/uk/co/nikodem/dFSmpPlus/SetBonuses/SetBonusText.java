package uk.co.nikodem.dFSmpPlus.SetBonuses;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SetBonusText {
    public static final String SET_BONUS_PREFIX = "<underlined><dark_gray>Set Bonus: ";

    public static void onItemDrop(PlayerDropItemEvent event) {
        removeSetBonusText(event.getItemDrop().getItemStack());
    }

    public static void onInventoryClick(InventoryClickEvent event) {
//        nuclearUpdateInventory((Player) event.getWhoClicked());

        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory().getType() != InventoryType.PLAYER) return;
        PlayerInventory inv = (PlayerInventory) event.getClickedInventory();
        Player plr = (Player) event.getWhoClicked();

        for (ItemStack piece : inv.getArmorContents()) {
            updateItem(plr, piece);
        }
        updateItem(plr, event.getCursor());
        updateItem(plr, event.getCurrentItem());
    }

    public static void nuclearUpdateInventory(Player plr) {
        // just scan the inventory cuz im lazy
        for (var i : plr.getInventory().getContents()) {
            if (i != null) updateItem(plr, i);
        }
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
