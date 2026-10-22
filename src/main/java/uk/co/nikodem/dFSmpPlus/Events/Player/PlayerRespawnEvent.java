package uk.co.nikodem.dFSmpPlus.Events.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.LastDeathInformation;
import uk.co.nikodem.dFSmpPlus.Player.Combat.WorldRecordAdvancementHandler;

import java.util.Date;

public class PlayerRespawnEvent implements Listener {
    @EventHandler
    public void PlayerRespawnEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        WorldRecordAdvancementHandler.onSpawn(event);

        Player plr = event.getPlayer();
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        LastDeathInformation info = data.lastDeathInformation;
        info.lastDeathTime = new Date().getTime();
        DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
    }
}
