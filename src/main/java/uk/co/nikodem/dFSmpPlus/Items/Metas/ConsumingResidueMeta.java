package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;

import java.util.Objects;

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

        if (residue == null) {
            for (DFMaterial potentialMaterial : DFMaterial.DFMaterialIndex) {
                if (Objects.equals(potentialMaterial.getNamedId(), residueItemId)) {
                    residue = potentialMaterial.toItemStack();
                    break;
                }
            }
        }

        if (residue != null) {
            if (item.getAmount() == 1) plr.getInventory().setItemInMainHand(residue);
            else plr.getInventory().addItem(residue);
        }
    };
}
