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
import org.bukkit.enchantments.Enchantment;
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

import java.util.ArrayList;
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
        // TODO: maybe doing this dynamically isn't the best idea, maybe define it when the item is made and update the list ?
        // (animations plays fine, but it doesn't actually break until it normally should

        Block block = event.getBlock();
        ChiselBlockData data = ChiselBlockData.getChiselBlockData(block);
        if (data == null) {
            tool.setData(DataComponentTypes.TOOL, Tool.tool().defaultMiningSpeed(1f).build());
            return;
        };

        // add the replacement block in case this code doesnt run fast enough to accomodate for when the block is replaced
        List<BlockType> expectedBlocks = new ArrayList<>();
        expectedBlocks.add(block.getType().asBlockType());
        if (data.getReplacingMaterial() != null) expectedBlocks.add(data.getReplacingMaterial().asBlockType());

        RegistryKeySet<BlockType> blocks = RegistrySet.keySetFromValues(
                RegistryKey.BLOCK,
                expectedBlocks
        );

        int efficiencyLevel = tool.getEnchantmentLevel(Enchantment.EFFICIENCY);
        float efficiencyMult = efficiencyLevel == 0 ? 0f : (efficiencyLevel^2)+1f;

        tool.setData(DataComponentTypes.TOOL, Tool.tool()
                .addRule(Tool.rule(
                        blocks,
                        (miningSpeed * data.getSpeedMultiplier()) + efficiencyMult,
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