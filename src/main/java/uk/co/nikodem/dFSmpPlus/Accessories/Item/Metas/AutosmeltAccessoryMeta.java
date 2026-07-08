package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation.AutosmeltingOnBlockbreak;
import uk.co.nikodem.dFSmpPlus.Constants.AutoSmeltable;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutosmeltAccessoryMeta implements AccessoryMeta {
    public final Map<Material, Material> allAutosmeltLists;

    public List<AccessoryAction> GetAccessoryActions() {
        return List.of(
                new AccessoryAction.Builder()
                        .setChooseItemStackicon((plr, accessoryItem, accessoryInformation) -> {
                            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                            if (data.autosmeltEssenceEnabled) {
                                ItemStack clickToDisable = ItemStack.of(Material.GREEN_STAINED_GLASS_PANE);
                                ItemMeta meta = clickToDisable.getItemMeta();
                                meta.displayName(MiniMessage.miniMessage().deserialize("<green>Autosmelt enabled"));
                                meta.lore(List.of(MiniMessage.miniMessage().deserialize("Click to deactivate autosmelting")));
                                clickToDisable.setItemMeta(meta);
                                return clickToDisable;
                            } else {
                                ItemStack clickToEnable = ItemStack.of(Material.RED_STAINED_GLASS_PANE);
                                ItemMeta meta = clickToEnable.getItemMeta();
                                meta.displayName(MiniMessage.miniMessage().deserialize("<red>Autosmelt disabled"));
                                meta.lore(List.of(MiniMessage.miniMessage().deserialize("Click to activate autosmelting")));
                                clickToEnable.setItemMeta(meta);
                                return clickToEnable;
                            }
                        })
                        .setOnClickAction((plr, accessoryItem, accessoryInformation,inventoryClickEvent) -> {
                            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                            data.autosmeltEssenceEnabled = !data.autosmeltEssenceEnabled;
                            DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
                        })
                        .create()
        );
    };

    public AutosmeltAccessoryMeta() {
        Map<Material, Material> finalList = new HashMap<>();
        for (Map<Material, Material> subList : List.of(AutoSmeltable.AutosmeltableAxe, AutoSmeltable.AutosmeltablePickaxe, AutoSmeltable.AutosmeltableShovel)) {
            finalList.putAll(subList);
        }
        this.allAutosmeltLists = finalList;
    }

    @Override
    public void MinedBlockDropItem(Player plr, ItemStack accessory, AccessoryInformation info, BlockDropItemEvent event) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        if (plr.isSneaking() && data.autosmeltEssenceEnabled) {
            Material output = this.allAutosmeltLists.get(event.getBlockState().getType());
            if (output != null) AutosmeltingOnBlockbreak.doAutosmelt(event, this.allAutosmeltLists);
        }
    };

    public static boolean canAutosmelt(Player plr) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        return plr.isSneaking() && data.autosmeltEssenceEnabled;
    }
}
