package uk.co.nikodem.dFSmpPlus.Items.Metas.VanillaItems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.spawner.BaseSpawner;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class SpawnEggInBucketMeta implements DFMaterialMeta {
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        // TODO: Migrate to new event API if it exists, for now this will do.
        // incredibly hacky, because there's pretty much no other way to do this

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (plr.hasCooldown(item)) return;
            if (event.getHand() == null) return;
            // Material#isInteractable() is pretty unreliable, but it works well enough /shrug
            Boolean result = shouldBePlaced(event.getClickedBlock());
            if (result == null) {
                event.setCancelled(true);
                return;
            }
            if (result.equals(Boolean.FALSE)) return;
            if (event.useItemInHand().equals(Event.Result.DENY)) return;
            ItemMeta meta = item.getItemMeta();
            String bucketUsedName = meta.getPersistentDataContainer().get(Keys.entityBucketUsed, PersistentDataType.STRING);
            if (bucketUsedName == null) return;

            for (DFMaterial potentialMaterial : DFMaterial.DFMaterialIndex) {
                if (Objects.equals(potentialMaterial.getNamedId(), bucketUsedName)) {
                    EquipmentSlot slot = event.getHand();
                    Bukkit.getScheduler().runTaskLater(DFSmpPlus.getPlugin(), () -> {
                        if (item.getAmount() == 1) plr.getInventory().setItem(slot, potentialMaterial.toItemStack());
                        if (plr.getInventory().firstEmpty() == -1) plr.dropItem(potentialMaterial.toItemStack());
                        else plr.getInventory().addItem(potentialMaterial.toItemStack());
                    }, 1L);
                    return;
                };
            }
        }
    };

    // true = run as usual
    // false = doesn't spawn entity
    // null = cancel the event or else it will break
    @Nullable
    public Boolean shouldBePlaced(Block block) {
        if (block == null) return true;

        if (block.getState() instanceof BaseSpawner spawner) {
            if (spawner.getSpawnedType() == null) return true;
            else return null;
        }

        List<Material> cancelMaterials = List.of(Material.CHISELED_BOOKSHELF, Material.BELL, Material.VAULT, Material.RESPAWN_ANCHOR, Material.CAULDRON, Material.CAKE, Material.JUKEBOX, Material.REDSTONE, Material.IRON_DOOR, Material.IRON_TRAPDOOR, Material.TNT, Material.PUMPKIN);
        List<Tag<Material>> cancelTags = List.of(Tag.CANDLES, Tag.CANDLE_CAKES, Tag.BEEHIVES, Tag.CAMPFIRES, Tag.WOODEN_SHELVES, Tag.FENCES);

        if (cancelMaterials.contains(block.getType())) return null;

        for (Tag<Material> tag : cancelTags) {
            if (tag.isTagged(block.getType())) return null;
        }

        return !block.getType().isInteractable();
    }
}
