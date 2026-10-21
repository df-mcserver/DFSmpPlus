package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.List;

public class BackpackMeta implements AccessoryMeta {
    private final int slots;

    public BackpackMeta(int slots) {
        this.slots = slots;
    }

    public void openBackpack(Player plr, ItemStack item) {
        plr.sendMessage("Backpack opened!");
    }

    public List<AccessoryAction> GetAccessoryActions() {
        return List.of(
                new AccessoryAction.Builder()
                        .setChooseItemStackicon((plr, accessoryItem, accessoryInformation) -> {
                            ItemStack clickToEnable = ItemStack.of(Material.ORANGE_STAINED_GLASS_PANE);
                            ItemMeta meta = clickToEnable.getItemMeta();
                            meta.displayName(MiniMessage.miniMessage().deserialize("Open backpack"));
//                            meta.lore(List.of(MiniMessage.miniMessage().deserialize("Click to activate vein mining")));
                            clickToEnable.setItemMeta(meta);
                            return clickToEnable;

                        })
                        .setOnClickAction((plr, accessoryItem, accessoryInformation,inventoryClickEvent) -> {
                            openBackpack(plr, accessoryItem);
                        })
                        .create()
        );
    };

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
                }
            }
        }

        return false;
    }
}
