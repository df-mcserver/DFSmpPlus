package uk.co.nikodem.dFSmpPlus.Utils.Sound;

import org.bukkit.Sound;

public class Sounds {
    public static PresetSoundData VeryLoudShovel = new PresetSoundData(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 100F, 1F);
    public static PresetSoundData WoodCrash = new PresetSoundData(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1F, 1F);
    public static PresetSoundData StoneClank = new PresetSoundData(Sound.BLOCK_ANVIL_PLACE, 1F, 1.75F);
    public static PresetSoundData AutoSmelt = new PresetSoundData(Sound.BLOCK_FIRE_EXTINGUISH, 0.5F, 1F);
    public static PresetSoundData UseBucket = new PresetSoundData(Sound.ITEM_BUCKET_FILL, 1F, 1F);
    public static PresetSoundData UseLifeCrystal = new PresetSoundData(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
    public static PresetSoundData Notification = new PresetSoundData(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
    public static PresetSoundData Teleport = new PresetSoundData(Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 1F);
    public static PresetSoundData FailedTeleport = new PresetSoundData(Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.3F);
    public static PresetSoundData BrokenTool = new PresetSoundData(Sound.ENTITY_ITEM_BREAK, 1F, 1F);
    public static PresetSoundData PutModuleIntoCompass = new PresetSoundData(Sound.ITEM_BUNDLE_INSERT, 1F, 1F);
    public static PresetSoundData SculkArmourActivate = new PresetSoundData(Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, 1F, 1F);
    public static PresetSoundData PestleAndMortarFinish = new PresetSoundData(Sound.ITEM_CROP_PLANT, 1F, 1F);

    public static PresetSoundData PlaceTargetDummy = new PresetSoundData(Sound.ENTITY_ARMOR_STAND_PLACE, 1F, 1F);
    public static PresetSoundData PickupTargetDummy = new PresetSoundData(Sound.ENTITY_ARMOR_STAND_BREAK, 1F, 1F);
}
