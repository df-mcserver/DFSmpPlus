package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Objects;

public class PowderSnowBucketMeta implements DFMaterialMeta {
    private final String emptyBucketNamedId;

    public PowderSnowBucketMeta(String emptyBucketNamedId) {
        this.emptyBucketNamedId = emptyBucketNamedId;
    }

    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock() == null) return;
            if (event.getClickedBlock().getType() == Material.CAULDRON) return;
            for (DFMaterial potentialMaterial : DFMaterial.DFMaterialIndex) {
                if (Objects.equals(potentialMaterial.getNamedId(), emptyBucketNamedId)) {
                    event.setUseItemInHand(Event.Result.DENY);
                    Objects.requireNonNull(event.getClickedBlock().getLocation().add(event.getBlockFace().getDirection())).getBlock().setType(Material.POWDER_SNOW);
                    if (plr.getInventory().getItemInMainHand().equals(item)) plr.getInventory().setItemInMainHand(potentialMaterial.toItemStack());
                    else if (plr.getInventory().getItemInOffHand().equals(item)) plr.getInventory().setItemInOffHand(potentialMaterial.toItemStack());
                    return;
                }
            }
        }
    };
}
