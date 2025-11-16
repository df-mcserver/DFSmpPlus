package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.ObsidianArmour;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

public class ObsidianMeta implements DFArmourSetMeta {
    @Override
    public void RunPerSecond(Player plr, DFArmourSet armourSet) {
        plr.addPotionEffect(new PotionEffect(
                PotionEffectType.RESISTANCE, 40, 1, true, false
        ));
    };

    @Override
    public void OnUpdateSetBonus(Player plr, ItemStack itemUpdated, DFArmourSet armourSet) {
        DFAdvancementsHandler.grantAdvancement(plr, ObsidianArmour.class);
    };
}
