package uk.co.nikodem.dFSmpPlus.Utils.Storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockData extends StorageUtility{

    public BlockData(DFSmpPlus plugin) {
        super(plugin);

        this.initialiseFile();
    }

    @Override
    String getFileName() {
        return "blockdata.yml";
    }

    public String serialiseLocationToString(Location loc) {
        return loc.getWorld().getName() +
                " " +
                loc.getBlockX() +
                " " +
                loc.getBlockY() +
                " " +
                loc.getBlockZ() +
                " ";
    }

    @Nullable
    public Location deserialiseLocationFromString(String loc) {
        String[] tokens = loc.split(" ");
        int length = tokens.length;
        if (length != 4) return null;

        String worldName = tokens[0];
        double xCoordinate = Double.parseDouble(tokens[1]);
        double yCoordinate = Double.parseDouble(tokens[2]);
        double zCoordinate = Double.parseDouble(tokens[3]);

        if (worldName == null) return null;

        World world = Bukkit.getWorld(worldName);
        if (world == null) return null;

        return new Location(world, xCoordinate, yCoordinate, zCoordinate);
    }

    public void addBlockLocation(Location loc) {
        FileConfiguration data = getFileData();
        List<String> list = data.isSet("path") ? data.getStringList("path") : new ArrayList<>();
        list.add(serialiseLocationToString(loc));
        data.set("path", list);
    }

    public void removeBlockLocation(Location loc) {
        FileConfiguration data = getFileData();
        List<String> list = data.isSet("path") ? data.getStringList("path") : new ArrayList<>();
        list.remove(serialiseLocationToString(loc));
        data.set("path", list);
    }

    @Nullable
    public List<Location> getBlockLocations() {
        FileConfiguration data = getFileData();

        List<String> list = data.isSet("path") ? data.getStringList("path") : new ArrayList<>();
        List<Location> locations = new ArrayList<>();

        for (String string : list) {
            Location location = deserialiseLocationFromString(string);
            if (location != null) locations.add(location);
        }

        return locations;
    }
}
