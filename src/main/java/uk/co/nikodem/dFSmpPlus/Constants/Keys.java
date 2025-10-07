package uk.co.nikodem.dFSmpPlus.Constants;

import org.bukkit.NamespacedKey;

public class Keys {
    public static NamespacedKey markedForUUID = createDefaultKey("markedforuuid");
    public static NamespacedKey UUID = createDefaultKey("uuid");
    public static NamespacedKey dfmaterial = createDefaultKey("dfmaterial");
    public static NamespacedKey dfmaterialVersion = createDefaultKey("dfmaterial_version");

    public static NamespacedKey vampireSwordStage = createDefaultKey("vampire_sword_stage");
    public static NamespacedKey vampireSwordDamage = createDefaultKey("vampire_sword_damage");
    public static NamespacedKey lifecrystal = createDefaultKey("lifecrystal");

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
