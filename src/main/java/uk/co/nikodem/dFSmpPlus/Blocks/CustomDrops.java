package uk.co.nikodem.dFSmpPlus.Blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.VacuumAccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Enchantments.DFEnchantment;
import uk.co.nikodem.dFSmpPlus.Enchantments.DFEnchantmentUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomDrops {
    public static void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!event.isDropItems()) return;

        List<ItemStack> newDrops = new ArrayList<>();

        Player plr = event.getPlayer();
        ItemStack tool = plr.getInventory().getItemInMainHand();
        PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
        Block origin = event.getBlock();
        if (origin.getType() == Material.GRAVEL) {
            event.setDropItems(false);
            newDrops = List.of(ItemStack.of(Material.GRAVEL));
        }

        boolean hasVacuum = accessoryData.hasAccessoryWithMetaEquipped(VacuumAccessoryMeta.class);

        Collection<ItemStack> realDrops = event.getBlock().getDrops(tool, plr);
        if (newDrops.isEmpty() && hasVacuum && !realDrops.isEmpty()) {
            newDrops = realDrops.stream().toList();
            event.setDropItems(false);
        }

        List<ItemStack> overflow = VacuumAccessoryMeta.giveItemsToPlayerViaVacuum(plr, newDrops);
        for (ItemStack item : overflow) {
            plr.getWorld().dropItemNaturally(origin.getLocation().add(new Location(origin.getWorld(), 0.5, 0.5, 0.5)), item);
        }
    }
}
