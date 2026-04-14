package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Tool;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.spawner.BaseSpawner;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.Metas.AccessoryItemMeta;

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
        return setModel(item, key == null ? null : Keys.createModelKey(key));
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
            case COPPER -> {
                res = isCopper(item)
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
        return item.getType() == Material.COPPER_SWORD
                || item.getType() == Material.COPPER_AXE
                || item.getType() == Material.COPPER_PICKAXE
                || item.getType() == Material.COPPER_SHOVEL
                || item.getType() == Material.COPPER_HOE
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
    public static AccessoryInformation getAccessoryInformation(ItemStack item) {
        DFMaterial material = getDFMaterial(item);
        if (material == null) return null;

        for (DFMaterialMeta meta : material.getMeta()) {
            if (meta instanceof AccessoryItemMeta accessoryItemMeta) {
                return accessoryItemMeta.information;
            }
        }

        return null;
    }

    public static boolean isAccessory(ItemStack item) {
        DFMaterial material = getDFMaterial(item);
        if (material == null) return false;

        return containsMeta(material, AccessoryItemMeta.class);
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

        return getDFMaterial(dfMaterialId);
    }

    @Nullable
    public static DFMaterial getDFMaterial(String namedId) {
        if (namedId == null) return null;

        return DFMaterial.DFMaterialIndex.get(namedId);
    }

    @Nullable
    public static Integer getLegacyDFVersion(ItemStack item) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        return meta.getPersistentDataContainer().get(
                Keys.legacy_dfmaterialVersion,
                PersistentDataType.INTEGER
        );
    }

    @Nullable
    public static String getDFUpdateId(ItemStack item) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        return meta.getPersistentDataContainer().get(
                Keys.dfUpdateId,
                PersistentDataType.STRING
        );
    }

    public static boolean itemIsRenamed(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        if (meta.hasCustomName()) {
            String name = MiniMessage.miniMessage().serialize(Objects.requireNonNull(meta.customName()));
            return !name.startsWith("<!italic>");
        }

        return false;
    }

    public static Tool createFasterTool(ItemType type, float speedMultiplier) {
        Tool base = type.getDefaultData(DataComponentTypes.TOOL);
        List<Tool.Rule> rules = new ArrayList<>();

        for (Tool.Rule rule : base.rules()) {
            rules.add(Tool.rule(rule.blocks(), rule.speed() == null ? null : (rule.speed() * speedMultiplier), rule.correctForDrops()));
        }

        return Tool.tool()
                .defaultMiningSpeed(base.defaultMiningSpeed())
                .addRules(rules)
                .damagePerBlock(base.damagePerBlock())
                .canDestroyBlocksInCreative(base.canDestroyBlocksInCreative())
                .build();
    }

    // true = no action on right click
    // false = action on right click guaranteed
    // null = action on right click is conditional / doesn't exist, but still considered interactable
    @Nullable
    public static Boolean shouldBePlaced(Block block) {
        if (block == null) return true;

        if (block.getState() instanceof BaseSpawner spawner) {
            if (spawner.getSpawnedType() == null) return true;
            else return null;
        }

        List<Material> cancelMaterials = List.of(Material.CHISELED_BOOKSHELF, Material.BELL, Material.VAULT, Material.RESPAWN_ANCHOR, Material.CAULDRON, Material.CAKE, Material.JUKEBOX, Material.REDSTONE, Material.IRON_DOOR, Material.IRON_TRAPDOOR, Material.TNT, Material.PUMPKIN);
        List<Tag<Material>> cancelTags = List.of(Tag.CANDLES, Tag.CANDLE_CAKES, Tag.BEEHIVES, Tag.CAMPFIRES, Tag.WOODEN_SHELVES, Tag.FENCES);

        if (cancelMaterials.contains(block.getType())) return null;

        for (Tag<Material> tag : cancelTags) {
            if (tag.isTagged(block.getType())) return null;
        }

        // Material#isInteractable() is pretty unreliable, but it works well enough /shrug
        return !block.getType().isInteractable();
    }
}
