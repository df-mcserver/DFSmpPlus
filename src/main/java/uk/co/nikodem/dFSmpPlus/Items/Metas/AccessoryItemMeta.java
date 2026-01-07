package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.List;

public class AccessoryItemMeta implements DFMaterialMeta {
    public final AccessoryInformation information;

    public AccessoryItemMeta(AccessoryInformation information) {
        this.information = information;
    }

    @Override
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        if (event.getAction().isRightClick()) {
            plr.sendActionBar(MiniMessage.miniMessage().deserialize("<grey>Equip this accessory with the /a command!"));
        }
    };

    @Override
    public List<TextComponent> AddAdditionalLore(DFMaterial material) {
        float armourPoints = information.getArmourPoints();
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
}
