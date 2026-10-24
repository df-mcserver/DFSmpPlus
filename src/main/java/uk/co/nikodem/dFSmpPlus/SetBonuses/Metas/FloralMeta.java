package uk.co.nikodem.dFSmpPlus.SetBonuses.Metas;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSet;
import uk.co.nikodem.dFSmpPlus.SetBonuses.DFArmourSetMeta;

public class FloralMeta implements DFArmourSetMeta {
    @Override
    public void UserMove(Player plr, DFArmourSet armourSet, PlayerMoveEvent event) {
        if (!event.hasExplicitlyChangedBlock()) return;
        Block floorBlock = plr.getLocation().subtract(new Vector(0, 1, 0)).getBlock();

        if (floorBlock.getType() == Material.DIRT) {
            floorBlock.setType(Material.GRASS_BLOCK);
        }
    };

    @Override
    public void UserInteract(Player plr, DFArmourSet armourSet, PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) {
            Block steppedOnBlock = event.getClickedBlock();
            if (steppedOnBlock == null) return;
            if (steppedOnBlock.getType().equals(Material.FARMLAND)) {
                event.setCancelled(true);
            }
        }
    };
}
