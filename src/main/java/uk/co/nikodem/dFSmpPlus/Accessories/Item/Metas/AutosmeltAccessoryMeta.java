package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation.AutosmeltingOnBlockbreak;

import java.util.HashMap;
import java.util.Map;

public class AutosmeltAccessoryMeta implements AccessoryMeta {
    public final Map<Material, Material> allAutosmeltLists;

    public AutosmeltAccessoryMeta(Map<Material, Material>... list) {
        Map<Material, Material> finalList = new HashMap<>();
        for (Map<Material, Material> subList : list) {
            finalList.putAll(subList);
        }
        this.allAutosmeltLists = finalList;
    }

    @Override
    public void MinedBlockDropItem(Player plr, ItemStack accessory, AccessoryInformation info, BlockDropItemEvent event) {
        if (plr.isSneaking()) {
            Material output = this.allAutosmeltLists.get(event.getBlockState().getType());
            if (output != null) AutosmeltingOnBlockbreak.doAutosmelt(event, this.allAutosmeltLists);
        }
    };
}
