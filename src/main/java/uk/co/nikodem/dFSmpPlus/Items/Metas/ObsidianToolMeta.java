package uk.co.nikodem.dFSmpPlus.Items.Metas;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class ObsidianToolMeta implements DFMaterialMeta {
    public void ItemStartMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakProgressUpdateEvent event) {
        plr.addPotionEffect(
                new PotionEffect(
                        PotionEffectType.HASTE,
                        10,
                        2,
                        false,
                        false
                )
        );
    }

    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            plr.addPotionEffect(
                    new PotionEffect(
                            PotionEffectType.HASTE,
                            10,
                            2,
                            false,
                            false
                    )
            );
        }
    }
}
