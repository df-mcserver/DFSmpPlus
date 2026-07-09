package uk.co.nikodem.dFSmpPlus.Items;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.*;
import io.papermc.paper.datacomponent.item.consumable.ItemUseAnimation;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.keys.ItemTypeKeys;
import io.papermc.paper.registry.set.RegistrySet;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.*;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar.Bluebellsar;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.BonAppetit;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.TDP;
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

import static uk.co.nikodem.dFSmpPlus.Constants.Keys.*;
import static uk.co.nikodem.dFSmpPlus.Items.DFItemUtils.createFasterTool;

public class DFMaterial {
    // please don't touch lol
    public final static LinkedHashMap<String, DFMaterial> DFMaterialIndex = new LinkedHashMap<>();

    public static DFMaterial MagicMirror = new Builder(Material.POPPED_CHORUS_FRUIT, "magic_mirror", "f86358")
            .setDisplayName("<dark_purple>Magic Mirror")
            .addLore("<gold>Teleports you back to your bed.")
            .addLore("<red>Will not work in combat.")
            .addEnchantment(Enchantment.LOYALTY, 100)
            .addItemFlag(ItemFlag.HIDE_ENCHANTS)
            .addMeta(new MagicMirrorMeta())
            .create();

    public static DFMaterial EntityBucket = new Builder(Material.BUCKET, "entity_bucket", "399a6f")
            .setDisplayName("Entity Bucket")
            .addLore("<grey>Requires either water or lava to store entities.")
            .addMeta(new EmptyEntityBucketMeta("cleaning_entity_bucket", "storing_entity_bucket"))
            .create();

    public static DFMaterial CleaningEntityBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "cleaning_entity_bucket", "2b6f5d")
            .setDisplayName("Cleaning Entity Bucket")
            .addLore("<light_purple>Stores an entire entity within a bucket.")
            .addLore("<red>Does not store entity data.")
            .addMeta(new EntityBucketMeta(false, Sounds.UseCleaningEntityBucket, "entity_bucket", "<white>[Entity Bucket]"))
            .setMaxStack(1)
            .create();

    public static DFMaterial StoringEntityBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "storing_entity_bucket", "")
            .setDisplayName("Storing Entity Bucket")
            .addLore("<light_purple>Stores an entire entity within a bucket.")
            .addLore("<green>Stores entity data.")
            .addMeta(new EntityBucketMeta(true, Sounds.UseStoringEntityBucket, "entity_bucket", "<white>[Entity Bucket]"))
            .setMaxStack(1)
            .create();

    public static DFMaterial VeinPickaxe = new Builder(Material.IRON_PICKAXE, "vein_pickaxe", "5be785")
            .setDisplayName("<light_purple>Vein Miner's Pickaxe")
            .addLore("<aqua>A powerful pickaxe from a well-respected miner.")
            .addMeta(new VeinminingItemMeta(VeinMineable.VeinOres), new AdvancementOnObtainMeta(VeinTool.class))
            .setTool(createFasterTool(ItemType.IRON_PICKAXE, 1.25f))
            .create();

    public static DFMaterial VeinAxe = new Builder(Material.IRON_AXE, "vein_axe", "501b62")
            .setDisplayName("<light_purple>Vein Miner's Axe")
            .addLore("<aqua>A powerful axe from a well-respected miner.")
            .addMeta(new VeinminingItemMeta(VeinMineable.VeinLogs), new AdvancementOnObtainMeta(VeinTool.class))
            .setTool(createFasterTool(ItemType.IRON_AXE, 1.25f))
            .create();

    public static DFMaterial HeatProofRod = new Builder(Material.POPPED_CHORUS_FRUIT, "heat_proof_rod", "b58dba")
            .setDisplayName("<red>Heat-proof Rod")
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumIngot = new Builder(Material.POPPED_CHORUS_FRUIT, "firidium_ingot", "724ded")
            .setDisplayName("<red>Firidium Ingot")
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumNugget = new Builder(Material.POPPED_CHORUS_FRUIT, "firidium_nugget", "11a0b1")
            .setDisplayName("<red>Firidium Nugget")
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumSword = new Builder(Material.IRON_SWORD, "firidium_sword", "a2bc91")
            .setDisplayName("<red>Firidium Sword")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AdvancementOnObtainMeta(FiridiumTool.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumPickaxe = new Builder(Material.IRON_PICKAXE, "firidium_pickaxe", "a66167")
            .setDisplayName("<red>Firidium Pickaxe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutosmeltingItemMeta(AutoSmeltable.AutosmeltablePickaxe), new AdvancementOnObtainMeta(FiridiumTool.class))
            .setTool(createFasterTool(ItemType.IRON_PICKAXE, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumAxe = new Builder(Material.IRON_AXE, "firidium_axe", "df6ecd")
            .setDisplayName("<red>Firidium Axe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutosmeltingItemMeta(AutoSmeltable.AutosmeltableAxe), new AdvancementOnObtainMeta(FiridiumTool.class))
            .setTool(createFasterTool(ItemType.IRON_AXE, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumShovel = new Builder(Material.IRON_SHOVEL, "firidium_shovel", "ceb2c4")
            .setDisplayName("<red>Firidium Shovel")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AutosmeltingItemMeta(AutoSmeltable.AutosmeltableShovel), new AdvancementOnObtainMeta(FiridiumTool.class))
            .setTool(createFasterTool(ItemType.IRON_SHOVEL, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumHoe = new Builder(Material.IRON_HOE, "firidium_hoe", "abfd61")
            .setDisplayName("<red>Firidium Hoe")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AdvancementOnObtainMeta(FiridiumTool.class))
            .setTool(createFasterTool(ItemType.IRON_HOE, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumHelmet = new Builder(Material.IRON_HELMET, "firidium_helmet", "66b0d4")
            .setDisplayName("<red>Firidium Helmet")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.HEAD)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Helmet)")
            .addMeta(new AdvancementOnObtainMeta(FiridiumTool.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumChestplate = new Builder(Material.IRON_CHESTPLATE, "firidium_chestplate", "158122")
            .setDisplayName("<red>Firidium Chestplate")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.CHEST)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Chestplate)")
            .addMeta(new AdvancementOnObtainMeta(FiridiumTool.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumLeggings = new Builder(Material.IRON_LEGGINGS, "firidium_leggings", "a8c203")
            .setDisplayName("<red>Firidium Leggings")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.LEGS)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Leggings)")
            .addMeta(new AdvancementOnObtainMeta(FiridiumTool.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FiridiumBoots = new Builder(Material.IRON_BOOTS, "firidium_boots", "897759")
            .setDisplayName("<red>Firidium Boots")
            .setEquippable("firidium", Sound.ITEM_ARMOR_EQUIP_IRON, EquipmentSlot.FEET)
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addLore("<gray>(Equivalent to Iron Boots)")
            .addMeta(new AdvancementOnObtainMeta(FiridiumTool.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial BluebellsarStick = new Builder(Material.POPPED_CHORUS_FRUIT, "bluebellsar_stick", "414eb0")
            .setDisplayName("<light_purple>Bluebellsar Stick")
            .markForUUID()
            .addLore("<aqua>Using this item shrivels shrieks from past souls.")
            .addLore("<red>Those souls politely request that you do not use this in your offhand")
            .addEnchantment(Enchantment.UNBREAKING, 25)
            .addEnchantment(Enchantment.LOOTING, 3)
            .addEnchantment(Enchantment.LUCK_OF_THE_SEA)
            .addEnchantment(Enchantment.AQUA_AFFINITY)
            .addEnchantment(Enchantment.MENDING)
            .addMeta(new BluebellsarMeta(), new AdvancementOnObtainMeta(Bluebellsar.class))
            .overrideCustomModel(createMinecraftKey("stick"))
            .create();
//
//    public static DFMaterial Tanzanite = new Builder(Material.FIREWORK_STAR, "tanzanite")
//            .setDisplayName("Tanzanite")
//            .create();
//
//    public static DFMaterial ImpureTanzanite = new Builder(Material.FIREWORK_STAR, "impure_tanzanite")
//            .setDisplayName("Impure Tanzanite")
//            .create();
//
//    public static DFMaterial TanzaniteOre = new Builder(Material.END_STONE, "tanzanite_ore")
//            .setDisplayName("Tanzanite Ore")
//            .create();
//
//    public static DFMaterial Uranium = new Builder(Material.FIREWORK_STAR, "uranium")
//            .setDisplayName("Uranium")
//            .create();
//
//    public static DFMaterial ImpureUranium = new Builder(Material.FIREWORK_STAR, "impure_uranium")
//            .setDisplayName("Impure Uranium")
//            .create();
//
//    public static DFMaterial ContaminatedMud = new Builder(Material.MUD, "contaminated_mud")
//            .setDisplayName("Contaminated Mud")
//            .create();

    public static DFMaterial ObsidianUpgradeTemplate = new Builder(Material.POPPED_CHORUS_FRUIT, "obsidian_upgrade", "d9dc97")
            .setDisplayName("<yellow>Obsidian Upgrade")
            .addLore("<gray>Smithing Template")
            .addLore("")
            .addLore("<gray>Applies to:")
            .addLore(" <blue>Netherite Equipment")
            .addLore("<gray>Ingredients:")
            .addLore(" <blue>Crying Obsidian")
            .create();

    public static DFMaterial ObsidianSword = new Builder(Material.NETHERITE_SWORD, "obsidian_sword", "fc974f")
            .setDisplayName("Obsidian Sword")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(false), new AdvancementOnObtainMeta(ObsidianItem.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianAxe = new Builder(Material.NETHERITE_AXE, "obsidian_axe", "0983d1")
            .setDisplayName("Obsidian Axe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnObtainMeta(ObsidianItem.class))
            .setTool(createFasterTool(ItemType.NETHERITE_AXE, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianPickaxe = new Builder(Material.NETHERITE_PICKAXE, "obsidian_pickaxe", "7dac36")
            .setDisplayName("Obsidian Pickaxe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnObtainMeta(ObsidianItem.class))
            .setTool(createFasterTool(ItemType.NETHERITE_PICKAXE, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianShovel = new Builder(Material.NETHERITE_SHOVEL, "obsidian_shovel", "6d2cd0")
            .setDisplayName("Obsidian Shovel")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnObtainMeta(ObsidianItem.class))
            .setTool(createFasterTool(ItemType.NETHERITE_SHOVEL, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianHoe = new Builder(Material.NETHERITE_HOE, "obsidian_hoe", "45d501")
            .setDisplayName("Obsidian Hoe")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnObtainMeta(ObsidianItem.class))
            .setTool(createFasterTool(ItemType.NETHERITE_HOE, 1.1f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianHelmet = new Builder(Material.NETHERITE_HELMET, "obsidian_helmet", "e36323")
            .setDisplayName("Obsidian Helmet")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.HEAD)
            .addMeta(new AdvancementOnObtainMeta(ObsidianItem.class), new CustomDurabilityMeta(450))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianHelmet, 5D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianHelmet, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianHelmet, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianChestplate = new Builder(Material.NETHERITE_CHESTPLATE, "obsidian_chestplate", "8e0d18")
            .setDisplayName("Obsidian Chestplate")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.CHEST)
            .addMeta(new AdvancementOnObtainMeta(ObsidianItem.class), new CustomDurabilityMeta(600))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianChestplate, 9D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianChestplate, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianChestplate, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianLeggings = new Builder(Material.NETHERITE_LEGGINGS, "obsidian_leggings", "b858c6")
            .setDisplayName("Obsidian Leggings")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.LEGS)
            .addMeta(new AdvancementOnObtainMeta(ObsidianItem.class), new CustomDurabilityMeta(575))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianLeggings, 7D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianLeggings, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianLeggings, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianBoots = new Builder(Material.NETHERITE_BOOTS, "obsidian_boots", "95b194")
            .setDisplayName("Obsidian Boots")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setEquippable("obsidian", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.FEET)
            .addMeta(new AdvancementOnObtainMeta(ObsidianItem.class), new CustomDurabilityMeta(500))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.obsidianBoots, 5D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addAttribute(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(Keys.obsidianBoots, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addAttribute(Attribute.KNOCKBACK_RESISTANCE, new AttributeModifier(Keys.obsidianBoots, 0.1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial LifeCrystal = new Builder(Material.POPPED_CHORUS_FRUIT, "life_crystal", "a36e2a")
            .setDisplayName("<red>Life Crystal")
            .addLore("<red>Increases your max health by 2.")
            .addMeta(new LifeCrystalMeta())
            .create();

    public static DFMaterial WarpedWart = new Builder(Material.NETHER_WART, "warped_wart", "6e4523")
            .setDisplayName("Warped Wart")
            .addMeta(new PreventPlacingMeta())
            .create();

    public static DFMaterial SculkHelmet = new Builder(Material.DIAMOND_HELMET, "sculk_helmet", "8b7931")
            .setDisplayName("Sculk Helmet")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.HEAD)
            .addLore("<gray>(Equivalent to Diamond Helmet)")
            .addMeta(new CustomDurabilityMeta(950))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial SculkChestplate = new Builder(Material.DIAMOND_CHESTPLATE, "sculk_chestplate", "3cf095")
            .setDisplayName("Sculk Chestplate")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.CHEST)
            .addLore("<gray>(Equivalent to Diamond Chestplate)")
            .addMeta(new CustomDurabilityMeta(1250))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial SculkLeggings = new Builder(Material.DIAMOND_LEGGINGS, "sculk_leggings", "11f3f3")
            .setDisplayName("Sculk Leggings")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.LEGS)
            .addLore("<gray>(Equivalent to Diamond Leggings)")
            .addMeta(new CustomDurabilityMeta(1100))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial SculkBoots = new Builder(Material.DIAMOND_BOOTS, "sculk_boots", "706dec")
            .setDisplayName("Sculk Boots")
            .setEquippable("sculk", Sound.ITEM_ARMOR_EQUIP_NETHERITE, EquipmentSlot.FEET)
            .addLore("<gray>(Equivalent to Diamond Boots)")
            .addMeta(new CustomDurabilityMeta(1000))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial SculkFragment = new Builder(Material.ECHO_SHARD, "sculk_fragment", "e8d1bb")
            .setDisplayName("Sculk Fragment")
            .create();

    public static DFMaterial VampireSword = new Builder(Material.WOODEN_SWORD, "vampire_sword", "a265a4")
            .setDisplayName("Vampire Sword")
            .overrideCustomModel("vamp_stage0")
            .addPossibleModels("vamp_stage1", "vamp_stage2", "vamp_stage3", "vamp_stage4", "vamp_stage5", "vamp_stage6", "vamp_stage7", "vamp_stage8", "vamp_stage9")
            .addLore("<aqua>A powerful sword which grows in power with every kill.")
            .markForUUID()
            .addPersistentData(Keys.vampireSwordStage, PersistentDataType.INTEGER, 0)
            .addMeta(new VampireSwordMeta(), new AdvancementOnObtainMeta(SoItBegins.class))
            .create();

    public static DFMaterial PointyStick = new Builder(Material.POPPED_CHORUS_FRUIT, "pointy_stick", "9350fa")
            .setDisplayName("Pointy Stick")
            .addMeta(new SoundOnCraftMeta(Sounds.WoodCrash), new ChiselMeta(1f), new CustomDurabilityMeta(2), new PointyStickMeta())
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .create();

    public static DFMaterial LooseStone = new Builder(Material.POPPED_CHORUS_FRUIT, "loose_stone", "0398b4")
            .setDisplayName("Loose Stone")
            .create();

    public static DFMaterial SharpStone = new Builder(Material.POPPED_CHORUS_FRUIT, "sharp_stone", "72e21e")
            .setDisplayName("Sharp Stone")
            .addMeta(new SoundOnCraftMeta(Sounds.StoneClank), new ChiselMeta(3f), new CustomDurabilityMeta(8))
            .setMaxStack(1)
            .create();

    public static DFMaterial CopperChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "copper_chisel", "088d4c")
            .setDisplayName("Copper Chisel")
            .addMeta(new ChiselMeta(4.5f), new CustomDurabilityMeta(40))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .create();

    public static DFMaterial IronChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "iron_chisel", "31e03e")
            .setDisplayName("Iron Chisel")
            .addMeta(new ChiselMeta(6.5f), new CustomDurabilityMeta(50))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .create();

    public static DFMaterial FiridiumChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "firidium_chisel", "1eda4b")
            .setDisplayName("Firidium Chisel")
            .addEnchantment(Enchantment.FIRE_ASPECT, 1)
            .addMeta(new ChiselMeta(6.5f), new CustomDurabilityMeta(50), new AutosmeltingItemMeta(), new AdvancementOnObtainMeta(FiridiumTool.class))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial GoldChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "gold_chisel", "1217e1")
            .setDisplayName("Gold Chisel")
            .addMeta(new ChiselMeta(7.5f), new CustomDurabilityMeta(70))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .create();

    public static DFMaterial DiamondChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "diamond_chisel", "7fde6f")
            .setDisplayName("Diamond Chisel")
            .addMeta(new ChiselMeta(7f), new CustomDurabilityMeta(500))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .create();

    public static DFMaterial NetheriteChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "netherite_chisel", "29e56a")
            .setDisplayName("Netherite Chisel")
            .addMeta(new ChiselMeta(8f), new CustomDurabilityMeta(1500))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "obsidian_chisel", "551ada")
            .setDisplayName("Obsidian Chisel")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ChiselMeta(10f), new CustomDurabilityMeta(1500), new ObsidianItemMeta(true), new AdvancementOnObtainMeta(ObsidianItem.class), new AdvancementOnObtainMeta(GenuineDedication.class))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial CalciteSword = new Builder(Material.STONE_SWORD, "calcite_sword", "56871a")
            .setDisplayName("Calcite Sword")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .create();

    public static DFMaterial CalciteAxe = new Builder(Material.IRON_AXE, "calcite_axe", "4ba115")
            .setDisplayName("Calcite Axe")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .setTool(createFasterTool(ItemType.IRON_AXE, 0.9f))
            .create();

    public static DFMaterial CalcitePickaxe = new Builder(Material.IRON_PICKAXE, "calcite_pickaxe", "e5510e")
            .setDisplayName("Calcite Pickaxe")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .setTool(createFasterTool(ItemType.IRON_PICKAXE, 0.9f))
            .create();

    public static DFMaterial CalciteShovel = new Builder(Material.IRON_SHOVEL, "calcite_shovel", "355969")
            .setDisplayName("Calcite Shovel")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .setTool(createFasterTool(ItemType.IRON_SHOVEL, 0.9f))
            .create();

    public static DFMaterial CalciteHoe = new Builder(Material.IRON_HOE, "calcite_hoe", "2cd42d")
            .setDisplayName("Calcite Hoe")
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .setTool(createFasterTool(ItemType.IRON_HOE, 0.9f))
            .create();

    public static DFMaterial CalciteHelmet = new Builder(Material.CHAINMAIL_HELMET, "calcite_helmet", "9b17c6")
            .setDisplayName("Calcite Helmet")
            .overrideCustomModel(createMinecraftKey("chainmail_helmet"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.HEAD)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteHelmet, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addMeta(new CustomDurabilityMeta(205))
            .create();

    public static DFMaterial CalciteChestplate = new Builder(Material.CHAINMAIL_CHESTPLATE, "calcite_chestplate", "0788dc")
            .setDisplayName("Calcite Chestplate")
            .overrideCustomModel(createMinecraftKey("chainmail_chestplate"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.CHEST)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteChestplate, 5D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addMeta(new CustomDurabilityMeta(295))
            .create();

    public static DFMaterial CalciteLeggings = new Builder(Material.CHAINMAIL_LEGGINGS, "calcite_leggings", "6c8a81")
            .setDisplayName("Calcite Leggings")
            .overrideCustomModel(createMinecraftKey("chainmail_leggings"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.LEGS)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteLeggings, 4D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addMeta(new CustomDurabilityMeta(240))
            .create();

    public static DFMaterial CalciteBoots = new Builder(Material.CHAINMAIL_BOOTS, "calcite_boots", "0aa188")
            .setDisplayName("Calcite Boots")
            .overrideCustomModel(createMinecraftKey("chainmail_boots"))
            .setEquippable(createMinecraftKey("chainmail"), Sound.ITEM_ARMOR_EQUIP_CHAIN, EquipmentSlot.FEET)
            .addEnchantment(Enchantment.UNBREAKING, 1)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.calciteBoots, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addMeta(new CustomDurabilityMeta(225))
            .create();

    public static DFMaterial SilkSword = new Builder(Material.WOODEN_SWORD, "silk_sword", "e098ae")
            .setDisplayName("Silk Sword")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .setRepairable(Repairable.repairable(RegistrySet.keySet(RegistryKey.ITEM, ItemTypeKeys.STRING)))
            .create();

    public static DFMaterial SilkAxe = new Builder(Material.WOODEN_AXE, "silk_axe", "a53b1d")
            .setDisplayName("Silk Axe")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .setRepairable(Repairable.repairable(RegistrySet.keySet(RegistryKey.ITEM, ItemTypeKeys.STRING)))
            .create();

    public static DFMaterial SilkPickaxe = new Builder(Material.WOODEN_PICKAXE, "silk_pickaxe", "03af02")
            .setDisplayName("Silk Pickaxe")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .setRepairable(Repairable.repairable(RegistrySet.keySet(RegistryKey.ITEM, ItemTypeKeys.STRING)))
            .create();

    public static DFMaterial SilkShovel = new Builder(Material.WOODEN_SHOVEL, "silk_shovel", "7e34e5")
            .setDisplayName("Silk Shovel")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .setRepairable(Repairable.repairable(RegistrySet.keySet(RegistryKey.ITEM, ItemTypeKeys.STRING)))
            .create();

    public static DFMaterial SilkHoe = new Builder(Material.WOODEN_HOE, "silk_hoe", "9eac31")
            .setDisplayName("Silk Hoe")
            .addEnchantment(Enchantment.SILK_TOUCH, 1)
            .setRepairable(Repairable.repairable(RegistrySet.keySet(RegistryKey.ITEM, ItemTypeKeys.STRING)))
            .create();

    public static DFMaterial TargetDummy = new Builder(Material.POPPED_CHORUS_FRUIT, "target_dummy", "78c904")
            .setDisplayName("Target Dummy")
            .addLore("<aqua>Allows you to check how much damage you're doing!")
            .addLore("<grey>Note: Shows you the damage that a normal enemy would take, not a player.")
            .overrideCustomModel(createMinecraftKey("armor_stand"))
            .addMeta(new TargetDummyMeta())
            .setMaxStack(1)
            .setRepairable(Repairable.repairable(RegistrySet.keySet(RegistryKey.ITEM, ItemTypeKeys.IRON_INGOT)))
            .create();

    public static DFMaterial ComicallyLargeShovel = new Builder(Material.IRON_SHOVEL, "comically_large_shovel", "68957e")
            .setDisplayName("Comically Large Shovel")
            .addLore("<aqua>thank you kornel")
            .addMeta(new CustomDurabilityMeta(1), new SoundOnCraftMeta(Sounds.VeryLoudShovel), new ComicallyLargeItemMeta())
            .create();

    public static DFMaterial LocatorCompass = new Builder(Material.COMPASS, "locator_compass", "f9445f")
            .setDisplayName("Locator Compass")
            .addLore("<aqua>A compass which will try to point you to a destination")
            .addMeta(new LocatorCompassMeta(), new AdvancementOnObtainMeta(CompassCraft.class))
            .setMaxStack(1)
            // .addLore("<red>Please insert a module.")
            .create();

    public static DFMaterial LocatorCompassModule = new Builder(Material.POPPED_CHORUS_FRUIT, "locator_compass_module_base", "ec1741")
            .setDisplayName("Empty Compass Module")
            .create();

    public static DFMaterial EndLocatorCompassModule = new Builder(Material.POPPED_CHORUS_FRUIT, "locator_compass_end_module", "19f1a0")
            .setDisplayName("End Locator Compass Module")
            .addLore("<aqua>Holds the location of a nearby stronghold.")
            .addMeta(new LocatorCompassModuleMeta())
            .create();

    public static DFMaterial CustomTotem = new Builder(Material.TOTEM_OF_UNDYING, "custom_totem", "a81996")
            .setDisplayName("Totem of Undying")
            .addLore("for bedrock resource pack auto generation")
            .addLore("don't actually use this item")
            .addPossibleModels("totem_legacy", "totem_creeper", "totem_amongus", "totem_dantdm", "totem_techno", "totem_herobrine")
            .removeCustomModel()
            .create();

    public static DFMaterial EmptyPestleAndMortar = new Builder(Material.POPPED_CHORUS_FRUIT, "empty_pestle_and_mortar", "3587b7")
            .setDisplayName("Empty Pestle and Mortar")
            .create();

    public static DFMaterial RottenFleshPestleAndMortar = new Builder(Material.POPPED_CHORUS_FRUIT, "rotten_flesh_pestle_and_mortar", "a3dc27")
            .setDisplayName("Pestle and Mortar")
            .addLore("<light_purple>Contains rotten flesh")
            .addMeta(new ConvertingItemMeta(ItemStack.of(Material.LEATHER), "empty_pestle_and_mortar", Sounds.RottenFleshPestleAndMortarFinish), new CustomDurabilityMeta(10))
            .setConsumable(Consumable.consumable().consumeSeconds(1f).hasConsumeParticles(false).animation(ItemUseAnimation.EAT).sound(Key.key("minecraft", "block.stone.break")).build())
            .setMaxStack(1)
            .create();

    public static DFMaterial GravelPestleAndMortar = new Builder(Material.POPPED_CHORUS_FRUIT, "gravel_pestle_and_mortar", "829434")
            .setDisplayName("Pestle and Mortar")
            .addLore("<light_purple>Contains gravel")
            .addMeta(new ConvertingItemMeta(ItemStack.of(Material.FLINT, 2), "empty_pestle_and_mortar", Sounds.GravelPestleAndMortarFinish), new CustomDurabilityMeta(3))
            .setConsumable(Consumable.consumable().consumeSeconds(1f).hasConsumeParticles(false).animation(ItemUseAnimation.EAT).sound(Key.key("minecraft", "block.stone.break")).build())
            .setMaxStack(1)
            .create();

    public static DFMaterial FlowerPestleAndMortar = new Builder(Material.POPPED_CHORUS_FRUIT, "flower_pestle_and_mortar", "b68765")
            .setDisplayName("Pestle and Mortar")
            .addLore("<light_purple>Contains flowers")
            .addMeta(new ConvertingItemMeta("flower_powder", "empty_pestle_and_mortar", Sounds.FlowerPestleAndMortarFinish), new CustomDurabilityMeta(5))
            .setConsumable(Consumable.consumable().consumeSeconds(1f).hasConsumeParticles(false).animation(ItemUseAnimation.EAT).sound(Key.key("minecraft", "block.stone.break")).build())
            .setMaxStack(1)
            .create();

    public static DFMaterial AmethystPestleAndMortar = new Builder(Material.POPPED_CHORUS_FRUIT, "amethyst_pestle_and_mortar", "5fb1e1")
            .setDisplayName("Pestle and Mortar")
            .addLore("<light_purple>Contains amethyst block")
            .addMeta(new ConvertingItemMeta(ItemStack.of(Material.AMETHYST_SHARD, 2), "empty_pestle_and_mortar", Sounds.AmethystPestleAndMortarFinish), new CustomDurabilityMeta(7))
            .setConsumable(Consumable.consumable().consumeSeconds(1f).hasConsumeParticles(false).animation(ItemUseAnimation.EAT).sound(Key.key("minecraft", "block.stone.break")).build())
            .setMaxStack(1)
            .create();


    public static DFMaterial FlowerPowder = new Builder(Material.POPPED_CHORUS_FRUIT, "flower_powder", "3f4dd9")
            .setDisplayName("<light_purple>Flower Powder")
            .create();

    public static DFMaterial FloralIngot = new Builder(Material.POPPED_CHORUS_FRUIT, "floral_ingot", "27452d")
            .setDisplayName("<light_purple>Floral Ingot")
            .create();

    public static DFMaterial FloralNugget = new Builder(Material.POPPED_CHORUS_FRUIT, "floral_nugget", "83a422")
            .setDisplayName("<light_purple>Floral Nugget")
            .create();

    public static DFMaterial FloralSword = new Builder(Material.IRON_SWORD, "floral_sword", "9b7e23")
            .setDisplayName("<light_purple>Floral Sword")
            .addMeta(new CustomDurabilityMeta(750), new HarvesterItemMeta())
            .create();

    public static DFMaterial FloralAxe = new Builder(Material.IRON_AXE, "floral_axe", "6dd2f2")
            .setDisplayName("<light_purple>Floral Axe")
            .addMeta(new CustomDurabilityMeta(750), new HarvesterItemMeta())
            .setTool(Material.DIAMOND_AXE.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralPickaxe = new Builder(Material.IRON_PICKAXE, "floral_pickaxe", "a2a2cb")
            .setDisplayName("<light_purple>Floral Pickaxe")
            .addMeta(new CustomDurabilityMeta(750), new HarvesterItemMeta())
            .setTool(createFasterTool(ItemType.IRON_PICKAXE, 1.25f)) // can't mine netherite
            .create();

    public static DFMaterial FloralShovel = new Builder(Material.IRON_SHOVEL, "floral_shovel", "1a667f")
            .setDisplayName("<light_purple>Floral Shovel")
            .addMeta(new CustomDurabilityMeta(750), new HarvesterItemMeta())
            .setTool(Material.DIAMOND_SHOVEL.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralHoe = new Builder(Material.IRON_HOE, "floral_hoe", "4fcb7e")
            .setDisplayName("<light_purple>Floral Hoe")
            .addMeta(new CustomDurabilityMeta(750), new HarvesterItemMeta())
            .setTool(Material.DIAMOND_HOE.getDefaultData(DataComponentTypes.TOOL))
            .create();

    public static DFMaterial FloralHelmet = new Builder(Material.IRON_HELMET, "floral_helmet", "4d2555")
            .setDisplayName("<light_purple>Floral Helmet")
            .setEquippable("floral", Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.HEAD)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralHelmet, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD))
            .addMeta(new CustomDurabilityMeta(280))
            .create();

    public static DFMaterial FloralChestplate = new Builder(Material.IRON_CHESTPLATE, "floral_chestplate", "74efb7")
            .setDisplayName("<light_purple>Floral Chestplate")
            .setEquippable("floral", Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.CHEST)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralChestplate, 8D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .addMeta(new CustomDurabilityMeta(345))
            .create();

    public static DFMaterial FloralLeggings = new Builder(Material.IRON_LEGGINGS, "floral_leggings", "0c4355")
            .setDisplayName("<light_purple>Floral Leggings")
            .setEquippable("floral", Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.LEGS)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralLeggings, 6D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS))
            .addMeta(new CustomDurabilityMeta(320))
            .create();

    public static DFMaterial FloralBoots = new Builder(Material.IRON_BOOTS, "floral_boots", "79272a")
            .setDisplayName("<light_purple>Floral Boots")
            .setEquippable("floral", Sound.ITEM_ARMOR_EQUIP_LEATHER, EquipmentSlot.FEET)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(Keys.floralBoots, 3D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET))
            .addMeta(new CustomDurabilityMeta(300))
            .create();

    public static DFMaterial LuckyHorseshoe = new Builder(Material.POPPED_CHORUS_FRUIT, "lucky_horseshoe", "a39063")
            .setDisplayName("Lucky Horseshoe")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("negating_falldamage")
                    .addMeta(new NegatingDamageMeta(DamageType.FALL))
                    .setDescription("Negates all fall damage")
                    .setEquipSound(Sounds.EquipAccessory_LuckyHorseshoe)
                    .create()))
            .create();

    public static DFMaterial ElytraBraces = new Builder(Material.POPPED_CHORUS_FRUIT, "elytra_braces", "c3bcb9")
            .setDisplayName("Elytra Braces")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("elytra_braces")
                    .addMeta(new NegatingDamageMeta(DamageType.FLY_INTO_WALL))
                    .setDescription("Negates all elytra kinetic damage")
                    .setEquipSound(Sounds.EquipAccessory_ElytraBraces)
                    .create()))
            .create();

    public static DFMaterial BracedHorseshoe = new Builder(Material.POPPED_CHORUS_FRUIT, "braced_horseshoe", "7c6ded")
            .setDisplayName("Braced Horseshoe")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("braced_horseshoe")
                    .addMeta(new NegatingDamageMeta(DamageType.FALL, DamageType.FLY_INTO_WALL))
                    .setDescription("Negates all fall damage and elytra kinetic damage")
                    .setEquipSound(Sounds.EquipAccessory_BracedHorseshoe)
                    .addConflicts("elytra_braces", "negating_falldamage")
                    .create()))
            .create();

    public static DFMaterial ContaminatedMembrane = new Builder(Material.POPPED_CHORUS_FRUIT, "contaminated_membrane", "cab995")
            .setDisplayName("Contaminated Membrane")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("phantom_ignore")
                    .addMeta(new PhantomIgnoresMeta())
                    .setDescription("Phantoms don't target you\nPhantoms are poisoned when they attack you")
                    .setEquipSound(Sounds.EquipAccessory_ContaminatedMembrane)
                    .create()))
            .create();

    public static DFMaterial CobaltShield = new Builder(Material.POPPED_CHORUS_FRUIT, "cobalt_shield", "242502")
            .setDisplayName("Cobalt Shield")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("shield")
                    .setArmourPoints(2D)
                    .setDescription("+3 Knockback Resistance")
                    .setEquipSound(Sounds.EquipAccessory_Shield)
                    .addMeta(new KnockbackResistenceMeta())
                    .create()))
            .create();

    public static DFMaterial VeinMinerEssence = new Builder(Material.POPPED_CHORUS_FRUIT, "vein_essence", "f28324")
            .setDisplayName("Vein Miner's essence")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("essence")
                    .setDescription("Can vein mines ores and logs when mined whilst sneaking\nCan be toggled")
                    .setEquipSound(Sounds.EquipAccessory_Essence)
                    .addMeta(new VeinminingAccessoryMeta())
                    .create()))
            .create();

    public static DFMaterial FiridiumEssence = new Builder(Material.POPPED_CHORUS_FRUIT, "firidium_essence", "77f510")
            .setDisplayName("Firidium essence")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("essence")
                    .setDescription("Can autosmelts any smeltable block when mined whilst sneaking\nCan be toggled")
                    .setEquipSound(Sounds.EquipAccessory_Essence)
                    .addMeta(new AutosmeltAccessoryMeta())
                    .create()))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial SplitEssence = new Builder(Material.POPPED_CHORUS_FRUIT, "split_essence", "7ec71d")
            .setDisplayName("Split essence")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("essence")
                    .setDescription("Can vein mine ores and logs when mined whilst sneaking\nCan autosmelt any smeltable block when mined whilst sneaking\nCan be configured")
                    .setEquipSound(Sounds.EquipAccessory_Essence)
                    .addMeta(new SplitMiningAccessoryMeta())
                    .create()))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial BootsOfSwiftness = new Builder(Material.POPPED_CHORUS_FRUIT, "boots_of_swiftness", "9d7a95")
            .setDisplayName("Boots of Swiftness")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("boots_of_swiftness")
                    .setDescription("+2% Speed")
                    .setEquipSound(Sounds.EquipAccessory_Boots)
                    .addMeta(new SwiftnessAccessoryMeta())
                    .create()))
            .create();

    public static DFMaterial FlowerBoots = new Builder(Material.POPPED_CHORUS_FRUIT, "flowerboots", "46d37a")
            .setDisplayName("Flower Boots")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("flower_boots")
                    .setDescription("Auto plants seeds when walking over farmland with seeds in hand\nPrevents stepping over crops")
                    .setEquipSound(Sounds.EquipAccessory_Boots)
                    .addMeta(new FlowerBootsMeta())
                    .create()))
            .create();

    public static DFMaterial FlowerBootsOfSwiftness = new Builder(Material.POPPED_CHORUS_FRUIT, "flower_boots_of_swiftness", "d3c4e33")
            .setDisplayName("Flower Boots of Swiftness")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("flower_boots_of_swiftness")
                    .setDescription("+2% Speed\nAuto plants seeds when walking over farmland with seeds in hand\nPrevents stepping over crops")
                    .setEquipSound(Sounds.EquipAccessory_Boots)
                    .addMeta(new SwiftnessAccessoryMeta(), new FlowerBootsMeta())
                    .addConflicts("flower_boots", "boots_of_swiftness")
                    .create()))
            .create();

    public static DFMaterial HitmanTechniquesBook = new Builder(Material.POPPED_CHORUS_FRUIT, "htbook", "ec7d0b")
            .setDisplayName("Hitman Techniques Book")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("htbook")
                    .setDescription("-5% Damage\nSilences players for 5 minutes after death")
                    .setEquipSound(Sounds.EquipAccessory_Book)
                    .addMeta(new HitmanTechniquesMeta())
                    .create()))
            .create();

    public static DFMaterial MattBlade = new Builder(Material.DIAMOND_SWORD, "matt_blade", "700aee")
            .setDisplayName("<red>Matt blade")
            .addMeta(new NoisyItem(Sounds.MattBladeSwing, Sounds.MattBlade), new AdvancementOnObtainMeta(TDP.class))
            .addLore("<dark_red>The DEADLIEST sword. From the DEADLIEST player.")
            .create();

    public static DFMaterial SebBlade = new Builder(Material.WOODEN_SWORD, "seb_blade", "e97e3b")
            .setDisplayName("<red>Seb blade")
            .addMeta(new NoisyItem(Sounds.SebBladeSwing, Sounds.SebBladeStab))
            .addLore("<dark_red>The LEAST DEADLY sword. From the LEAST DEADLY player.")
            .create();

    public static DFMaterial FriedEgg = new Builder(Material.POPPED_CHORUS_FRUIT, "fried_egg", "71b2ac")
            .setDisplayName("Fried Egg")
            .setFoodProperties(FoodProperties.food().nutrition(4).saturation(1.5f).build())
            .setConsumable(Consumable.consumable().consumeSeconds(1.6f).animation(ItemUseAnimation.EAT).build())
            .create();

    public static DFMaterial EggSandwich = new Builder(Material.POPPED_CHORUS_FRUIT, "egg_sandwich", "021fbf")
            .setDisplayName("Egg Sandwich")
            .setFoodProperties(FoodProperties.food().nutrition(8).saturation(15f).build())
            .setConsumable(Consumable.consumable().consumeSeconds(1.6f).animation(ItemUseAnimation.EAT).build())
            .addMeta(new AdvancementOnObtainMeta(BonAppetit.class))
            .create();

    public static DFMaterial EdibleUranium = new Builder(Material.POPPED_CHORUS_FRUIT, "edible_uranium", "59b1a5")
            .setDisplayName("Uranium")
            .addLore("<grey>Nutritional value:")
            .addLore("<grey>- 20000000000 calories")
            .setFoodProperties(FoodProperties.food().nutrition(999).saturation(999f).build())
            .setConsumable(Consumable.consumable().consumeSeconds(1.6f).animation(ItemUseAnimation.EAT).build())
            .addMeta(new ExtremelyUnhealthyItemMeta())
            .create();

    public static DFMaterial VacuumAccessory = new Builder(Material.POPPED_CHORUS_FRUIT, "vacuum_accessory", "605e7f")
            .setDisplayName("Vacuum")
            .setMaxStack(1)
            .addMeta(new AccessoryItemMeta(new AccessoryInformation.Builder("vacuum_accessory")
                    .setDescription("Automatically picks up any mined items")
                    .setEquipSound(Sounds.EquipAccessory_Vacuum)
                    .addMeta(new VacuumAccessoryMeta())
                    .create()))
            .create();

    public static DFMaterial AncientDebrisFragment = new Builder(Material.POPPED_CHORUS_FRUIT, "ancient_debris_fragment", "89d45c")
            .setDisplayName("Ancient Debris Fragment")
            .create();

    public static DFMaterial AncientDebrisChunk = new Builder(Material.POPPED_CHORUS_FRUIT, "ancient_debris_chunk", "ffb0fa")
            .setDisplayName("Ancient Debris Chunk")
            .create();

    public static DFMaterial Cardboard = new Builder(Material.POPPED_CHORUS_FRUIT, "cardboard", "e466e4")
            .setDisplayName("Cardboard")
            .create();

    public static DFMaterial StrangeBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "strange_bucket", "6b62ab")
            .setDisplayName("Strange Bucket")
            .setMaxStack(16)
            .create();

    public static DFMaterial StrangeChipBox = new Builder(Material.POPPED_CHORUS_FRUIT, "strange_chip_box", "6ae3b9")
            .setDisplayName("Strange Chip Box")
            .setMaxStack(16)
            .create();

    public static DFMaterial DiscreetChickenBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "discreet_chicken_bucket", "342c9d")
            .setDisplayName("Discreet Chicken Bucket")
            .addLore("It's not finger licking good.")
            .setFoodProperties(FoodProperties.food().nutrition(12).saturation(4f).build())
            .setConsumable(Consumable.consumable().consumeSeconds(2.4f).animation(ItemUseAnimation.EAT).build())
            .addMeta(new ConsumingResidueMeta("strange_bucket", null))
            .setMaxStack(1)
            .create();

    public static DFMaterial DiscreetChips = new Builder(Material.POPPED_CHORUS_FRUIT, "discreet_chips", "5f7499")
            .setDisplayName("Discreet Chips")
            .addLore("It's not finger licking good.")
            .setFoodProperties(FoodProperties.food().nutrition(9).saturation(2f).build())
            .setConsumable(Consumable.consumable().consumeSeconds(2.4f).animation(ItemUseAnimation.EAT).build())
            .addMeta(new ConsumingResidueMeta("strange_chip_box", null))
            .setMaxStack(1)
            .create();

    public static DFMaterial ChickenBurger = new Builder(Material.POPPED_CHORUS_FRUIT, "chicken_burger", "1549e6")
            .setDisplayName("Chicken Burger")
            .setFoodProperties(FoodProperties.food().nutrition(7).saturation(10f).build())
            .setConsumable(Consumable.consumable().consumeSeconds(1.6f).animation(ItemUseAnimation.EAT).build())
            .create();

    public static DFMaterial Coke = new Builder(Material.POPPED_CHORUS_FRUIT, "coke", "8f2bab")
            .setDisplayName("Coke")
            .addLore("Literally the same as pepsi")
            .setFoodProperties(FoodProperties.food().canAlwaysEat(true).build())
            .setConsumable(Consumable.consumable().consumeSeconds(1.6f).sound(Registry.SOUNDS.getKey(Sound.ENTITY_GENERIC_DRINK)).hasConsumeParticles(false).animation(ItemUseAnimation.DRINK).build())
            .addMeta(new ConsumingResidueMeta(ItemStack.of(Material.GLASS_BOTTLE), null))
            .setMaxStack(1)
            .create();

    public static DFMaterial Pepsi = new Builder(Material.POPPED_CHORUS_FRUIT, "pepsi", "3f6d82")
            .setDisplayName("Pepsi")
            .addLore("Literally the same as coke")
            .setFoodProperties(FoodProperties.food().canAlwaysEat(true).build())
            .setConsumable(Consumable.consumable().consumeSeconds(1.6f).sound(Registry.SOUNDS.getKey(Sound.ENTITY_GENERIC_DRINK)).hasConsumeParticles(false).animation(ItemUseAnimation.DRINK).build())
            .addMeta(new ConsumingResidueMeta(ItemStack.of(Material.GLASS_BOTTLE), null))
            .setMaxStack(1)
            .create();

    public static DFMaterial Sprite = new Builder(Material.POTION, "sprite", "f8084a")
            .setDisplayName("Sprite")
            .setFoodProperties(FoodProperties.food().canAlwaysEat(true).build())
            .setConsumable(Consumable.consumable().consumeSeconds(1.6f).sound(Registry.SOUNDS.getKey(Sound.ENTITY_GENERIC_DRINK)).hasConsumeParticles(false).animation(ItemUseAnimation.DRINK).build())
            .addMeta(new ConsumingResidueMeta(ItemStack.of(Material.GLASS_BOTTLE), null))
            .setMaxStack(1)
            .create();

    public static DFMaterial CopperShears = new Builder(Material.SHEARS, "copper_shears", "d3de48")
            .setDisplayName("Copper Shears")
            .addMeta(new CustomDurabilityMeta(150))
            .setTool(createFasterTool(ItemType.SHEARS, 0.75f))
            .create();

    public static DFMaterial FiridiumShears = new Builder(Material.SHEARS, "firidium_shears", "4236e4")
            .setDisplayName("Firidium Shears")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial GoldShears = new Builder(Material.SHEARS, "gold_shears", "ea9014")
            .setDisplayName("Golden Shears")
            .addMeta(new CustomDurabilityMeta(100))
            .setTool(createFasterTool(ItemType.SHEARS, 2.5f))
            .create();

    public static DFMaterial DiamondShears = new Builder(Material.SHEARS, "diamond_shears", "39a5e7")
            .setDisplayName("Diamond Shears")
            .addMeta(new CustomDurabilityMeta(500))
            .setTool(createFasterTool(ItemType.SHEARS, 3.25f))
            .create();

    public static DFMaterial NetheriteShears = new Builder(Material.SHEARS, "netherite_shears", "d5847a")
            .setDisplayName("Netherite Shears")
            .addMeta(new CustomDurabilityMeta(750))
            .setTool(createFasterTool(ItemType.SHEARS, 3.25f))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianShears = new Builder(Material.SHEARS, "obsidian_shears", "62eefc")
            .setDisplayName("Obsidian Shears")
            .addMeta(new CustomDurabilityMeta(1000))
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .setTool(createFasterTool(ItemType.SHEARS, 3.5f))
            .addMeta(new ObsidianItemMeta(true), new AdvancementOnObtainMeta(ObsidianItem.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial CopperBucket = new Builder(Material.BUCKET, "copper_bucket", "953da0")
            .setDisplayName("Copper Bucket")
            .addMeta(new EmptyCopperBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperEntityBucket = new Builder(Material.BUCKET, "copper_entity_bucket", "97196d")
            .setDisplayName("Copper Entity Bucket")
            .addLore("<grey>Requires either water or lava to store entities.")
            .addMeta(new EmptyEntityBucketMeta("copper_cleaning_entity_bucket", null))
            .create();

    public static DFMaterial CopperWaterBucket = new Builder(Material.WATER_BUCKET, "copper_water_bucket", "54b856")
            .setDisplayName("Copper Water Bucket")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperLavaBucket = new Builder(Material.LAVA_BUCKET, "copper_lava_bucket", "c2a22d")
            .setDisplayName("Copper Lava Bucket")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperPowderSnowBucket = new Builder(Material.POWDER_SNOW_BUCKET, "copper_powder_snow_bucket", "33eb91")
            .setDisplayName("Copper Powder Snow Bucket")
            .addMeta(new FilledCustomBucketMeta("copper_"), new PowderSnowBucketMeta("copper_bucket"))
            .create();

    public static DFMaterial CopperMilkBucket = new Builder(Material.MILK_BUCKET, "copper_milk_bucket", "32e7fb")
            .setDisplayName("Copper Milk Bucket")
            .addMeta(new MilkCustomBucketMeta("copper_bucket"))
            .create();

    public static DFMaterial CopperCodBucket = new Builder(Material.COD_BUCKET, "copper_cod_bucket", "04c26c")
            .setDisplayName("Copper Bucket of Cod")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperSalmonBucket = new Builder(Material.SALMON_BUCKET, "copper_salmon_bucket", "8ae1d1")
            .setDisplayName("Copper Bucket of Salmon")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperTropicalFishBucket = new Builder(Material.TROPICAL_FISH_BUCKET, "copper_tropical_fish_bucket", "4d7cad")
            .setDisplayName("Copper Bucket of Tropical Fish")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperPufferfishBucket = new Builder(Material.PUFFERFISH_BUCKET, "copper_pufferfish_bucket", "cfe4fb")
            .setDisplayName("Copper Bucket of Pufferfish")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperAxolotlBucket = new Builder(Material.AXOLOTL_BUCKET, "copper_axolotl_bucket", "023e6f")
            .setDisplayName("Copper Bucket of Axolotl")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperTadpoleBucket = new Builder(Material.TADPOLE_BUCKET, "copper_tadpole_bucket", "e6b165")
            .setDisplayName("Copper Bucket of Tadpole")
            .addMeta(new FilledCustomBucketMeta("copper_"))
            .create();

    public static DFMaterial CopperCleaningEntityBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "copper_cleaning_entity_bucket", "f83d19")
            .setDisplayName("Copper Cleaning Entity Bucket")
            .addLore("<light_purple>Stores an entire entity within a bucket.")
            .addLore("<red>Does not store entity data.")
            .addMeta(new EntityBucketMeta(false, Sounds.UseCleaningEntityBucket, null, null))
            .setMaxStack(1)
            .create();

    public static DFMaterial CopperStoringEntityBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "copper_storing_entity_bucket", "0aba59")
            .setDisplayName("Copper Storing Entity Bucket")
            .addLore("<light_purple>Stores an entire entity within a bucket.")
            .addLore("<green>Stores entity data.")
            .addMeta(new EntityBucketMeta(true, Sounds.UseStoringEntityBucket, null, null))
            .setMaxStack(1)
            .create();

    public static DFMaterial GoldBucket = new Builder(Material.BUCKET, "gold_bucket", "204208")
            .setDisplayName("Golden Bucket")
            .addMeta(new EmptyCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldEntityBucket = new Builder(Material.BUCKET, "gold_entity_bucket", "fced25")
            .setDisplayName("Golden Entity Bucket")
            .addLore("<grey>Requires either water or lava to store entities.")
            .addMeta(new EmptyEntityBucketMeta("gold_cleaning_entity_bucket", "gold_storing_entity_bucket"))
            .create();

    public static DFMaterial GoldWaterBucket = new Builder(Material.WATER_BUCKET, "gold_water_bucket", "c4ff10")
            .setDisplayName("Golden Water Bucket")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldLavaBucket = new Builder(Material.LAVA_BUCKET, "gold_lava_bucket", "72453e")
            .setDisplayName("Golden Lava Bucket")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldPowderSnowBucket = new Builder(Material.POWDER_SNOW_BUCKET, "gold_powder_snow_bucket", "ca312b")
            .setDisplayName("Golden Powder Snow Bucket")
            .addMeta(new FilledCustomBucketMeta("gold_"), new PowderSnowBucketMeta("gold_bucket"))
            .create();

    public static DFMaterial GoldMilkBucket = new Builder(Material.MILK_BUCKET, "gold_milk_bucket", "940d9b")
            .setDisplayName("Golden Milk Bucket")
            .addMeta(new MilkCustomBucketMeta("gold_bucket"))
            .create();

    public static DFMaterial GoldCodBucket = new Builder(Material.COD_BUCKET, "gold_cod_bucket", "a3654e")
            .setDisplayName("Golden Bucket of Cod")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldSalmonBucket = new Builder(Material.SALMON_BUCKET, "gold_salmon_bucket", "3d7ed1")
            .setDisplayName("Golden Bucket of Salmon")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldTropicalFishBucket = new Builder(Material.TROPICAL_FISH_BUCKET, "gold_tropical_fish_bucket", "d88792")
            .setDisplayName("Golden Bucket of Tropical Fish")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldPufferfishBucket = new Builder(Material.PUFFERFISH_BUCKET, "gold_pufferfish_bucket", "526f79")
            .setDisplayName("Golden Bucket of Pufferfish")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldAxolotlBucket = new Builder(Material.AXOLOTL_BUCKET, "gold_axolotl_bucket", "0bef28")
            .setDisplayName("Golden Bucket of Axolotl")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldTadpoleBucket = new Builder(Material.TADPOLE_BUCKET, "gold_tadpole_bucket", "3ab314")
            .setDisplayName("Golden Bucket of Tadpole")
            .addMeta(new FilledCustomBucketMeta("gold_"))
            .create();

    public static DFMaterial GoldCleaningEntityBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "gold_cleaning_entity_bucket", "2a7102")
            .setDisplayName("Golden Cleaning Entity Bucket")
            .addLore("<light_purple>Stores an entire entity within a bucket.")
            .addLore("<light_purple>Not destroyed after use")
            .addLore("<red>Does not store entity data.")
            .addMeta(new EntityBucketMeta(false, Sounds.UseCleaningEntityBucket, "gold_cleaning_entity_bucket", "<gold>[Golden Cleaning Entity Bucket]"))
            .setMaxStack(1)
            .create();

    public static DFMaterial GoldStoringEntityBucket = new Builder(Material.POPPED_CHORUS_FRUIT, "gold_storing_entity_bucket", "a3ae07")
            .setDisplayName("Golden Storing Entity Bucket")
            .addLore("<light_purple>Stores an entire entity within a bucket.")
            .addLore("<light_purple>Not destroyed after use")
            .addLore("<green>Stores entity data.")
            .addMeta(new EntityBucketMeta(true, Sounds.UseStoringEntityBucket, "gold_storing_entity_bucket", "<gold>[Golden Storing Entity Bucket]"))
            .setMaxStack(1)
            .create();

    public static DFMaterial CalciteSpear = new Builder(Material.IRON_SPEAR, "calcite_spear", "39a19b")
            .setDisplayName("Calcite Spear")
            .create();

    public static DFMaterial FiridiumSpear = new Builder(Material.IRON_SPEAR, "firidium_spear", "31e9a3")
            .setDisplayName("Firidium Spear")
            .addEnchantment(Enchantment.FIRE_ASPECT)
            .addMeta(new AdvancementOnObtainMeta(FiridiumTool.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial FloralSpear = new Builder(Material.IRON_SPEAR, "floral_spear", "e40fdc")
            .setDisplayName("<light_purple>Floral Spear")
            .create();

    public static DFMaterial ObsidianSpear = new Builder(Material.NETHERITE_SPEAR, "obsidian_spear", "17fae1")
            .setDisplayName("Obsidian Spear")
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addMeta(new ObsidianItemMeta(false), new AdvancementOnObtainMeta(ObsidianItem.class))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial CalciteChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "calcite_chisel", "0dedfe")
            .setDisplayName("Calcite Chisel")
            .addMeta(new ChiselMeta(5.5f), new CustomDurabilityMeta(45))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .create();

    public static DFMaterial FloralChisel = new Builder(Material.POPPED_CHORUS_FRUIT, "floral_chisel", "3c5329")
            .setDisplayName("<light_purple>Floral Chisel")
            .addMeta(new ChiselMeta(7f), new CustomDurabilityMeta(75))
            .addAllowedUnsafeEnchantments(Enchantment.MENDING, Enchantment.EFFICIENCY)
            .setMaxStack(1)
            .create();

    public static DFMaterial CalciteShears = new Builder(Material.SHEARS, "calcite_shears", "fa0dce")
            .setDisplayName("Calcite Shears")
            .addMeta(new CustomDurabilityMeta(175))
            .setTool(createFasterTool(ItemType.SHEARS, 0.95f))
            .create();

    public static DFMaterial FloralShears = new Builder(Material.SHEARS, "floral_shears", "509e97")
            .setDisplayName("<light_purple>Floral Shears")
            .addMeta(new CustomDurabilityMeta(450))
            .setTool(createFasterTool(ItemType.SHEARS, 1.5f))
            .create();

    public static DFMaterial DiamondTintedElytra = new Builder(Material.ELYTRA, "diamond_tinted_elytra", "3b3a22")
            .setDisplayName("<light_purple>Diamond Tinted Elytra")
            .addMeta(new CustomDurabilityMeta(500))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(diamondTintedElytra, 1D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .create();

    public static DFMaterial NetheriteTintedElytra = new Builder(Material.ELYTRA, "netherite_tinted_elytra", "341366")
            .setDisplayName("<light_purple>Netherite Tinted Elytra")
            .addMeta(new CustomDurabilityMeta(550))
            .addAttribute(Attribute.ARMOR, new AttributeModifier(netheriteTintedElytra, 1.5D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .setCanSurviveLava(true)
            .create();

    public static DFMaterial ObsidianTintedElytra = new Builder(Material.ELYTRA, "obsidian_tinted_elytra", "25be6b")
            .setDisplayName("<light_purple>Obsidian Tinted Elytra")
            .addMeta(new CustomDurabilityMeta(575), new AdvancementOnObtainMeta(ObsidianItem.class))
            .addEnchantment(Enchantment.UNBREAKING, 10)
            .addAttribute(Attribute.ARMOR, new AttributeModifier(obsidianTintedElytra, 2D, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST))
            .setCanSurviveLava(true)
            .create();

    private final String namedId;
    private final TextComponent displayName;
    private final Material base;
    private final boolean markedForUuid;
    private final List<TextComponent> lores;
    private final ItemStack item;
    private final List<DFMaterialMeta> metas;
    private final List<NamespacedKey> possibleModels;
    private final List<Enchantment> allowedUnsafeEnchantments;
    private final boolean survivesLava;
    private final String updateId;

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

    public String getUpdateId() {
        return this.updateId;
    }

    public boolean hasPossibleModels() {
        return !this.possibleModels.isEmpty();
    }

    public boolean hasMeta() {
        return !this.metas.isEmpty();
    }

    public boolean canSurviveLava() {
        return this.survivesLava;
    }

    public List<Enchantment> getAllowedUnsafeEnchantments() {
        return this.allowedUnsafeEnchantments;
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
            @Nullable List<ItemFlag> flags,
            List<DFMaterialMeta> dfmetas,
            boolean hasCustomModel,
            NamespacedKey customModel,
            @Nullable Integer maxStack,
            List<NamespacedKey> possibleModels,
            @Nullable Consumable consumable,
            @Nullable Tool tool,
            @Nullable FoodProperties food,
            @Nullable Repairable repairable,
            String updateId,
            @Nullable List<Enchantment> allowedUnsafeEnchantments,
            boolean survivesLava
            )
    {
        List<TextComponent> workingLore = lores == null ? List.of() : lores;
        this.possibleModels = possibleModels;
        this.markedForUuid = markedForUuid;
        this.namedId = namedId;
        this.displayName = Name;
        this.base = base;
        this.updateId = updateId;

        if (dfmetas == null) this.metas = new ArrayList<>();
        else this.metas = dfmetas;

        ItemStack newItem = new ItemStack(base);
        this.item = newItem;

        ItemMeta meta = newItem.getItemMeta();

        if (hasCustomModel) {
            meta.setItemModel(customModel);
        }

        if (maxStack != null) meta.setMaxStackSize(maxStack);

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
                Keys.dfUpdateId,
                PersistentDataType.STRING,
                updateId
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
        if (food != null) item.setData(DataComponentTypes.FOOD, food);
        if (repairable != null) item.setData(DataComponentTypes.REPAIRABLE, repairable);
        if (Name != null) item.setData(DataComponentTypes.ITEM_NAME, Name);

        this.allowedUnsafeEnchantments = allowedUnsafeEnchantments;
        this.survivesLava = survivesLava;

        if (this.hasMeta()) {
            for (DFMaterialMeta createdmeta : this.getMeta()) {
                createdmeta.ItemCreated(this, this.item);
            }
        }
    }

    public static class Builder {
        private final Material base;
        private final String namedId;
        private final String updateId;

        private TextComponent displayName;
        private boolean markedForUuid;

        private NamespacedKey equipModel;
        private Sound equipSound;
        private EquipmentSlot equipSlot;

        private final List<DFMaterialMeta> metas = new ArrayList<>();
        private final List<TextComponent> lores = new ArrayList<>();
        private final List<ItemFlag> flags = new ArrayList<>();

        private final HashMap<Enchantment, Integer> enchants = new HashMap<>();
        private final HashMap<Attribute, AttributeModifier> attributes = new HashMap<>();
        private final HashMap<NamespacedKey, Map.Entry<PersistentDataType, Object>> persistentData = new HashMap<>();

        private boolean hasCustomModel = true;
        private NamespacedKey overrideModel = null;

        private Integer maxStack = null;

        private Consumable consumable = null;
        private Tool tool = null;
        private FoodProperties food = null;
        private Repairable repairable = null;

        private List<Enchantment> allowedUnsafeEnchantments = new ArrayList<>();

        private List<NamespacedKey> possibleModels = new ArrayList<>();
        private boolean survivesLava = false;

        public Builder(Material base, String namedId, String updateId) {
            this.base = base;
            this.namedId = namedId;
            this.updateId = updateId;
        }

        public Builder setDisplayName(TextComponent name) {
            this.displayName = name;
            return this;
        }

        public Builder setDisplayName(String name) {
            MiniMessage mm = MiniMessage.miniMessage();
            this.displayName = (TextComponent) mm.deserialize("<!italic>"+name);
            return this;
        }

        public Builder markForUUID() {
            this.markedForUuid = true;
            return this;
        }

        public Builder addPersistentData(String key, PersistentDataType type, Object val) {
            return addPersistentData(createDefaultKey(key), type, val);
        }

        public Builder addPersistentData(NamespacedKey key, PersistentDataType type, Object val) {
            persistentData.put(key, new AbstractMap.SimpleEntry<>(
                    type,
                    val
            ));
            return this;
        }

        public Builder addMeta(DFMaterialMeta... metas) {
            this.metas.addAll(Arrays.asList(metas));
            return this;
        }

        public Builder addLore(String lore) {
            MiniMessage mm = MiniMessage.miniMessage();
            lores.add((TextComponent) mm.deserialize("<!italic>"+lore));
            return this;
        }

        public Builder addLore(TextComponent lore) {
            this.lores.add(lore);
            return this;
        }

        public Builder addEnchantment(Enchantment ench) {
            this.enchants.put(ench, 1);
            return this;
        }

        public Builder addEnchantment(Enchantment ench, int level) {
            this.enchants.put(ench, level);
            return this;
        }

        public Builder addAttribute(Attribute attribute, AttributeModifier modifier) {
            this.attributes.put(attribute, modifier);
            return this;
        }

        public Builder addItemFlag(ItemFlag flag) {
            this.flags.add(flag);
            return this;
        }

        public Builder setEquippable(String equipModel, Sound equipSound, EquipmentSlot equipSlot) {
            this.equipModel = createModelKey(equipModel);
            this.equipSound = equipSound;
            this.equipSlot = equipSlot;
            return this;
        }

        public Builder setEquippable(NamespacedKey equipModel, Sound equipSound, EquipmentSlot equipSlot) {
            this.equipModel = equipModel;
            this.equipSound = equipSound;
            this.equipSlot = equipSlot;
            return this;
        }

        public Builder setEquippable(NamespacedKey equipModel, EquipmentSlot equipSlot) {
            this.equipModel = equipModel;
            this.equipSound = Sound.ITEM_ARMOR_EQUIP_IRON;
            this.equipSlot = equipSlot;
            return this;
        }

        public Builder removeCustomModel() {
            this.hasCustomModel = false;
            return this;
        }

        public Builder overrideCustomModel(String newModel) {
            this.overrideModel = createModelKey(newModel);
            return this;
        }

        public Builder overrideCustomModel(NamespacedKey newModel) {
            this.overrideModel = newModel;
            return this;
        }

        public Builder setMaxStack(Integer max) {
            this.maxStack = max;
            return this;
        }

        public Builder addPossibleModels(String... models) {
            for (String model : models) {
                this.possibleModels.add(createModelKey(model));
            }
            return this;
        }

        public Builder addPossibleModels(NamespacedKey... models) {
            this.possibleModels.addAll(Arrays.asList(models));
            return this;
        }

        public Builder setConsumable(Consumable consumable) {
            this.consumable = consumable;
            return this;
        }

        public Builder setTool(Tool tool) {
            this.tool = tool;
            return this;
        }

        public Builder setRepairable(Repairable repairable) {
            this.repairable = repairable;
            return this;
        }

        public Builder setFoodProperties(FoodProperties food) {
            this.food = food;
            return this;
        }

        public Builder addAllowedUnsafeEnchantments(Enchantment... enchantments) {
            this.allowedUnsafeEnchantments.addAll(Arrays.asList(enchantments));
            return this;
        }

        public Builder setCanSurviveLava(boolean bool) {
            this.survivesLava = bool;
            return this;
        }

        public DFMaterial create() {
            DFMaterial newMaterial = new DFMaterial(
                    base,
                    namedId,
                    displayName,
                    lores,
                    enchants,
                    attributes,
                    persistentData,
                    markedForUuid,
                    equipModel,
                    equipSound,
                    equipSlot,
                    flags,
                    metas,
                    hasCustomModel,
                    overrideModel == null ? createModelKey(namedId) : overrideModel,
                    maxStack,
                    possibleModels,
                    consumable,
                    tool,
                    food,
                    repairable,
                    updateId,
                    allowedUnsafeEnchantments,
                    survivesLava
            );
            DFMaterial.DFMaterialIndex.put(namedId, newMaterial);
            return newMaterial;
        }
    }
}
