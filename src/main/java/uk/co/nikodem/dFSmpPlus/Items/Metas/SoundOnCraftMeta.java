package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class SoundOnCraftMeta implements DFMaterialMeta {
    private final Sound sound;
    private float volume = 1F;
    private float pitch = 1F;

    public SoundOnCraftMeta(Sound sound) {
        this.sound = sound;
    }

    public SoundOnCraftMeta(Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void ItemCrafted(DFMaterial material, ItemStack item, CraftItemEvent event) {
        Location loc = event.getWhoClicked().getLocation();
        loc.getWorld().playSound(loc, sound, volume, pitch);
    };
}
