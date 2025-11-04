package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Entities.EntityUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

public class EntityBucketMeta implements DFMaterialMeta {
    @Override
    public void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {
        event.setCancelled(true);
        Entity entity = event.getRightClicked();

        Material egg = EntityUtils.ConvertEntityToEgg(entity.getType());

        if (egg != null) {
            Sounds.UseBucket.playSound(plr);
            entity.remove();

            ItemStack eggItem = new ItemStack(egg);

            if (plr.getGameMode() == GameMode.CREATIVE) {
                plr.getInventory().addItem(eggItem);
            } else {
                plr.getInventory().setItemInMainHand(eggItem);
            }
        }
    }

    @Override
    public void BucketUseEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {
        event.setCancelled(true);
    }
}
