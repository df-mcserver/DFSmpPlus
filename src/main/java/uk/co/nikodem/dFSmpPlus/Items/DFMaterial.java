package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Consumable;
import io.papermc.paper.datacomponent.item.Tool;
import io.papermc.paper.datacomponent.item.consumable.ItemUseAnimation;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.Bluebellsar;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.*;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Vamp.SoItBegins;
import uk.co.nikodem.dFSmpPlus.Constants.AutoSmeltable;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Constants.VeinMineable;
import uk.co.nikodem.dFSmpPlus.Items.Metas.*;
import org.bukkit.Material;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;

import static uk.co.nikodem.dFSmpPlus.Constants.Keys.createMinecraftKey;
import static uk.co.nikodem.dFSmpPlus.Constants.Keys.createModelKey;

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
            .addMeta(new VeinMiningMeta(VeinMineable.VeinOres), new AdvancementOnCraftMeta(VeinTool.class))
            .create();

    public static DFMaterial VeinAxe = new DFMaterialBuilder(Material.IRON_AXE, "vein_axe", 1)
            .setDisplayName("<light_purple>Vein Miner's Axe")
            .addLore("<aqua>A powerful axe from a well-respected miner.")
            .addMeta(new VeinMiningMeta(VeinMineable.VeinLogs), new AdvancementOnCraftMeta(VeinTool.class))
            .create();

    public static DFMaterial FiridiumSword = new DFMaterialBuilder(Material.IRON_SWORD, "firidium_sword", 1)
            .setDisplayName("<red>Firidium Sword")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumPickaxe = new DFMaterialBuilder(Material.IRON_PICKAXE, "firidium_pickaxe", 1)
            .setDisplayName("<red>Firidium Pickaxe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutoSmeltingMeta(AutoSmeltable.AutosmeltablePickaxe), new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumAxe = new DFMaterialBuilder(Material.IRON_AXE, "firidium_axe", 1)
            .setDisplayName("<red>Firidium Axe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutoSmeltingMeta(AutoSmeltable.AutosmeltableAxe), new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumShovel = new DFMaterialBuilder(Material.IRON_SHOVEL, "firidium_shovel", 1)
            .setDisplayName("<red>Firidium Shovel")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutoSmeltingMeta(AutoSmeltable.AutosmeltableShovel), new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumHoe = new DFMaterialBuilder(Material.IRON_HOE, "firidium_hoe", 1)
            .setDisplayName("<red>Firidium Hoe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumIngot = new DFMaterialBuilder(Material.IRON_INGOT, "firidium_ingot", 1)
            .setDisplayName("<red>Firidium Ingot")
            .create();

    public static DFMaterial FiridiumNugget = new DFMaterialBuilder(Material.IRON_NUGGET, "firidium_nugget", 1)
            .setDisplayName("<red>Firidium Nugget")
            .create();

    public static DFMaterial FiridiumHelmet = new DFMaterialBuilder(Material.IRON_HELMET, "firidium_helmet", 1)
            .setDisplayName("<red>Firidium Helmet")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.HEAD)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Helmet)")
            .addMeta(new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumChestplate = new DFMaterialBuilder(Material.IRON_CHESTPLATE, "firidium_chestplate", 1)
            .setDisplayName("<red>Firidium Chestplate")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.CHEST)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Chestplate)")
            .addMeta(new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumLeggings = new DFMaterialBuilder(Material.IRON_LEGGINGS, "firidium_leggings", 1)
            .setDisplayName("<red>Firidium Leggings")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.LEGS)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Leggings)")
            .addMeta(new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial FiridiumBoots = new DFMaterialBuilder(Material.IRON_BOOTS, "firidium_boots", 1)
            .setDisplayName("<red>Firidium Boots")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.FEET)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Boots)")
            .addMeta(new AdvancementOnCraftMeta(FiridiumTool.class))
            .create();

    public static DFMaterial BluebellsarStick = new DFMaterialBuilder(Material.STICK, "bluebellsar_stick", 1)
            .setDisplayName("<light_purple>Bluebellsar Stick")
            .markForUUID()
            .addLore("<aqua>Using this item shrivels shrieks from past souls.")
            .addLore("<red>Those souls politely request that you do not use this in your offhand")
            .addEnchantment(Enchantment.UNBREAKING, 25)
            .addEnchantment(Enchantment.LOOTING, 3)
            .addEnchantment(Enchantment.LUCK_OF_THE_SEA)
            .addEnchantment(Enchantment.AQUA_AFFINITY)
            .addEnchantment(Enchantment.MENDING)
            .addMeta(new BluebellsarMeta(), new AdvancementOnCraftMeta(Bluebellsar.class))
            .removeCustomModel()
            .create();

    public static DFMaterial CopperNugget = new DFMaterialBuilder(Material.IRON_NUGGET, "copper_nugget", 1)
            .setDisplayName("Copper Nugget")
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
            .addMeta(new ObsidianItemMeta(false), new AdvancementOnCraftMeta(ObsidianItem.class))
            .create();

    public static DFMaterial ObsidianAxe = new DFMaterialBuilder(Material.NETHERITE_AXE, "obsidian_axe", 1)
            .setDisplayName("Obsidian Axe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnCraftMeta(ObsidianItem.class))
            .create();

    public static DFMaterial ObsidianPickaxe = new DFMaterialBuilder(Material.NETHERITE_PICKAXE, "obsidian_pickaxe", 1)
            .setDisplayName("Obsidian Pickaxe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnCraftMeta(ObsidianItem.class))
            .create();

    public static DFMaterial ObsidianShovel = new DFMaterialBuilder(Material.NETHERITE_SHOVEL, "obsidian_shovel", 1)
            .setDisplayName("Obsidian Shovel")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnCraftMeta(ObsidianItem.class))
            .create();

    public static DFMaterial ObsidianHoe = new DFMaterialBuilder(Material.NETHERITE_HOE, "obsidian_hoe", 1)
            .setDisplayName("Obsidian Hoe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnCraftMeta(ObsidianItem.class))
            .create();

    public static DFMaterial ObsidianHelmet = new DFMaterialBuilder(Material.NETHERITE_HELMET, "obsidian_helmet", 1)
            .setDisplayName("Obsidian Helmet")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.HEAD)
            .addMeta(new AdvancementOnCraftMeta(ObsidianItem.class), new CustomDurabilityMeta(450))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianHelmet, 4D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianHelmet, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianHelmet, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .create();

    public static DFMaterial ObsidianChestplate = new DFMaterialBuilder(Material.NETHERITE_CHESTPLATE, "obsidian_chestplate", 1)
            .setDisplayName("Obsidian Chestplate")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.CHEST)
            .addMeta(new AdvancementOnCraftMeta(ObsidianItem.class), new CustomDurabilityMeta(600))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianChestplate, 9D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianChestplate, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianChestplate, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .create();

    public static DFMaterial ObsidianLeggings = new DFMaterialBuilder(Material.NETHERITE_LEGGINGS, "obsidian_leggings", 1)
            .setDisplayName("Obsidian Leggings")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.LEGS)
            .addMeta(new AdvancementOnCraftMeta(ObsidianItem.class), new CustomDurabilityMeta(575))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianLeggings, 7D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianLeggings, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianLeggings, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .create();

    public static DFMaterial ObsidianBoots = new DFMaterialBuilder(Material.NETHERITE_BOOTS, "obsidian_boots", 1)
            .setDisplayName("Obsidian Boots")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.FEET)
            .addMeta(new AdvancementOnCraftMeta(ObsidianItem.class), new CustomDurabilityMeta(500))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianBoots, 4D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianBoots, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianBoots, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
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
            .addMeta(new CustomDurabilityMeta(950))
            .create();

    public static DFMaterial SculkChestplate = new DFMaterialBuilder(Material.DIAMOND_CHESTPLATE, "sculk_chestplate", 1)
            .setDisplayName("Sculk Chestplate")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.CHEST)
            .addLore("<gray>(Equivalent to Diamond Chestplate)")
            .addMeta(new CustomDurabilityMeta(1250))
            .create();

    public static DFMaterial SculkLeggings = new DFMaterialBuilder(Material.DIAMOND_LEGGINGS, "sculk_leggings", 1)
            .setDisplayName("Sculk Leggings")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.LEGS)
            .addLore("<gray>(Equivalent to Diamond Leggings)")
            .addMeta(new CustomDurabilityMeta(1100))
            .create();

    public static DFMaterial SculkBoots = new DFMaterialBuilder(Material.DIAMOND_BOOTS, "sculk_boots", 1)
            .setDisplayName("Sculk Boots")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.FEET)
            .addLore("<gray>(Equivalent to Diamond Boots)")
            .addMeta(new CustomDurabilityMeta(1000))
            .create();

    public static DFMaterial SculkFragment = new DFMaterialBuilder(Material.ECHO_SHARD, "sculk_fragment", 1)
            .setDisplayName("Sculk Fragment")
            .create();

    public static DFMaterial VampireSword = new DFMaterialBuilder(Material.WOODEN_SWORD, "vampire_sword", 1)
            .setDisplayName("Vampire Sword")
            .overrideCustomModel("vamp_stage0")
            .addPossibleModels("vamp_stage1", "vamp_stage2", "vamp_stage3", "vamp_stage4", "vamp_stage5", "vamp_stage6", "vamp_stage7", "vamp_stage8", "vamp_stage9")
            .addLore("<aqua>A powerful sword which grows in power with every kill.")
            .markForUUID()
            .addPersistentData(Keys.vampireSwordStage, PersistentDataType.INTEGER, 0)
            .addMeta(new VampireSwordMeta(), new AdvancementOnCraftMeta(SoItBegins.class))
            .create();

    public static DFMaterial PointyStick = new DFMaterialBuilder(Material.STICK, "pointy_stick", 1)
            .setDisplayName("Pointy Stick")
            .addMeta(new SoundOnCraftMeta(Sounds.WoodCrash), new ChiselMeta(1f), new CustomDurabilityMeta(2), new PointyStickMeta())
            .setMaxStack(1)
            .create();

    public static DFMaterial LooseStone = new DFMaterialBuilder(Material.STICK, "loose_stone", 1)
            .setDisplayName("Loose Stone")
            .create();

    public static DFMaterial SharpStone = new DFMaterialBuilder(Material.STICK, "sharp_stone", 1)
            .setDisplayName("Sharp Stone")
            .addMeta(new SoundOnCraftMeta(Sounds.StoneClank), new ChiselMeta(3f), new CustomDurabilityMeta(8))
            .setMaxStack(1)
            .create();

    public static DFMaterial CopperChisel = new DFMaterialBuilder(Material.STICK, "copper_chisel", 1)
            .setDisplayName("Copper Chisel")
            .addMeta(new ChiselMeta(5f), new CustomDurabilityMeta(50))
            .setMaxStack(1)
            .create();

    public static DFMaterial IronChisel = new DFMaterialBuilder(Material.STICK, "iron_chisel", 1)
            .setDisplayName("Iron Chisel")
            .addMeta(new ChiselMeta(5f), new CustomDurabilityMeta(50))
            .setMaxStack(1)
            .create();

    public static DFMaterial FiridiumChisel = new DFMaterialBuilder(Material.STICK, "firidium_chisel", 1)
            .setDisplayName("Firidium Chisel")
            .addEnchantment(Enchantment.FIRE_ASPECT, 1)
            .addMeta(new ChiselMeta(5f), new CustomDurabilityMeta(50), new AutoSmeltingMeta(), new AdvancementOnCraftMeta(FiridiumTool.class))
            .setMaxStack(1)
            .create();

    public static DFMaterial GoldChisel = new DFMaterialBuilder(Material.STICK, "gold_chisel", 1)
            .setDisplayName("Gold Chisel")
            .addMeta(new ChiselMeta(5.5f), new CustomDurabilityMeta(70))
            .setMaxStack(1)
            .create();

    public static DFMaterial DiamondChisel = new DFMaterialBuilder(Material.STICK, "diamond_chisel", 1)
            .setDisplayName("Diamond Chisel")
            .addMeta(new ChiselMeta(6f), new CustomDurabilityMeta(500))
            .setMaxStack(1)
            .create();

    public static DFMaterial NetheriteChisel = new DFMaterialBuilder(Material.STICK, "netherite_chisel", 1)
            .setDisplayName("Netherite Chisel")
            .addMeta(new ChiselMeta(7f), new CustomDurabilityMeta(1500))
            .setMaxStack(1)
            .create();

    public static DFMaterial ObsidianChisel = new DFMaterialBuilder(Material.STICK, "obsidian_chisel", 1)
            .setDisplayName("Obsidian Chisel")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ChiselMeta(10f), new CustomDurabilityMeta(1500), new ObsidianItemMeta(true), new AdvancementOnCraftMeta(ObsidianItem.class), new AdvancementOnCraftMeta(UselessFlex.class))
            .setMaxStack(1)
            .create();

    public static DFMaterial CalciteSword = new DFMaterialBuilder(Material.STONE_SWORD, "calcite_sword", 1)
            .setDisplayName("Calcite Sword")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .create();

    public static DFMaterial CalciteAxe = new DFMaterialBuilder(Material.IRON_AXE, "calcite_axe", 1)
            .setDisplayName("Calcite Axe")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .create();

    public static DFMaterial CalcitePickaxe = new DFMaterialBuilder(Material.IRON_PICKAXE, "calcite_pickaxe", 1)
            .setDisplayName("Calcite Pickaxe")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .create();

    public static DFMaterial CalciteShovel = new DFMaterialBuilder(Material.IRON_SHOVEL, "calcite_shovel", 1)
            .setDisplayName("Calcite Shovel")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .create();

    public static DFMaterial CalciteHoe = new DFMaterialBuilder(Material.IRON_HOE, "calcite_hoe", 1)
            .setDisplayName("Calcite Hoe")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .create();

    public static DFMaterial CalciteHelmet = new DFMaterialBuilder(Material.CHAINMAIL_HELMET, "calcite_helmet", 1)
            .setDisplayName("Calcite Helmet")
            .overrideCustomModel(createMinecraftKey("chainmail_helmet"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.HEAD)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteHelmet, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addMeta(new CustomDurabilityMeta(205))
            .create();

    public static DFMaterial CalciteChestplate = new DFMaterialBuilder(Material.CHAINMAIL_CHESTPLATE, "calcite_chestplate", 1)
            .setDisplayName("Calcite Chestplate")
            .overrideCustomModel(createMinecraftKey("chainmail_chestplate"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.CHEST)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteChestplate, 5D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addMeta(new CustomDurabilityMeta(295))
            .create();

    public static DFMaterial CalciteLeggings = new DFMaterialBuilder(Material.CHAINMAIL_LEGGINGS, "calcite_leggings", 1)
            .setDisplayName("Calcite Leggings")
            .overrideCustomModel(createMinecraftKey("chainmail_leggings"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.LEGS)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteLeggings, 4D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addMeta(new CustomDurabilityMeta(240))
            .create();

    public static DFMaterial CalciteBoots = new DFMaterialBuilder(Material.CHAINMAIL_BOOTS, "calcite_boots", 1)
            .setDisplayName("Calcite Boots")
            .overrideCustomModel(createMinecraftKey("chainmail_boots"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.FEET)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteBoots, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addMeta(new CustomDurabilityMeta(225))
            .create();

    public static DFMaterial SilkSword = new DFMaterialBuilder(Material.WOODEN_SWORD, "silk_sword", 1)
            .setDisplayName("Silk Sword")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .create();

    public static DFMaterial SilkAxe = new DFMaterialBuilder(Material.WOODEN_AXE, "silk_axe", 1)
            .setDisplayName("Silk Axe")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .create();

    public static DFMaterial SilkPickaxe = new DFMaterialBuilder(Material.WOODEN_PICKAXE, "silk_pickaxe", 1)
            .setDisplayName("Silk Pickaxe")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .create();

    public static DFMaterial SilkShovel = new DFMaterialBuilder(Material.WOODEN_SHOVEL, "silk_shovel", 1)
            .setDisplayName("Silk Shovel")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .create();

    public static DFMaterial SilkHoe = new DFMaterialBuilder(Material.WOODEN_HOE, "silk_hoe", 1)
            .setDisplayName("Silk Hoe")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .create();

    public static DFMaterial TargetDummy = new DFMaterialBuilder(Material.STICK, "target_dummy", 1)
            .setDisplayName("Target Dummy")
            .addLore("<aqua>Allows you to check how much damage you're doing!")
            .addLore("<grey>Note: Shows you the damage that a normal enemy would take, not a player.")
            .overrideCustomModel(createMinecraftKey("armor_stand"))
            .addMeta(new TargetDummyMeta())
            .setMaxStack(1)
            .create();

    public static DFMaterial ComicallyLargeShovel = new DFMaterialBuilder(Material.IRON_SHOVEL, "comically_large_shovel", 1)
            .setDisplayName("Comically Large Shovel")
            .addLore("<aqua>thank you kornel")
            .addMeta(new CustomDurabilityMeta(1), new SoundOnCraftMeta(Sounds.VeryLoudShovel), new ComicallyLargeItemMeta())
            .create();

    public static DFMaterial LocatorCompass = new DFMaterialBuilder(Material.COMPASS, "locator_compass", 1)
            .setDisplayName("Locator Compass")
            .addLore("<aqua>A compass which will try to point you to a destination")
            .addMeta(new LocatorCompassMeta(), new AdvancementOnCraftMeta(CompassCraft.class))
            .setMaxStack(1)
            // .addLore("<red>Please insert a module.")
            .create();

    public static DFMaterial LocatorCompassModule = new DFMaterialBuilder(Material.STICK, "locator_compass_module_base", 1)
            .setDisplayName("Empty Compass Module")
            .create();

    public static DFMaterial EndLocatorCompassModule = new DFMaterialBuilder(Material.STICK, "locator_compass_end_module", 1)
            .setDisplayName("End Locator Compass Module")
            .addLore("<aqua>Holds the location of a nearby stronghold.")
            .addMeta(new LocatorCompassModuleMeta())
            .create();

    public static DFMaterial CustomTotem = new DFMaterialBuilder(Material.TOTEM_OF_UNDYING, "custom_totem", 1)
            .setDisplayName("Totem of Undying")
            .addLore("for bedrock resource pack auto generation")
            .addLore("don't actually use this item")
            .addPossibleModels("totem_legacy", "totem_creeper", "totem_amongus", "totem_dantdm", "totem_techno", "totem_herobrine")
            .removeCustomModel()
            .create();

    public static DFMaterial EmptyPestleAndMortar = new DFMaterialBuilder(Material.STICK, "empty_pestle_and_mortar", 1)
            .setDisplayName("Empty Pestle and Mortar")
            .create();

    public static DFMaterial FlowerPestleAndMortar = new DFMaterialBuilder(Material.STICK, "flower_pestle_and_mortar", 1)
            .setDisplayName("Pestle and Mortar")
            .addLore("<light_purple>Contains flowers")
            .addMeta(new ConvertingItem("flower_powder", "empty_pestle_and_mortar", Sounds.PestleAndMortarFinish), new CustomDurabilityMeta(5))
            .setConsumable(Consumable.consumable().consumeSeconds(1f).hasConsumeParticles(false).animation(ItemUseAnimation.EAT).sound(Key.key("minecraft", "block.stone.break")).build())
            .setMaxStack(1)
            .create();

    public static DFMaterial FlowerPowder = new DFMaterialBuilder(Material.STICK, "flower_powder", 1)
            .setDisplayName("<light_purple>Flower Powder")
            .create();

    public static DFMaterial FloralIngot = new DFMaterialBuilder(Material.STICK, "floral_ingot", 1)
            .setDisplayName("<light_purple>Floral Ingot")
            .create();

    public static DFMaterial FloralNugget = new DFMaterialBuilder(Material.STICK, "floral_nugget", 1)
            .setDisplayName("<light_purple>Floral Nugget")
            .create();

    public static DFMaterial FloralSword = new DFMaterialBuilder(Material.IRON_SWORD, "floral_sword", 1)
            .setDisplayName("<light_purple>Floral Sword")
            .addMeta(new CustomDurabilityMeta(750))
            .setTool(Material.DIAMOND_SWORD.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralAxe = new DFMaterialBuilder(Material.IRON_AXE, "floral_axe", 1)
            .setDisplayName("<light_purple>Floral Axe")
            .addMeta(new CustomDurabilityMeta(750))
            .setTool(Material.DIAMOND_AXE.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralPickaxe = new DFMaterialBuilder(Material.IRON_PICKAXE, "floral_pickaxe", 1)
            .setDisplayName("<light_purple>Floral Pickaxe")
            .addMeta(new CustomDurabilityMeta(750))
            .setTool(Material.DIAMOND_PICKAXE.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralShovel = new DFMaterialBuilder(Material.IRON_SHOVEL, "floral_shovel", 1)
            .setDisplayName("<light_purple>Floral Shovel")
            .addMeta(new CustomDurabilityMeta(750))
            .setTool(Material.DIAMOND_SHOVEL.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralHoe = new DFMaterialBuilder(Material.IRON_HOE, "floral_hoe", 1)
            .setDisplayName("<light_purple>Floral Hoe")
            .addMeta(new CustomDurabilityMeta(750))
            .setTool(Material.DIAMOND_HOE.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralHelmet = new DFMaterialBuilder(Material.IRON_HELMET, "floral_helmet", 1)
            .setDisplayName("<light_purple>Floral Helmet")
            .setEquippable(createMinecraftKey("floral"), Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.HEAD)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralHelmet, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addMeta(new CustomDurabilityMeta(280))
            .create();

    public static DFMaterial FloralChestplate = new DFMaterialBuilder(Material.IRON_CHESTPLATE, "floral_chestplate", 1)
            .setDisplayName("<light_purple>Floral Chestplate")
            .setEquippable(createMinecraftKey("floral"), Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.CHEST)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralChestplate, 8D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addMeta(new CustomDurabilityMeta(345))
            .create();

    public static DFMaterial FloralLeggings = new DFMaterialBuilder(Material.IRON_LEGGINGS, "floral_leggings", 1)
            .setDisplayName("<light_purple>Floral Leggings")
            .setEquippable(createMinecraftKey("floral"), Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.LEGS)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralLeggings, 6D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addMeta(new CustomDurabilityMeta(320))
            .create();

    public static DFMaterial FloralBoots = new DFMaterialBuilder(Material.IRON_BOOTS, "floral_boots", 1)
            .setDisplayName("<light_purple>Floral Boots")
            .setEquippable(createMinecraftKey("floral"), Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.FEET)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralBoots, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addMeta(new CustomDurabilityMeta(300))
            .create();

    private final String namedId;
    private final TextComponent displayName;
    private final Material base;
    private final boolean markedForUuid;
    private final List<TextComponent> lores;
    private final ItemStack item;
    private final int version;
    private final List<DFMaterialMeta> metas;
    private final List<NamespacedKey> possibleModels;

    public ItemStack toItemStack() {
        return item.clone();
    }

    public ItemStack toItemStack(int amount) {
        ItemStack i = item.clone();
        i.setAmount(amount);
        return i;
    }

    public ItemStack toItemStack(NamespacedKey model) {
        ItemStack i = item.clone();
        ItemMeta meta = i.getItemMeta();
        meta.setItemModel(model);
        i.setItemMeta(meta);
        return i;
    }

    public ItemStack toItemStack(NamespacedKey model, int amount) {
        ItemStack i = toItemStack(model);
        i.setAmount(amount);
        return i;
    }

    public ItemStack toItemStack(String model, int amount) {
        return toItemStack(createModelKey(model), amount);
    }

    public ItemStack toItemStack(String model) {
        return toItemStack(createModelKey(model));
    }

    public boolean isSimilar(ItemStack comparison) {
        String comparisonDFMaterialName = DFItemUtils.getString(comparison, Keys.dfmaterial);
        if (comparisonDFMaterialName == null) return false;
        return Objects.equals(comparisonDFMaterialName, this.namedId);
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

    public List<NamespacedKey> getPossibleModels() {
        return this.possibleModels;
    }

    public boolean hasPossibleModels() {
        return !this.possibleModels.isEmpty();
    }

    public boolean hasMeta() {
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
            NamespacedKey customModel,
            @Nullable Integer maxStack,
            List<NamespacedKey> possibleModels,
            @Nullable Consumable consumable,
            @Nullable Tool tool
            )
    {
        List<TextComponent> workingLore = lores == null ? List.of() : lores;
        this.possibleModels = possibleModels;
        this.version = version;
        this.markedForUuid = markedForUuid;
        this.namedId = namedId;
        this.displayName = Name;
        this.base = base;

        if (dfmetas == null) this.metas = new ArrayList<>();
        else this.metas = dfmetas;

        ItemStack newItem = new ItemStack(base);
        this.item = newItem;

        ItemMeta meta = newItem.getItemMeta();

        if (hasCustomModel) {
            meta.setItemModel(customModel);
        }

        if (maxStack != null) meta.setMaxStackSize(maxStack);

        if (Name != null) meta.displayName(Name);
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

        if (this.hasMeta()) {
            for (DFMaterialMeta createdmeta : this.getMeta()) {
                List<TextComponent> additionalLore = createdmeta.AddAdditionalLore(this);

                workingLore = Stream.concat(
                        workingLore.stream(),
                        additionalLore.stream()
                ).toList();
            }
        }

        this.lores = workingLore;
        if (!this.lores.isEmpty()) meta.lore(this.lores);

        newItem.setItemMeta(meta);
        if (consumable != null) item.setData(DataComponentTypes.CONSUMABLE, consumable);
        if (tool != null) item.setData(DataComponentTypes.TOOL, tool);

        if (this.hasMeta()) {
            for (DFMaterialMeta createdmeta : this.getMeta()) {
                createdmeta.ItemCreated(this, this.item);
            }
        }
    }
}
