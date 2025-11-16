package uk.co.nikodem.dFSmpPlus.Player.Combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.WorldRecord;

import java.util.HashMap;
import java.util.UUID;

public class WorldRecordAdvancementHandler {

    public static HashMap<UUID, Integer> spawnTick = new HashMap<>();

    public static void onDeath(PlayerDeathEvent event) {
        Player plr = event.getEntity();
        Integer tick = spawnTick.get(plr.getUniqueId());
        if (tick == null) return;

        if (Bukkit.getCurrentTick() - tick <= 100) {
            DFAdvancementsHandler.grantAdvancement(plr, WorldRecord.class);
        }
    }

    public static void onSpawn(PlayerRespawnEvent event) {
        Player plr = event.getPlayer();
        if (spawnTick.containsKey(plr.getUniqueId())) spawnTick.replace(plr.getUniqueId(), Bukkit.getCurrentTick());
        else spawnTick.put(plr.getUniqueId(), Bukkit.getCurrentTick());
    }

    public static void onJoin(PlayerJoinEvent event) {
        Player plr = event.getPlayer();
        if (spawnTick.containsKey(plr.getUniqueId())) spawnTick.replace(plr.getUniqueId(), Bukkit.getCurrentTick());
        else spawnTick.put(plr.getUniqueId(), Bukkit.getCurrentTick());
    }
}
