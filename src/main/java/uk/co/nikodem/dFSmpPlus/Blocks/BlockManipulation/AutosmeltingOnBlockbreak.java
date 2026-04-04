package uk.co.nikodem.dFSmpPlus.Blocks.BlockManipulation;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.Map;

public class AutosmeltingOnBlockbreak {
    public static void doAutosmelt(BlockDropItemEvent event, Map<Material, Material> matches) {
        Player plr = event.getPlayer();

        boolean changed = false;

        for (Item item : event.getItems()) {
            ItemStack itemStack = item.getItemStack();
            Material newMaterial = matches.get(event.getBlockState().getType());

            if (newMaterial == null) continue;
            ItemStack newItemStack = ItemStack.of(newMaterial, itemStack.getAmount());
            item.setItemStack(newItemStack);

            changed = true;
        }

        if (changed) Sounds.AutoSmelt.playSound(plr);
    }
}
