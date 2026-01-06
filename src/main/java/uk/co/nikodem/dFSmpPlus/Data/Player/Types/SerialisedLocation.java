package uk.co.nikodem.dFSmpPlus.Data.Player.Types;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class SerialisedLocation {
    public Double x = null;
    public Double y = null;
    public Double z = null;
    public Float yaw = null;
    public Float pitch = null;
    public String worldId = null;

    public Location getLocation() {
        return new Location(Bukkit.getWorld(UUID.fromString(worldId)), x, y, z, yaw, pitch);
    }

    public SerialisedLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
        this.worldId = location.getWorld().getUID().toString();
    }
}
