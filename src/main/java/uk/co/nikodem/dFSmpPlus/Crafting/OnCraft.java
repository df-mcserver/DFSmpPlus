package uk.co.nikodem.dFSmpPlus.Crafting;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class OnCraft implements Listener {
    @EventHandler
    public void OnCraft(CraftItemEvent e) {
        if (e.isCancelled()) return;

        ItemStack item = e.getCurrentItem();
        if (item == null) return;
        DFItemUtils.addUUIDIfMarked(item);

        DFMaterial material = DFItemUtils.getDFMaterial(item);
        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemCrafted(material, item, e);
            }
        }
    }
}
