package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public interface ChiselBlockModify {
    void modifyBlock(Player plr, Block block, BlockFace blockFace);
}
