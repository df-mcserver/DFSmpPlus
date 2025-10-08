package uk.co.nikodem.dFSmpPlus.Player.Mining;

import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class MiningListener implements Listener {
    private final MiningManager manager = new MiningManager();

    @EventHandler
    public void onMine(PlayerAnimationEvent e) {
        Player player = e.getPlayer();
        if (!e.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) return;
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) return; // require survival mode

        ItemStack tool = player.getInventory().getItemInMainHand();

        if (!DFItemUtils.isChisel(tool)) return;

        DFMaterial material = DFItemUtils.getDFMaterial(tool);
        if (material == null) return;

        // get the block the player is looking at.
        // you may notice that 3 sometimes isn't enough, you might want to increase this to 4.
        Block block = player.getTargetBlockExact(5, FluidCollisionMode.NEVER);
        if (block == null) return;
        if (block.getType().equals(Material.AIR)) return; // make sure the block isn't air
        player.sendMessage("Valid block");
        manager.updateAndNextPhase(player); // update the mining phase.

        // send the block stage before updating mining. This will attempt to prevent placing blocks making destruction animations.
        int blockStage = manager.getBlockStage(block.getLocation());
        manager.sendBlockDamage(player, block.getLocation(), blockStage); // send the block damage packet
        blockStage = ((blockStage+1) % 10); // increment the block stage, if it's already 10, set it back to 0.
        manager.setBlockStage(block.getLocation(), blockStage);
        if (blockStage == 0) {
            manager.removeBlockStage(block.getLocation()); // remove the block stage
            block.breakNaturally(player.getInventory().getItemInMainHand()); // break the block
        }
    }
}
