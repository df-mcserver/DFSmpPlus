package uk.co.nikodem.dFSmpPlus.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DFWaypoints implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player plr) {
            if (args.length < 1) {
                sender.sendMessage(Component.text("usage"));
                return true;
            }
            String cmd = args[0].toLowerCase().replaceAll("\\p{C}", "");
        } else {
            sender.sendMessage(Component.text("You are not a player!"));
        }
        return true;
    }
}
