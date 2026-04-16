package uk.co.nikodem.dFSmpPlus.Blocks;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.VacuumAccessoryMeta;

import java.util.Iterator;
import java.util.List;

public class CustomDrops {
    public static void onBlockDropEvent(BlockDropItemEvent event) {
        Player plr = event.getPlayer();

        if (event.getBlockState().getType() == Material.GRAVEL) {
            for (Item i : event.getItems()) {
                i.setItemStack(ItemStack.of(Material.GRAVEL));
            }
        }

        List<ItemStack> overflow = VacuumAccessoryMeta.giveItemEntitiesToPlayerViaVacuum(plr, event.getItems());

        Iterator<ItemStack> itemStackIterator = overflow.iterator();
        Iterator<Item> itemIterator = event.getItems().iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (!itemStackIterator.hasNext()) {
                itemIterator.remove();
                continue;
            }
            ItemStack itemStack = itemStackIterator.next();

            if (itemStack == null) {
                itemIterator.remove();
            } else {
                item.setItemStack(itemStack);
            }
        }
    }
}
