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
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.util.Vector;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.AutosmeltAccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.VacuumAccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Constants.AutoSmeltable;
import uk.co.nikodem.dFSmpPlus.Constants.ChiselBlockData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.Collections;
import java.util.List;

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
                event.setDropItems(false);
                BlockFace face = getBlockFace(plr);
                if (face == null) return;
                ItemStack drop = data.getDrop();
                PlayerAccessoryData accessoryData = AccessoryManager.getPlayerAccessoryData(plr);
                boolean autosmelt = DFItemUtils.containsMeta(material, AutosmeltingItemMeta.class) || (accessoryData.hasAccessoryWithMetaEquipped(AutosmeltAccessoryMeta.class) && plr.isSneaking());

                if (autosmelt) {
                    Material autosmeltMat = AutoSmeltable.AutosmeltableChisel.get(drop.getType());
                    if (autosmeltMat != null) {
                        drop = ItemStack.of(autosmeltMat);
                        Sounds.AutoSmelt.playSound(loc);
                    }
                }

                if (DFItemUtils.isLevelOrAbove(tool, data.getMinimumToolLevel())) {
                    if (data.getAdvancementOnChisel() != null) DFAdvancementsHandler.grantAdvancement(plr, data.getAdvancementOnChisel());
                    if (data.getBlockMaterial() != Material.AIR) {
                        loc.add(face.getDirection().divide(new Vector(1.3, 1.3, 1.3)));
                    }
                    for (ItemStack overflow : VacuumAccessoryMeta.giveItemsToPlayerViaVacuum(plr, List.of(drop))) {
                        block.getWorld().dropItemNaturally(loc, overflow);
                    }
                    block.setType(data.getReplacingMaterial());
                }

                data.getSoundData().playSound(loc);
                DFItemUtils.reduceDurability(plr, tool, 1, true);
                TelemetryUtils.increaseChiselUses(1);
                data.runBlockModifications(plr, block, face);
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

    // https://www.spigotmc.org/threads/getting-the-blockface-of-a-targeted-block.319181/
    // tysm
    public BlockFace getBlockFace(Player player) {
        List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 10);
        if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) return null;
        Block targetBlock = lastTwoTargetBlocks.get(1);
        Block adjacentBlock = lastTwoTargetBlocks.get(0);
        return targetBlock.getFace(adjacentBlock);
    }
}