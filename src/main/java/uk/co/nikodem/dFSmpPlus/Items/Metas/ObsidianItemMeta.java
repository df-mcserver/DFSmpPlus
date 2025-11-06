package uk.co.nikodem.dFSmpPlus.Items.Metas;

import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.Entities.EntityUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;

import java.util.List;

public class ObsidianItemMeta implements DFMaterialMeta {
    public final boolean isTool;

    public ObsidianItemMeta(boolean isTool) {
        this.isTool = isTool;
    }

    public void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {
        float mult = event.getEntity().getType() == EntityType.PLAYER || EntityUtils.isBoss(event.getEntityType()) ? 1.2f : 1.75f;
        event.setDamage(event.getDamage() * mult);
    }

    public void ItemStartMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakProgressUpdateEvent event) {
        if (!isTool) return;
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
        if (!isTool) return;
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

    public List<TextComponent> AddAdditionalLore(DFMaterial material) {
        return List.of((TextComponent) MiniMessage.miniMessage().deserialize("<dark_grey><i>Deals x1.75 damage to enemies, x1.2 otherwise."));
    }

    public void ItemCrafted(DFMaterial material, ItemStack item, CraftItemEvent event) {
        TelemetryUtils.increaseObsidianToolsMade(1);
    }
}
