package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class CustomDurabilityMeta implements DFMaterialMeta {
    public final int maxDamage;

    public CustomDurabilityMeta(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public void ItemCreated(DFMaterial material, ItemStack item) {
        if (item == null) return;
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setMaxDamage(maxDamage);
        item.setItemMeta(meta);
    };
}
