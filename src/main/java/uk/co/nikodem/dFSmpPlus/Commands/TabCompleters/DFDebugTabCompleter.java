package uk.co.nikodem.dFSmpPlus.Commands.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DFDebugTabCompleter implements TabCompleter {
    private final List<String> subcommands = List.of(
            "damage"
    );

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        String cmd = args[0].toLowerCase().replaceAll("\\p{C}", "");

        if (!subcommands.contains(cmd)) return subcommands;
        else return List.of();
    }
}
