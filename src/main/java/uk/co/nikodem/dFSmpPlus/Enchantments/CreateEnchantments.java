package uk.co.nikodem.dFSmpPlus.Enchantments;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateEnchantments {
    public static void init(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event -> {
            for (DFEnchantment enchantment : DFEnchantment.DFEnchantmentIndex) {
                event.registry().register(
                        EnchantmentKeys.create(enchantment.getKey()),
                        b -> b
                                .supportedItems(event.getOrCreateTag(enchantment.getSupportedItems()))
                                .primaryItems(event.getOrCreateTag(enchantment.getPrimaryItems() == null ? enchantment.getSupportedItems() : enchantment.getPrimaryItems()))
                                .activeSlots(enchantment.getActiveSlotGroup())
                                .description(enchantment.getName())
                                .maxLevel(enchantment.getMaxLevel())
                                .weight(enchantment.getWeight())
                                .anvilCost(enchantment.getAnvilCost())
                                .minimumCost(enchantment.getMinCost())
                                .maximumCost(enchantment.getMaxCost())
                                .exclusiveWith(RegistrySet.keySet(RegistryKey.ENCHANTMENT, enchantment.getIncompatibleEnchantments()))
                );
            }
        }));

        context.getLifecycleManager().registerEventHandler(LifecycleEvents.TAGS.postFlatten(RegistryKey.ENCHANTMENT).newHandler(event -> {
            HashMap<TagKey<Enchantment>, List<TypedKey<Enchantment>>> mappings = new HashMap<>();
            for (DFEnchantment enchantment : DFEnchantment.DFEnchantmentIndex) {
                for (TagKey<Enchantment> key : enchantment.getEnchantmentTagKeys()) {
                    List<TypedKey<Enchantment>> entry = mappings.get(key);
                    if (entry == null) entry = new ArrayList<>();
                    entry.add(EnchantmentKeys.create(enchantment.getKey()));
                    mappings.replace(key, entry);
                }
            }

            for (Map.Entry<TagKey<Enchantment>, List<TypedKey<Enchantment>>> entry : mappings.entrySet()) {
                event.registrar().addToTag(entry.getKey(), entry.getValue());
            }
        }));
    }
}
