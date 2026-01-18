package uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.Map;

public class AutosmeltingOnBlockbreak {
    public static void doAutosmelt(BlockBreakEvent event, Map<Material, Material> matches) {
        Player plr = event.getPlayer();
        Block b = event.getBlock();
        ItemStack tool = plr.getInventory().getItemInMainHand();

        Material drop = matches.get(b.getType());

        event.setDropItems(false);

        Sounds.AutoSmelt.playSound(plr);

        for (ItemStack a : b.getDrops(tool)) {
            ItemStack newDrop = new ItemStack(drop);
            newDrop.setAmount(a.getAmount());
            b.getWorld().dropItemNaturally(b.getLocation().add(new Location(b.getWorld(), 0.5, 0.5, 0.5)), newDrop);
        }
    }
}
