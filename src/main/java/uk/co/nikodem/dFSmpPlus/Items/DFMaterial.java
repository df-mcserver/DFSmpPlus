package uk.co.nikodem.dFSmpPlus.Items;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.AutoSmeltable;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Constants.VeinMineable;
import uk.co.nikodem.dFSmpPlus.Items.Metas.*;
import org.bukkit.Material;

import javax.annotation.Nullable;
import java.util.*;

public class DFMaterial {
    // please don't touch lol
    public final static List<DFMaterial> DFMaterialIndex = new ArrayList<>();

    public static DFMaterial MagicMirror = new DFMaterialBuilder(Material.COMPASS, "magic_mirror", 1)
            .setDisplayName("<dark_purple>Magic Mirror")
            .addLore("<gold>Teleports you back to your bed.")
            .addLore("<red>Will not work in combat.")
            .addEnchantment(Enchantment.LOYALTY, 100)
            .addItemFlag(ItemFlag.HIDE_ENCHANTS)
            .addMeta(new MagicMirrorMeta())
            .create();

    public static DFMaterial EntityBucket = new DFMaterialBuilder(Material.BUCKET, "entity_bucket", 1)
            .setDisplayName("Entity Bucket")
            .addMeta(new EntityBucketMeta())
            .create();

    public static DFMaterial VeinPickaxe = new DFMaterialBuilder(Material.IRON_PICKAXE, "vein_pickaxe", 1)
            .setDisplayName("<light_purple>Vein Miner's Pickaxe")
            .addLore("<aqua>A powerful pickaxe from a well-respected miner.")
            .addMeta(new VeinMiningMeta(VeinMineable.VeinOres))
            .create();

    public static DFMaterial VeinAxe = new DFMaterialBuilder(Material.IRON_AXE, "vein_axe", 1)
            .setDisplayName("<light_purple>Vein Miner's Axe")
            .addLore("<aqua>A powerful axe from a well-respected miner.")
            .addMeta(new VeinMiningMeta(VeinMineable.VeinLogs))
            .create();

    public static DFMaterial FiridiumSword = new DFMaterialBuilder(Material.IRON_SWORD, "firidium_sword", 1)
            .setDisplayName("<red>Firidium Sword")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .create();

    public static DFMaterial FiridiumPickaxe = new DFMaterialBuilder(Material.IRON_PICKAXE, "firidium_pickaxe", 1)
            .setDisplayName("<red>Firidium Pickaxe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutoSmeltingMeta(AutoSmeltable.AutosmeltablePickaxe))
            .create();

    public static DFMaterial FiridiumAxe = new DFMaterialBuilder(Material.IRON_AXE, "firidium_axe", 1)
            .setDisplayName("<red>Firidium Axe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutoSmeltingMeta(AutoSmeltable.AutosmeltableAxe))
            .create();

    public static DFMaterial FiridiumShovel = new DFMaterialBuilder(Material.IRON_SHOVEL, "firidium_shovel", 1)
            .setDisplayName("<red>Firidium Shovel")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutoSmeltingMeta(AutoSmeltable.AutosmeltableShovel))
            .create();

    public static DFMaterial FiridiumHoe = new DFMaterialBuilder(Material.IRON_HOE, "firidium_hoe", 1)
            .setDisplayName("<red>Firidium Hoe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .create();

    public static DFMaterial FiridiumIngot = new DFMaterialBuilder(Material.IRON_INGOT, "firidium_ingot", 1)
            .setDisplayName("<red>Firidium Ingot")
            .create();

    public static DFMaterial FiridiumNugget = new DFMaterialBuilder(Material.IRON_INGOT, "firidium_nugget", 1)
            .setDisplayName("<red>Firidium Nugget")
            .create();

    public static DFMaterial FiridiumHelmet = new DFMaterialBuilder(Material.IRON_HELMET, "firidium_helmet", 1)
            .setDisplayName("<red>Firidium Helmet")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.HEAD)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Helmet)")
            .create();

    public static DFMaterial FiridiumChestplate = new DFMaterialBuilder(Material.IRON_CHESTPLATE, "firidium_chestplate", 1)
            .setDisplayName("<red>Firidium Chestplate")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.CHEST)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Chestplate)")
            .create();

    public static DFMaterial FiridiumLeggings = new DFMaterialBuilder(Material.IRON_LEGGINGS, "firidium_leggings", 1)
            .setDisplayName("<red>Firidium Leggings")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.LEGS)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Leggings)")
            .create();

    public static DFMaterial FiridiumBoots = new DFMaterialBuilder(Material.IRON_BOOTS, "firidium_boots", 1)
            .setDisplayName("<red>Firidium Boots")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.FEET)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Boots)")
            .create();

    public static DFMaterial BluebellsarStick = new DFMaterialBuilder(Material.STICK, "bluebellsar_stick", 1)
            .setDisplayName("<light_purple>Bluebellsar Stick")
            .markForUUID()
            .addLore("<aqua>Using this item shrivels shrieks from past souls.")
            .addLore("<red>Will not work in combat.")
            .addEnchantment(Enchantment.UNBREAKING, 25)
            .addEnchantment(Enchantment.LOOTING, 3)
            .addEnchantment(Enchantment.LUCK_OF_THE_SEA)
            .addEnchantment(Enchantment.AQUA_AFFINITY)
            .addEnchantment(Enchantment.MENDING)
            .addMeta(new BluebellsarMeta())
            .removeCustomModel()
            .create();

    public static DFMaterial CopperSword = new DFMaterialBuilder(Material.IRON_SWORD, "copper_sword", 1)
            .setDisplayName("Copper Sword")
            .create();

    public static DFMaterial CopperAxe = new DFMaterialBuilder(Material.IRON_AXE, "copper_axe", 1)
            .setDisplayName("Copper Axe")
            .create();

    public static DFMaterial CopperPickaxe = new DFMaterialBuilder(Material.IRON_PICKAXE, "copper_pickaxe", 1)
            .setDisplayName("Copper Pickaxe")
            .create();

    public static DFMaterial CopperShovel = new DFMaterialBuilder(Material.IRON_SHOVEL, "copper_shovel", 1)
            .setDisplayName("Copper Shovel")
            .create();

    public static DFMaterial CopperHoe = new DFMaterialBuilder(Material.IRON_HOE, "copper_hoe", 1)
            .setDisplayName("Copper Hoe")
            .create();

    public static DFMaterial CopperHelmet = new DFMaterialBuilder(Material.IRON_HELMET, "copper_helmet", 1)
            .setDisplayName("Copper Helmet")
            .setEquippable("copper", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.HEAD)
            .addLore("<gray>(Equivalent to Iron Helmet)")
            .create();

    public static DFMaterial CopperChestplate = new DFMaterialBuilder(Material.IRON_CHESTPLATE, "copper_chestplate", 1)
            .setDisplayName("Copper Chestplate")
            .setEquippable("copper", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.CHEST)
            .addLore("<gray>(Equivalent to Iron Chestplate)")
            .create();

    public static DFMaterial CopperLeggings = new DFMaterialBuilder(Material.IRON_LEGGINGS, "copper_leggings", 1)
            .setDisplayName("Copper Leggings")
            .setEquippable("copper", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.LEGS)
            .addLore("<gray>(Equivalent to Iron Leggings)")
            .create();

    public static DFMaterial CopperBoots = new DFMaterialBuilder(Material.IRON_BOOTS, "copper_boots", 1)
            .setDisplayName("Copper Boots")
            .setEquippable("copper", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.FEET)
            .addLore("<gray>(Equivalent to Iron Boots)")
            .create();
//
//    public static DFMaterial Tanzanite = new DFMaterialBuilder(Material.FIREWORK_STAR, "tanzanite", 1)
//            .setDisplayName("Tanzanite")
//            .create();
//
//    public static DFMaterial ImpureTanzanite = new DFMaterialBuilder(Material.FIREWORK_STAR, "impure_tanzanite", 1)
//            .setDisplayName("Impure Tanzanite")
//            .create();
//
//    public static DFMaterial TanzaniteOre = new DFMaterialBuilder(Material.END_STONE, "tanzanite_ore", 1)
//            .setDisplayName("Tanzanite Ore")
//            .create();
//
//    public static DFMaterial Uranium = new DFMaterialBuilder(Material.FIREWORK_STAR, "uranium", 1)
//            .setDisplayName("Uranium")
//            .create();
//
//    public static DFMaterial ImpureUranium = new DFMaterialBuilder(Material.FIREWORK_STAR, "impure_uranium", 1)
//            .setDisplayName("Impure Uranium")
//            .create();
//
//    public static DFMaterial ContaminatedMud = new DFMaterialBuilder(Material.MUD, "contaminated_mud", 1)
//            .setDisplayName("Contaminated Mud")
//            .create();

    public static DFMaterial ObsidianUpgradeTemplate = new DFMaterialBuilder(Material.FIREWORK_STAR, "obsidian_upgrade", 1)
            .setDisplayName("<yellow>Obsidian Upgrade")
            .addLore("<gray>Smithing Template")
            .addLore("")
            .addLore("<gray>Applies to:")
            .addLore(" <blue>Netherite Equipment")
            .addLore("<gray>Ingredients:")
            .addLore(" <blue>Crying Obsidian")
            .create();

    public static DFMaterial ObsidianSword = new DFMaterialBuilder(Material.NETHERITE_SWORD, "obsidian_sword", 1)
            .setDisplayName("Obsidian Sword")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianSwordMeta())
            .create();

    public static DFMaterial ObsidianAxe = new DFMaterialBuilder(Material.NETHERITE_AXE, "obsidian_axe", 1)
            .setDisplayName("Obsidian Axe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianToolMeta(), new ObsidianSwordMeta())
            .create();

    public static DFMaterial ObsidianPickaxe = new DFMaterialBuilder(Material.NETHERITE_PICKAXE, "obsidian_pickaxe", 1)
            .setDisplayName("Obsidian Pickaxe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianToolMeta())
            .create();

    public static DFMaterial ObsidianShovel = new DFMaterialBuilder(Material.NETHERITE_SHOVEL, "obsidian_shovel", 1)
            .setDisplayName("Obsidian Shovel")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianToolMeta())
            .create();

    public static DFMaterial ObsidianHoe = new DFMaterialBuilder(Material.NETHERITE_HOE, "obsidian_hoe", 1)
            .setDisplayName("Obsidian Hoe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianToolMeta())
            .create();

    public static DFMaterial ObsidianHelmet = new DFMaterialBuilder(Material.NETHERITE_HELMET, "obsidian_helmet", 1)
            .setDisplayName("Obsidian Helmet")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.HEAD)
            .addLore("<gray>(Equivalent to Netherite Helmet)")
            .create();

    public static DFMaterial ObsidianChestplate = new DFMaterialBuilder(Material.NETHERITE_CHESTPLATE, "obsidian_chestplate", 1)
            .setDisplayName("Obsidian Chestplate")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.CHEST)
            .addLore("<gray>(Equivalent to Netherite Chestplate)")
            .create();

    public static DFMaterial ObsidianLeggings = new DFMaterialBuilder(Material.NETHERITE_LEGGINGS, "obsidian_leggings", 1)
            .setDisplayName("Obsidian Leggings")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.LEGS)
            .addLore("<gray>(Equivalent to Netherite Leggings)")
            .create();

    public static DFMaterial ObsidianBoots = new DFMaterialBuilder(Material.NETHERITE_BOOTS, "obsidian_boots", 1)
            .setDisplayName("Obsidian Boots")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.FEET)
            .addLore("<gray>(Equivalent to Netherite Boots)")
            .create();

    public static DFMaterial LifeCrystal = new DFMaterialBuilder(Material.FIREWORK_STAR, "life_crystal", 1)
            .setDisplayName("<red>Life Crystal")
            .addLore("<red>Increases your max health by 2.")
            .addMeta(new LifeCrystalMeta())
            .create();

    public static DFMaterial WarpedWart = new DFMaterialBuilder(Material.NETHER_WART, "warped_wart", 1)
            .setDisplayName("Warped Wart")
            .create();

    public static DFMaterial SculkHelmet = new DFMaterialBuilder(Material.DIAMOND_HELMET, "sculk_helmet", 1)
            .setDisplayName("Sculk Helmet")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.HEAD)
            .addLore("<gray>(Equivalent to Diamond Helmet)")
            .create();

    public static DFMaterial SculkChestplate = new DFMaterialBuilder(Material.DIAMOND_CHESTPLATE, "sculk_chestplate", 1)
            .setDisplayName("Sculk Chestplate")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.CHEST)
            .addLore("<gray>(Equivalent to Diamond Chestplate)")
            .create();

    public static DFMaterial SculkLeggings = new DFMaterialBuilder(Material.DIAMOND_LEGGINGS, "sculk_leggings", 1)
            .setDisplayName("Sculk Leggings")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.LEGS)
            .addLore("<gray>(Equivalent to Diamond Leggings)")
            .create();

    public static DFMaterial SculkBoots = new DFMaterialBuilder(Material.DIAMOND_BOOTS, "sculk_boots", 1)
            .setDisplayName("Sculk Boots")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.FEET)
            .addLore("<gray>(Equivalent to Diamond Boots)")
            .create();

    public static DFMaterial SculkFragment = new DFMaterialBuilder(Material.ECHO_SHARD, "sculk_fragment", 1)
            .setDisplayName("Sculk Fragment")
            .create();

    public static DFMaterial VampireSword = new DFMaterialBuilder(Material.WOODEN_SWORD, "vampire_sword", 1)
            .setDisplayName("Vampire Sword")
            .overrideCustomModel("vamp_stage0")
            .addLore("<aqua>A powerful sword which grows in power with every kill.")
            .markForUUID()
            .addPersistentData(Keys.vampireSwordStage, PersistentDataType.INTEGER, 0)
            .addMeta(new VampireSwordMeta())
            .create();

    public static DFMaterial PointyStick = new DFMaterialBuilder(Material.STICK, "pointy_stick", 1)
            .setDisplayName("Pointy Stick")
            .addMeta(new SoundOnCraftMeta(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR), new ChiselMeta())
            // TODO: make markForUUID do something
            .markForUUID() // 1 stack
            .create();

    public static DFMaterial LooseStone = new DFMaterialBuilder(Material.STICK, "loose_stone", 1)
            .setDisplayName("Loose Stone")
            .create();

    public static DFMaterial SharpStone = new DFMaterialBuilder(Material.STICK, "sharp_stone", 1)
            // TODO: Add faster mining speed attribute
            .setDisplayName("Sharp Stone")
            .addMeta(new SoundOnCraftMeta(Sound.BLOCK_ANVIL_PLACE, 1F, 1.75F), new ChiselMeta())
            .markForUUID() // 1 stack
            .create();

    public static DFMaterial IronChisel = new DFMaterialBuilder(Material.STICK, "iron_chisel", 1)
            // TODO: double check this
            .setDisplayName("Iron Chisel")
            .addMeta(new ChiselMeta())
            .markForUUID()
            .create();

    public static DFMaterial 

    private final String namedId;
    private final TextComponent displayName;
    private final Material base;
    private final boolean markedForUuid;
    private final List<TextComponent> lores;
    private final ItemStack item;
    private final int version;
    private final List<DFMaterialMeta> metas;

    public ItemStack toItemStack() {
        return item.clone();
    }

    public ItemStack toItemStack(int amount) {
        ItemStack i = item.clone();
        i.setAmount(amount);
        return i;
    }

    public boolean isSimilar(ItemStack comparison) {

        if (comparison.getType() != base) return false;

        ItemMeta meta = comparison.getItemMeta();
        return meta.hasCustomModelDataComponent() && Objects.equals(meta.getCustomModelDataComponent().toString(), namedId);
    }

    public Material getType() {
        return base;
    }

    public int getVersion() {
        return version;
    }

    public boolean isMarkedForUuid() {
        return markedForUuid;
    }

    public TextComponent getDisplayName() {
        return displayName;
    }

    public List<TextComponent> getLore() {
        return lores;
    }

    public String getNamedId() {
        return this.namedId;
    }

    public List<DFMaterialMeta> getMeta() {
        return this.metas;
    }

    public Boolean hasMeta() {
        return !this.metas.isEmpty();
    }

    public DFMaterial(
            Material base,
            String namedId,
            @Nullable TextComponent Name,
            @Nullable List<TextComponent> lores,
            @Nullable HashMap<Enchantment, Integer> Enchantments,
            @Nullable HashMap<org.bukkit.attribute.Attribute, AttributeModifier> Attributes,
            @Nullable HashMap<NamespacedKey, Map.Entry<PersistentDataType, Object>> PersistentData,
            boolean markedForUuid,
            @Nullable NamespacedKey equipModel,
            @Nullable Sound equipSound,
            @Nullable EquipmentSlot equipSlot,
            int version,
            @Nullable List<ItemFlag> flags,
            List<DFMaterialMeta> dfmetas,
            boolean hasCustomModel,
            NamespacedKey customModel
    )
    {
        this.version = version;
        this.markedForUuid = markedForUuid;
        this.namedId = namedId;
        if (Name != null) this.displayName = Name;
        else this.displayName = null;
        this.base = base;
        this.lores = lores;

        if (dfmetas == null) this.metas = new ArrayList<>();
        else this.metas = dfmetas;

        ItemStack newItem = new ItemStack(base);
        this.item = newItem;

        ItemMeta meta = newItem.getItemMeta();
        if (meta == null) return;

        if (hasCustomModel) {
            meta.setItemModel(customModel);
        }

        if (Name != null) meta.displayName(Name);
        if (lores != null && !lores.isEmpty()) meta.lore(lores);
        if (Enchantments != null && !Enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> ench : Enchantments.entrySet()) {
                meta.addEnchant(ench.getKey(), ench.getValue(), true);
            }
        }
        if (Attributes != null && !Attributes.isEmpty()) {
            for (Map.Entry<Attribute, AttributeModifier> attribute : Attributes.entrySet()) {
                meta.addAttributeModifier(attribute.getKey(), attribute.getValue());
            }
        }
        meta.getPersistentDataContainer().set(
                Keys.dfmaterial,
                PersistentDataType.STRING,
                namedId
        );
        meta.getPersistentDataContainer().set(
                Keys.markedForUUID,
                PersistentDataType.BOOLEAN,
                markedForUuid
        );
        meta.getPersistentDataContainer().set(
                Keys.dfmaterialVersion,
                PersistentDataType.INTEGER,
                version
        );
        if (PersistentData != null && !PersistentData.isEmpty()) {
            for (Map.Entry<NamespacedKey, Map.Entry<PersistentDataType, Object>> data : PersistentData.entrySet()) {
                NamespacedKey key = data.getKey();
                PersistentDataType type = data.getValue().getKey();
                var val = data.getValue().getValue();

                meta.getPersistentDataContainer().set(
                        key,
                        type,
                        val
                );
            }
        }
        if (equipSlot != null && equipSound != null && equipModel != null) {
            meta.getEquippable();
            EquippableComponent equippable = meta.getEquippable();
            equippable.setSlot(equipSlot);
            equippable.setEquipSound(equipSound);
            equippable.setModel(equipModel);
            meta.setEquippable(equippable);
        }
        if (flags != null && !flags.isEmpty()) {
            for (ItemFlag flag : flags) {
                meta.addItemFlags(flag);
            }
        }
        newItem.setItemMeta(meta);

        if (this.hasMeta()) {
            for (DFMaterialMeta createdmeta : this.getMeta()) {
                createdmeta.ItemCreated(this, this.item);
            }
        }
    }
}
