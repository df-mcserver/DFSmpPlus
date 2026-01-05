package uk.co.nikodem.dFSmpPlus.Data.TypeAdapters;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;

public class LocationAdapter implements JsonSerializer<Location>, JsonDeserializer<Location> {
    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("world", location.getWorld().getUID().toString());
        obj.addProperty("x", location.getX());
        obj.addProperty("y", location.getY());
        obj.addProperty("z", location.getZ());
        obj.addProperty("yaw", location.getYaw());
        obj.addProperty("pitch", location.getPitch());
        return obj;
    }

    @Override
    public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        World world = Bukkit.getWorld(obj.getAsJsonPrimitive("world").getAsString());
        double x = obj.getAsJsonPrimitive("x").getAsDouble();
        double y = obj.getAsJsonPrimitive("y").getAsDouble();
        double z = obj.getAsJsonPrimitive("z").getAsDouble();
        float yaw = obj.getAsJsonPrimitive("yaw").getAsFloat();
        float pitch = obj.getAsJsonPrimitive("pitch").getAsFloat();
        return new Location(world, x, y, z, yaw, pitch);
    }
}
