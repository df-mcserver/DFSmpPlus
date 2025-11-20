package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;

import java.util.Objects;

public class ConvertingItem implements DFMaterialMeta {
    public final String convertsToId;
    public final String residueItemId;
    public final PresetSoundData completionSound;

    public ConvertingItem(String convertsTo, String residueDFMaterial, PresetSoundData completionSound) {
        this.convertsToId = convertsTo;
        this.residueItemId = residueDFMaterial;
        this.completionSound = completionSound;
    }

    public void ItemConsumed(Player plr, DFMaterial material, ItemStack item, PlayerItemConsumeEvent event) {
        Damageable damageable = (Damageable) item.getItemMeta();
        if (damageable == null) return;
        event.setCancelled(true);
        int maxDamage = damageable.hasMaxDamage() ? damageable.getMaxDamage() : item.getType().getMaxDurability();
        int damage = (damageable.hasDamage() ? damageable.getDamage() : maxDamage) - 1;
        if (damage <= 0) {
            // consumed
            DFMaterial residue = null;
            DFMaterial convertedTo = null;

            for (DFMaterial potentialMaterial : DFMaterial.DFMaterialIndex) {
                if (Objects.equals(potentialMaterial.getNamedId(), convertsToId)) convertedTo = potentialMaterial;
                if (Objects.equals(potentialMaterial.getNamedId(), residueItemId)) residue = potentialMaterial;
                if (convertedTo != null && residue != null) break;
            }

            if (convertedTo == null || residue == null) {
                item.setAmount(0);
                return;
            }

            this.completionSound.playSound(plr);
            plr.getInventory().setItemInMainHand(residue.toItemStack());
            plr.getInventory().addItem(convertedTo.toItemStack());
        } else {
            damageable.setDamage(damage);
            item.setItemMeta(damageable);
        }
    };

    // ItemCreated throws an error lol
    public void ItemCrafted(DFMaterial material, ItemStack item, Player plr, CraftItemEvent event) {
        Damageable damageable = (Damageable) item.getItemMeta();
        if (damageable == null) return;
        int maxDamage = damageable.hasMaxDamage() ? damageable.getMaxDamage() : item.getType().getMaxDurability();
        damageable.setDamage(maxDamage);
        item.setItemMeta(damageable);
    }
}
