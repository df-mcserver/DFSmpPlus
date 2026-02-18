package uk.co.nikodem.dFSmpPlus.Enchantments;

import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.Range;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Enchantments.Metas.HarvestMeta;
import uk.co.nikodem.dFSmpPlus.Enchantments.Metas.MouseMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DFEnchantment {
    // do not touch this list
    public final static List<DFEnchantment> DFEnchantmentIndex = new ArrayList<>();

    public static final DFEnchantment Harvesting = new DFEnchantment.Builder("harvesting")
            .setName("Harvesting")
            .addEnchantmentTagKeys(EnchantmentTagKeys.IN_ENCHANTING_TABLE, EnchantmentTagKeys.ON_TRADED_EQUIPMENT, EnchantmentTagKeys.TRADEABLE)
            .setActiveSlotGroups(EquipmentSlotGroup.MAINHAND)
            .setPrimaryItems(ItemTypeTagKeys.HOES)
            .setSupportedItems(ItemTypeTagKeys.HOES)
            .setAnvilCost(1)
            .setMaxLevel(3)
            .setWeight(100)
            .setMaxCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 3))
            .setMinCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 3))
            .addIncompatibleEnchantments(Enchantment.SILK_TOUCH)
            .addMeta(new HarvestMeta())
            .create();

    public static final DFEnchantment Mouse = new DFEnchantment.Builder("mouse")
            .setName("Mouse")
            .addEnchantmentTagKeys(EnchantmentTagKeys.ON_TRADED_EQUIPMENT, EnchantmentTagKeys.TRADEABLE)
            .setActiveSlotGroups(EquipmentSlotGroup.CHEST)
            .setPrimaryItems(ItemTypeTagKeys.ENCHANTABLE_CHEST_ARMOR)
            .setSupportedItems(ItemTypeTagKeys.ENCHANTABLE_CHEST_ARMOR)
            .setAnvilCost(1)
            .setMaxLevel(1)
            .setWeight(100)
            .setMaxCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 3))
            .setMinCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 3))
            .addMeta(new MouseMeta())
            .create();

    private final String id;
    private final Component name;
    private final List<TagKey<Enchantment>> enchantmentTagKeys;
    private final Iterable<EquipmentSlotGroup> activeSlotGroup;
    private final TagKey<ItemType> supportedItems;
    private final TagKey<ItemType> primaryItems;
    private final @Range(from = 1L, to = 255L) int maxLevel;
    private final @Range(from = 0L, to = 2147483647L) int anvilCost;
    private final @Range(from = 1L, to = 1024L) int weight;
    private final EnchantmentRegistryEntry.EnchantmentCost minCost;
    private final EnchantmentRegistryEntry.EnchantmentCost maxCost;
    private final Iterable<TypedKey<Enchantment>> incompatibleEnchantments;
    private final List<DFEnchantmentMeta> metas;

    public String getId() {
        return this.id;
    }
    public Key getKey() {
        return Keys.createRegistryKey(this.id);
    }
    public Component getName() {
        return this.name;
    }

    public List<TagKey<Enchantment>> getEnchantmentTagKeys() {
        return this.enchantmentTagKeys;
    }

    public Iterable<EquipmentSlotGroup> getActiveSlotGroup() {
        return this.activeSlotGroup;
    }

    public TagKey<ItemType> getSupportedItems() {
        return this.supportedItems;
    }

    @Nullable
    public TagKey<ItemType> getPrimaryItems() {
        return this.primaryItems;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public int getAnvilCost() {
        return this.anvilCost;
    }

    public int getWeight() {
        return this.weight;
    }

    public EnchantmentRegistryEntry.EnchantmentCost getMinCost() {
        return this.minCost;
    }

    public EnchantmentRegistryEntry.EnchantmentCost getMaxCost() {
        return this.maxCost;
    }

    public Iterable<TypedKey<Enchantment>> getIncompatibleEnchantments() {
        return incompatibleEnchantments;
    }

    public List<DFEnchantmentMeta> getMeta() {
        return this.metas;
    }

    public DFEnchantment(
            String id,
            Component name,
            List<TagKey<Enchantment>> enchantmentTagKeys,
            Iterable<EquipmentSlotGroup> activeSlotGroup,
            TagKey<ItemType> supportedItems,
            TagKey<ItemType> primaryItems,
            int maxLevel,
            int anvilCost,
            int weight,
            EnchantmentRegistryEntry.EnchantmentCost minCost,
            EnchantmentRegistryEntry.EnchantmentCost maxCost,
            Iterable<TypedKey<Enchantment>> incompatibleEnchantments,
            List<DFEnchantmentMeta> metas
    )
    {
        this.id = id;
        this.name = name;
        this.enchantmentTagKeys = enchantmentTagKeys;
        this.activeSlotGroup = activeSlotGroup;
        this.supportedItems = supportedItems;
        this.primaryItems = primaryItems;
        this.maxLevel = maxLevel;
        this.anvilCost = anvilCost;
        this.weight = weight;
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.incompatibleEnchantments = incompatibleEnchantments;
        this.metas = metas;
    }

    public static class Builder {
        private final String id;
        private Component name;
        private final List<TagKey<Enchantment>> enchantmentTagKeys = new ArrayList<>();
        private Iterable<EquipmentSlotGroup> activeSlotGroup;
        private TagKey<ItemType> supportedItems;
        private TagKey<ItemType> primaryItems;
        private @Range(from = 1L, to = 255L) int maxLevel = 1;
        private @Range(from = 0L, to = 2147483647L) int anvilCost = 1;
        private @Range(from = 1L, to = 1024L) int weight = 1;
        private EnchantmentRegistryEntry.EnchantmentCost minCost = EnchantmentRegistryEntry.EnchantmentCost.of(1, 3);
        private EnchantmentRegistryEntry.EnchantmentCost maxCost = EnchantmentRegistryEntry.EnchantmentCost.of(1, 3);
        private final List<TypedKey<Enchantment>> incompatibleEnchantments = new ArrayList<>();
        private final List<DFEnchantmentMeta> metas = new ArrayList<>();

        public Builder(String id) {
            this.id = id;
        }

        public Builder setName(String name) {
            this.name = Component.text(name);
            return this;
        }

        public Builder setName(Component name) {
            this.name = name;
            return this;
        }

        @SafeVarargs
        public final Builder addEnchantmentTagKeys(TagKey<Enchantment>... keys) {
            this.enchantmentTagKeys.addAll(List.of(keys));
            return this;
        }

        public Builder setActiveSlotGroups(EquipmentSlotGroup... activeSlotGroup) {
            this.activeSlotGroup = List.of(activeSlotGroup);
            return this;
        }

        public Builder setSupportedItems(TagKey<ItemType> supportedItems) {
            this.supportedItems = supportedItems;
            return this;
        }

        public Builder setPrimaryItems(TagKey<ItemType> primaryItems) {
            this.primaryItems = primaryItems;
            return this;
        }

        public Builder setMaxLevel(@Range(from = 1L, to = 255L) int maxLevel) {
            this.maxLevel = maxLevel;
            return this;
        }

        public Builder setAnvilCost(@Range(from = 0L, to = 2147483647L) int anvilCost) {
            this.anvilCost = anvilCost;
            return this;
        }

        public Builder setWeight(@Range(from = 1L, to = 1024L) int weight) {
            this.weight = weight;
            return this;
        }

        public Builder setMinCost(EnchantmentRegistryEntry.EnchantmentCost cost) {
            this.minCost = cost;
            return this;
        }

        public Builder setMaxCost(EnchantmentRegistryEntry.EnchantmentCost cost) {
            this.maxCost = cost;
            return this;
        }

        @SafeVarargs
        public final Builder addIncompatibleEnchantments(TypedKey<Enchantment>... enchants) {
            this.incompatibleEnchantments.addAll(Arrays.stream(enchants).toList());
            return this;
        }

        @SafeVarargs
        public final Builder addIncompatibleEnchantments(Enchantment... enchants) {
            List<TypedKey<Enchantment>> it = new ArrayList<>();
            for (Enchantment enchantment : enchants) {
                it.add(TypedKey.create(RegistryKey.ENCHANTMENT, enchantment.key()));
            }
            this.incompatibleEnchantments.addAll(it);
            return this;
        }

        public Builder addMeta(DFEnchantmentMeta... meta) {
            this.metas.addAll(List.of(meta));
            return this;
        }

        public DFEnchantment create() {
            DFEnchantment newSet = new DFEnchantment(
                    this.id,
                    this.name == null ? Component.text("Unknown") : this.name,
                    this.enchantmentTagKeys,
                    this.activeSlotGroup == null ? Collections.singleton(EquipmentSlotGroup.ANY) : activeSlotGroup,
                    this.supportedItems == null ? ItemTypeTagKeys.DIRT : this.supportedItems,
                    this.primaryItems,
                    this.maxLevel,
                    this.anvilCost,
                    this.weight,
                    this.minCost,
                    this.maxCost,
                    this.incompatibleEnchantments,
                    this.metas
            );
            DFEnchantment.DFEnchantmentIndex.add(newSet);
            return newSet;
        }
    }
}
