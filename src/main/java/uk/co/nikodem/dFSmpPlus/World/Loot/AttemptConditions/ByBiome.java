package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptConditions;

import org.bukkit.block.Biome;
import org.bukkit.event.world.LootGenerateEvent;
import org.jspecify.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.World.Loot.LootEditCondition;

import java.util.List;

public class ByBiome implements LootEditCondition {
    private Biome biome = Biome.PLAINS;

    public ByBiome setBiome(Biome biome) {
        this.biome = biome;
        return this;
    }

    @Override
    public @Nullable List<Integer> runCheck(LootGenerateEvent event) {
        return event.getLootContext().getLocation().getBlock().getBiome().equals(biome) ? List.of() : null;
    }
}
