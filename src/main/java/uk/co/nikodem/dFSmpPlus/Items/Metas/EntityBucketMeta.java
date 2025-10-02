package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Constants.EntityBucketData;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class EntityBucketMeta implements DFMaterialMeta {
    @Override
    public void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {
        event.setCancelled(true);
        Entity entity = event.getRightClicked();

        Material egg = EntityBucketData.ConvertEntityToEgg(entity.getType());

        if (egg != null) {
            plr.getWorld().playSound(plr, Sound.ITEM_BUCKET_FILL, 1F, 1F);
            entity.remove();

            ItemStack eggItem = new ItemStack(egg);

            if (plr.getGameMode() == GameMode.CREATIVE) {
                plr.getInventory().addItem(eggItem);
            } else {
                plr.getInventory().setItemInMainHand(eggItem);
            }
        }
    }
}
