package uk.co.nikodem.dFSmpPlus.Items.Metas;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Tool;
import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.util.Vector;
import uk.co.nikodem.dFSmpPlus.Constants.AutoSmeltable;
import uk.co.nikodem.dFSmpPlus.Constants.Chisel.ChiselBlockData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

public class ChiselMeta implements DFMaterialMeta {
    private final float miningSpeed;

    public ChiselMeta(float miningSpeed) {
        this.miningSpeed = miningSpeed;
    }

    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {

        Block block = event.getBlock();
        Location loc = block.getLocation();

        for (ChiselBlockData data : ChiselBlockData.ChiselBlockDataIndex) {
            if (data.getBlockMaterial() == block.getType()) {
                event.setCancelled(true);
                block.setType(data.getReplacingMaterial());
                if (DFItemUtils.isLevelOrAbove(tool, data.getMinimumToolLevel())) block.getWorld().dropItemNaturally(loc.add(new Vector(0.5, 0.5, 0.5)), data.getDrop());

                Sound sound = Sound.BLOCK_ANVIL_PLACE;
                float pitch = 1.75F;

                if (data.hasSoundOverride()) sound = data.getSoundOverride();
                if (data.hasPitchOverride()) pitch = data.getPitchOverride();

                data.getSoundData().playSound(loc);
                DFItemUtils.reduceDurability(tool, 1);
                break;
            }
        }
    }

    @Override
    public void ItemStartMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakProgressUpdateEvent event) {

        //TODO: fix this on bedrock
        // (animations plays fine, but it doesn't actually break until it normally should

        Block block = event.getBlock();
        ChiselBlockData data = ChiselBlockData.getChiselBlockData(block);
        // !! WARNING !!
        // the code below may or may not make you want to kill yourself
        // PLEASE I BEG if there's a better way to do this that is easy to do
        // fix it as soon as physcially possible
        if (data == null) {
            tool.setData(DataComponentTypes.TOOL, Tool.tool().defaultMiningSpeed(1f).build());
            return;
        };
        tool.setData(DataComponentTypes.TOOL, Tool.tool().defaultMiningSpeed(miningSpeed * data.getSpeedMultiplier()).build());
    }
}
