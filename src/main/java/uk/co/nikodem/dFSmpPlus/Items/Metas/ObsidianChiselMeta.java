package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.UselessFlex;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class ObsidianChiselMeta implements DFMaterialMeta {
    public void ItemCrafted(DFMaterial material, ItemStack item, Player plr, CraftItemEvent event) {
        DFAdvancementsHandler.grantAdvancement(plr, UselessFlex.class);
    }
}
