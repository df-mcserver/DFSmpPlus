package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class TargetDummyMeta implements DFMaterialMeta {
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (!event.getAction().isRightClick()) return;
        event.setCancelled(true);
        Damageable damageable = (Damageable) item.getItemMeta();
        if (damageable == null) return;
        ArmorStand entity = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().add(0.5, 1, 0.5), EntityType.ARMOR_STAND);
        entity.getAttribute(Attribute.MAX_HEALTH).addModifier(
                new AttributeModifier(
                        Keys.targetDummy,
                        10000.0,
                        AttributeModifier.Operation.ADD_NUMBER
                )
        );
        entity.customName(Component.text("Target Dummy"));
        entity.getPersistentDataContainer().set(
                Keys.targetDummy,
                PersistentDataType.INTEGER,
                damageable.getDamage()
        );
        plr.sendMessage("Used Target Dummy");
    };

    public static void TargetDummyHit(EntityDamageByEntityEvent event) {
        double damage = event.getDamage();
        Entity damager = event.getDamager();
        if (damager instanceof Player plr) {
            event.getEntity().getPersistentDataContainer().set(
                    Keys.targetDummy,
                    PersistentDataType.INTEGER,
                    (int) damage + event.getEntity().getPersistentDataContainer().get(
                            Keys.targetDummy,
                            PersistentDataType.INTEGER
                    )
            );
            if (event.isCritical()) event.getEntity().getWorld().playSound(event.getEntity(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1F, 1F);
            else event.getEntity().getWorld().playSound(event.getEntity(), Sound.ENTITY_ARMOR_STAND_HIT, 1F, 1F);
            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>♥ <white>"+event.getDamage()));
        }

        event.setCancelled(true);
    }

    public static void TargetDummyInteracted(PlayerInteractAtEntityEvent event) {
        event.setCancelled(true);

        Entity dummy = event.getRightClicked();

        Player plr = event.getPlayer();
        if (plr.isSneaking()) {
            ItemStack newItem = DFMaterial.TargetDummy.toItemStack();
            DFItemUtils.setDamage(newItem, dummy.getPersistentDataContainer().get(Keys.targetDummy, PersistentDataType.INTEGER));
            if (plr.getInventory().getItemInMainHand().getType() == Material.AIR) plr.getInventory().setItemInMainHand(newItem);
            else plr.getInventory().addItem(newItem);

            dummy.remove();
        }
    }
}
