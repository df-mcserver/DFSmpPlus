package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptConditions;

import org.bukkit.World;
import org.bukkit.event.world.LootGenerateEvent;
import org.jspecify.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.World.Loot.LootEditCondition;

import java.util.List;

public class ByDimension implements LootEditCondition {
    private World.Environment dimension = World.Environment.CUSTOM;

    public ByDimension setDimension(World.Environment dimension) {
        this.dimension = dimension;
        return this;
    }

    @Override
    public @Nullable List<Integer> runCheck(LootGenerateEvent event) {
        return event.getLootContext().getLocation().getBlock().getWorld().getEnvironment().equals(dimension) ? List.of() : null;
    }
}
