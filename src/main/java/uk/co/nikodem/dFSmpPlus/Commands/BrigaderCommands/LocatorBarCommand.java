package uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Commands.DFCommand;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;

import java.util.Collection;
import java.util.List;

import static uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.DFWaypointCommand.getPlayerLocatorBar;
import static uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.DFWaypointCommand.setPlayerLocatorBar;

public class LocatorBarCommand implements DFCommand {
    @Override
    public LiteralArgumentBuilder<CommandSourceStack> createCommand() {
        return Commands.literal("locatorbar")
                .then(Commands.argument("enabled", BoolArgumentType.bool()).executes(ctx -> {
                            Player plr = (Player) ctx.getSource().getExecutor();
                            if (plr == null) return 0;
                            if (Boolean.TRUE.equals(BedrockPlayers.isBedrock(plr))) return 0;
                            final Boolean toggle = ctx.getArgument("enabled", Boolean.class);
                            if (toggle == null) return 0;

                            setPlayerLocatorBar(plr, toggle);
                            if (toggle) {
                                plr.sendMessage(MiniMessage.miniMessage().deserialize("<green>Enabled <reset>locator bar!"));
                            } else {
                                plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Disabled <reset>locator bar!"));
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .executes(ctx -> {
                    Player plr = (Player) ctx.getSource().getExecutor();
                    if (plr == null) return 0;
                    if (Boolean.TRUE.equals(BedrockPlayers.isBedrock(plr))) return 0;

                    boolean res = getPlayerLocatorBar(plr);

                    plr.sendMessage(MiniMessage.miniMessage().deserialize("Your locator bar is currently "+(res ? "<green>enabled" : "<red>disabled")));

                    return Command.SINGLE_SUCCESS;
                });
    }

    @Override
    public String getDescription() {
        return "Enable/disable your locator bar. More advanced waypoint management can be done with the /waypoint command.";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of("locator", "bar", "lb");
    }
}
