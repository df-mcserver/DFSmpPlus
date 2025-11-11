package uk.co.nikodem.dFSmpPlus.Utils.Server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HidingUtils {
    private final Plugin plugin;

    private HashMap<Player, List<Entity>> exclusiveEntities = new HashMap<>();

    public HidingUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    public void HideEntity(Player plr, Entity entity) {
        plr.hideEntity(plugin, entity);
    }

    public void MakeEntityExclusiveToPlayer(Player plr, Entity entity) {
        List<Entity> list = this.exclusiveEntities.get(plr);
        if (list == null) list = new ArrayList<>();
        list.add(entity);
        this.exclusiveEntities.replace(plr, list);

        for (Player other : Bukkit.getOnlinePlayers()) {
            if (!plr.equals(other)) HideEntity(other, entity);
        }
    }

    public boolean isEntityExclusiveToPlayer(Player plr, Entity entity) {
        List<Entity> entities = this.exclusiveEntities.get(plr);
        if (entities == null) return false;
        return entities.contains(entity);
    }

    public void hideAllExclusiveEntitiesOnJoin(Player plr) {
        for (Map.Entry<Player, List<Entity>> entry : exclusiveEntities.entrySet()) {
            if (entry.getKey() != plr) {
                List<Entity> entitiesToHide = entry.getValue();
                for (Entity entity : entitiesToHide) {
                    HideEntity(plr, entity);
                }
            }
        }
    }
}
