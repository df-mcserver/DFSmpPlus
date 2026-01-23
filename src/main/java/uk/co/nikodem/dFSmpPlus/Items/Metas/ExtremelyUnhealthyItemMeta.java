package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class ExtremelyUnhealthyItemMeta implements DFMaterialMeta {
    public void ItemConsumed(Player plr, DFMaterial material, ItemStack item, PlayerItemConsumeEvent event) {
        plr.getPersistentDataContainer().set(Keys.uranium, PersistentDataType.BOOLEAN, true);
        plr.getLocation().createExplosion(7.5f);
        plr.damage(1000000.0);
    };
}
