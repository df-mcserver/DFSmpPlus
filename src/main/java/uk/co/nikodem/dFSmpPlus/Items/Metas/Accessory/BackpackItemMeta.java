package uk.co.nikodem.dFSmpPlus.Items.Metas.Accessory;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.BackpackMeta;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class BackpackItemMeta implements DFMaterialMeta {
    public static final HashMap<UUID, Long> debounce = new HashMap<>();

    @Override
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            Boolean result = DFItemUtils.shouldBePlaced(plr, event.getClickedBlock());
            if (result == null || plr.hasCooldown(item) || result.equals(Boolean.FALSE) || event.useItemInHand().equals(Event.Result.DENY)) {
                return;
            }

            boolean opensBackpack = false;

            long current = new Date().getTime();

            Long lastTimestamp = debounce.get(plr.getUniqueId());
            if (lastTimestamp != null) {
                opensBackpack = current < lastTimestamp + (500);
            }

            debounce.remove(plr.getUniqueId());
            debounce.put(plr.getUniqueId(), current);

            if (opensBackpack || Boolean.TRUE.equals(BedrockPlayers.isBedrock(plr))) {
                AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
                if (info == null) return;

                for (AccessoryMeta meta : info.getMeta()) {
                    if (meta instanceof BackpackMeta backpackMeta) {
                        backpackMeta.openBackpack(plr, item);
                    }
                }

                plr.sendActionBar(MiniMessage.miniMessage().deserialize("<grey>Opened backpack!"));
            } else plr.sendActionBar(MiniMessage.miniMessage().deserialize("<grey>Double click to open backpack!"));
        }
    };
}
