package uk.co.nikodem.dFSmpPlus.Data.Player;

import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.DataHandler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataHandler extends DataHandler {
    public PlayerDataHandler(DFSmpPlus plugin) {
        super(plugin);
    }

    public PlayerData getPlayerData(UUID playerUUID) {
        File file = getFileInDirectory(playerUUID.toString(), true);
        if (!file.exists()) return new PlayerData();
        PlayerData data = this.gson.fromJson(readFileAsString(file), PlayerData.class);
        if (data == null) data = new PlayerData();
        return data;
    }

    public PlayerData getPlayerData(Player plr) {
        return getPlayerData(plr.getUniqueId());
    }

    public void writePlayerData(UUID playerUUID, PlayerData playerData) {
        File file = getFileInDirectory(playerUUID.toString(), true);
        if (!file.exists()) {
            try {
                createFileInDirectory(playerUUID.toString(), true);
                writeStringToFile(file, gson.toJson(playerData));
            } catch (IOException e) {
                plugin.getLogger().info("Failed to create "+file.getAbsolutePath()+"!");
            }
        } else {
            writeStringToFile(file, gson.toJson(playerData));
        }
    }

    public void writePlayerData(Player plr, PlayerData playerData) {
        writePlayerData(plr.getUniqueId(), playerData);
    }

    @Override
    public String getFolderName() {
        return "players";
    }
}
