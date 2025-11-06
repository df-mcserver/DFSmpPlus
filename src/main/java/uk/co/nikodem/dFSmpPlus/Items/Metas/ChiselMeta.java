package uk.co.nikodem.dFSmpPlus.Items.Metas;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Tool;
import io.papermc.paper.event.block.BlockBreakProgressUpdateEvent;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import net.kyori.adventure.util.TriState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.IITNIG;
import uk.co.nikodem.dFSmpPlus.Constants.AutoSmeltable;
import uk.co.nikodem.dFSmpPlus.Constants.Chisel.ChiselBlockData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.Collections;

public class ChiselMeta implements DFMaterialMeta {
    private final float miningSpeed;

    public ChiselMeta(float miningSpeed) {
        this.miningSpeed = miningSpeed;
    }

    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {

        Block block = event.getBlock();
        Location loc = block.getLocation().add(0.5, 0.5, 0.5);

        for (ChiselBlockData data : ChiselBlockData.ChiselBlockDataIndex) {
            if (data.getBlockMaterial() == block.getType()) {
                event.setCancelled(true);
                ItemStack drop = data.getDrop();
                boolean autosmelt = DFItemUtils.containsMeta(material, AutoSmeltingMeta.class); // TODO: accessories

                if (block.getType().equals(Material.BOOKSHELF)) DFAdvancementsHandler.grantAdvancement(plr, IITNIG.class);

                if (autosmelt) {
                    Material autosmeltMat = AutoSmeltable.AutosmeltableChisel.get(drop.getType());
                    if (autosmeltMat != null) {
                        drop = ItemStack.of(autosmeltMat);
                        Sounds.AutoSmelt.playSound(loc);
                    }
                }

                if (DFItemUtils.isLevelOrAbove(tool, data.getMinimumToolLevel())) {
                    if (data.getReplacingMaterial() != Material.AIR &&
                            block.getLocation().add(0, 1, 0).getBlock().getType() == Material.AIR) loc.add(0, 0.5, 0);
                    block.getWorld().dropItemNaturally(loc, drop);
                    block.setType(data.getReplacingMaterial());
                }

                data.getSoundData().playSound(loc);
                DFItemUtils.reduceDurability(plr, tool, 1, true);
                TelemetryUtils.increaseChiselUses(1);
                break;
            }
        }
    }

    @Override
    public void ItemStartMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakProgressUpdateEvent event) {

        // TODO: fix this on bedrock
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
        RegistryKeySet<BlockType> blocks = RegistrySet.keySetFromValues(
                RegistryKey.BLOCK,
                Collections.singleton(block.getType().asBlockType())
        );
        tool.setData(DataComponentTypes.TOOL, Tool.tool()
                .addRule(Tool.rule(
                        blocks,
                        miningSpeed * data.getSpeedMultiplier(),
                        TriState.NOT_SET
                ))
            .defaultMiningSpeed(1f).build());
//        tool.setData(DataComponentTypes.TOOL, Tool.tool().defaultMiningSpeed(miningSpeed * data.getSpeedMultiplier()).build());
    }
}
