package uk.co.nikodem.dFSmpPlus.Player;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.DialogKeys;
import io.papermc.paper.registry.keys.tags.DialogTagKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.BackpackMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.AccessoryUI;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointDialog;

import java.util.List;

public class DialogManager {
    public static TypedKey<Dialog> customQuickActions = null;

    public static void init(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(
                RegistryEvents.DIALOG.compose(),
                event -> event.registry().register(
                        customQuickActions = DialogKeys.create(Keys.quickActionsDfsmpDialog),
                        builder -> builder
                                .base(DialogBase.builder(Component.text("DFSmpPlus Quick actions"))
                                        .externalTitle(Component.text("Quick actions"))
                                        .body(List.of(DialogBody.plainMessage(Component.text(""))))
                                        .build())
                                .type(DialogType.multiAction(
                                        List.of(
                                                ActionButton.builder(Component.text("Teleport to spawn")).tooltip(Component.text("/spawn")).action(DialogAction.customClick(Keys.quickActionsSpawn, null)).build(),
                                                ActionButton.builder(Component.text("Go back to last death")).tooltip(Component.text("/back")).action(DialogAction.customClick(Keys.quickActionsBack, null)).build(),
                                                ActionButton.builder(Component.text("Open Accessories")).tooltip(Component.text("/accessories, /a")).action(DialogAction.customClick(Keys.quickActionsAccessories, null)).build(),
                                                ActionButton.builder(Component.text("Open Backpack")).tooltip(Component.text("/backpack, /b")).action(DialogAction.customClick(Keys.quickActionsBackpack, null)).build(),
                                                ActionButton.builder(Component.text("Manage Waypoints")).tooltip(Component.text("/waypoint menu")).action(DialogAction.customClick(Keys.waypointDialog, null)).build(),
                                                ActionButton.builder(Component.text("Server Links")).action(DialogAction.staticAction(ClickEvent.showDialog(Dialog.SERVER_LINKS))).build()
                                        )
                                ).columns(1).build())
                )
        );

        context.getLifecycleManager().registerEventHandler(LifecycleEvents.TAGS.postFlatten(RegistryKey.DIALOG).newHandler(event -> {
            event.registrar().addToTag(DialogTagKeys.QUICK_ACTIONS, List.of(customQuickActions));
            event.registrar().addToTag(DialogTagKeys.PAUSE_SCREEN_ADDITIONS, List.of(customQuickActions));
        }));
    }

    public static void onClick(PlayerCustomClickEvent event) {
        if (event.getCommonConnection() instanceof PlayerGameConnection connection) {
            Player plr = connection.getPlayer();
            Key key = event.getIdentifier();

            if (key.equals(Keys.quickActionsAccessories.key())) {
                AccessoryUI.open(plr);
            } else if (key.equals(Keys.quickActionsBackpack.key())) {
                if (!BackpackMeta.openPlayerBackpack(plr)) {
                    plr.sendActionBar(MiniMessage.miniMessage().deserialize("<red>No backpack equipped!"));
                }
            } else if (key.equals(Keys.waypointDialog.key())) {
                WaypointDialog.openDialog(plr);
            } else if (key.equals(Keys.quickActionsBack)) { // im being lazy
                Bukkit.getServer().dispatchCommand(plr, "back");
            } else if (key.equals(Keys.quickActionsSpawn)) {
                Bukkit.getServer().dispatchCommand(plr, "spawn");
            }
        }
    }
}
