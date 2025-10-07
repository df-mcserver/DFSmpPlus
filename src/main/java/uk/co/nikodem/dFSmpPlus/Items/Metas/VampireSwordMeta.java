package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import javax.annotation.Nullable;

public class VampireSwordMeta implements DFMaterialMeta {
    public final int MAX_STAGE = 9;

    @Override
    public void ItemKilledEntity(Player plr, DFMaterial material, Entity entity, EntityDeathEvent event) {
        if (entity instanceof Player target) {
            ItemStack item = plr.getInventory().getItemInMainHand();
            Integer stage = incrementStage(item);
            updateModel(item, stage);
            plr.sendMessage("Vampire sword upgrade to stage "+stage);
        }
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

        DFItemUtils.removeAttribute(item, Attribute.ATTACK_DAMAGE);

        DFItemUtils.setAttribute(
                item,
                Attribute.ATTACK_DAMAGE,
                new AttributeModifier(
                        Keys.vampireSwordDamage,
                        (double) currentStage+1,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlotGroup.MAINHAND
                )
        );

        return currentStage+1;
    }

    public void updateModel(ItemStack item, Integer stage) {
        if (stage == null) return;
        DFItemUtils.setModel(item, "vamp_stage"+stage);
    }
}
