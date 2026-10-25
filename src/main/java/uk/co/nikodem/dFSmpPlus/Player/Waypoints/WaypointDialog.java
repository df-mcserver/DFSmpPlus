package uk.co.nikodem.dFSmpPlus.Player.Waypoints;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.dialog.DialogResponseView;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.DFWaypointCommand;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Constants.WaypointConstants;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Data.Player.Types.WaypointInformation;

import java.util.*;

import static uk.co.nikodem.dFSmpPlus.Commands.BrigaderCommands.DFWaypointCommand.setPlayerLocatorBar;

public class WaypointDialog {
    public static Component dialogTitle = Component.text("Waypoints");

    public static void openDialog(Player plr) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);

        int waypointCount = data.waypoints.size();

        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(dialogTitle)
                        .body(List.of(
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Manage your locator bar waypoints ("+waypointCount+"/"+ WaypointConstants.WAYPOINT_MAX+" waypoints)<br>Locator bar is currently "+(data.locatorBarEnabled ? "<green>enabled</green>" : "<red>disabled</red>")+"!"), 500)
                        ))
                        .build())
                .type(DialogType.multiAction(
                        getMainMenuButtons(plr, data, waypointCount) // list of waypoints
                ).columns(1).exitAction(ActionButton.builder(Component.text("Close")).width(250).build()).build())
        );

        plr.showDialog(dialog);
    }

    public static List<ActionButton> getMainMenuButtons(Player plr, PlayerData data, int waypointCount) {
        List<ActionButton> buttons = new ArrayList<>();

        for (Map.Entry<String, WaypointInformation> entry : data.waypoints.entrySet()) {
            String name = entry.getKey();
            WaypointInformation info = entry.getValue();
            String hex = String.format("#%06x", info.colour);

            buttons.add(
                    ActionButton.builder(MiniMessage.miniMessage().deserialize((info.enabled ? "" : "<i>")+"<color:"+hex+">"+name))
                            .tooltip(MiniMessage.miniMessage().deserialize(name+(info.enabled ? "" : " (disabled)")+"<br>"+getStringifiedPosition(plr, info.location, true)+"<br>Click to edit"))
                            .action(DialogAction.customClick(Keys.appendStringToKeysValue(Keys.waypointDialogEdit, convertStringToHexString(name)), null))
                            .width(200)
                            .build()
            );
        }

        boolean canAddMore = waypointCount < WaypointConstants.WAYPOINT_MAX;
        buttons.add(
                ActionButton.builder(Component.text(canAddMore ? "Add new waypoint" : "Too many waypoints!"))
                        .action(DialogAction.customClick(canAddMore ? Keys.waypointDialogAdd : Keys.waypointDialog, null))
                        .tooltip(canAddMore ? null : Component.text("Delete some waypoints to add more!"))
                        .width(150)
                        .build()
        );

        boolean hasWaypoints = waypointCount > 0;
        buttons.add(
                ActionButton.builder(Component.text(hasWaypoints ? "Remove waypoints" : "No waypoints to delete!"))
                        .action(DialogAction.customClick(Keys.waypointDialogRemove, null))
                        .tooltip(hasWaypoints ? null : Component.text("Add some waypoints to delete them!"))
                        .width(150)
                        .build()
        );

        return buttons;
    }

    public static void openEditWaypointDialog(Player plr, String hexString) {
        String name = convertHexStringToString(hexString);

        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        WaypointInformation info = data.waypoints.get(name);
        if (info == null) return;

        String colourHex = String.format("#%06x", info.colour);

        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(dialogTitle)
                        .body(List.of(
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Editing waypoint - "+(info.enabled ? "" : "<i>")+"<color:"+colourHex+">"+name), 500),
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Accepted colour names: "+listColours()+". Also accepts hex values eg. #ff77aa"), 500)
                        ))
                        .inputs(List.of(
                                DialogInput.text("name", Component.text("Name ("+name+")")).maxLength(WaypointConstants.WAYPOINT_NAME_MAX_SIZE).width(250).build(),
                                DialogInput.text("position", Component.text(
                                        "Position ("+
                                        getStringifiedPosition(plr, info.location, false)
                                        +")")).width(200).build(),
                                DialogInput.text("colour", Component.text("Colour (hex or name) ("+colourHex+")")).width(200).build(),
                                DialogInput.bool("enabled", Component.text("Is enabled")).initial(info.enabled).onFalse("Disabled").onTrue("Enabled").build()
                        ))
                        .build())
                .type(DialogType.multiAction(List.of(
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("<green>Confirm")).action(DialogAction.customClick(Keys.appendStringToKeysValue(Keys.waypointDialogCompletedEdit, hexString), null)).build(),
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("<red>Delete")).action(DialogAction.customClick(Keys.appendStringToKeysValue(Keys.waypointDialogSingleRemove, hexString), null)).build()
                )).columns(3).exitAction(ActionButton.builder(MiniMessage.miniMessage().deserialize("Discard changes")).action(DialogAction.customClick(Keys.waypointDialog, null)).build()).build())
        );

        plr.showDialog(dialog);
    }

    public static void openAddWaypointDialog(Player plr) {
        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(dialogTitle)
                        .body(List.of(
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Adding new waypoint"), 500),
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Accepted colour names: "+listColours()+". Also accepts hex values eg. #ff77aa"), 500)
                        ))
                        .inputs(List.of(
                                DialogInput.text("name", Component.text("Name")).maxLength(WaypointConstants.WAYPOINT_NAME_MAX_SIZE).width(250).build(),
                                DialogInput.text("position", Component.text("Position")).initial("~ ~ ~").width(200).build(),
                                DialogInput.text("colour", Component.text("Colour (hex or name)")).width(200).build()
                        ))
                        .build())
                .type(DialogType.confirmation(
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("<green>Add")).action(DialogAction.customClick(Keys.waypointDialogCompletedAdd, null)).build(),
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("Discard")).action(DialogAction.customClick(Keys.waypointDialog, null)).build()
                ))
        );

        plr.showDialog(dialog);
    }

    public static void openRemoveWaypointDialog(Player plr) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        List<DialogInput> buttons = new ArrayList<>();

        for (Map.Entry<String, WaypointInformation> entry : data.waypoints.entrySet()) {
            String name = entry.getKey();
            WaypointInformation info = entry.getValue();
            String hex = String.format("#%06x", info.colour);

            buttons.add(
                DialogInput.bool(convertStringToHexString(name), MiniMessage.miniMessage().deserialize((info.enabled ? "" : "<i>")+"<color:"+hex+">"+name+" ("+getStringifiedPosition(plr, info.location, true)+")")).initial(false).build()
            );
        }

        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(dialogTitle)
                        .body(List.of(
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Removing waypoints"), 500)
                        ))
                        .inputs(buttons)
                        .build())
                .type(DialogType.confirmation(
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("<red>Delete")).action(DialogAction.customClick(Keys.waypointDialogCompletedRemove, null)).build(),
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("Cancel")).action(DialogAction.customClick(Keys.waypointDialog, null)).build()
                ))
        );

        plr.showDialog(dialog);
    }

    public static void openSingleRemoveWaypointDialogue(Player plr, String hexString) {
        String name = convertHexStringToString(hexString);

        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        WaypointInformation info = data.waypoints.get(name);
        if (info == null) return;

        String colourHex = String.format("#%06x", info.colour);

        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(dialogTitle)
                        .body(List.of(
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Removing waypoint - "+(info.enabled ? "" : "<i>")+"<color:"+colourHex+">"+name), 500),
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Are you sure you want to remove this waypoint? This is <red><b>irreversible</red>."))
                        ))
                        .build())
                .type(DialogType.confirmation(
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("<red>Delete")).action(DialogAction.customClick(Keys.appendStringToKeysValue(Keys.waypointDialogSingleCompletedRemove, hexString), null)).build(),
                        ActionButton.builder(MiniMessage.miniMessage().deserialize("Cancel")).action(DialogAction.customClick(Keys.waypointDialog, null)).build()
                ))
        );

        plr.showDialog(dialog);
    }

    public static void openMessageDialog(Player plr, Component message, Key redirect) {
        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(dialogTitle)
                        .body(List.of(
                                DialogBody.plainMessage(message, 500)
                        ))
                        .build())
                .type(DialogType.notice(ActionButton.builder(MiniMessage.miniMessage().deserialize("Ok")).action(DialogAction.customClick(redirect, null)).build()))
        );

        plr.showDialog(dialog);
    }

    public static void onClick(PlayerCustomClickEvent event) {
        if (event.getCommonConnection() instanceof PlayerGameConnection connection) {
            Player plr = connection.getPlayer();
            Key key = event.getIdentifier();

            DialogResponseView view = event.getDialogResponseView();
            if (view == null) {
                return;
            }

            String namespace = Keys.waypointDialog.namespace();
            Key editPageKey = Keys.waypointDialogEdit.key();
            Key editConfirmPageKey = Keys.waypointDialogCompletedEdit.key();
            Key singleRemovePageKey = Keys.waypointDialogSingleRemove.key();
            Key singleConfirmRemovePageKey = Keys.waypointDialogSingleCompletedRemove.key();

            if (key.namespace().equals(namespace)) {
                if (key.value().startsWith(editPageKey.value())) {
                    String hexString = key.value().replace(editPageKey.value(), "");
                    openEditWaypointDialog(plr, hexString);
                } else if (key.value().startsWith(editConfirmPageKey.value())) {
                    String hexString = key.value().replace(editConfirmPageKey.value(), "");
                    String name = convertHexStringToString(hexString);

                    handleConfirmEdits(plr, view, name, hexString);
                } else if (key.value().startsWith(Keys.waypointDialogAdd.key().value())) {
                    openAddWaypointDialog(plr);
                } else if (key.value().startsWith(Keys.waypointDialogCompletedAdd.key().value())) {
                    handleConfirmAdd(plr, view);
                } else if (key.value().startsWith(Keys.waypointDialogRemove.key().value())) {
                    openRemoveWaypointDialog(plr);
                } else if (key.value().startsWith(Keys.waypointDialogCompletedRemove.key().value())) {
                    handleConfirmRemove(plr, view);
                } else if (key.value().startsWith(singleRemovePageKey.value())) {
                    String hexString = key.value().replace(singleRemovePageKey.value(), "");
                    openSingleRemoveWaypointDialogue(plr, hexString);
                } else if (key.value().startsWith(singleConfirmRemovePageKey.value())) {
                    String hexString = key.value().replace(singleConfirmRemovePageKey.value(), "");
                    String name = convertHexStringToString(hexString);

                    WaypointManager.RemoveWaypoint(plr, name);

                    openDialog(plr);
                }
            }
        }
    }

    public static void handleConfirmRemove(Player plr, DialogResponseView view) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);

        for (Map.Entry<String, WaypointInformation> entry : data.waypoints.entrySet()) {
            String hexString = convertStringToHexString(entry.getKey());

            Boolean toDelete = view.getBoolean(hexString);
            if (Boolean.TRUE.equals(toDelete)) {
                WaypointManager.RemoveWaypoint(plr, entry.getKey());
            }
        }

        openDialog(plr);
    }

    public static void handleConfirmAdd(Player plr, DialogResponseView view) {
        String name = view.getText("name");
        String colour_string = view.getText("colour");
        String position_string = view.getText("position");
        if (name == null || colour_string == null || position_string == null) {
            openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>Invalid inputs!"), Keys.waypointDialog);
            return;
        }

        String errors = "";

        if (name.isEmpty()) errors += "No name specified!<br>";

        Long colour;
        if (colour_string.isEmpty()) {
            int randomIndex = new Random().nextInt(0, DFWaypointCommand.colourToHex.size()-1);
            colour = Long.decode((String) DFWaypointCommand.colourToHex.values().toArray()[randomIndex]);
        }
        else {
            colour = DFWaypointCommand.getColourFromString(colour_string);
            if (colour == null) errors += "Invalid colour specified!<br>";
        }

        Location location = null;
        String[] coords = position_string.split(" ");
        if (coords.length == 3) {
            try {
                int x = Objects.equals(coords[0], "~") ? plr.getLocation().getBlockX() : Integer.parseInt(coords[0]);
                int y = Objects.equals(coords[1], "~") ? plr.getLocation().getBlockY() : Integer.parseInt(coords[1]);
                int z = Objects.equals(coords[2], "~") ? plr.getLocation().getBlockZ() : Integer.parseInt(coords[2]);

                location = new Location(plr.getWorld(), x, y, z).toCenterLocation();
            } catch (NumberFormatException ignored) {
                errors += "Invalid position specified!<br>";
            }
        } else {
            errors += "Invalid position specified!<br>";
        }

        if (!errors.isEmpty()) openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>There were errors in your waypoint!<br><br>"+errors), Keys.waypointDialogAdd);
        else {
            WaypointCreationResult result = WaypointManager.CreateNewWaypoint(plr, location, name, colour);

            switch (result) {
                case SUCCESS -> {
                    setPlayerLocatorBar(plr, true);
                    openDialog(plr);
                }
                case FAILED_CREATING_WAYPOINT -> openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>There was an error creating the waypoint!"), Keys.waypointDialogAdd);
                case FAILED_ALREADY_EXISTS -> openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>The waypoint "+name+" already exists!"), Keys.waypointDialogAdd);
                case FAILED_REACHED_MAXIMUM -> openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>You cannot make more than "+WaypointConstants.WAYPOINT_MAX+"!"), Keys.waypointDialogAdd);
                case FAILED_NAME_TOO_LONG -> openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>The waypoint name cannot be longer than "+WaypointConstants.WAYPOINT_NAME_MAX_SIZE+" characters!"), Keys.waypointDialogAdd);
            }
        }
    }

    public static void handleConfirmEdits(Player plr, DialogResponseView view, String name, String hexString) {
        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        WaypointInformation info = data.waypoints.get(name);

        String new_name = view.getText("name");
        String new_colour_string = view.getText("colour");
        String new_position_string = view.getText("position");
        Boolean new_enabled = view.getBoolean("enabled");
        if (new_name == null || new_colour_string == null || new_position_string == null || new_enabled == null) {
            openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>Invalid inputs!"), Keys.waypointDialog);
            return;
        }

        String errors = "";

        boolean shouldUpdateName = !new_name.isEmpty();
        boolean shouldUpdateEnabled = info.enabled != new_enabled;

        boolean colourIsValid = false;
        boolean shouldUpdateColour = false;
        Long new_colour = null;
        if (new_colour_string.isEmpty()) {
            colourIsValid = true;
        } else {
            new_colour = DFWaypointCommand.getColourFromString(new_colour_string);
            if (new_colour != null) {
                colourIsValid = true;
                if (info.colour != new_colour) shouldUpdateColour = true;
            }
        }

        if (!colourIsValid) errors += "Invalid colour specified!<br>";

        boolean positionIsValid = false;
        boolean shouldUpdatePosition = false;

        Location new_location = null;
        if (new_position_string.isEmpty()) {
            positionIsValid = true;
        }
        else {
            String[] coords = new_position_string.split(" ");
            if (coords.length == 3) {
                try {
                    int x = Objects.equals(coords[0], "~") ? plr.getLocation().getBlockX() : Integer.parseInt(coords[0]);
                    int y = Objects.equals(coords[1], "~") ? plr.getLocation().getBlockY() : Integer.parseInt(coords[1]);
                    int z = Objects.equals(coords[2], "~") ? plr.getLocation().getBlockZ() : Integer.parseInt(coords[2]);

                    new_location = new Location(plr.getWorld(), x, y, z).toCenterLocation();
                    positionIsValid = true;
                    shouldUpdatePosition = true;
                } catch (NumberFormatException ignored) {}
            }
        }

        if (!positionIsValid) errors += "Invalid position specified!<br>";

        if (!errors.isEmpty()) openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>There were errors in your edit!<br><br>"+errors), Keys.appendStringToKeysValue(Keys.waypointDialogEdit, hexString));
        else {
            if (!shouldUpdateName) {
                if (shouldUpdateColour) info.colour = new_colour;
                if (shouldUpdatePosition) info.location = new_location;
                if (shouldUpdateEnabled) info.enabled = new_enabled;

                if (shouldUpdateColour || shouldUpdatePosition || shouldUpdateEnabled) {
                    DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
                    WaypointManager.UpdateWaypoint(plr, name);
                    openDialog(plr);
                }
            } else {
                WaypointManager.RemoveWaypoint(plr, name);
                WaypointCreationResult result = WaypointManager.CreateNewWaypoint(
                        plr,
                        shouldUpdatePosition ? new_location : info.location,
                        new_name,
                        shouldUpdateColour ? new_colour : info.colour,
                        shouldUpdateEnabled ? new_enabled : info.enabled
                );

                if (result != WaypointCreationResult.SUCCESS) openMessageDialog(plr, MiniMessage.miniMessage().deserialize("<red>There was an error renaming your waypoint!"), Keys.waypointDialog);
                else {
                    setPlayerLocatorBar(plr, true);
                    openDialog(plr);
                }
            }
        }
    }

    public static String listColours() {
        return String.join(", ", DFWaypointCommand.colourToHex.keySet());
    }

    public static String getStringifiedPosition(Player plr, Location location, boolean includeDistance) {
        double blocksAway = plr.getWorld().equals(location.getWorld()) ? plr.getLocation().distance(location) : -1L;

        String distanceString = "";
        if (blocksAway > -1L) distanceString = (int) blocksAway +" blocks away";

        World.Environment env = location.getWorld().getEnvironment();
        if (env.equals(plr.getWorld().getEnvironment())) {
            return location.getBlockX()+" "+location.getBlockY()+" "+location.getBlockZ()+(includeDistance ? " ("+distanceString+")" : "");
        } else {
            switch (env) {
                case NORMAL -> {
                    return "In the Overworld";
                }
                case NETHER -> {
                    return "In the Nether";
                }
                case THE_END -> {
                    return "In The End";
                }
                default -> {
                    return "Unknown location";
                }
            }
        }
    }

    public static String convertStringToHexString(String value) {
        return HexFormat.of().formatHex(value.getBytes());
    }

    public static String convertHexStringToString(String hexString) {
        return new String(HexFormat.of().parseHex(hexString));
    }
}