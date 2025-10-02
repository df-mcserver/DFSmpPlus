package uk.co.nikodem.dFSmpPlus.Utils.Storage;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.io.File;
import java.io.IOException;

public abstract class StorageUtility {
    private final DFSmpPlus plugin;
    private File file;
    private FileConfiguration fileData;

    abstract String getFileName();

    public StorageUtility(DFSmpPlus plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration getFileData() {
        return this.fileData;
    }

    public void saveData() {
        try {
            fileData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialiseFile() {
        file = new File(plugin.getDataFolder(), getFileName());
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(getFileName(), false);
        }

        fileData = new YamlConfiguration();
        try {
            fileData.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
