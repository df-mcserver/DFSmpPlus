package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.AccessoryUI;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;

import java.util.*;

public class AccessoryItemMeta implements DFMaterialMeta {
    public final AccessoryInformation information;

    public static final HashMap<UUID, Long> debounce = new HashMap<>();

    public AccessoryItemMeta(AccessoryInformation information) {
        this.information = information;
    }

    @Override
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            boolean opensMenu = false;

            long current = new Date().getTime();

            Long lastTimestamp = debounce.get(plr.getUniqueId());
            if (lastTimestamp != null) {
                opensMenu = current < lastTimestamp + (500);
            }

            debounce.remove(plr.getUniqueId());
            debounce.put(plr.getUniqueId(), current);

            if (opensMenu) {
                AccessoryUI.open(plr);
                plr.sendActionBar(MiniMessage.miniMessage().deserialize("<grey>Opened accessory menu!"));
            } else plr.sendActionBar(MiniMessage.miniMessage().deserialize("<grey>"+ (Boolean.TRUE.equals(BedrockPlayers.isBedrock(plr)) ? "Triple" : "Double") +" click or use /a to open the accessory menu!"));
        }
    };

    @Override
    public List<TextComponent> AddAdditionalLore(DFMaterial material) {
        double armourPoints = information.getArmourPoints();
        String unsplitDescription = information.getDescription();

        List<String> descriptions = List.of(unsplitDescription.split("\n"));

        List<TextComponent> lore = new java.util.ArrayList<>(List.of(
                Component.text().decoration(TextDecoration.ITALIC, false).build(),
                (TextComponent) MiniMessage.miniMessage().deserialize("<i:false><grey>When equipped as an accessory:")
        ));

        if (armourPoints > 0) lore.add((TextComponent) MiniMessage.miniMessage().deserialize("<i:false><blue>+"+armourPoints/2+" Armor"));
        for (String desc : descriptions) {
            lore.add((TextComponent) MiniMessage.miniMessage().deserialize("<i:false><blue>" + desc));
        }

        return lore;
    };

    public void ItemUpdated(DFMaterial material, ItemStack item) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        if (material == null) return;
        ItemStack refItem = material.toItemStack();
        ItemMeta refMeta = refItem.getItemMeta();
        if (refMeta == null) return;

        meta.lore(refMeta.hasLore() ? refMeta.lore() : null);

        item.setItemMeta(meta);
    };
}
