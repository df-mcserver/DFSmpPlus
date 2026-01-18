package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation.VeinminingOnBlockbreak;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.List;

public class VeinminingItemMeta implements DFMaterialMeta {
    public final List<Material> list;

    public VeinminingItemMeta(List<Material> list) {
        this.list = list;
    }

    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {
        VeinminingOnBlockbreak.doVeinmine(event, list);
    };
}
