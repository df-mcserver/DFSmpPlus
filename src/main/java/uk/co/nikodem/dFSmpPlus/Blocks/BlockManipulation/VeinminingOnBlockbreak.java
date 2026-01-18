package uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.AutosmeltAccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Items.Metas.AutosmeltingItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static uk.co.nikodem.dFSmpPlus.Constants.VeinMineable.MAX_BLOCK_CHAIN;

public class VeinminingOnBlockbreak {
    public static void doVeinmine(BlockBreakEvent event, List<Material> veinMineable) {
        Player plr = event.getPlayer();
        Block b = event.getBlock();
        ItemStack tool = plr.getInventory().getItemInMainHand();

        if (!veinMineable.contains(b.getType())) return;

        List<Block> veinBlocks = new ArrayList<>();
        veinBlocks.add(b);
        findVeinBlocks(b, b.getType(), veinBlocks);

        Damageable im = (Damageable) tool.getItemMeta();
        if (im == null) return;
        int damage = im.getDamage() + 1;
        im.setDamage(damage);
        tool.setItemMeta(im);

        Map<Material, Material> autosmeltMapping = null;
        DFMaterial material = DFItemUtils.getDFMaterial(tool);
        if (material != null) {
            for (DFMaterialMeta meta : material.getMeta()) {
                if (meta instanceof AutosmeltingItemMeta autosmeltingItemMeta) {
                    autosmeltMapping = autosmeltingItemMeta.list;
                    break;
                }
            }
        }

        if (autosmeltMapping == null && plr.isSneaking()) {
            PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
            for (ItemStack accessory : accessoryData.slots) {
                AccessoryInformation info = DFItemUtils.getAccessoryInformation(accessory);
                if (info == null) continue;
                for (AccessoryMeta meta : info.getMeta()) {
                    if (meta instanceof AutosmeltAccessoryMeta autosmeltAccessoryMeta) {
                        autosmeltMapping = autosmeltAccessoryMeta.allAutosmeltLists;
                    }
                }
            }
        }

        for (Block block : veinBlocks) {
            if (autosmeltMapping != null) {
                AutosmeltingOnBlockbreak.doAutosmelt(new BlockBreakEvent(block, plr), autosmeltMapping);
                block.setType(Material.AIR);
            }
            else block.breakNaturally(tool);
        }
    };

    public static void findVeinBlocks(Block origin, Material type, List<Block> veinBlocks) {
        BlockFace[] allSides = BlockFace.values();

        for (BlockFace face : allSides) {
            if (veinBlocks.size() > MAX_BLOCK_CHAIN) return; // max of 64 blocks, to not lag the server
            Block relative = origin.getRelative(face);
            if (relative.getType() == type && !veinBlocks.contains(relative)) {
                veinBlocks.add(relative);
                findVeinBlocks(relative, type, veinBlocks); // recursive
            }
        }
    }
}
