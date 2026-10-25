package uk.co.nikodem.dFSmpPlus.Events.Player;

import net.kyori.adventure.text.minimessage.MiniMessage;
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
        if (info == null) return;
        info.lastDeathTime = new Date().getTime();
        DFSmpPlus.playerDataHandler.writePlayerData(plr, data);

        if ((!info.diedInCombat) && (!info.invalidLocation)) {
            plr.sendMessage(MiniMessage.miniMessage().deserialize("<i><grey>/back to return to your death location"));
        }
    }
}
