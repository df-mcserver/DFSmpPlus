package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation.AutosmeltingOnBlockbreak;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Items.Metas.VeinminingItemMeta;

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
    public void BlockMined(Player plr, ItemStack accessory, AccessoryInformation info, BlockBreakEvent event) {
        if (plr.isSneaking()) {
            boolean isVeinMining = false;
            ItemStack tool = plr.getInventory().getItemInMainHand();
            DFMaterial material = DFItemUtils.getDFMaterial(tool);
            if (material != null) {
                for (DFMaterialMeta meta : material.getMeta()) {
                    if (meta instanceof VeinminingItemMeta veinminingItemMeta) {
                        if (veinminingItemMeta.list.contains(event.getBlock().getType())) {
                            isVeinMining = true;
                            break;
                        }
                    }
                }
            }
            if (!isVeinMining) AutosmeltingOnBlockbreak.doAutosmelt(event, this.allAutosmeltLists);
        }
    };
}
