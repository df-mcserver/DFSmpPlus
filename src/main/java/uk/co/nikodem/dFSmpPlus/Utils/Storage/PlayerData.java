package uk.co.nikodem.dFSmpPlus.Utils.Storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PlayerData extends StorageUtility {

    private final HashMap<Player, Map<String, String>> temporaryData;

    public PlayerData(DFSmpPlus plugin) {
        super(plugin);
        this.temporaryData = new HashMap<>();

        this.initialiseFile();
    }

    @Override
    String getFileName() {
        return "playerdata.yml";
    }

    public void setData(Player plr, String key, String value) {
        FileConfiguration data = getFileData();
        String path = plr.getUniqueId()+".";
        data.set(path+key, value);
    }

    public void setData(Player plr, String key, Integer value) {
        FileConfiguration data = getFileData();
        String path = plr.getUniqueId()+".";
        data.set(path+key, value);
    }

    public void setData(Player plr, String key, Long value) {
        FileConfiguration data = getFileData();
        String path = plr.getUniqueId()+".";
        data.set(path+key, value);
    }

    public void setTemporaryData(Player plr, String key, String value) {
        Map<String, String> data = temporaryData.get(plr);
        if (data == null) {
            data = new HashMap<>();
        }

        data.put(key, value);

        if (temporaryData.containsKey(plr)) {
            temporaryData.replace(plr, data);
        } else {
            temporaryData.put(plr, data);
        }
    }

    public void setTemporaryData(Player plr, String key, Integer value) {
        this.setTemporaryData(plr, key, value.toString());
    }

    public void setTemporaryData(Player plr, String key, Long value) {
        this.setTemporaryData(plr, key, value.toString());
    }

    @Nullable
    public String getString(Player plr, String key) {
        FileConfiguration data = getFileData();
        String path = plr.getUniqueId()+".";
        return data.getString(path+key);
    }

    @Nullable
    public Integer getInt(Player plr, String key) {
        FileConfiguration data = getFileData();
        String path = plr.getUniqueId()+".";
        return data.getInt(path+key);
    }

    @Nullable
    public Long getLong(Player plr, String key) {
        FileConfiguration data = getFileData();
        String path = plr.getUniqueId()+".";
        return data.getLong(path+key);
    }

    @Nullable
    public String getTemporaryString(Player plr, String key) {
        Map<String, String> data = temporaryData.get(plr);
        if (data == null) return null;

        return data.get(key);
    }

    @Nullable
    public Integer getTemporaryInt(Player plr, String key) {
        String data = this.getTemporaryString(plr, key);
        if (data == null) return null;
        return Integer.parseInt(data);
    }

    @Nullable
    public Long getTemporaryLong(Player plr, String key) {
        String data = this.getTemporaryString(plr, key);
        if (data == null) return null;
        return Long.parseLong(data);
    }
}