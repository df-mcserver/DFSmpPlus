package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.Particle;
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
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.BreakTargetDummy;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.awt.*;
import java.text.DecimalFormat;

public class TargetDummyMeta implements DFMaterialMeta {
    public final static int maxdamage = 5000;

    @Override
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (!event.getAction().isRightClick()) return;
        event.setCancelled(true);
        Damageable damageable = (Damageable) item.getItemMeta();
        if (damageable == null) return;
        item.setAmount(item.getAmount() - 1);
        ArmorStand entity = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().add(0.5, 1, 0.5), EntityType.ARMOR_STAND);
        entity.getAttribute(Attribute.MAX_HEALTH).addModifier(
                new AttributeModifier(
                        Keys.targetDummy,
                        10000.0,
                        AttributeModifier.Operation.ADD_NUMBER
                )
        );
        entity.customName(Component.text("♥  "+(maxdamage-damageable.getDamage())+"/"+maxdamage, NamedTextColor.RED));
        entity.setCustomNameVisible(true);
        entity.getPersistentDataContainer().set(
                Keys.targetDummy,
                PersistentDataType.INTEGER,
                damageable.getDamage()
        );
        Sounds.PlaceTargetDummy.playSound(entity);
    };

    @Override
    public void ItemCreated(DFMaterial material, ItemStack item) {
        if (item == null) return;
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setMaxDamage(maxdamage);
        item.setItemMeta(meta);
    };

    public static void TargetDummyHit(EntityDamageByEntityEvent event) {
        double damage = event.getDamage();
        Entity damager = event.getDamager();
        if (damager instanceof Player plr) {
            int newDamage = event.getEntity().getPersistentDataContainer().has(Keys.targetDummy) ? event.getEntity().getPersistentDataContainer().get(
                    Keys.targetDummy,
                    PersistentDataType.INTEGER
            ) + (int) damage : (int) damage;
            event.getEntity().getPersistentDataContainer().set(
                    Keys.targetDummy,
                    PersistentDataType.INTEGER,
                    newDamage
            );

            if (newDamage > maxdamage) {
                event.getEntity().getWorld().playSound(event.getEntity(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1F, 1F);
                Sounds.BrokenTool.playSound(event.getEntity());
                event.getEntity().getWorld().spawnParticle(Particle.CRIT, event.getEntity().getLocation().add(0, 1.5, 0), 25, 0, 0, 0, 0.6);
                event.getEntity().remove();

                DFAdvancementsHandler.grantAdvancement(plr, BreakTargetDummy.class);
                return;
            }

            event.getEntity().customName(Component.text("♥  "+(maxdamage-newDamage)+"/"+maxdamage, NamedTextColor.RED));

            if (event.isCritical()) {
                event.getEntity().getWorld().playSound(event.getEntity(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1F, 1F);
                event.getEntity().getWorld().spawnParticle(Particle.CRIT, event.getEntity().getLocation().add(0, 1.5, 0), 6, 0, 0, 0, 0.6);
            } else event.getEntity().getWorld().playSound(event.getEntity(), Sound.ENTITY_ARMOR_STAND_HIT, 1F, 1F);

            plr.sendActionBar(MiniMessage.miniMessage().deserialize("<red>-♥ <white>"+new DecimalFormat("#0.00").format(event.getDamage())));
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

            Sounds.PickupTargetDummy.playSound(dummy);

            dummy.remove();
        }
    }
}
