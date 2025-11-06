package uk.co.nikodem.dFSmpPlus.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

import javax.annotation.Nullable;
import java.util.*;

public class DFItemUtils {
    public static void addUUID(ItemStack item) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        meta.getPersistentDataContainer().set(
                Keys.UUID,
                PersistentDataType.STRING,
                UUID.randomUUID().toString()
        );
        item.setItemMeta(meta);
    }

    public static void addUUIDIfMarked(ItemStack item) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        Boolean marked = meta.getPersistentDataContainer().get(
                Keys.markedForUUID,
                PersistentDataType.BOOLEAN
        );
        if (marked == null) return;
        if (marked) addUUID(item);
        meta.getPersistentDataContainer().remove(Keys.markedForUUID);
    }

    @Nullable
    public static String getUUID(ItemStack item) {
        if (item == null) return null;
        return getString(item, Keys.UUID);
    }

    @Nullable
    public static boolean set(ItemStack item, NamespacedKey key, PersistentDataType type, Object val) {
        if (item == null || type == null || val == null) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        meta.getPersistentDataContainer().set(
                key,
                type,
                val
        );
        item.setItemMeta(meta);
        return true;
    }

    @Nullable
    public static String getString(ItemStack item, NamespacedKey key) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        String string = meta.getPersistentDataContainer().get(
                key,
                PersistentDataType.STRING
        );
        if (string == null) return null;
        return string;
    }

    @Nullable
    public static Boolean getBoolean(ItemStack item, NamespacedKey key) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        Boolean bool = meta.getPersistentDataContainer().get(
                key,
                PersistentDataType.BOOLEAN
        );
        if (bool == null) return null;
        return bool;
    }

    @Nullable
    public static Integer getInteger(ItemStack item, NamespacedKey key) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        Integer integer = meta.getPersistentDataContainer().get(
                key,
                PersistentDataType.INTEGER
        );
        if (integer == null) return null;
        return integer;
    }

    @Nullable
    public static ItemStack setModel(ItemStack item, NamespacedKey key) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        meta.setItemModel(key);
        item.setItemMeta(meta);
        return item;
    }

    @Nullable
    public static ItemStack setModel(ItemStack item, String key) {
        return setModel(item, Keys.createModelKey(key));
    }

    public static boolean removeAttributes(ItemStack item) {
        if (item == null) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        if (!meta.hasAttributeModifiers()) return false;
        for (Map.Entry<Attribute, AttributeModifier> a : meta.getAttributeModifiers().entries()) {
            meta.removeAttributeModifier(a.getKey());
        }
        item.setItemMeta(meta);
        return true;
    }

    public static boolean removeAttribute(ItemStack item, Attribute attr) {
        if (item == null) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        if (!meta.hasAttributeModifiers()) return false;
        meta.removeAttributeModifier(attr);
        item.setItemMeta(meta);
        return true;
    }

    public static boolean setAttribute(ItemStack item, Attribute attr, AttributeModifier modifier) {
        if (item == null) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        meta.addAttributeModifier(
                attr,
                modifier
        );
        item.setItemMeta(meta);
        return true;
    }

    public static boolean setDamage(ItemStack item, int damage) {
        if (item == null) return false;
        Damageable meta = (Damageable) item.getItemMeta();
        if (meta == null) return false;
//        if (!meta.hasMaxDamage()) return false;

        meta.setDamage(damage);
        item.setItemMeta(meta);
        return true;
    }

    public static boolean reduceDurability(Player plr, ItemStack item, int damageAmount, boolean destructive) {
        // ItemStack::damage doesn't work for me for some reason
        // cba to report it to paper so im just rewriting my own implementation :p
        if (plr.getGameMode() == GameMode.CREATIVE) return false;

        if (item == null) return false;
        Damageable meta = (Damageable) item.getItemMeta();
        if (meta == null) return false;
        if (!meta.hasMaxDamage()) return false;

        int unbreakingLevel = meta.getEnchantLevel(Enchantment.UNBREAKING);
        float chance = (float) 100 / (unbreakingLevel+1);

        float random_chance = new Random().nextFloat(100);

        if (random_chance <= chance) {
            if (meta.hasDamage()) {
                if (meta.getMaxDamage() - meta.getDamage() <= damageAmount) {
                    item.setAmount(item.getAmount() - 1);
                    plr.broadcastSlotBreak(EquipmentSlot.HAND);
                }
                else meta.setDamage(meta.getDamage() + damageAmount);
            } else meta.setDamage(damageAmount);
            item.setItemMeta(meta);
            return true;
        }

        return false;
    }

    public static boolean reduceDurability(Player plr, ItemStack item, int damageAmount) {
        return reduceDurability(plr, item, damageAmount, true);
    }

    public static boolean containsMeta(DFMaterial material, Class<? extends DFMaterialMeta> clazz) {
        for (DFMaterialMeta meta : material.getMeta()) {
            if (meta.getClass() == clazz) return true;
        }
        return false;
    }

    public static boolean isRealTool(ItemStack item) {
        return isAxe(item)
                || isPickaxe(item)
                || isShovel(item)
                || isHoe(item);
    }

    public static boolean isTool(ItemStack item) {
        return isSword(item)
                || isAxe(item)
                || isPickaxe(item)
                || isShovel(item)
                || isHoe(item);
    }

    public static boolean isLevelOrAbove(ItemStack item, Enums.ToolLevel level) {

        boolean res = false;
        switch (level) {
            case NONE -> res = true;
            case WOODEN -> {
                res = isWooden(item)
                        || isStone(item)
                        || isIron(item)
                        || isFiridium(item)
                        || isCopper(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item)
                        || isObsidian(item);
            }
            case STONE -> {
                res = isStone(item)
                        || isIron(item)
                        || isFiridium(item)
                        || isCopper(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item)
                        || isObsidian(item);
            }
            case IRON -> {
                res = isIron(item)
                        || isFiridium(item)
                        || isCopper(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item)
                        || isObsidian(item);
            }
            case GOLDEN -> {
                res = isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item)
                        || isObsidian(item);
            }
            case DIAMOND -> {
                res = isDiamond(item)
                        || isNetherite(item)
                        || isObsidian(item);
            }
            case NETHERITE -> {
                res = isNetherite(item)
                        || isObsidian(item);
            }
        };

        return res;
    }

    public static boolean isWooden(ItemStack item) {
        return item.getType() == Material.WOODEN_SWORD
                || item.getType() == Material.WOODEN_AXE
                || item.getType() == Material.WOODEN_PICKAXE
                || item.getType() == Material.WOODEN_SHOVEL
                || item.getType() == Material.WOODEN_HOE
                || DFMaterial.PointyStick.isSimilar(item);
    }

    public static boolean isStone(ItemStack item) {
        return item.getType() == Material.STONE_SWORD
                || item.getType() == Material.STONE_AXE
                || item.getType() == Material.STONE_PICKAXE
                || item.getType() == Material.STONE_SHOVEL
                || item.getType() == Material.STONE_HOE
                || DFMaterial.SharpStone.isSimilar(item);
    }

    public static boolean isIron(ItemStack item) {
        return (item.getType() == Material.IRON_SWORD
                || item.getType() == Material.IRON_AXE
                || item.getType() == Material.IRON_PICKAXE
                || item.getType() == Material.IRON_SHOVEL
                || item.getType() == Material.IRON_HOE
                || DFMaterial.IronChisel.isSimilar(item)) && !isCopper(item);
    }

    public static boolean isGolden(ItemStack item) {
        return item.getType() == Material.GOLDEN_SWORD
                || item.getType() == Material.GOLDEN_AXE
                || item.getType() == Material.GOLDEN_PICKAXE
                || item.getType() == Material.GOLDEN_SHOVEL
                || item.getType() == Material.GOLDEN_HOE
                || DFMaterial.GoldChisel.isSimilar(item);
    }

    public static boolean isDiamond(ItemStack item) {
        return item.getType() == Material.DIAMOND_SWORD
                || item.getType() == Material.DIAMOND_AXE
                || item.getType() == Material.DIAMOND_PICKAXE
                || item.getType() == Material.DIAMOND_SHOVEL
                || item.getType() == Material.DIAMOND_HOE
                || DFMaterial.DiamondChisel.isSimilar(item);
    }

    public static boolean isNetherite(ItemStack item) {
        return item.getType() == Material.NETHERITE_SWORD
                || item.getType() == Material.NETHERITE_AXE
                || item.getType() == Material.NETHERITE_PICKAXE
                || item.getType() == Material.NETHERITE_SHOVEL
                || item.getType() == Material.NETHERITE_HOE
                || DFMaterial.NetheriteChisel.isSimilar(item);
    }

    public static boolean isCopper(ItemStack item) {
        return DFMaterial.CopperSword.isSimilar(item)
                || DFMaterial.CopperAxe.isSimilar(item)
                || DFMaterial.CopperPickaxe.isSimilar(item)
                || DFMaterial.CopperShovel.isSimilar(item)
                || DFMaterial.CopperHoe.isSimilar(item)
                || DFMaterial.CopperChisel.isSimilar(item);
    }

    public static boolean isObsidian(ItemStack item) {
        return DFMaterial.ObsidianSword.isSimilar(item)
                || DFMaterial.ObsidianAxe.isSimilar(item)
                || DFMaterial.ObsidianPickaxe.isSimilar(item)
                || DFMaterial.ObsidianShovel.isSimilar(item)
                || DFMaterial.ObsidianHoe.isSimilar(item)
                || DFMaterial.ObsidianChisel.isSimilar(item);
    }

    public static boolean isFiridium(ItemStack item) {
        return DFMaterial.FiridiumSword.isSimilar(item)
                || DFMaterial.FiridiumAxe.isSimilar(item)
                || DFMaterial.FiridiumPickaxe.isSimilar(item)
                || DFMaterial.FiridiumShovel.isSimilar(item)
                || DFMaterial.FiridiumHoe.isSimilar(item)
                || DFMaterial.FiridiumChisel.isSimilar(item);
    }

    public static boolean isSword(ItemStack item) {
        return item.getType() == Material.WOODEN_SWORD
                || item.getType() == Material.STONE_SWORD
                || item.getType() == Material.IRON_SWORD
                || item.getType() == Material.GOLDEN_SWORD
                || item.getType() == Material.DIAMOND_SWORD
                || item.getType() == Material.NETHERITE_SWORD;
    }

    public static boolean isAxe(ItemStack item) {
        return item.getType() == Material.WOODEN_AXE
                || item.getType() == Material.STONE_AXE
                || item.getType() == Material.IRON_AXE
                || item.getType() == Material.GOLDEN_AXE
                || item.getType() == Material.DIAMOND_AXE
                || item.getType() == Material.NETHERITE_AXE;
    }

    public static boolean isPickaxe(ItemStack item) {
        return item.getType() == Material.WOODEN_PICKAXE
                || item.getType() == Material.STONE_PICKAXE
                || item.getType() == Material.IRON_PICKAXE
                || item.getType() == Material.GOLDEN_PICKAXE
                || item.getType() == Material.DIAMOND_PICKAXE
                || item.getType() == Material.NETHERITE_PICKAXE;
    }

    public static boolean isShovel(ItemStack item) {
        return item.getType() == Material.WOODEN_SHOVEL
                || item.getType() == Material.STONE_SHOVEL
                || item.getType() == Material.IRON_SHOVEL
                || item.getType() == Material.GOLDEN_SHOVEL
                || item.getType() == Material.DIAMOND_SHOVEL
                || item.getType() == Material.NETHERITE_SHOVEL;
    }

    public static boolean isHoe(ItemStack item) {
        return item.getType() == Material.WOODEN_HOE
                || item.getType() == Material.STONE_HOE
                || item.getType() == Material.IRON_HOE
                || item.getType() == Material.GOLDEN_HOE
                || item.getType() == Material.DIAMOND_HOE
                || item.getType() == Material.NETHERITE_HOE;
    }

    public static boolean isChisel(ItemStack item) {
        return DFMaterial.PointyStick.isSimilar(item)
                || DFMaterial.SharpStone.isSimilar(item)
                || DFMaterial.CopperChisel.isSimilar(item)
                || DFMaterial.IronChisel.isSimilar(item)
                || DFMaterial.FiridiumChisel.isSimilar(item)
                || DFMaterial.GoldChisel.isSimilar(item)
                || DFMaterial.DiamondChisel.isSimilar(item)
                || DFMaterial.NetheriteChisel.isSimilar(item)
                || DFMaterial.ObsidianChisel.isSimilar(item);
    }

    public static boolean hasFireAspect(ItemStack item) {
        return item.containsEnchantment(Enchantment.FIRE_ASPECT);
    }

    @Nullable
    public static DFMaterial getDFMaterial(ItemStack item) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        String dfMaterialId = meta.getPersistentDataContainer().get(
                Keys.dfmaterial,
                PersistentDataType.STRING
        );

        for (DFMaterial material : DFMaterial.DFMaterialIndex) {
            if (Objects.equals(material.getNamedId(), dfMaterialId)) {
                return material;
            }
        }

        return null;
    }

    @Nullable
    public static Integer getCurrentVersion(ItemStack item) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        return meta.getPersistentDataContainer().get(
                Keys.dfmaterialVersion,
                PersistentDataType.INTEGER
        );
    }

    public static boolean itemIsRenamed(ItemStack item) {
        DFMaterial material = DFItemUtils.getDFMaterial(item);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        Component displayName = meta.displayName();

        if (material == null) {
            return displayName != null;
        }

        return displayName != material.getDisplayName();
    }
}
