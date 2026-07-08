package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;

import java.util.List;

public class SplitMiningAccessoryMeta extends AutosmeltAccessoryMeta {
    public final VeinminingAccessoryMeta veinminingAccessoryMeta = new VeinminingAccessoryMeta();

    public List<AccessoryAction> GetAccessoryActions() {
        return List.of(
                new AccessoryAction.Builder()
                        .setChooseItemStackicon((plr, accessoryItem, accessoryInformation) -> {
                            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);

                            boolean autosmelt = data.autosmeltEssenceEnabled;
                            boolean vein = data.veinMinerEssenceEnabled;

                            ItemStack cycleItem = ItemStack.of(autosmelt && vein ? Material.GREEN_STAINED_GLASS_PANE : autosmelt || vein ? Material.ORANGE_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE);
                            ItemMeta meta = cycleItem.getItemMeta();
                            meta.displayName(MiniMessage.miniMessage().deserialize(
                                    (autosmelt || vein ? "<green>" : "<red>") +
                                            (autosmelt && vein ? "Autosmelt and vein mining enabled" : autosmelt ? "Autosmelt enabled" : vein ? "Vein mining enabled" : "Autosmelt and vein mining disabled")
                                    )
                            );
                            meta.lore(List.of(MiniMessage.miniMessage().deserialize("Click to cycle")));
                            cycleItem.setItemMeta(meta);
                            return cycleItem;
                        })
                        .setOnClickAction((plr, accessoryItem, accessoryInformation,inventoryClickEvent) -> {
                            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);

                            boolean autosmelt = data.autosmeltEssenceEnabled;
                            boolean vein = data.veinMinerEssenceEnabled;

                            if (autosmelt && vein) {
                                data.veinMinerEssenceEnabled = false;
                            } else if (autosmelt) {
                                data.autosmeltEssenceEnabled = false;
                                data.veinMinerEssenceEnabled = true;
                            } else if (vein) {
                                data.veinMinerEssenceEnabled = false;
                            } else {
                                data.veinMinerEssenceEnabled = true;
                                data.autosmeltEssenceEnabled = true;
                            }

                            DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
                        })
                        .create()
        );
    };

    @Override
    public void BlockMined(Player plr, ItemStack accessory, AccessoryInformation info, BlockBreakEvent event) {
        veinminingAccessoryMeta.BlockMined(plr, accessory, info, event);
    };
}
