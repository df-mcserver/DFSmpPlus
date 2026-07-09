package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptEdits;

import org.bukkit.event.world.LootGenerateEvent;

import java.util.List;

public class LootEditTransformerData {
    private final List<Integer> modifiedSlots;
    private final LootGenerateEvent event;

    public LootEditTransformerData(LootGenerateEvent event, List<Integer> checkResult) {
        this.modifiedSlots = checkResult;
        this.event = event;
    }

    public List<Integer> getModifiedSlots() {
        return modifiedSlots;
    }

    public LootGenerateEvent getEvent() {
        return event;
    }
}
