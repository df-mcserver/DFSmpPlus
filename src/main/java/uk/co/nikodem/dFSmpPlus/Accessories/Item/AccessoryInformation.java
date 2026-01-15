package uk.co.nikodem.dFSmpPlus.Accessories.Item;

import org.bukkit.NamespacedKey;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;

import java.util.List;

public class AccessoryInformation {
    private final String namedId;
    private final List<AccessoryMeta> metas;
    private final double armourPoints;
    private final String accessoryDescription;
    private final PresetSoundData equipSound;

    public AccessoryInformation(
            String namedId,
            List<AccessoryMeta> metas,
            double armourPoints,
            String accessoryDescription,
            PresetSoundData equipSound
    )
    {
        this.namedId = namedId;
        this.metas = metas;
        this.armourPoints = armourPoints;
        this.accessoryDescription = accessoryDescription;
        this.equipSound = equipSound;
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

    public PresetSoundData getEquipSound() {
        return this.equipSound;
    }
}
