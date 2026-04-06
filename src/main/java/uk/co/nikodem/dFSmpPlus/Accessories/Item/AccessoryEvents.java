package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Accessory.EquipAccessory;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Accessory.EquipAllAccessories;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessoryEvents {
    public static boolean RefreshAccessoryAttributes(Player plr) {
        for (Attribute attr : RegistryAccess.registryAccess().getRegistry(RegistryKey.ATTRIBUTE).stream().toList()) {
            AttributeInstance instance = plr.getAttribute(attr);
            if (instance == null) continue;
            List<NamespacedKey> attributesToRemove = new ArrayList<>();
            instance.getModifiers().forEach(mods -> {
                if (mods.getKey().getNamespace().equals(Keys.createAccessoryKey("").getNamespace())) attributesToRemove.add(mods.getKey());
            });

            for (NamespacedKey key : attributesToRemove) {
                instance.removeModifier(key);
            }
        }

        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        boolean detectedInvalidAccessories = false;

        for (int i = 0; i < accessoryData.slots.length; i++) {
            ItemStack item = accessoryData.slots[i];
            if (item == null) continue;
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
            if (info == null) {
                accessoryData.slots[i] = null;
                detectedInvalidAccessories = true;
                continue;
            }

            AttributeInstance instance = plr.getAttribute(Attribute.ARMOR);
            if (instance != null) {
                AttributeModifier newModifier = new AttributeModifier(info.getNamespacedKey(), info.getArmourPoints(), AttributeModifier.Operation.ADD_NUMBER);
                instance.addModifier(newModifier);
            }

            for (AccessoryMeta meta : info.getMeta()) {
                meta.AccessoryEquipped(plr, item, info);

                Map<Attribute, AttributeModifier> extraModifiers = meta.AddAdditionalAttributeModifiers(plr, item, info);
                if (!extraModifiers.isEmpty()) {
                    for (Map.Entry<Attribute, AttributeModifier> extraModifier : extraModifiers.entrySet()) {
                        AttributeInstance extraInstance = plr.getAttribute(extraModifier.getKey());
                        if (extraInstance != null) {
                            AttributeModifier alreadyExistingModifier = extraInstance.getModifier(extraModifier.getValue().getKey());
                            if (alreadyExistingModifier != null) extraInstance.removeModifier(extraModifier.getValue().getKey());

                            extraInstance.addModifier(extraModifier.getValue());
                        }
                    }
                }
            }
        }

        if (detectedInvalidAccessories) AccessoryManager.updatePlayerData(plr, accessoryData);

        return detectedInvalidAccessories;
    }

    public static void ApplyRunPerSecond(Player plr) {
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

        for (int i = 0; i < accessoryData.slots.length; i++) {
            if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

            ItemStack accessoryItem = accessoryData.slots[i];
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) continue;

            for (AccessoryMeta meta : info.getMeta()) {
                meta.RunPerSecond(plr, accessoryItem, info);
            }
        }
    }

    public static void AccessoryEquipped(Player plr, ItemStack item, AccessoryInformation info) {
        AttributeInstance instance = plr.getAttribute(Attribute.ARMOR);
        if (instance != null) {
            AttributeModifier alreadyExistingModifier = instance.getModifier(info.getNamespacedKey());
            if (alreadyExistingModifier != null) instance.removeModifier(info.getNamespacedKey());

            AttributeModifier newModifier = new AttributeModifier(info.getNamespacedKey(), info.getArmourPoints(), AttributeModifier.Operation.ADD_NUMBER);
            instance.addModifier(newModifier);
        }

        info.getEquipSound().playSound(plr);

        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        DFAdvancementsHandler.grantAdvancement(plr, EquipAccessory.class);
        boolean hasEmptySlot = false;
        for (ItemStack accessory : accessoryData.slots) {
            if (accessory == null) {
                hasEmptySlot = true;
                break;
            }
        }

        if (!hasEmptySlot) DFAdvancementsHandler.grantAdvancement(plr, EquipAllAccessories.class);

        for (AccessoryMeta meta : info.getMeta()) {
            meta.AccessoryEquipped(plr, item, info);

            Map<Attribute, AttributeModifier> extraModifiers = meta.AddAdditionalAttributeModifiers(plr, item, info);
            if (!extraModifiers.isEmpty()) {
                for (Map.Entry<Attribute, AttributeModifier> extraModifier : extraModifiers.entrySet()) {
                    AttributeInstance extraInstance = plr.getAttribute(extraModifier.getKey());
                    if (extraInstance != null) {
                        AttributeModifier alreadyExistingModifier = extraInstance.getModifier(extraModifier.getValue().getKey());
                        if (alreadyExistingModifier != null) extraInstance.removeModifier(extraModifier.getValue().getKey());

                        extraInstance.addModifier(extraModifier.getValue());
                    }
                }
            }
        }
    }

    public static void AccessoryUnequipped(Player plr, ItemStack item, AccessoryInformation info) {
        AttributeInstance instance = plr.getAttribute(Attribute.ARMOR);
        if (instance != null) {
            AttributeModifier alreadyExistingModifier = instance.getModifier(info.getNamespacedKey());
            if (alreadyExistingModifier != null) instance.removeModifier(info.getNamespacedKey());
        }

        for (AccessoryMeta meta : info.getMeta()) {
            meta.AccessoryUnequipped(plr, item, info);

            Map<Attribute, AttributeModifier> extraModifiers = meta.AddAdditionalAttributeModifiers(plr, item, info);
            if (!extraModifiers.isEmpty()) {
                for (Map.Entry<Attribute, AttributeModifier> extraModifier : extraModifiers.entrySet()) {
                    AttributeInstance extraInstance = plr.getAttribute(extraModifier.getKey());
                    if (extraInstance != null) {
                        AttributeModifier alreadyExistingModifier = extraInstance.getModifier(extraModifier.getValue().getKey());
                        if (alreadyExistingModifier != null) extraInstance.removeModifier(extraModifier.getValue().getKey());
                    }
                }
            }
        }
    }

    public static void UserDamaged(EntityDamageEvent event) {
        Entity damaged = event.getEntity();
        if (damaged instanceof Player plr) {
            PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

            for (int i = 0; i < accessoryData.slots.length; i++) {
                if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

                ItemStack accessoryItem = accessoryData.slots[i];
                AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
                if (info == null) continue;

                for (AccessoryMeta meta : info.getMeta()) {
                    meta.UserDamaged(plr, accessoryItem, info, event);
                }
            }
        }
    }

    public static void UserAttacking(EntityDamageByEntityEvent event) {
        Entity causer = event.getDamager();
        if (causer instanceof Player plr) {
            PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

            for (int i = 0; i < accessoryData.slots.length; i++) {
                if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

                ItemStack accessoryItem = accessoryData.slots[i];
                AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
                if (info == null) continue;

                for (AccessoryMeta meta : info.getMeta()) {
                    meta.UserAttacking(plr, accessoryItem, info, event);
                }
            }
        }
    }

    public static void UserInteract(PlayerInteractEvent event) {
        Player plr = event.getPlayer();
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

        for (int i = 0; i < accessoryData.slots.length; i++) {
            if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

            ItemStack accessoryItem = accessoryData.slots[i];
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) continue;

            for (AccessoryMeta meta : info.getMeta()) {
                meta.UserInteract(plr, accessoryItem, info, event);
            }
        }
    }

    public static void UserMove(PlayerMoveEvent event) {
        Player plr = event.getPlayer();
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

        for (int i = 0; i < accessoryData.slots.length; i++) {
            if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

            ItemStack accessoryItem = accessoryData.slots[i];
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) continue;

            for (AccessoryMeta meta : info.getMeta()) {
                meta.UserMove(plr, accessoryItem, info, event);
            }
        }
    }

    public static void UserKilledPlayer(PlayerDeathEvent event) {
        Entity causer = event.getDamageSource().getCausingEntity();
        if (causer instanceof Player plr) {
            PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

            for (int i = 0; i < accessoryData.slots.length; i++) {
                if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

                ItemStack accessoryItem = accessoryData.slots[i];
                AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
                if (info == null) continue;

                for (AccessoryMeta meta : info.getMeta()) {
                    meta.UserKilledPlayer(plr, accessoryItem, info, event);
                }
            }
        }
    }

    public static void UserTargetted(EntityTargetEvent event) {
       Entity targetted = event.getTarget();
        if (targetted instanceof Player plr) {
            PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

            for (int i = 0; i < accessoryData.slots.length; i++) {
                if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

                ItemStack accessoryItem = accessoryData.slots[i];
                AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
                if (info == null) continue;

                for (AccessoryMeta meta : info.getMeta()) {
                    meta.UserTargetted(plr, accessoryItem, info, event);
                }
            }
        }
    }

    public static void BlockMined(BlockBreakEvent event) {
        Player plr = event.getPlayer();
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

        for (int i = 0; i < accessoryData.slots.length; i++) {
            if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

            ItemStack accessoryItem = accessoryData.slots[i];
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) continue;

            for (AccessoryMeta meta : info.getMeta()) {
                meta.BlockMined(plr, accessoryItem, info, event);
            }
        }
    }

    public static void MinedBlockDropItem(BlockDropItemEvent event) {
        Player plr = event.getPlayer();
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);

        for (int i = 0; i < accessoryData.slots.length; i++) {
            if ((i + 1) >= accessoryData.slots.length && i > accessoryData.getAccessoryCapIndex()) return;

            ItemStack accessoryItem = accessoryData.slots[i];
            AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessoryItem);
            if (info == null) continue;

            for (AccessoryMeta meta : info.getMeta()) {
                meta.MinedBlockDropItem(plr, accessoryItem, info, event);
            }
        }
    }
}
