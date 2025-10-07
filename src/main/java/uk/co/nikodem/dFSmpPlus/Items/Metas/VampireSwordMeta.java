package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VampireSwordMeta implements DFMaterialMeta {
    public final int MAX_STAGE = 9;

    @Override
    public void ItemKilledEntity(Player plr, DFMaterial material, Entity entity, EntityDeathEvent event) {
        if (entity instanceof Player target) {
            ItemStack item = plr.getInventory().getItemInMainHand();
            Integer stage = incrementStage(item);
            if (stage == null) return;
            playSnapSound(plr);
            updateLore(item, stage);
            updateModel(item, stage);
        }
    };

    @Override
    public void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {
        // work around to buggy attributes
        Integer stage = getStage(weapon);
        if (stage == null) return;

        event.setDamage(event.getDamage() + stage);
    };

    @Nullable
    public Integer getStage(ItemStack item) {
        return DFItemUtils.getInteger(item, Keys.vampireSwordStage);
    }

    @Nullable
    public Integer incrementStage(ItemStack item) {
        Integer currentStage = getStage(item);
        if (currentStage == null) return null;
        if (currentStage + 1 > MAX_STAGE) return null;
        DFItemUtils.set(
                item,
                Keys.vampireSwordStage,
                PersistentDataType.INTEGER,
                currentStage+1
        );

        return currentStage+1;
    }

    public void updateModel(ItemStack item, Integer stage) {
        if (stage == null) return;
        DFItemUtils.setModel(item, "vamp_stage"+stage);
    }

    public void playSnapSound(Player plr) {
        plr.getWorld().playSound(plr, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.3F, 1F);
    }

    public void updateLore(ItemStack item, Integer stage) {
        if (item == null) return;
        if (stage == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        List<TextComponent> newLore = new ArrayList<>(DFMaterial.VampireSword.getLore());
        newLore.add(Component.text("  +"+stage+" damage", NamedTextColor.RED));
        meta.lore(newLore);
        item.setItemMeta(meta);
    }
}
