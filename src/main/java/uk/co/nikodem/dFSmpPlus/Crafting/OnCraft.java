package uk.co.nikodem.dFSmpPlus.Crafting;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import javax.annotation.Nullable;

public class OnCraft {
    public static void OnCraft(CraftItemEvent e) {
        if (e.isCancelled()) return;

        ItemStack item = e.getCurrentItem();
        OnCraft(item, (Player) e.getWhoClicked(), e);
    }

    public static void OnCraft(ItemStack item, Player plr, @Nullable CraftItemEvent e) {
        if (item == null) return;
        DFItemUtils.addUUIDIfMarked(item);

        DFMaterial material = DFItemUtils.getDFMaterial(item);
        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemCrafted(material, item, plr, e);
            }
        }
    }
}
