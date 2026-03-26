package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Grindstone;

import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class GrindstoneEvents {
    public static void onGrindstoneEvent(PrepareGrindstoneEvent event) {
        ItemStack result = event.getInventory().getResult();
        if (result == null) return;

        ItemStack relevantItem = event.getInventory().getUpperItem() == null ? event.getInventory().getLowerItem() : event.getInventory().getUpperItem();
        if (relevantItem == null) return;

        DFMaterial dfMaterial = DFItemUtils.getDFMaterial(result);
        if (dfMaterial == null) return;

        ItemStack baseMaterial = dfMaterial.toItemStack();
        Damageable baseMaterialMeta = (Damageable) baseMaterial.getItemMeta();
        result.addUnsafeEnchantments(baseMaterialMeta.getEnchants());
        Damageable relevantItemMeta = (Damageable) relevantItem.getItemMeta();
        Damageable resultMeta = (Damageable) result.getItemMeta();

        boolean isSameItem = baseMaterialMeta.equals(relevantItemMeta);
        // you can probably simplify this, but icba
        if (!isSameItem) {
            if (baseMaterialMeta.getEnchants().equals(relevantItemMeta.getEnchants())) {
                if (resultMeta.getDamage() == relevantItemMeta.getDamage()) {
                    isSameItem = true;
                }
            }
        }

        if (isSameItem) event.setResult(null);
        else event.setResult(result);
    }
}
