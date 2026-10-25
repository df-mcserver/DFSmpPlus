package uk.co.nikodem.dFSmpPlus.Constants;

import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;
import org.intellij.lang.annotations.Subst;

public class Keys {
    public static NamespacedKey markedForUUID = createDefaultKey("markedforuuid");
    public static NamespacedKey UUID = createDefaultKey("uuid");

    public static NamespacedKey dfmaterial = createDefaultKey("dfmaterial");
    public static NamespacedKey dfUpdateId = createDefaultKey("dfupdateid");
    public static NamespacedKey legacy_dfmaterialVersion = createDefaultKey("dfmaterial_version");

    public static NamespacedKey multiconfigOption = createDefaultKey("multichoice");
    public static NamespacedKey cobaltShieldKb = createAccessoryKey("cobalt_shield_kb");
    public static NamespacedKey hermesBootsSpeed = createAccessoryKey("hermes_boots_speed");

    public static NamespacedKey vampireSwordStage = createDefaultKey("vampire_sword_stage");
    public static NamespacedKey vampireSwordWasRepaired = createDefaultKey("vampire_sword_was_repaired");
    public static NamespacedKey lifecrystal = createDefaultKey("lifecrystal");
    public static NamespacedKey emptySlotModel = createModelKey("inventory_part");

    public static NamespacedKey targetDummy = createDefaultKey("target_dummy");
    public static NamespacedKey comicallyLarge = createDefaultKey("comically_large");
    public static NamespacedKey bluebellsarDeath = createDefaultKey("bluebellsar_death");
    public static NamespacedKey bullyBluebellsarStick = createDefaultKey("bully_bluebellsar_stick");
    public static NamespacedKey uranium = createDefaultKey("uranium");
    public static NamespacedKey mouse = createDefaultKey("mouse");
    public static NamespacedKey entityBucketUsed = createDefaultKey("entity_bucket_used");

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

    public static NamespacedKey diamondTintedElytra = createDefaultKey("diamond_tinted_elytra");
    public static NamespacedKey netheriteTintedElytra = createDefaultKey("netherite_tinted_elytra");
    public static NamespacedKey obsidianTintedElytra = createDefaultKey("obsidian_tinted_elytra");

    public static NamespacedKey quickActionsDfsmpDialog = createDefaultKey("quick_actions_dfsmp_dialog");
    public static NamespacedKey quickActionsBackpack = createDefaultKey("quick_actions_dfsmp_dialog/backpack");
    public static NamespacedKey quickActionsAccessories = createDefaultKey("quick_actions_dfsmp_dialog/accessories");

    public static NamespacedKey waypointDialog = createDefaultKey("quick_actions_dfsmp_dialog/waypoints");
    public static NamespacedKey waypointDialogAdd = createDefaultKey("waypoint_dialog/add");
    public static NamespacedKey waypointDialogCompletedAdd = createDefaultKey("waypoint_dialog/completed_add");
    public static NamespacedKey waypointDialogEdit = createDefaultKey("waypoint_dialog/edit/"); // requires waypoint name
    public static NamespacedKey waypointDialogCompletedEdit = createDefaultKey("waypoint_dialog/completed_edit/"); // requires waypoint name
    public static NamespacedKey waypointDialogRemove = createDefaultKey("waypoint_dialog/remove");
    public static NamespacedKey waypointDialogCompletedRemove = createDefaultKey("waypoint_dialog/completed_remove");
    public static NamespacedKey waypointDialogSingleRemove = createDefaultKey("waypoint_dialog/singleremove");
    public static NamespacedKey waypointDialogSingleCompletedRemove = createDefaultKey("waypoint_dialog/completed_singleremove");

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

    public static Key createRegistryKey(@Subst("test") String key) {
        return Key.key(
                "dfjr:"+key
        );
    }

    public static NamespacedKey createAccessoryKey(String key) {
        return new NamespacedKey(
                "dfjraccessory",
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

    public static NamespacedKey appendStringToKeysValue(NamespacedKey key, String append) {
        return new NamespacedKey(
                key.namespace(),
                key.value()+append
        );
    }
}
