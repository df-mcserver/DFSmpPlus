package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Player.LifeCrystals.LifeCrystalManager;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.Date;

public class LifeCrystalMeta implements DFMaterialMeta {
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            // use LifeCrystal

            Long lastUsed = DFSmpPlus.playerData.getTemporaryLong(plr, "lifeCrystalLastUsed");

            if (lastUsed == null || lastUsed+500 < new Date().getTime()) {
                DFSmpPlus.playerData.setTemporaryData(plr, "lifeCrystalLastUsed", new Date().getTime());

                Integer amount = LifeCrystalManager.getPlayerLifeCrystals(plr);
                if (amount == null) amount = 0;

                if (amount > LifeCrystalManager.maxLifeCrystals) {
                    LifeCrystalManager.setPlayerLifeCrystals(plr, LifeCrystalManager.maxLifeCrystals);
                    LifeCrystalManager.updatePlayerLifeCrystalsModifier(plr);
                } else if (amount < LifeCrystalManager.maxLifeCrystals) {
                    LifeCrystalManager.setPlayerLifeCrystals(plr, amount+1);
                    LifeCrystalManager.updatePlayerLifeCrystalsModifier(plr);

                    item.setAmount(item.getAmount() - 1);
                    Sounds.UseLifeCrystal.playSound(plr);
                    plr.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 3));
                }
            }
        }
    }
}
