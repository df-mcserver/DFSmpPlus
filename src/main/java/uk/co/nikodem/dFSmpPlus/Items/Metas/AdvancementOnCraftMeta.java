package uk.co.nikodem.dFSmpPlus.Items.Metas;

import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class AdvancementOnCraftMeta implements DFMaterialMeta {
    private final Class<? extends BaseAdvancement> clazz;

    public AdvancementOnCraftMeta(Class<? extends BaseAdvancement> clazz) {
        this.clazz = clazz;
    }

    public void ItemCrafted(DFMaterial material, ItemStack item, Player plr, CraftItemEvent event) {
        DFAdvancementsHandler.grantAdvancement(plr, clazz);
    }
}
