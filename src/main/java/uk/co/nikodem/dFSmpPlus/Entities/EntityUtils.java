package uk.co.nikodem.dFSmpPlus.Entities;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

import static uk.co.nikodem.dFSmpPlus.Constants.EntityBucketData.EntityEggIndex;

public class EntityUtils {
    public static boolean isBoss(EntityType type) {
        return type == EntityType.ENDER_DRAGON || type == EntityType.WITHER || type == EntityType.ELDER_GUARDIAN;
    }

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

    public static Boolean IsConvertibleToEgg(EntityType entity) {
        return ConvertEntityToEgg(entity) != null;
    }

    public static Boolean IsConvertibleToEgg(Material egg) {
        return ConvertEggToEntity(egg) != null;
    }
}
