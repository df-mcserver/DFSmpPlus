package uk.co.nikodem.dFSmpPlus.Constants.Chisel;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import static uk.co.nikodem.dFSmpPlus.Constants.Chisel.ChiselBlockData.ChiselBlockDataIndex;

public class ChiselBlockDataBuilder {

    private Enums.ToolLevel minimumToolLevel;
    private final Material block;
    private Material convertInto;
    private ItemStack drop;
    private Float speedMultiplier;
    private PresetSoundData soundData;

    public ChiselBlockDataBuilder(Material block) {
        this.block = block;
    }

    public ChiselBlockDataBuilder setReplacement(Material replacement) {
        this.convertInto = replacement;
        return this;
    }

    public ChiselBlockDataBuilder setDrop(Material drop) {
        this.drop = ItemStack.of(drop);
        return this;
    }

    public ChiselBlockDataBuilder setDrop(Material drop, int amnt) {
        this.drop = ItemStack.of(drop, amnt);
        return this;
    }

    public ChiselBlockDataBuilder setDrop(ItemStack drop) {
        this.drop = drop;
        return this;
    }

    public ChiselBlockDataBuilder setDrop(DFMaterial drop) {
        this.drop = drop.toItemStack();
        return this;
    }

    public ChiselBlockDataBuilder setSpeedMultiplayer(float speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
        return this;
    }

    public ChiselBlockDataBuilder setMinimumTool (Enums.ToolLevel level) {
        this.minimumToolLevel = level;
        return this;
    }

    public ChiselBlockDataBuilder setSoundData(PresetSoundData soundData) {
        this.soundData = soundData;
        return this;
    }

    public ChiselBlockData create() {
        ChiselBlockData data = new ChiselBlockData(
                minimumToolLevel == null ? Enums.ToolLevel.WOODEN : minimumToolLevel,
                block,
                convertInto == null ? Material.AIR : convertInto,
                drop == null ? ItemStack.of(Material.AIR) : drop,
                speedMultiplier == null ? 1.5F : speedMultiplier,
                soundData == null ? Sounds.StoneClank : soundData
        );
        ChiselBlockDataIndex.add(data);
        return data;
    }
}
