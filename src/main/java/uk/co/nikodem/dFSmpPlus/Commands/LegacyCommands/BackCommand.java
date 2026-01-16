package uk.co.nikodem.dFSmpPlus.Commands.LegacyCommands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.LastDeathInformation;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager;

import java.util.concurrent.TimeUnit;

public class BackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player plr) {
            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
            LastDeathInformation info = data.lastDeathInformation;

            if (info == null) {
                return denyCommand(sender, "you haven't died recently!");
            }

            Location deathLocation = info.location;
            Long timeOfDeath = info.lastDeathTime;
            Boolean diedInCombat = info.diedInCombat;

            if (deathLocation == null || timeOfDeath == null || diedInCombat == null) {
                return denyCommand(sender, "you haven't died recently!");
            }

            long diff = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-timeOfDeath);

            if (timeOfDeath < (System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5))) {
                return denyCommand(sender, "you died "+diff+" "+(diff == 1 ? "minute" : "minutes")+" ago!");
            }

            if (diedInCombat) {
                return denyCommand(sender, "you died in combat!");
            }

            if (CombatLoggingManager.isInCombat(plr)) {
                return denyCommand(sender, "you are in combat!");
            }

            plr.teleport(deathLocation);
            data.lastDeathInformation = null;
            DFSmpPlus.playerDataHandler.writePlayerData(plr, data);

            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Successfully teleported back!"));
        } else {
            return denyCommand(sender, "you are not a player!");
        }
        return true;
    }

    private boolean denyCommand(CommandSender sender, String reason) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You cannot go back because "+reason));
        return false;
    }
}
