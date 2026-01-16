package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessoryInformationBuilder {
    private final String namedId;
    private double armourPoints = 0f;
    private String accessoryDescription = "Unknown effect";
    private final List<AccessoryMeta> metas = new ArrayList<>();
    private PresetSoundData equipSound = Sounds.DefaultEquipAccessory;

    public AccessoryInformationBuilder(String namedId) {
        this.namedId = namedId;
    }

    public AccessoryInformationBuilder setDescription(String description) {
        this.accessoryDescription = description;
        return this;
    }

    public AccessoryInformationBuilder setArmourPoints(double armourPoints) {
        this.armourPoints = armourPoints;
        return this;
    }

    public AccessoryInformationBuilder addMeta(AccessoryMeta... metas) {
        this.metas.addAll(Arrays.asList(metas));
        return this;
    }

    public AccessoryInformationBuilder setEquipSound(PresetSoundData sound) {
        this.equipSound = sound;
        return this;
    }

    public AccessoryInformation create() {
        return new AccessoryInformation(
                namedId,
                metas,
                armourPoints,
                accessoryDescription,
                equipSound
        );
    }
}
