package uk.co.nikodem.dFSmpPlus.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public class DFItemUtils {


    public static void addUUID(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        meta.getPersistentDataContainer().set(
                Keys.UUID,
                PersistentDataType.STRING,
                UUID.randomUUID().toString()
        );
    }

    public static void addUUIDIfMarked(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        NamespacedKey key = Keys.markedForUUID;
        Boolean marked = meta.getPersistentDataContainer().get(
                key,
                PersistentDataType.BOOLEAN
        );
        meta.getPersistentDataContainer().remove(key);
        if (marked == null) return;
        if (marked) addUUID(item);
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
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case STONE -> {
                res = isStone(item)
                        || isIron(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case IRON -> {
                res = isIron(item)
                        || isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case GOLDEN -> {
                res = isGolden(item)
                        || isDiamond(item)
                        || isNetherite(item);
            }
            case DIAMOND -> {
                res = isDiamond(item)
                        || isNetherite(item);
            }
            case NETHERITE -> {
                res = isNetherite(item);
            }
        };

        return res;
    }

    public static boolean isWooden(ItemStack item) {
        return item.getType() == Material.WOODEN_SWORD
                || item.getType() == Material.WOODEN_AXE
                || item.getType() == Material.WOODEN_PICKAXE
                || item.getType() == Material.WOODEN_SHOVEL
                || item.getType() == Material.WOODEN_HOE;
    }

    public static boolean isStone(ItemStack item) {
        return item.getType() == Material.STONE_SWORD
                || item.getType() == Material.STONE_AXE
                || item.getType() == Material.STONE_PICKAXE
                || item.getType() == Material.STONE_SHOVEL
                || item.getType() == Material.STONE_HOE;
    }

    public static boolean isIron(ItemStack item) {
        return item.getType() == Material.IRON_SWORD
                || item.getType() == Material.IRON_AXE
                || item.getType() == Material.IRON_PICKAXE
                || item.getType() == Material.IRON_SHOVEL
                || item.getType() == Material.IRON_HOE;
    }

    public static boolean isGolden(ItemStack item) {
        return item.getType() == Material.GOLDEN_SWORD
                || item.getType() == Material.GOLDEN_AXE
                || item.getType() == Material.GOLDEN_PICKAXE
                || item.getType() == Material.GOLDEN_SHOVEL
                || item.getType() == Material.GOLDEN_HOE;
    }

    public static boolean isDiamond(ItemStack item) {
        return item.getType() == Material.DIAMOND_SWORD
                || item.getType() == Material.DIAMOND_AXE
                || item.getType() == Material.DIAMOND_PICKAXE
                || item.getType() == Material.DIAMOND_SHOVEL
                || item.getType() == Material.DIAMOND_HOE;
    }

    public static boolean isNetherite(ItemStack item) {
        return item.getType() == Material.NETHERITE_SWORD
                || item.getType() == Material.NETHERITE_AXE
                || item.getType() == Material.NETHERITE_PICKAXE
                || item.getType() == Material.NETHERITE_SHOVEL
                || item.getType() == Material.NETHERITE_HOE;
    }

    public static boolean isCopper(ItemStack item) {
        return DFMaterial.CopperSword.isSimilar(item)
                || DFMaterial.CopperAxe.isSimilar(item)
                || DFMaterial.CopperPickaxe.isSimilar(item)
                || DFMaterial.CopperShovel.isSimilar(item)
                || DFMaterial.CopperHoe.isSimilar(item);
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

        DFMaterial dfMaterial = null;
        for (DFMaterial material : DFMaterial.DFMaterialIndex) {
            if (Objects.equals(material.getNamedId(), dfMaterialId)) {
                dfMaterial = material;
                break;
            }
        }

        return dfMaterial;
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
        ItemMeta meta = item.getItemMeta();
        Component displayName = meta.displayName();
        return displayName != null;
    }
}
