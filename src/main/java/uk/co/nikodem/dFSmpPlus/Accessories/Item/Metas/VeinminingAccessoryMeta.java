package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation.VeinminingOnBlockbreak;

import java.util.ArrayList;
import java.util.List;

public class VeinminingAccessoryMeta implements AccessoryMeta {
    public final List<Material> allVeinmineLists;

    public VeinminingAccessoryMeta(List<Material>... list) {
        List<Material> finalList = new ArrayList<>();
        for (List<Material> subList : list) {
            finalList.addAll(subList);
        }
        this.allVeinmineLists = finalList;
    }

    @Override
    public void BlockMined(Player plr, ItemStack accessory, AccessoryInformation info, BlockBreakEvent event) {
        if (plr.isSneaking()) VeinminingOnBlockbreak.doVeinmine(event, this.allVeinmineLists);
    };
}
