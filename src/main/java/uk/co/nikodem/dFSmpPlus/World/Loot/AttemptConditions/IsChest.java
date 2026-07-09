package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptConditions;

import org.bukkit.block.Chest;
import org.bukkit.event.world.LootGenerateEvent;
import org.jspecify.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.World.Loot.LootEditCondition;

import java.util.List;

public class IsChest implements LootEditCondition {

    @Override
    public @Nullable List<Integer> runCheck(LootGenerateEvent event) {
        if (event.getInventoryHolder() == null) return null;
        if (event.getInventoryHolder() instanceof Chest) {
            return List.of();
        }
        return null;
    }
}
