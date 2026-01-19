package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation.AutosmeltingOnBlockbreak;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.Map;

public class AutosmeltingItemMeta implements DFMaterialMeta {
    public final Map<Material, Material> list;

    public AutosmeltingItemMeta(Map<Material, Material> list) {
        this.list = list;
    }
    public AutosmeltingItemMeta() {
        this.list = Map.of();
    }

    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {
        if (!event.isDropItems()) return;
        if (!DFItemUtils.hasFireAspect(tool)) return; // if you remove fireaspect, autosmelting no work

        if (list.containsKey(event.getBlock().getType())) AutosmeltingOnBlockbreak.doAutosmelt(event, list);
    }
}
