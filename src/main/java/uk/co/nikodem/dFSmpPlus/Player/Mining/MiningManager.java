package uk.co.nikodem.dFSmpPlus.Player.Mining;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MiningManager {
    private final HashMap<UUID, Long> nextPhase = new HashMap<UUID, Long>();
    private final HashMap<Location, Integer> blockStages = new HashMap<>(); // this could be changed to a Cache, so the blockStages **don't last forever**

    public long getNextPhase(Player player) {
        return nextPhase.get(player.getUniqueId());
    }

    /**
     * @return true if the player can go to the next phase, false if they can't
     */
    public boolean updatePhaseCooldown(Player player) {
        List<UUID> toRemove = new ArrayList<>();
        nextPhase.forEach((uuid, phase) -> {
            if (phase <= System.currentTimeMillis()) {
                toRemove.add(uuid);
            }
        });
        toRemove.forEach(nextPhase::remove);
        if (nextPhase.containsKey(player.getUniqueId())) return false;
        nextPhase(player);
        return true;
    }

    public void nextPhase(Player player) {
        nextPhase.put(player.getUniqueId(), System.currentTimeMillis() + 400); // 400 milliseconds between phases
    }

    /**
     * does both updatePhaseCooldown and nextPhase
     * @return true if the phase has been updated
     */
    public boolean updateAndNextPhase(Player player) {
        if (updatePhaseCooldown(player)) {
            nextPhase(player);
            return true;
        }
        return false;
    }

    public void sendBlockDamage(Player player, Location location, float progress) {
        int locationId = location.getBlockX() + location.getBlockY() + location.getBlockZ();
        PacketContainer packet = DFSmpPlus.protocolManager.createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
        // set entity ID to the location so the block break animation doesn't break (this is why we need packets).
        packet.getIntegers().write(0, locationId);
        packet.getBlockPositionModifier().write(0, new BlockPosition(location.toVector())); // set the block location
        packet.getIntegers().write(1, getBlockStage(location)); // set the damage to blockStage
        DFSmpPlus.protocolManager.sendServerPacket(player, packet);
    }

    public int getBlockStage(Location loc) {
        return blockStages.getOrDefault(loc, 0);
    }

    public void setBlockStage(Location loc, int stage) {
        blockStages.remove(loc);
        blockStages.put(loc, stage);
    }

    public void removeBlockStage(Location loc) {
        blockStages.remove(loc);
    }
}
