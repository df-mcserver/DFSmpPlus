package uk.co.nikodem.dFSmpPlus.Items;

import org.bukkit.Material;
import uk.co.nikodem.dFSmpPlus.Items.Metas.VanillaItems.FireChargeMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VanillaItems {
    public static Map<Material, List<DFMaterialMeta>> vanillaItemMetas;

    static {
        vanillaItemMetas = new HashMap<>();
        vanillaItemMetas.put(Material.FIRE_CHARGE, List.of(new FireChargeMeta()));
    }
}
