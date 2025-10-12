package uk.co.nikodem.dFSmpPlus.Player.DFUpdates;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialUpdater;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uk.co.nikodem.dFSmpPlus.Constants.Enums.UpdateResult.*;

public class OnOpenContainer {
    public static void OnOpenContainer(InventoryOpenEvent e) {
        Inventory inv = e.getInventory();

        for (ItemStack i : inv) {
            if (i != null) {
                if (i.getType() == Material.BUNDLE) {
                    BundleMeta meta = (BundleMeta) i.getItemMeta();
                    List<ItemStack> newitems = new ArrayList<>();
                    for (ItemStack item : meta.getItems()) {
                        Enums.UpdateResult res = DFMaterialUpdater.doUpdate(item, false);
                        if (!Objects.equals(res, DELETED)) newitems.add(item);
                    }
                    meta.setItems(newitems);
                    i.setItemMeta(meta);
                } else {
                    DFMaterialUpdater.doUpdate(i);
                }
            }
        }
    }
}
