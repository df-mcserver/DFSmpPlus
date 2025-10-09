package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Location;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;

public class SoundOnCraftMeta implements DFMaterialMeta {
    private final PresetSoundData soundData;

    public SoundOnCraftMeta(PresetSoundData soundData) {
        this.soundData = soundData;
    }

    @Override
    public void ItemCrafted(DFMaterial material, ItemStack item, CraftItemEvent event) {
        Location loc = event.getWhoClicked().getLocation();
        soundData.playSound(loc);
    };
}
