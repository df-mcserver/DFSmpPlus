package uk.co.nikodem.dFSmpPlus.Accessories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.PlayerAccessoryData;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// I would do everything via PlayerData, but it could cause disk thrashing & performance issues if we're reading from it every second
public class AccessoryManager {
    public static Map<UUID, PlayerAccessoryData> playerAccessoryDataCache = new HashMap<>();

    public static PlayerAccessoryData getPlayerAccessoryData(Player plr) {
        PlayerAccessoryData playerAccessoryData = playerAccessoryDataCache.get(plr.getUniqueId());
        if (playerAccessoryData == null) {
            PlayerData playerData = DFSmpPlus.playerDataHandler.getPlayerData(plr);
            PlayerAccessoryData accessoryData = playerData.playerAccessoryData;
            if (accessoryData == null) accessoryData = new PlayerAccessoryData();
            playerAccessoryDataCache.put(plr.getUniqueId(), accessoryData);
            return accessoryData;
        } else return playerAccessoryData;
    }

    public static void onJoin(Player plr) {
        // load accessory data from player data to cache
//        PlayerData playerData = DFSmpPlus.playerDataHandler.getPlayerData(plr);
//        PlayerAccessoryData accessoryData = playerData.playerAccessoryData;
//        if (accessoryData == null) accessoryData = new PlayerAccessoryData();
//
//        for (ItemStack item : accessoryData.slots) {
//            if (item == null) continue;
//            plr.sendMessage(item.displayName());
//        }
//
//        if (playerAccessoryDataCache.containsKey(plr.getUniqueId())) playerAccessoryDataCache.replace(plr.getUniqueId(), accessoryData);
//        else playerAccessoryDataCache.put(plr.getUniqueId(), accessoryData);
    }

    public static void updatePlayerData(Player plr, PlayerAccessoryData accessoryData) {
        if (playerAccessoryDataCache.containsKey(plr.getUniqueId())) playerAccessoryDataCache.replace(plr.getUniqueId(), accessoryData);
        else playerAccessoryDataCache.put(plr.getUniqueId(), accessoryData);

        PlayerData playerData = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        playerData.playerAccessoryData = accessoryData;

        DFSmpPlus.playerDataHandler.writePlayerData(plr, playerData);
    }

    public static void updatePlayerDataWithCache(Player plr) {
        PlayerAccessoryData accessoryData = playerAccessoryDataCache.get(plr.getUniqueId());
        if (accessoryData == null) return;

        updatePlayerData(plr, accessoryData);
    }

    public static void onLeave(Player plr) {
        // move accessory data from cache to player data
        PlayerAccessoryData accessoryData = playerAccessoryDataCache.get(plr.getUniqueId());
        if (accessoryData == null) return;

        PlayerData playerData = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        playerData.playerAccessoryData = accessoryData;
        DFSmpPlus.playerDataHandler.writePlayerData(plr, playerData);

        playerAccessoryDataCache.remove(plr.getUniqueId());
    }
}
