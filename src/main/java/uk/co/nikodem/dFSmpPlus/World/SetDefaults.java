package uk.co.nikodem.dFSmpPlus.World;

import org.bukkit.Bukkit;
import org.bukkit.entity.SpawnCategory;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

public class SetDefaults {
    public static void checkRecommendedSettings() {
        DFSmpPlus.getPlugin().getLogger().info("Checking recommended config values!");
        if (Bukkit.getServer().getViewDistance() != 16) DFSmpPlus.getPlugin().getLogger().info("View distance is not set to the recommended value of 16!");
        if (Bukkit.getServer().getSpawnLimit(SpawnCategory.ANIMAL) != 25)  DFSmpPlus.getPlugin().getLogger().info("Animal mob cap is not set to the recommended value of 25!");
        if (Bukkit.getServer().getSpawnLimit(SpawnCategory.MONSTER) != 70)  DFSmpPlus.getPlugin().getLogger().info("Monster mob cap is not set to the recommended value of 70!");

        // can't check spawn-protection lol
        DFSmpPlus.getPlugin().getLogger().info("Cannot check spawn-protection, please ensure it is set to 0!");
    }
}
