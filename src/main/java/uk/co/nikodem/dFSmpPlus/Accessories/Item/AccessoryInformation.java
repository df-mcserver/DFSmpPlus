package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.NamespacedKey;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessoryInformation {
    private final String namedId;
    private final List<AccessoryMeta> metas;
    private final double armourPoints;
    private final String accessoryDescription;
    private final PresetSoundData equipSound;
    private final List<String> conflicts;

    public AccessoryInformation(
            String namedId,
            List<AccessoryMeta> metas,
            double armourPoints,
            String accessoryDescription,
            PresetSoundData equipSound,
            List<String> conflicts
    )
    {
        this.namedId = namedId;
        this.metas = metas;
        this.armourPoints = armourPoints;
        this.accessoryDescription = accessoryDescription;
        this.equipSound = equipSound;
        this.conflicts = conflicts;
    }

    public NamespacedKey getNamespacedKey() {
        return Keys.createAccessoryKey(namedId);
    }

    public double getArmourPoints() {
        return this.armourPoints;
    }

    public List<AccessoryMeta> getMeta() {
        return this.metas;
    }

    public String getDescription() {
        return this.accessoryDescription;
    }

    public boolean hasMeta() {
        return !this.metas.isEmpty();
    }

    public List<String> getConflicts() {
        return this.conflicts;
    }

    public PresetSoundData getEquipSound() {
        return this.equipSound;
    }

    public static class Builder {
        private final String namedId;
        private double armourPoints = 0f;
        private String accessoryDescription = "Unknown effect";
        private final List<AccessoryMeta> metas = new ArrayList<>();
        private PresetSoundData equipSound = Sounds.DefaultEquipAccessory;
        private final List<String> conflicts = new ArrayList<>();

        public Builder(String namedId) {
            this.namedId = namedId;
        }

        public Builder setDescription(String description) {
            this.accessoryDescription = description;
            return this;
        }

        public Builder setArmourPoints(double armourPoints) {
            this.armourPoints = armourPoints;
            return this;
        }

        public Builder addMeta(AccessoryMeta... metas) {
            this.metas.addAll(Arrays.asList(metas));
            return this;
        }

        public Builder setEquipSound(PresetSoundData sound) {
            this.equipSound = sound;
            return this;
        }

        public Builder addConflicts(String... accessoryIds) {
            this.conflicts.addAll(Arrays.asList(accessoryIds));
            return this;
        }

        public AccessoryInformation create() {
            return new AccessoryInformation(
                    namedId,
                    metas,
                    armourPoints,
                    accessoryDescription,
                    equipSound,
                    conflicts
            );
        }
    }
}
