package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;

public class ConsumingResidueMeta implements DFMaterialMeta {
    public final String residueItemId;
    public final ItemStack residueItemStack;
    public final PresetSoundData completionSound;

    public ConsumingResidueMeta(String residueDFMaterial, PresetSoundData completionSound) {
        this.residueItemStack = null;
        this.residueItemId = residueDFMaterial;
        this.completionSound = completionSound;
    }

    public ConsumingResidueMeta(ItemStack residueItem, PresetSoundData completionSound) {
        this.residueItemStack = residueItem;
        this.residueItemId = null;
        this.completionSound = completionSound;
    }

    public void ItemConsumed(Player plr, DFMaterial material, ItemStack item, PlayerItemConsumeEvent event) {
        ItemStack residue = residueItemStack;

        if (residue == null) residue = DFMaterial.DFMaterialIndex.get(residueItemId).toItemStack();
        if (residue == null) return;

        event.setReplacement(residue);
    };
}
