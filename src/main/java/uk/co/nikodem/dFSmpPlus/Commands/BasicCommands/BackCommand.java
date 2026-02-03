package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.LastDeathInformation;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BackCommand implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (sender instanceof Player plr) {
            PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
            LastDeathInformation info = data.lastDeathInformation;

            if (info == null) {
                denyCommand(sender, "you haven't died recently!");
                return;
            }

            Location deathLocation = info.location;
            Long timeOfDeath = info.lastDeathTime;
            Boolean diedInCombat = info.diedInCombat;

            if (deathLocation == null || timeOfDeath == null || diedInCombat == null) {
                denyCommand(sender, "you haven't died recently!");
                return;
            }

            long diff = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-timeOfDeath);

            if (timeOfDeath < (System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5))) {
                denyCommand(sender, "you died "+diff+" "+(diff == 1 ? "minute" : "minutes")+" ago!");
                return;
            }

            if (diedInCombat) {
                denyCommand(sender, "you died in combat!");
                return;
            }

            if (CombatLoggingManager.isInCombat(plr)) {
                denyCommand(sender, "you are in combat!");
                return;
            }

            plr.teleport(deathLocation);
            data.lastDeathInformation = null;
            DFSmpPlus.playerDataHandler.writePlayerData(plr, data);

            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Successfully teleported back!"));
        } else {
            denyCommand(sender, "you are not a player!");
        }
    }

    private void denyCommand(CommandSender sender, String reason) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You cannot go back because "+reason));
    }


    @Override
    public String getLabel() {
        return "back";
    }

    @Override
    public String getDescription() {
        return "Return back to your death, if you meet the requirements";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of();
    }
}
