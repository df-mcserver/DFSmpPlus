package uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.World;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Commands.DFCommand;
import uk.co.nikodem.dFSmpPlus.Constants.WaypointConstants;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.DefaultWaypointAttributes;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointCreationResult;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Map.entry;

public class DFWaypointCommand implements DFCommand {
    public LiteralArgumentBuilder<CommandSourceStack> createCommand() {
        return Commands.literal("waypoint")
                .executes(ctx -> {
                    Player plr = (Player) ctx.getSource().getExecutor();
                    if (plr == null) return 0;

                    plr.sendMessage(MiniMessage.miniMessage().deserialize(
                            "<red>Specify a subcommand!"
                    ));
                    return Command.SINGLE_SUCCESS;
                })
                .then(locatorbarSubcommand)
                .then(addSubcommand)
                .then(removeSubcommand)
                .then(infoSubcommand)
                .then(toggleSubcommand)
                .then(listSubcommand);
    }

    public LiteralArgumentBuilder<CommandSourceStack> locatorbarSubcommand = new LocatorBarCommand().createCommand();
    public LiteralArgumentBuilder<CommandSourceStack> addSubcommand = Commands.literal("add")
            .then(Commands.argument("waypoint_name", StringArgumentType.string()).executes(ctx -> {
                Player plr = (Player) ctx.getSource().getExecutor();
                if (plr == null) return 0;

                final String name = ctx.getArgument("waypoint_name", String.class);
                Random random = new Random();

                int randomIndex = random.nextInt(0, colourToHex.size()-1);

                return doPlaceWaypointCommand(plr, (Long) colourToHex.values().toArray()[randomIndex], name);
            })
            .then(Commands.argument("colour", StringArgumentType.string())
                    .suggests(getColoursSuggestion()).executes(ctx -> {
                Player plr = (Player) ctx.getSource().getExecutor();
                if (plr == null) return 0;

                final String name = ctx.getArgument("waypoint_name", String.class);
                String colour = ctx.getArgument("colour", String.class);

                if (colourToHex.containsKey(colour)) {
                    colour = colourToHex.get(colour);
                }

                try {
                    return doPlaceWaypointCommand(plr, Long.decode(colour), name);
                } catch (NumberFormatException e) {
                    plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>Invalid colour!"));
                    return 0;
                }
            })));

    public LiteralArgumentBuilder<CommandSourceStack> removeSubcommand = Commands.literal("remove")
            .then(Commands.argument("waypoint_name", StringArgumentType.string())
                            .suggests(getWaypointNamesSuggestion()).executes(ctx -> {
                                Player plr = (Player) ctx.getSource().getExecutor();
                                if (plr == null) return 0;

                                final String name = ctx.getArgument("waypoint_name", String.class);

                                Boolean removal = WaypointManager.RemoveWaypoint(plr, name);
                                if (removal == null) {
                                    plr.sendMessage("Waypoint \""+name+"\" does not exist!");
                                    return Command.SINGLE_SUCCESS;
                                }

                                plr.sendMessage("Waypoint \""+name+"\" successfully deleted!");
                                return Command.SINGLE_SUCCESS;
                            })
            );
    public LiteralArgumentBuilder<CommandSourceStack> infoSubcommand = Commands.literal("info")
            .then(Commands.argument("waypoint_name", StringArgumentType.string())
                            .suggests(getWaypointNamesSuggestion()).executes(ctx -> {
                                Player plr = (Player) ctx.getSource().getExecutor();
                                if (plr == null) return 0;

                                final String waypointname = StringArgumentType.getString(ctx, "waypoint_name");

                                WaypointInformation info = DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.get(waypointname);
                                if (info == null) {
                                    plr.sendMessage("Waypoint \""+waypointname+"\" does not exist!");
                                    return Command.SINGLE_SUCCESS;
                                }

                                String hex = String.format("#%06X", info.colour);

                                double blocksAway = plr.getWorld().equals(info.location.getWorld()) ? plr.getLocation().distance(info.location) : -1L;

                                String distanceString;
                                if (blocksAway > -1L) distanceString = (int) blocksAway +" blocks away";
                                else {
                                    World world = info.location.getWorld();
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
                                                String.format("<color:%s>Waypoint %s:</color:%s>\n- X: %d\n- Y: %d\n- Z: %d\n- Distance: %s\n- Enabled: %s", hex, waypointname, hex, info.location.getBlockX(), info.location.getBlockY(), info.location.getBlockZ(), distanceString, info.enabled)
                                        )
                                );
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
                                    plr.sendMessage("Waypoint \""+name+"\" does not exist!");
                                    return Command.SINGLE_SUCCESS;
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
                                    plr.sendMessage("Waypoint \""+name+"\" does not exist!");
                                    return Command.SINGLE_SUCCESS;
                                }

                                if (!state_change) plr.sendMessage(MiniMessage.miniMessage().deserialize("Waypoint \""+name+"\" is now <red>disabled</red>!"));
                                else plr.sendMessage(MiniMessage.miniMessage().deserialize("Waypoint \""+name+"\" is now <green>enabled</green>!"));
                                return Command.SINGLE_SUCCESS;
                            }))
            );
    public LiteralArgumentBuilder<CommandSourceStack> listSubcommand = Commands.literal("list")
            .executes(ctx -> {
                Player plr = (Player) ctx.getSource().getExecutor();
                if (plr == null) return 0;

                PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                if (data.waypoints.isEmpty()) {
                    plr.sendMessage("You have no waypoints set!");
                    return Command.SINGLE_SUCCESS;
                }
                plr.sendMessage("All waypoints:");
                for (Map.Entry<String, WaypointInformation> info : data.waypoints.entrySet()) {
                    plr.sendMessage(MiniMessage.miniMessage().deserialize((info.getValue().enabled ? "" : "<grey><i>")+"- "+info.getKey()));
                }
                return Command.SINGLE_SUCCESS;
            });

    public SuggestionProvider<CommandSourceStack> getWaypointNamesSuggestion() {
        return (ctx, builder) -> {
            Player plr = (Player) ctx.getSource().getExecutor();
            if (plr == null) return builder.buildFuture();

            for (Map.Entry<String, WaypointInformation> info : DFSmpPlus.playerDataHandler.getPlayerData(plr).waypoints.entrySet()) {
                builder.suggest(info.getKey());
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

    public static int doPlaceWaypointCommand(Player plr, long colour, String id) {
        WaypointCreationResult result = WaypointManager.CreateNewWaypoint(plr, plr.getLocation(), id, colour);

        switch (result) {
            case SUCCESS -> {
                plr.sendMessage("Successfully created " + id + "!");
                setPlayerLocatorBar(plr, true);
            }
            case FAILED_CREATING_WAYPOINT -> plr.sendMessage("Error creating "+id+"!");
            case FAILED_ALREADY_EXISTS -> plr.sendMessage("Waypoint "+id+" already exists!");
            case FAILED_REACHED_MAXIMUM -> plr.sendMessage("You cannot make more than "+ WaypointConstants.WAYPOINT_MAX +" waypoints!");
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
