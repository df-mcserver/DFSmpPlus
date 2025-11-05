package uk.co.nikodem.dFSmpPlus.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.io.*;
import java.nio.file.Path;

public abstract class DataHandler {
    public abstract String getFolderName();

    public final Gson gson = new GsonBuilder().create();
    public final DFSmpPlus plugin;

    public DataHandler(DFSmpPlus plugin) {
        this.plugin = plugin;
    }

    @Nullable
    public String readFileAsString(File file) {
        if (!file.exists()) {
            plugin.getLogger().info("Cannot read file "+file.getAbsolutePath()+"! File does not exist!");
            return null;
        }

        if (!file.canRead()) {
            plugin.getLogger().info("Cannot read file "+file.getAbsolutePath()+"! Check permissions!");
            return null;
        }

        try (FileReader fileReader = new FileReader(file)) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                return bufferedReader.readLine(); // data should only be on the first line
            } catch (IOException e) {
                plugin.getLogger().info("Failed to parse file "+file.getAbsolutePath()+"!");
            }
        } catch (IOException e) {
            plugin.getLogger().info("Failed to read file "+file.getAbsolutePath()+"!");
        }

        return null;
    }

    public void writeStringToFile(File file, String content) {
        if (!file.exists()) {
            plugin.getLogger().info("Cannot write to file "+file.getAbsolutePath()+"! File does not exist!");
            return;
        }

        if (!file.canRead()) {
            plugin.getLogger().info("Cannot write to file "+file.getAbsolutePath()+"! Check permissions!");
            return;
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(content);
                bufferedWriter.flush();
            } catch (IOException e) {
                plugin.getLogger().info("Failed to write to file "+file.getAbsolutePath()+"!");
            }
        } catch (IOException e) {
            plugin.getLogger().info("Failed to read file "+file.getAbsolutePath()+"!");
        }
    }

    public boolean createFileInDirectory(String fileName, boolean addJsonEnding) throws IOException {
        File file = getFileInDirectory(fileName, addJsonEnding);
        ensureAllocatedDirectoryExists();
        return file.createNewFile();
    }

    public File getFileInDirectory(String fileName, boolean addJsonEnding) {
        return Path.of(getAllocatedDirectory().toURI().getPath(), "/"+fileName+(addJsonEnding ? ".json" : "")).toFile();
    }

    public File getFileInDirectory(String fileName) {
        return getFileInDirectory(fileName, false);
    }

    public boolean ensureAllocatedDirectoryExists() {
        return getAllocatedDirectory().mkdirs();
    }

    public File getAllocatedDirectory() {
        return Path.of(plugin.getDataPath().toUri().getPath(), "/"+getFolderName()).toFile();
    }
}
