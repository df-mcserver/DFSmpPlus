package uk.co.nikodem.dFSmpPlus.World.Loot;

import org.bukkit.event.world.LootGenerateEvent;
import uk.co.nikodem.dFSmpPlus.World.Loot.AttemptEdits.LootEditTransformerData;
import uk.co.nikodem.dFSmpPlus.World.Loot.AttemptEdits.PerformLootEdit;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LootEditAttempt {
    private final LinkedHashMap<LootEditCondition, PerformLootEdit> conditionsToMeet = new LinkedHashMap<>();
    private PerformLootEdit finalEdit = null;

    public LootEditAttempt addCondition(LootEditCondition condition, PerformLootEdit transformer) {
        conditionsToMeet.put(condition, transformer);
        return this;
    }

    public LootEditAttempt addCondition(LootEditCondition condition) {
        conditionsToMeet.put(condition, x -> {});
        return this;
    }

    public LootEditAttempt setFinalEdit(PerformLootEdit edit) {
        this.finalEdit = edit;
        return this;
    }

    public void runAttempt(LootGenerateEvent event) {
        for (Map.Entry<LootEditCondition, PerformLootEdit> entry : conditionsToMeet.entrySet()) {
            List<Integer> checkResult = entry.getKey().runCheck(event);
            if (checkResult == null) return;
            entry.getValue().edit(new LootEditTransformerData(event, checkResult));
        }

        if (finalEdit != null) {
            finalEdit.edit(new LootEditTransformerData(event, List.of()));
        }
    }
}
