package uk.co.nikodem.dFSmpPlus.World.Loot;

import org.bukkit.event.world.LootGenerateEvent;

import javax.annotation.Nullable;
import java.util.List;

public interface LootEditCondition {
    // null = failed check
    // empty list = passed, no specific slots
    // list = passed w/ specific slots
    @Nullable List<Integer> runCheck(LootGenerateEvent event);
}
