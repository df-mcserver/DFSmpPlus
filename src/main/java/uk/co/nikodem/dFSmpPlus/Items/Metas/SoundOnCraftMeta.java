package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class SoundOnCraftMeta implements DFMaterialMeta {
    private final Sound sound;

    public SoundOnCraftMeta(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void ItemCrafted(DFMaterial material, ItemStack item, CraftItemEvent event) {
        Location loc = event.getWhoClicked().getLocation();
        loc.getWorld().playSound(loc, sound, 1F, 1F);
    };
}
