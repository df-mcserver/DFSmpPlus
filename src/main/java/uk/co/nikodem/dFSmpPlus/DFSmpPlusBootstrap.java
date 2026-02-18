package uk.co.nikodem.dFSmpPlus;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.EquipmentSlotGroup;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

import java.util.List;

public class DFSmpPlusBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event -> {
            event.registry().register(
                    EnchantmentKeys.create(Keys.createRegistryKey("harvesting")),
                    b -> b
                            .supportedItems(event.getOrCreateTag(ItemTypeTagKeys.HOES))
                            .primaryItems(event.getOrCreateTag(ItemTypeTagKeys.HOES))
                            .description(Component.text("Harvesting"))
                            .anvilCost(1)
                            .maxLevel(3)
                            .weight(100)
                            .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 1))
                            .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(3, 3))
                            .activeSlots(EquipmentSlotGroup.MAINHAND)
            );
        }));

        context.getLifecycleManager().registerEventHandler(LifecycleEvents.TAGS.postFlatten(RegistryKey.ENCHANTMENT).newHandler(event -> {
            event.registrar().addToTag(EnchantmentTagKeys.IN_ENCHANTING_TABLE, List.of(EnchantmentKeys.create(Keys.createRegistryKey("harvesting"))));
            event.registrar().addToTag(EnchantmentTagKeys.TRADEABLE, List.of(EnchantmentKeys.create(Keys.createRegistryKey("harvesting"))));
            event.registrar().addToTag(EnchantmentTagKeys.ON_TRADED_EQUIPMENT, List.of(EnchantmentKeys.create(Keys.createRegistryKey("harvesting"))));
        }));
    }
}
