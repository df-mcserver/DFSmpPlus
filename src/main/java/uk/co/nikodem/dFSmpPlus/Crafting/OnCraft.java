package uk.co.nikodem.dFSmpPlus.Crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
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
        if (e == null) return;
        if (item == null) return;
        DFItemUtils.addUUIDIfMarked(item);

        // hopefully this doesn't cause any issues later on down the line
        ItemStack[] newMatrix = new ItemStack[e.getInventory().getMatrix().length];
        for (int i = 0; i < newMatrix.length; i++) {
            ItemStack x = e.getInventory().getMatrix()[i];
            if (x != null) {
                if (DFMaterial.GoldMilkBucket.isSimilar(x)) newMatrix[i] = DFMaterial.GoldBucket.toItemStack();
                else if (DFMaterial.CopperMilkBucket.isSimilar(x)) newMatrix[i] = DFMaterial.CopperBucket.toItemStack();
                else if (x.getType().equals(Material.MILK_BUCKET)) newMatrix[i] = ItemStack.of(Material.BUCKET); // fixes issue on bedrock
                else newMatrix[i] = x;
            }
        }

        Bukkit.getScheduler().runTaskLater(DFSmpPlus.getPlugin(), () -> {
            e.getInventory().setMatrix(newMatrix);
        }, 1L);

        DFMaterial material = DFItemUtils.getDFMaterial(item);
        if (material == null) return;

        if (material.hasMeta()) {
            for (DFMaterialMeta meta : material.getMeta()) {
                meta.ItemCrafted(material, item, plr, e);
            }
        }
    }
}
