package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.NamespacedKey;

public class Keys {
    public static NamespacedKey markedForUUID = createDefaultKey("markedforuuid");
    public static NamespacedKey UUID = createDefaultKey("uuid");
    public static NamespacedKey dfmaterial = createDefaultKey("dfmaterial");
    public static NamespacedKey dfmaterialVersion = createDefaultKey("dfmaterial_version");

    public static NamespacedKey vampireSwordStage = createDefaultKey("vampire_sword_stage");
    public static NamespacedKey lifecrystal = createDefaultKey("lifecrystal");

    public static NamespacedKey targetDummy = createDefaultKey("target_dummy");
    public static NamespacedKey comicallyLarge = createDefaultKey("comically_large");
    public static NamespacedKey bluebellsarDeath = createDefaultKey("bluebellsar_death");
    public static NamespacedKey bullyBluebellsarStick = createDefaultKey("bully_bluebellsar_stick");

    public static NamespacedKey calciteHelmet = createDefaultKey("calcite_helmet");
    public static NamespacedKey calciteChestplate = createDefaultKey("calcite_chestplate");
    public static NamespacedKey calciteLeggings = createDefaultKey("calcite_leggings");
    public static NamespacedKey calciteBoots = createDefaultKey("calcite_boots");

    public static NamespacedKey obsidianHelmet = createDefaultKey("obsidian_helmet");
    public static NamespacedKey obsidianChestplate = createDefaultKey("obsidian_chestplate");
    public static NamespacedKey obsidianLeggings = createDefaultKey("obsidian_leggings");
    public static NamespacedKey obsidianBoots = createDefaultKey("obsidian_boots");

    public static NamespacedKey floralHelmet = createDefaultKey("floral_helmet");
    public static NamespacedKey floralChestplate = createDefaultKey("floral_chestplate");
    public static NamespacedKey floralLeggings = createDefaultKey("floral_leggings");
    public static NamespacedKey floralBoots = createDefaultKey("floral_boots");

    public static NamespacedKey createDefaultKey(String key) {
        return new NamespacedKey(
                "dfsmpplus",
                key
        );
    }

    public static NamespacedKey createCraftingKey(String key) {
        return createDefaultKey(key);
    }

    public static NamespacedKey createResourceKey(String key) {
        return new NamespacedKey(
                "dfjr",
                key
        );
    }

    public static NamespacedKey createModelKey(String key) {
        return createResourceKey(key); // i thought this would be different, but im keeping it cuz im too lazy to revert that one line of code
    }

    public static NamespacedKey createMinecraftKey(String key) {
        return new NamespacedKey(
                "minecraft",
                key
        );
    }
}
