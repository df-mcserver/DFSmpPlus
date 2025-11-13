package uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointCreationResult;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;

import java.awt.*;
import java.util.Map;
import java.util.Random;

public class DFWaypointCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> createCommand() {
        return Commands.literal("waypoint")
                .then(
                    Commands.literal("locatorbar")
                        .then(Commands.argument("toggle", BoolArgumentType.bool()).executes(ctx -> {
                                ctx.getSource().getSender().sendMessage(String.valueOf(BoolArgumentType.getBool(ctx, "toggle")));

                                return Command.SINGLE_SUCCESS;
                            })
                        )
                ).then(
                        Commands.literal("add")
                                .then(Commands.argument("name", StringArgumentType.string()).executes(ctx -> {
                                            final String name = ctx.getArgument("name", String.class);
                                            Random random = new Random();
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;

                                            return doPlaceWaypointCommand(plr, random.nextInt(128)+127, random.nextInt(128)+127, random.nextInt(128)+127, name);
                                        })
                                        .then(Commands.argument("colour", ArgumentTypes.namedColor()).executes(ctx -> {
                                            final String name = ctx.getArgument("name", String.class);
                                            final NamedTextColor colour = ctx.getArgument("colour", NamedTextColor.class);
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;

                                            return doPlaceWaypointCommand(plr, colour.red(), colour.green(), colour.blue(), name);
                                        })))
                ).then(
                        Commands.literal("remove")
                                .then(Commands.argument("name", StringArgumentType.string())
                                        .suggests((ctx, builder) -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return builder.buildFuture();
                                            for (Map.Entry<String, WaypointInformation> info : DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.entrySet()) {
                                                builder.suggest(info.getKey());
                                            }
                                            return builder.buildFuture();
                                        }).executes(ctx -> {
                                            final String name = ctx.getArgument("name", String.class);
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;

                                            return Command.SINGLE_SUCCESS;
                                })
                            )
                ).then(
                        Commands.literal("info")
                                .then(Commands.argument("waypoint", StringArgumentType.string())
                                        .suggests((ctx, builder) -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return builder.buildFuture();
                                            for (Map.Entry<String, WaypointInformation> info : DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.entrySet()) {
                                                builder.suggest(info.getKey());
                                            }
                                            return builder.buildFuture();
                                        }).executes(ctx -> {
                                            final String waypointname = StringArgumentType.getString(ctx, "waypoint");

                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;

                                            WaypointInformation info = DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.get(waypointname);
                                            if (info == null) {
                                                plr.sendMessage("Waypoint \""+waypointname+"\" does not exist!");
                                                return Command.SINGLE_SUCCESS;
                                            }

                                            String hex = String.format("#%06X", info.colour);

                                            plr.sendMessage(
                                                    MiniMessage.miniMessage().deserialize(
                                                            String.format("<color:%s>Waypoint %s:</color:%s>\n- X: %g\n- Y: %g\n- Z: %g", hex, waypointname, hex, info.x, info.y, info.z)
                                                    )
                                            );
                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                ).then(
                        Commands.literal("list")
                                .executes(ctx -> {
                                    Player plr = (Player) ctx.getSource().getExecutor();
                                    if (plr == null) return 0;
                                    for (Map.Entry<String, WaypointInformation> info : DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.entrySet()) {
                                        plr.sendMessage(info.getKey());
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })
                );
    }

    public static int doPlaceWaypointCommand(Player plr, int r, int g, int b, String id) {

        String hex = String.format("#%02X%02X%02X", r, g, b);

        WaypointCreationResult result = WaypointManager.CreateNewWaypoint(plr, plr.getLocation(), id, Long.decode(hex.toLowerCase()));

        switch (result) {
            case SUCCESS -> plr.sendMessage("Successfully created "+id+"!");
            case FAILED_CREATING_WAYPOINT -> plr.sendMessage("Error creating "+id+"!");
            case FAILED_ALREADY_EXISTS -> plr.sendMessage("Waypoint "+id+" already exists!");
            case FAILED_REACHED_MAXIMUM -> plr.sendMessage("You cannot make more than "+WaypointManager.MAX_WAYPOINTS+" waypoints!");
        }

        return Command.SINGLE_SUCCESS;
    }
}
