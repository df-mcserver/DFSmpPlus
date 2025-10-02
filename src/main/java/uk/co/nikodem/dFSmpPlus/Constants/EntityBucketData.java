package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

public class EntityBucketData {

    public static final Map<EntityType, Material> EntityEggIndex = Map.<EntityType, Material>ofEntries(
            Map.entry(EntityType.BAT, Material.BAT_SPAWN_EGG),
            Map.entry(EntityType.BEE, Material.BEE_SPAWN_EGG),
            Map.entry(EntityType.CAT, Material.CAT_SPAWN_EGG),
            Map.entry(EntityType.COD, Material.COD_SPAWN_EGG),
            Map.entry(EntityType.COW, Material.COW_SPAWN_EGG),
            Map.entry(EntityType.FOX, Material.FOX_SPAWN_EGG),
            Map.entry(EntityType.PIG, Material.PIG_SPAWN_EGG),
            Map.entry(EntityType.VEX, Material.VEX_SPAWN_EGG),
            Map.entry(EntityType.FROG, Material.FROG_SPAWN_EGG),
            Map.entry(EntityType.GOAT, Material.GOAT_SPAWN_EGG),
            Map.entry(EntityType.HUSK, Material.HUSK_SPAWN_EGG),
            Map.entry(EntityType.MULE, Material.MULE_SPAWN_EGG),
            Map.entry(EntityType.WOLF, Material.WOLF_SPAWN_EGG),
            Map.entry(EntityType.ALLAY, Material.ALLAY_SPAWN_EGG),
            Map.entry(EntityType.BLAZE, Material.BLAZE_SPAWN_EGG),
            Map.entry(EntityType.CAMEL, Material.CAMEL_SPAWN_EGG),
            Map.entry(EntityType.GHAST, Material.GHAST_SPAWN_EGG),
            Map.entry(EntityType.HORSE, Material.HORSE_SPAWN_EGG),
            Map.entry(EntityType.LLAMA, Material.LLAMA_SPAWN_EGG),
            Map.entry(EntityType.PANDA, Material.PANDA_SPAWN_EGG),
            Map.entry(EntityType.SHEEP, Material.SHEEP_SPAWN_EGG),
            Map.entry(EntityType.SLIME, Material.SLIME_SPAWN_EGG),
            Map.entry(EntityType.SQUID, Material.SQUID_SPAWN_EGG),
            Map.entry(EntityType.STRAY, Material.STRAY_SPAWN_EGG),
            Map.entry(EntityType.WITCH, Material.WITCH_SPAWN_EGG),
            Map.entry(EntityType.EVOKER, Material.EVOKER_SPAWN_EGG),
            Map.entry(EntityType.DONKEY, Material.DONKEY_SPAWN_EGG),
            Map.entry(EntityType.HOGLIN, Material.HOGLIN_SPAWN_EGG),
            Map.entry(EntityType.OCELOT, Material.OCELOT_SPAWN_EGG),
            Map.entry(EntityType.PARROT, Material.PARROT_SPAWN_EGG),
            Map.entry(EntityType.PIGLIN, Material.PIGLIN_SPAWN_EGG),
            Map.entry(EntityType.RABBIT, Material.RABBIT_SPAWN_EGG),
            Map.entry(EntityType.SALMON, Material.SALMON_SPAWN_EGG),
            Map.entry(EntityType.SPIDER, Material.SPIDER_SPAWN_EGG),
            Map.entry(EntityType.TURTLE, Material.TURTLE_SPAWN_EGG),
            Map.entry(EntityType.ZOGLIN, Material.ZOGLIN_SPAWN_EGG),
            Map.entry(EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG),
            Map.entry(EntityType.AXOLOTL, Material.AXOLOTL_SPAWN_EGG),
            Map.entry(EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG),
            Map.entry(EntityType.CREEPER, Material.CREEPER_SPAWN_EGG),
            Map.entry(EntityType.DOLPHIN, Material.DOLPHIN_SPAWN_EGG),
            Map.entry(EntityType.DROWNED, Material.DROWNED_SPAWN_EGG),
            Map.entry(EntityType.PHANTOM, Material.PHANTOM_SPAWN_EGG),
            Map.entry(EntityType.RAVAGER, Material.RAVAGER_SPAWN_EGG),
            Map.entry(EntityType.SHULKER, Material.SHULKER_SPAWN_EGG),
            Map.entry(EntityType.SNIFFER, Material.SNIFFER_SPAWN_EGG),
            Map.entry(EntityType.SNOW_GOLEM, Material.SNOW_GOLEM_SPAWN_EGG),
            Map.entry(EntityType.STRIDER, Material.STRIDER_SPAWN_EGG),
            Map.entry(EntityType.TADPOLE, Material.TADPOLE_SPAWN_EGG),
            Map.entry(EntityType.ENDERMAN, Material.ENDERMAN_SPAWN_EGG),
            Map.entry(EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG),
            Map.entry(EntityType.PILLAGER, Material.PILLAGER_SPAWN_EGG),
            Map.entry(EntityType.SKELETON, Material.SKELETON_SPAWN_EGG),
            Map.entry(EntityType.VILLAGER, Material.VILLAGER_SPAWN_EGG),
            Map.entry(EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG),
            Map.entry(EntityType.GLOW_SQUID, Material.GLOW_SQUID_SPAWN_EGG),
            Map.entry(EntityType.IRON_GOLEM, Material.IRON_GOLEM_SPAWN_EGG),
            Map.entry(EntityType.MAGMA_CUBE, Material.MAGMA_CUBE_SPAWN_EGG),
            Map.entry(EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG),
            Map.entry(EntityType.PUFFERFISH, Material.PUFFERFISH_SPAWN_EGG),
            Map.entry(EntityType.SILVERFISH, Material.SILVERFISH_SPAWN_EGG),
            Map.entry(EntityType.VINDICATOR, Material.VINDICATOR_SPAWN_EGG),
            Map.entry(EntityType.CAVE_SPIDER, Material.CAVE_SPIDER_SPAWN_EGG),
            Map.entry(EntityType.MOOSHROOM, Material.MOOSHROOM_SPAWN_EGG),
            Map.entry(EntityType.PIGLIN_BRUTE, Material.PIGLIN_BRUTE_SPAWN_EGG),
            Map.entry(EntityType.ZOMBIE_HORSE, Material.ZOMBIE_HORSE_SPAWN_EGG),
            Map.entry(EntityType.TROPICAL_FISH, Material.TROPICAL_FISH_SPAWN_EGG),
            Map.entry(EntityType.WITHER_SKELETON, Material.WITHER_SKELETON_SPAWN_EGG),
            Map.entry(EntityType.ZOMBIE_VILLAGER, Material.ZOMBIE_VILLAGER_SPAWN_EGG),
            Map.entry(EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG),
            Map.entry(EntityType.WANDERING_TRADER, Material.WANDERING_TRADER_SPAWN_EGG),
            Map.entry(EntityType.SKELETON_HORSE, Material.SKELETON_HORSE_SPAWN_EGG),
            Map.entry(EntityType.ARMADILLO, Material.ARMADILLO_SPAWN_EGG),
            Map.entry(EntityType.BREEZE, Material.BREEZE_SPAWN_EGG),
            Map.entry(EntityType.TRADER_LLAMA, Material.TRADER_LLAMA_SPAWN_EGG),
            Map.entry(EntityType.BOGGED, Material.BOGGED_SPAWN_EGG)
    );

    @Nullable
    public static Material ConvertEntityToEgg(EntityType entity) {
        for (Map.Entry<EntityType, Material> value : EntityEggIndex.entrySet()) {
            EntityType entityValue = value.getKey();
            if (Objects.equals(entity, entityValue)) return value.getValue();
        }
        return null;
    }

    @Nullable
    public static EntityType ConvertEggToEntity(Material egg) {
        for (Map.Entry<EntityType, Material> value : EntityEggIndex.entrySet()) {
            Material eggValue = value.getValue();
            if (Objects.equals(egg, eggValue)) return value.getKey();
        }
        return null;
    }

    public static Boolean IsConvertible(EntityType entity) {
        return ConvertEntityToEgg(entity) != null;
    }

    public static Boolean IsConvertible(Material egg) {
        return ConvertEggToEntity(egg) != null;
    }
}
