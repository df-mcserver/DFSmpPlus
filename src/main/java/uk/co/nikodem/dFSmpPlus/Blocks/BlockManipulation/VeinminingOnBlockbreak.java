package uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;

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

        for (Block block : veinBlocks) {
            Location dropLocation = block.getLocation().add(0.5, 0.5, 0.5);

            List<Item> itemDrops = new ArrayList<>();
            for (ItemStack item : block.getDrops(tool, plr)) {
                Item newItem = block.getWorld().dropItem(dropLocation, item);
                newItem.setItemStack(item);
                itemDrops.add(newItem);
            }

            BlockData blockParticleData = block.getType().createBlockData();
            if (!block.equals(b)) block.getWorld().spawnParticle(Particle.BLOCK, dropLocation, 10, blockParticleData);
            Bukkit.getPluginManager().callEvent(new BlockDropItemEvent(block, block.getState(), plr, itemDrops));
            block.setType(Material.AIR);
        }
    };

    public static void findVeinBlocks(Block origin, Material type, List<Block> veinBlocks) {
        BlockFace[] allSides = BlockFace.values();

        for (BlockFace face : allSides) {
            if (veinBlocks.size() >= MAX_BLOCK_CHAIN) return; // max of 64 blocks, to not lag the server
            Block relative = origin.getRelative(face);
            if (relative.getType() == type && !veinBlocks.contains(relative)) {
                veinBlocks.add(relative);
                findVeinBlocks(relative, type, veinBlocks); // recursive
            }
        }
    }
}
