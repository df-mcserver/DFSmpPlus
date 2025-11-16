package uk.co.nikodem.dFSmpPlus.Commands.LegacyCommands.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class GiveDFTabCompleter implements TabCompleter {
    private List<String> validOptions = new ArrayList<>();

    public GiveDFTabCompleter() {
        for (DFMaterial material : DFMaterial.DFMaterialIndex) {
            validOptions.add(material.getNamedId());
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (command.getName().equals("givedf")) {
            return validOptions;
        }
        return List.of();
    }
}
