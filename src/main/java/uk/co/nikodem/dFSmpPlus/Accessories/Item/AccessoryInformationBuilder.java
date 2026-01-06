package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessoryInformationBuilder {
    private final String namedId;
    private float armourPoints;
    private final List<AccessoryMeta> metas = new ArrayList<>();
    private final List<String> conflictingAccessoryIds = new ArrayList<>();

    public AccessoryInformationBuilder(String namedId) {
        this.namedId = namedId;
    }

    public AccessoryInformationBuilder setArmourPoints(float armourPoints) {
        this.armourPoints = armourPoints;
        return this;
    }

    public AccessoryInformationBuilder addMeta(AccessoryMeta... metas) {
        this.metas.addAll(Arrays.asList(metas));
        return this;
    }

    public AccessoryInformationBuilder addConflictingAccessory(String... namedIds) {
        this.conflictingAccessoryIds.addAll(Arrays.asList(namedIds));
        return this;
    }

    public AccessoryInformation create() {
        return new AccessoryInformation(
                namedId,
                metas,
                armourPoints,
                conflictingAccessoryIds
        );
    }
}
