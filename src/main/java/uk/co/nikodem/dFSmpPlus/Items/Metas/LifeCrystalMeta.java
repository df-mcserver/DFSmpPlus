package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.IYCBTJT;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Player.LifeCrystals.LifeCrystalManager;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class LifeCrystalMeta implements DFMaterialMeta {
    public static HashMap<UUID, Long> lifeCrystalCooldowns = new HashMap<>();

    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            // use LifeCrystal

            Long lastUsed = lifeCrystalCooldowns.get(plr.getUniqueId());

            if (lastUsed == null || lastUsed+500 < new Date().getTime()) {
                if (lifeCrystalCooldowns.containsKey(plr.getUniqueId())) {
                    lifeCrystalCooldowns.replace(plr.getUniqueId(), new Date().getTime());
                } else lifeCrystalCooldowns.put(plr.getUniqueId(), new Date().getTime());

                Integer amount = LifeCrystalManager.getPlayerLifeCrystals(plr);
                if (amount == null) amount = 0;

                DFAdvancementsHandler.grantAdvancement(plr, IYCBTJT.class);

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
