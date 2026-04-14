package uk.co.nikodem.dFSmpPlus.Items.Metas.VanillaItems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.EntityBucketData;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class SpawnEggInBucketMeta implements DFMaterialMeta {
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        // TODO: Migrate to new event API if it exists, for now this will do.
        // incredibly hacky, because there's pretty much no other way to do this

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (plr.hasCooldown(item)) return;
            if (event.getHand() == null) return;

            Boolean result = DFItemUtils.shouldBePlaced(event.getClickedBlock());
            if (result == null) {
                event.setCancelled(true);
                return;
            }
            if (result.equals(Boolean.FALSE)) return;
            if (event.useItemInHand().equals(Event.Result.DENY)) return;
            ItemMeta meta = item.getItemMeta();
            String bucketUsedName = meta.getPersistentDataContainer().get(Keys.entityBucketUsed, PersistentDataType.STRING);
            if (bucketUsedName == null) return;

            DFMaterial newMaterial = DFMaterial.DFMaterialIndex.get(bucketUsedName);
            if (newMaterial != null) {
                EquipmentSlot slot = event.getHand();
                Bukkit.getScheduler().runTaskLater(DFSmpPlus.getPlugin(), () -> {
                    if (item.getAmount() == 1) plr.getInventory().setItem(slot, newMaterial.toItemStack());
                    if (plr.getInventory().firstEmpty() == -1) plr.dropItem(newMaterial.toItemStack());
                    else plr.getInventory().addItem(newMaterial.toItemStack());
                }, 1L);
                return;
            }
        }
    };

    public void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {
        Entity rightClickedEntity = event.getRightClicked();
        SpawnEggMeta meta = (SpawnEggMeta) item.getItemMeta();
        EntityType type = meta.getCustomSpawnedType();
        if (type == null) type = EntityBucketData.EntityEggToEntityIndex.get(item.getType());
        if (type == null) return;

        if (rightClickedEntity.getType().equals(type)) {
            event.setCancelled(true);
        }
    };
}
