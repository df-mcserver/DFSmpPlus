package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptConditions;

import org.bukkit.event.world.LootGenerateEvent;
import org.jspecify.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.World.Loot.LootEditCondition;

import java.util.List;
import java.util.Random;

public class ByRandomChance implements LootEditCondition {
    private static final Random rand = new Random();

    private int max = 1;

    public ByRandomChance setMaxInteger(int max) {
        this.max = max;
        return this;
    }

    @Override
    public @Nullable List<Integer> runCheck(LootGenerateEvent event) {
        return rand.nextInt(0, max) == 0 ? List.of() : null;
    }
}
