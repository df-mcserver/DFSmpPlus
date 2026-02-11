package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;

public class NoisyItem implements DFMaterialMeta {
    public final PresetSoundData onHit;
    public final PresetSoundData onKillEntity;

    public NoisyItem(PresetSoundData onHit, PresetSoundData onKillEntity) {
        this.onHit = onHit;
        this.onKillEntity = onKillEntity;
    }

    public void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {
        if (onHit != null) onHit.playSound(plr);
    };

    public void ItemKilledEntity(Player plr, DFMaterial material, Entity target, EntityDeathEvent event) {
        if (onKillEntity != null) onKillEntity.playSound(plr);
    };

    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        if (onHit != null) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) onHit.playSound(plr);
        }
    };
}
