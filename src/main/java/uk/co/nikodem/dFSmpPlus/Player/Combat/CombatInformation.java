package uk.co.nikodem.dFSmpPlus.Player.Combat;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.UUID;

public class CombatInformation {
    private UUID lastAttackerUUID = null;
    private Integer startTick = null;
    private Integer kills = null;
    private BossBar bar = null;

    @Nullable
    public Player getLastAttackerPlayer() {
        UUID uuid = getLastAttackerUUID();
        if (uuid == null) return null;

        return Bukkit.getOnlinePlayers().stream().filter(player -> player.getUniqueId().equals(uuid)).findFirst().orElse(null);
    }

    @Nullable
    public UUID getLastAttackerUUID() {
        return lastAttackerUUID;
    }

    @Nullable
    public Integer getStartTick() {
        return startTick;
    }

    @Nullable
    public Integer getKills() {
        return kills;
    }

    @Nullable
    public BossBar getBar() {
        return bar;
    }

    public void setLastAttacker(UUID attacker) {
        this.lastAttackerUUID = attacker;
    }

    public void setLastAttacker(Player attacker) {
        this.lastAttackerUUID = attacker.getUniqueId();
    }

    public void setStartTick(Integer startTick) {
        this.startTick = startTick;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public void setBar(BossBar bar) {
        this.bar = bar;
    }

    public void reset() {
        this.lastAttackerUUID = null;
        this.startTick = null;
        this.bar = null;
        this.kills = null;
    }
}
