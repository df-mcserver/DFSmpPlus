package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.NamespacedKey;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;

import java.util.List;

public class AccessoryInformation {
    private final String namedId;
    private final List<AccessoryMeta> metas;
    private final float armourPoints;
    private final List<String> conflictingAccessoryIds;
    private final String accessoryDescription;

    public AccessoryInformation(
            String namedId,
            List<AccessoryMeta> metas,
            float armourPoints,
            List<String> conflictingAccessoryIds,
            String accessoryDescription
    )
    {
        this.namedId = namedId;
        this.metas = metas;
        this.armourPoints = armourPoints;
        this.conflictingAccessoryIds = conflictingAccessoryIds;
        this.accessoryDescription = accessoryDescription;
    }

    public NamespacedKey getNamespacedKey() {
        return Keys.createAccessoryKey(namedId);
    }

    public float getArmourPoints() {
        return this.armourPoints;
    }

    public List<AccessoryMeta> getMeta() {
        return this.metas;
    }

    public List<String> getConflicts() {
        return this.conflictingAccessoryIds;
    }

    public String getDescription() {
        return this.accessoryDescription;
    }

    public boolean hasMeta() {
        return !this.metas.isEmpty();
    }
}
