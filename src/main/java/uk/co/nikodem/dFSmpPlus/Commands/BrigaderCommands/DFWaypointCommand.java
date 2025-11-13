package uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.Nullable;

public class DFWaypointCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {

    }

    @Override
    public @Nullable String permission() {
        return "dfsmpplus.waypoint.command";
    }
}
