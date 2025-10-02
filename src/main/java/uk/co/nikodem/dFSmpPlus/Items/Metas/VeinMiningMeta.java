package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

import java.util.ArrayList;
import java.util.List;

public class VeinMiningMeta implements DFMaterialMeta {
    public final Material[] list;

    public VeinMiningMeta(Material[] list) {
        this.list = list;
    }

    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {
        Block b = event.getBlock();

        Boolean isVeinable = false;
        for (Material potential : list) {
            if (potential == b.getType()) {
                isVeinable = true;
                break;
            }
        }

        if (!isVeinable) return;

        List<Block> veinLogs = new ArrayList<>();
        veinLogs.add(b);
        findVeinLogs(b, b.getType(), veinLogs);

        Damageable im = (Damageable) tool.getItemMeta();
        if (im == null) return;
        int damage = im.getDamage() + 1;
        im.setDamage(damage);
        tool.setItemMeta(im);

        for (Block block : veinLogs) {
            block.breakNaturally(tool);
        }
    };

    private static void findVeinLogs(Block origin, Material type, List<Block> veinLogs) {
        BlockFace[] var4 = BlockFace.values();
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            BlockFace face = var4[var6];
            Block relative = origin.getRelative(face);
            if (relative.getType() == type && !veinLogs.contains(relative)) {
                veinLogs.add(relative);
                findVeinLogs(relative, type, veinLogs);
            }
        }

    }
}
