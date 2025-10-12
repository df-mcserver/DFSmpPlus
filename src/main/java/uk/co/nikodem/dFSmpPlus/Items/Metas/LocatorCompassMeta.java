package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.ArrayList;
import java.util.List;

public class LocatorCompassMeta implements DFMaterialMeta {
    public void ItemCreated(DFMaterial material, ItemStack item) {
        CompassMeta meta = (CompassMeta) item.getItemMeta();
        meta.setLodestoneTracked(false);
        meta.setLodestone(new Location(Bukkit.getWorld("world"), 110D, 0D, 110D));

        updateLore(meta.lore(), item);
    }

    public void setCompassLocation(ItemStack item, Location location) {
        if (item == null) return;
        CompassMeta meta = (CompassMeta) item.getItemMeta();
        if (meta == null) return;
        meta.setLodestoneTracked(false);
        meta.setLodestone(location);
    }

    public void updateLore(List<Component> base, ItemStack item) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        List<Component> newLore = new ArrayList<>(base);
        newLore.add(Component.text("Please insert a module.", NamedTextColor.RED));
        meta.lore(newLore);
        item.setItemMeta(meta);
    }
}
