package uk.co.nikodem.dFSmpPlus.Items;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import uk.co.nikodem.dFSmpPlus.Constants.EntityBucketData;
import uk.co.nikodem.dFSmpPlus.Items.Metas.VanillaItems.FireChargeMeta;
import uk.co.nikodem.dFSmpPlus.Items.Metas.VanillaItems.SpawnEggInBucketMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VanillaItems {
    public static Map<Material, List<DFMaterialMeta>> vanillaItemMetas;

    static {
        vanillaItemMetas = new HashMap<>();
        vanillaItemMetas.put(Material.FIRE_CHARGE, List.of(new FireChargeMeta()));

        for (Map.Entry<EntityType, Material> spawnegg : EntityBucketData.EntityEggIndex.entrySet()) {
            vanillaItemMetas.put(spawnegg.getValue(), List.of(new SpawnEggInBucketMeta()));
        }
    }
}
