package uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.BlockPositionResolver;
import io.papermc.paper.math.BlockPosition;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Commands.DFCommand;
import uk.co.nikodem.dFSmpPlus.Constants.WaypointConstants;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.DefaultWaypointAttributes;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointCreationResult;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointDialog;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Map.entry;

public class DFWaypointCommand implements DFCommand {
    public LiteralArgumentBuilder<CommandSourceStack> createCommand() {
        return Commands.literal("waypoint")
                .then(addSubcommand)
                .then(removeSubcommand)
                .then(toggleSubcommand)
                .then(editSubcommand)
                .then(locatorbarSubcommand)
                .then(infoSubcommand)
                .then(listSubcommand)
                .then(menuSubcommand)
                .executes(ctx -> {
                    Player plr = (Player) ctx.getSource().getExecutor();
                    if (plr == null) return 0;

                    plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Incomplete command! Use /waypoint menu to open the waypoint menu!"));
                    return Command.SINGLE_SUCCESS;
                });
    }

    public LiteralArgumentBuilder<CommandSourceStack> addSubcommand = Commands.literal("add")
            .then(Commands.argument("waypoint_name", StringArgumentType.string()).executes(ctx -> {
                Player plr = (Player) ctx.getSource().getExecutor();
                if (plr == null) return 0;

                final String name = ctx.getArgument("waypoint_name", String.class);

                int randomIndex = new Random().nextInt(0, colourToHex.size()-1);

                return doPlaceWaypointCommand(plr, Long.decode((String) colourToHex.values().toArray()[randomIndex]), name);
            })
            .then(Commands.argument("colour", StringArgumentType.string())
                    .suggests(getColoursSuggestion()).executes(ctx -> {
                        Player plr = (Player) ctx.getSource().getExecutor();
                        if (plr == null) return 0;

                        final String name = ctx.getArgument("waypoint_name", String.class);
                        final Long colour = getColourFromArguments(ctx);

                        if (colour != null) {
                            doPlaceWaypointCommand(plr, colour, name);
                            return Command.SINGLE_SUCCESS;
                        } else {
                            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Invalid colour!"));
                            return 0;
                        }
                    })
                    .then(Commands.argument("position", ArgumentTypes.blockPosition())
                            .executes(ctx -> {
                                Player plr = (Player) ctx.getSource().getExecutor();
                                if (plr == null) return 0;

                                final String name = ctx.getArgument("waypoint_name", String.class);
                                final Long colour = getColourFromArguments(ctx);
                                final BlockPositionResolver blockPositionResolver = ctx.getArgument("position", BlockPositionResolver.class);

                                if (colour != null) {
                                    doPlaceWaypointCommand(plr, colour, name, blockPositionResolver.resolve(ctx.getSource()).toLocation(plr.getWorld()).toCenterLocation());
                                    return Command.SINGLE_SUCCESS;
                                } else {
                                    plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Invalid colour!"));
                                    return 0;
                                }
                            })))
            );

    public LiteralArgumentBuilder<CommandSourceStack> removeSubcommand = Commands.literal("remove")
            .then(Commands.argument("waypoint_name", StringArgumentType.string())
                            .suggests(getWaypointNamesSuggestion()).executes(ctx -> {
                                Player plr = (Player) ctx.getSource().getExecutor();
                                if (plr == null) return 0;

                                final String name = ctx.getArgument("waypoint_name", String.class);

                                Boolean removal = WaypointManager.RemoveWaypoint(plr, name);
                                if (removal == null) {
                                    plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Waypoint \""+name+"\" does not exist!"));
                                    return 0;
                                }

                                plr.sendMessage(MiniMessage.miniMessage().deserialize("Waypoint \""+name+"\" successfully <red>deleted</red>!"));
                                return Command.SINGLE_SUCCESS;
                            })
            );
    public LiteralArgumentBuilder<CommandSourceStack> toggleSubcommand = Commands.literal("toggle")
            .then(Commands.argument("waypoint_name", StringArgumentType.string())
                    .suggests(getWaypointNamesSuggestion()).executes(ctx -> {
                        Player plr = (Player) ctx.getSource().getExecutor();
                        if (plr == null) return 0;

                        final String name = ctx.getArgument("waypoint_name", String.class);

                        Boolean state_change = WaypointManager.ToggleWaypoint(plr, name, null);
                        if (state_change == null) {
                            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Waypoint \""+name+"\" does not exist!"));
                            return 0;
                        }

                        if (!state_change) plr.sendMessage(MiniMessage.miniMessage().deserialize("Waypoint \""+name+"\" is now <red>disabled</red>!"));
                        else plr.sendMessage(MiniMessage.miniMessage().deserialize("Waypoint \""+name+"\" is now <green>enabled</green>!"));
                        return Command.SINGLE_SUCCESS;
                    })
                    .then(Commands.argument("state", BoolArgumentType.bool()) .executes(ctx -> {
                        Player plr = (Player) ctx.getSource().getExecutor();
                        if (plr == null) return 0;

                        final String name = ctx.getArgument("waypoint_name", String.class);
                        final Boolean state = ctx.getArgument("state", Boolean.class);

                        if (state == null) {
                            plr.sendMessage("<red>Invalid state!");
                        }

                        Boolean state_change = WaypointManager.ToggleWaypoint(plr, name, state);
                        if (state_change == null) {
                            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Waypoint \""+name+"\" does not exist!"));
                            return 0;
                        }

                        if (!state_change) plr.sendMessage(MiniMessage.miniMessage().deserialize("Waypoint \""+name+"\" is now <red>disabled</red>!"));
                        else plr.sendMessage(MiniMessage.miniMessage().deserialize("Waypoint \""+name+"\" is now <green>enabled</green>!"));
                        return Command.SINGLE_SUCCESS;
                    }))
            );
    public LiteralArgumentBuilder<CommandSourceStack> editSubcommand = Commands.literal("edit")
            .then(Commands.argument("waypoint_name", StringArgumentType.string())
                .suggests(getWaypointNamesSuggestion())
                    .then(Commands.literal("colour")
                            .then(Commands.argument("colour", StringArgumentType.string())
                                    .suggests(getColoursSuggestion()).executes(ctx -> {
                                        Player plr = (Player) ctx.getSource().getExecutor();
                                        if (plr == null) return 0;

                                        final String name = ctx.getArgument("waypoint_name", String.class);
                                        final Long colour = getColourFromArguments(ctx);

                                        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                                        WaypointInformation info = data.waypoints.get(name);
                                        if (info == null) {
                                            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Waypoint \""+name+"\" does not exist!"));
                                            return 0;
                                        }

                                        if (colour != null) {
                                            info.colour = colour;
                                            DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
                                            WaypointManager.UpdateWaypoint(plr, name);

                                            String hex = String.format("#%06X", info.colour);

                                            plr.sendMessage(MiniMessage.miniMessage().deserialize("Changed waypoint <color:"+hex+">\""+name+"\"</color:"+hex+">'s colour!"));
                                        } else {
                                            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Invalid colour!"));
                                            return 0;
                                        }

                                        return Command.SINGLE_SUCCESS;
                                    })))
                    .then(Commands.literal("position")
                            .then(Commands.argument("position", ArgumentTypes.blockPosition())
                                    .executes(ctx -> {
                                        Player plr = (Player) ctx.getSource().getExecutor();
                                        if (plr == null) return 0;

                                        final String name = ctx.getArgument("waypoint_name", String.class);
                                        final BlockPositionResolver blockPositionResolver = ctx.getArgument("position", BlockPositionResolver.class);

                                        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                                        WaypointInformation info = data.waypoints.get(name);
                                        if (info == null) {
                                            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Waypoint \""+name+"\" does not exist!"));
                                            return 0;
                                        }

                                        BlockPosition position = blockPositionResolver.resolve(ctx.getSource());

                                        info.location = position.toLocation(plr.getWorld()).toCenterLocation();
                                        DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
                                        WaypointManager.UpdateWaypoint(plr, name);

                                        Location location = position.toLocation(plr.getWorld());

                                        plr.sendMessage(MiniMessage.miniMessage().deserialize("Changed waypoint \""+name+"\"'s position to "+location.getBlockX()+" "+location.getBlockY()+" "+location.getBlockZ()+"!"));

                                        return Command.SINGLE_SUCCESS;
                                    })))
                    .then(Commands.literal("name")
                            .then(Commands.argument("new_name", StringArgumentType.string())
                                    .executes(ctx -> {
                                        Player plr = (Player) ctx.getSource().getExecutor();
                                        if (plr == null) return 0;

                                        final String name = ctx.getArgument("waypoint_name", String.class);
                                        final String new_name = ctx.getArgument("new_name", String.class);

                                        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                                        WaypointInformation info = data.waypoints.get(name);
                                        if (info == null) {
                                            plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Waypoint \""+name+"\" does not exist!"));
                                            return 0;
                                        }

                                        WaypointManager.RemoveWaypoint(plr, name);
                                        doPlaceWaypointCommand(plr, info.colour, new_name, info.location, name);

                                        return Command.SINGLE_SUCCESS;
                                    })))
            );
    public LiteralArgumentBuilder<CommandSourceStack> locatorbarSubcommand = new LocatorBarCommand().createCommand();
    public LiteralArgumentBuilder<CommandSourceStack> infoSubcommand = Commands.literal("info")
            .then(Commands.argument("waypoint_name", StringArgumentType.string())
                .suggests(getWaypointNamesSuggestion()).executes(ctx -> {
                    Player plr = (Player) ctx.getSource().getExecutor();
                    if (plr == null) return 0;

                    final String name = StringArgumentType.getString(ctx, "waypoint_name");

                    WaypointInformation info = DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.get(name);
                    if (info == null) {
                        plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Waypoint \""+name+"\" does not exist!"));
                        return 0;
                    }

                    String hex = String.format("#%06X", info.colour);

                    String distanceString = getDistanceFromWaypoint(plr, info.location);

                    plr.sendMessage(
                            MiniMessage.miniMessage().deserialize(
                                    String.format("Waypoint <color:%s>%s</color:%s>:%s\n- Position: %d, %d, %d\n- Distance: %s", hex, name, hex, (info.enabled ? "" : " (disabled)"), info.location.getBlockX(), info.location.getBlockY(), info.location.getBlockZ(), distanceString)
                            )
                    );
                    return Command.SINGLE_SUCCESS;
                })
            );
    public LiteralArgumentBuilder<CommandSourceStack> listSubcommand = Commands.literal("list")
            .executes(ctx -> {
                Player plr = (Player) ctx.getSource().getExecutor();
                if (plr == null) return 0;

                PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                if (data.waypoints.isEmpty()) {
                    plr.sendMessage("You have no waypoints!");
                    return Command.SINGLE_SUCCESS;
                }
                plr.sendMessage("All waypoints:");
                for (Map.Entry<String, WaypointInformation> entry : data.waypoints.entrySet()) {
                    String distanceString = getDistanceFromWaypoint(plr, entry.getValue().location);
                    plr.sendMessage(MiniMessage.miniMessage().deserialize((entry.getValue().enabled ? "" : "<grey><i>")+"- "+entry.getKey()+" ("+distanceString+")"));
                }
                return Command.SINGLE_SUCCESS;
            });
    public LiteralArgumentBuilder<CommandSourceStack> menuSubcommand = Commands.literal("menu")
            .executes(ctx -> {
                Player plr = (Player) ctx.getSource().getExecutor();
                if (plr == null) return 0;

                WaypointDialog.openDialog(plr);
                return Command.SINGLE_SUCCESS;
            });

    public SuggestionProvider<CommandSourceStack> getWaypointNamesSuggestion() {
        return (ctx, builder) -> {
            Player plr = (Player) ctx.getSource().getExecutor();
            if (plr == null) return builder.buildFuture();

            HashMap<String, WaypointInformation> waypoints = DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints;
            if (waypoints.isEmpty()) builder.suggest("(empty)");
            else {
                for (Map.Entry<String, WaypointInformation> info : waypoints.entrySet()) {
                    builder.suggest(info.getKey());
                }
            }

            return builder.buildFuture();
        };
    }

    public SuggestionProvider<CommandSourceStack> getColoursSuggestion() {
        return (ctx, builder) -> {
            for (Map.Entry<String, String> entry : colourToHex.entrySet()) {
                builder.suggest(entry.getKey(), new LiteralMessage(entry.getValue()));
            }

            return builder.buildFuture();
        };
    }

    public static Map<String, String> colourToHex = Map.ofEntries(
            entry("white", "#ffffff"),
            entry("grey", "#808080"),
            entry("black", "#000000"),
            entry("red", "#ff0000"),
            entry("dark_red", "#8b0000"),
            entry("orange", "#ffa500"),
            entry("yellow", "#ffff00"),
            entry("green", "#008000"),
            entry("dark_green", "#006400"),
            entry("blue", "#0000ff"),
            entry("dark_blue", "#00008b"),
            entry("indigo", "#4b0082"),
            entry("violet", "#ee82ee"),
            entry("purple", "#800080"),
            entry("pink", "#ffc0cb"),
            entry("teal", "#008080"),
            entry("brown", "#63452c"),
            entry("goldenrod", "#daa520"),
            entry("aqua", "#00ffff"),
            entry("magenta", "#ff00ff")
    );

    public static Long getColourFromArguments(CommandContext<CommandSourceStack> ctx) {
        Player plr = (Player) ctx.getSource().getExecutor();
        if (plr == null) return null;

        String colour = ctx.getArgument("colour", String.class);

        return getColourFromString(colour);
    }

    public static Long getColourFromString(String colour) {
        if (colourToHex.containsKey(colour.toLowerCase())) {
            colour = colourToHex.get(colour.toLowerCase());
        }

        try {
            return Long.decode(colour);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String getDistanceFromWaypoint(Player plr, Location waypointLocation) {
        double blocksAway = plr.getWorld().equals(waypointLocation.getWorld()) ? plr.getLocation().distance(waypointLocation) : -1L;

        String distanceString;
        if (blocksAway > -1L) distanceString = (int) blocksAway +" blocks away";
        else {
            World world = waypointLocation.getWorld();
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

        return distanceString;
    }

    public static int doPlaceWaypointCommand(Player plr, long colour, String id) {
        return doPlaceWaypointCommand(plr, colour, id, plr.getLocation().toBlockLocation().toCenterLocation());
    }

    public static int doPlaceWaypointCommand(Player plr, long colour, String id, Location location) {
        return doPlaceWaypointCommand(plr, colour, id, location, null);
    }

    public static int doPlaceWaypointCommand(Player plr, long colour, String id, Location location, @Nullable String oldName) {
        WaypointCreationResult result = WaypointManager.CreateNewWaypoint(plr, location, id, colour);

        switch (result) {
            case SUCCESS -> {
                if (oldName == null) plr.sendMessage("Successfully created " + id + "!");
                else plr.sendMessage("Successfully renamed \""+oldName+"\" to " + id + "!");
                setPlayerLocatorBar(plr, true);
            }
            case FAILED_CREATING_WAYPOINT -> plr.sendMessage("Error creating "+id+"!");
            case FAILED_ALREADY_EXISTS -> plr.sendMessage("Waypoint "+id+" already exists!");
            case FAILED_REACHED_MAXIMUM -> plr.sendMessage("You cannot make more than "+ WaypointConstants.WAYPOINT_MAX +" waypoints!");
            case FAILED_NAME_TOO_LONG -> plr.sendMessage("Waypoint name cannot be longer than "+WaypointConstants.WAYPOINT_NAME_MAX_SIZE+" characters!");
        }

        return Command.SINGLE_SUCCESS;
    }

    public static void setPlayerLocatorBar(Player plr, boolean bool) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        data.locatorBarEnabled = bool;
        DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
        DefaultWaypointAttributes.updateLocatorBar(plr);
    }

    public static boolean getPlayerLocatorBar(Player plr) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        return data.locatorBarEnabled;
    }

    @Override
    public String getDescription() {
        return "Manage your waypoints, which shows up in the locator bar.";
    }

    public Collection<String> getAliases() {
        return List.of("waypoints");
    };
}
