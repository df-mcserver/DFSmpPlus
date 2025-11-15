package uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.DefaultWaypointAttributes;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointCreationResult;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;

import java.awt.*;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class DFWaypointCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> createCommand() {
        return Commands.literal("waypoint")
                .then(
                    Commands.literal("locatorbar")
                        .then(Commands.argument("enabled", BoolArgumentType.bool()).executes(ctx -> {
                            Player plr = (Player) ctx.getSource().getExecutor();
                            if (plr == null) return 0;
                            if (BedrockPlayers.isBedrock(plr)) return 0;
                            final Boolean toggle = ctx.getArgument("enabled", Boolean.class);
                            if (toggle == null) return 0;

                            setPlayerLocatorBar(plr, toggle);
                            if (toggle) {
                                plr.sendMessage("Enabled locator bar!");
                            } else {
                                plr.sendMessage("Disabled locator bar!");
                            }

                            return Command.SINGLE_SUCCESS;
                            })
                        )
                ).then(
                        Commands.literal("add")
                                .then(Commands.argument("name", StringArgumentType.string()).executes(ctx -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;
                                            if (BedrockPlayers.isBedrock(plr)) return 0;

                                            final String name = ctx.getArgument("name", String.class);
                                            Random random = new Random();

                                            return doPlaceWaypointCommand(plr, random.nextInt(128)+127, random.nextInt(128)+127, random.nextInt(128)+127, name);
                                        })
                                        .then(Commands.argument("colour", ArgumentTypes.namedColor()).executes(ctx -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;
                                            if (BedrockPlayers.isBedrock(plr)) return 0;

                                            final String name = ctx.getArgument("name", String.class);
                                            final NamedTextColor colour = ctx.getArgument("colour", NamedTextColor.class);

                                            return doPlaceWaypointCommand(plr, colour.red(), colour.green(), colour.blue(), name);
                                        })))
                ).then(
                        Commands.literal("remove")
                                .then(Commands.argument("name", StringArgumentType.string())
                                        .suggests((ctx, builder) -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return builder.buildFuture();
                                            if (BedrockPlayers.isBedrock(plr)) return builder.buildFuture();

                                            for (Map.Entry<String, WaypointInformation> info : DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.entrySet()) {
                                                builder.suggest(info.getKey());
                                            }
                                            return builder.buildFuture();
                                        }).executes(ctx -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;
                                            if (BedrockPlayers.isBedrock(plr)) return 0;

                                            final String name = ctx.getArgument("name", String.class);

                                            Boolean removal = WaypointManager.RemoveWaypoint(plr, name);
                                            if (removal == null) {
                                                plr.sendMessage("Waypoint \""+name+"\" does not exist!");
                                                return Command.SINGLE_SUCCESS;
                                            }

                                            plr.sendMessage("Waypoint \""+name+"\" successfully deleted!");
                                            return Command.SINGLE_SUCCESS;
                                })
                            )
                ).then(
                        Commands.literal("info")
                                .then(Commands.argument("waypoint", StringArgumentType.string())
                                        .suggests((ctx, builder) -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return builder.buildFuture();
                                            if (BedrockPlayers.isBedrock(plr)) return builder.buildFuture();

                                            for (Map.Entry<String, WaypointInformation> info : DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.entrySet()) {
                                                builder.suggest(info.getKey());
                                            }
                                            return builder.buildFuture();
                                        }).executes(ctx -> {
                                            Player plr = (Player) ctx.getSource().getExecutor();
                                            if (plr == null) return 0;
                                            if (BedrockPlayers.isBedrock(plr)) return 0;

                                            final String waypointname = StringArgumentType.getString(ctx, "waypoint");

                                            WaypointInformation info = DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.get(waypointname);
                                            if (info == null) {
                                                plr.sendMessage("Waypoint \""+waypointname+"\" does not exist!");
                                                return Command.SINGLE_SUCCESS;
                                            }

                                            String hex = String.format("#%06X", info.colour);

                                            double blocksAway = plr.getWorld().getName().equals(info.worldName) ? plr.getLocation().distance(new Location(plr.getWorld(), info.x, info.y, info.z)) : -1L;

                                            String distanceString;
                                            if (blocksAway > -1L) distanceString = (int) blocksAway +" blocks away";
                                            else {
                                                World world = Bukkit.getWorld(info.worldName);
                                                if (world == null) distanceString = "Unknown location";
                                                else {
                                                    switch (world.getEnvironment()) {
                                                        case NORMAL -> distanceString = "In the Overworld";
                                                        case NETHER -> distanceString = "In the Nether";
                                                        case THE_END -> distanceString = "In The End";
                                                        default -> distanceString = "Unknown location";
                                                    }
                                                }
                                            }

                                            plr.sendMessage(
                                                    MiniMessage.miniMessage().deserialize(
                                                            String.format("<color:%s>Waypoint %s:</color:%s>\n- X: %g\n- Y: %g\n- Z: %g\n- Distance: %s", hex, waypointname, hex, info.x, info.y, info.z, distanceString)
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
                                    if (BedrockPlayers.isBedrock(plr)) return 0;

                                    PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                                    if (data.waypoints.isEmpty()) {
                                        plr.sendMessage("You have no waypoints set!");
                                        return Command.SINGLE_SUCCESS;
                                    }
                                    plr.sendMessage("All waypoints:");
                                    for (Map.Entry<String, WaypointInformation> info : data.waypoints.entrySet()) {
                                        plr.sendMessage("- "+info.getKey());
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })
                );
    }

    public static int doPlaceWaypointCommand(Player plr, int r, int g, int b, String id) {

        String hex = String.format("#%02X%02X%02X", r, g, b);

        WaypointCreationResult result = WaypointManager.CreateNewWaypoint(plr, plr.getLocation(), id, Long.decode(hex.toLowerCase()));

        switch (result) {
            case SUCCESS -> {
                plr.sendMessage("Successfully created " + id + "!");
                setPlayerLocatorBar(plr, true);
            }
            case FAILED_CREATING_WAYPOINT -> plr.sendMessage("Error creating "+id+"!");
            case FAILED_ALREADY_EXISTS -> plr.sendMessage("Waypoint "+id+" already exists!");
            case FAILED_REACHED_MAXIMUM -> plr.sendMessage("You cannot make more than "+WaypointManager.MAX_WAYPOINTS+" waypoints!");
        }

        return Command.SINGLE_SUCCESS;
    }

    public static void setPlayerLocatorBar(Player plr, boolean bool) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        data.locatorBarEnabled = bool;
        DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
        DefaultWaypointAttributes.updateLocatorBar(plr);
    }
}
