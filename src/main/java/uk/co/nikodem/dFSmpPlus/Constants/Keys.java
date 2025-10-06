package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.NamespacedKey;

public class Keys {
    public static NamespacedKey markedForUUID = createDefaultKey("markedforuuid");
    public static NamespacedKey UUID = createDefaultKey("uuid");
    public static NamespacedKey dfmaterial = createDefaultKey("dfmaterial");
    public static NamespacedKey dfmaterialVersion = createDefaultKey("dfmaterial_version");
    public static NamespacedKey dfblock = createDefaultKey("dfblock");
    public static NamespacedKey fishPlayer = createDefaultKey("fish");
    public static NamespacedKey bluebellsarStick = createDefaultKey("bluebellsar_stick");

    public static NamespacedKey copperEquippable = createResourceKey("copper");
    public static NamespacedKey firidiumEquippable = createResourceKey("firidium");
    public static NamespacedKey obsidianEquippable = createResourceKey("obsidian");

    public static NamespacedKey lifefruit = createDefaultKey("lifefruit");

    public static NamespacedKey isDFBlockEntity = createDefaultKey("dfblockentity");

    public static NamespacedKey genericAttributeModifier = createDefaultKey("genericattribute");

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
}
