package uk.co.nikodem.dFSmpPlus;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.EquipmentSlotGroup;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

public class DFSmpPlusBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event -> {
            event.registry().register(
                    EnchantmentKeys.create(Keys.createRegistryKey("harvesting")),
                    b -> b
                            .supportedItems(event.getOrCreateTag(ItemTypeTagKeys.HOES))
                            .description(Component.text("Harvesting"))
                            .anvilCost(1)
                            .maxLevel(3)
                            .weight(10)
                            .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 1))
                            .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(3, 3))
                            .activeSlots(EquipmentSlotGroup.MAINHAND)
            );
        }));
    }
}
