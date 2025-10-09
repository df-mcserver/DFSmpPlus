package uk.co.nikodem.dFSmpPlus.Utils.Sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PresetSoundData {
    private final Sound sound;
    private final float volume;
    private final float pitch;

    public PresetSoundData(Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public Sound getSound() {
        return this.sound;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void playSound(Location location) {
        location.getWorld().playSound(location, getSound(), getVolume(), getPitch());
    }

    public void playSound(Location location, float overrideVolume) {
        location.getWorld().playSound(location, getSound(), overrideVolume, getPitch());
    }

    public void playSound(Location location, float overrideVolume, float overridePitch) {
        location.getWorld().playSound(location, getSound(), overrideVolume, overridePitch);
    }

    public void playSound(Entity entity) {
        playSound(entity.getLocation());
    }

    public void playSound(Entity entity, float overrideVolume) {
        playSound(entity.getLocation(), overrideVolume);
    }

    public void playSound(Entity entity, float overrideVolume, float overridePitch) {
        playSound(entity.getLocation(), overrideVolume, overridePitch);
    }

    public void playSound(Block block) {
        playSound(block.getLocation());
    }

    public void playSound(Block block, float overrideVolume) {
        playSound(block.getLocation(), overrideVolume);
    }

    public void playSound(Block block, float overrideVolume, float overridePitch) {
        playSound(block.getLocation(), overrideVolume, overridePitch);
    }

    // locally

    public void playSoundLocally(Player plr, Location location) {
        plr.playSound(location, getSound(), getVolume(), getPitch());
    }

    public void playSoundLocally(Player plr, Location location, float overrideVolume) {
        plr.playSound(location, getSound(), overrideVolume, getPitch());
    }

    public void playSoundLocally(Player plr, Location location, float overrideVolume, float overridePitch) {
        plr.playSound(location, getSound(), overrideVolume, overridePitch);
    }

    public void playSoundLocally(Player plr, Entity entity) {
        playSoundLocally(plr, entity.getLocation());
    }

    public void playSoundLocally(Player plr, Entity entity, float overrideVolume) {
        playSoundLocally(plr, entity.getLocation(), overrideVolume);
    }

    public void playSoundLocally(Player plr, Entity entity, float overrideVolume, float overridePitch) {
        playSoundLocally(plr, entity.getLocation(), overrideVolume, overridePitch);
    }

    public void playSoundLocally(Player plr, Block block) {
        playSoundLocally(plr, block.getLocation());
    }

    public void playSoundLocally(Player plr, Block block, float overrideVolume) {
        playSoundLocally(plr, block.getLocation(), overrideVolume);
    }

    public void playSoundLocally(Player plr, Block block, float overrideVolume, float overridePitch) {
        playSoundLocally(plr, block.getLocation(), overrideVolume, overridePitch);
    }

    public void playSoundLocally(Player plr) {
        playSoundLocally(plr, plr.getLocation());
    }

    public void playSoundLocally(Player plr, float overrideVolume) {
        playSoundLocally(plr, plr.getLocation(), overrideVolume);
    }

    public void playSoundLocally(Player plr, float overrideVolume, float overridePitch) {
        playSoundLocally(plr, plr.getLocation(), overrideVolume, overridePitch);
    }
}
