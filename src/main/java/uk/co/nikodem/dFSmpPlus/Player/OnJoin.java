package uk.co.nikodem.dFSmpPlus.Player;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChannelEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Player.Combat.WorldRecordAdvancementHandler;
import uk.co.nikodem.dFSmpPlus.Player.DFUpdates.DFUpdateEvents;
import uk.co.nikodem.dFSmpPlus.Player.LifeCrystals.LifeCrystalManager;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.DefaultWaypointAttributes;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;

import java.util.*;

import static uk.co.nikodem.dFSmpPlus.Utils.Server.MessageUtils.CUSTOM_PROXY_CHANNEL;

public class OnJoin {
    public static void OnJoin(PlayerJoinEvent e) {
        Player plr = e.getPlayer();

        PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        if (data.firstTime) {
            Dialog dialog = Dialog.create(builder -> builder.empty()
                    .base(DialogBase.builder(MiniMessage.miniMessage().deserialize("<u>Welcome!"))
                            .body(List.of(
                                    DialogBody.item(DFMaterial.ObsidianAxe.toItemStack(), DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("<b><aqua>Welcome to the server!"), 180), true, false, 16, 16),
                                    DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("This server has <grey>custom content</grey>, and you should have the resource pack already installed."), 500),
                                    DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("If you're on java, on the pause screen you should have a <grey>\"Quick Actions\"</grey> button. On bedrock, you can do any emote and then press on the <grey>\"See server actions\"</grey> button. This menu should give you access to all of the new commands that this plugin adds."), 500),
                                    DialogBody.plainMessage(MiniMessage.miniMessage().deserialize("Thank you for reading, and have fun!"), 500)
                            ))
                            .build())
                    .type(DialogType.notice(ActionButton.builder(Component.text("Ok")).action(DialogAction.customClick(Keys.welcomeDialogAccept, null)).build()))
            );

            plr.setNoDamageTicks(20*60);
            plr.showDialog(dialog);
        }

        WorldRecordAdvancementHandler.onJoin(e);

        AccessoryManager.onJoin(plr);

        DFSmpPlus.hidingUtils.hideAllExclusiveEntitiesOnJoin(plr);
        DefaultWaypointAttributes.applyDefaults(plr);
        WaypointManager.CreateOnJoin(plr);

        for (CraftingTemplate template : DFSmpPlus.craftingTemplateList) {
            template.discoverRecipes(plr);
        }

        TelemetryUtils.increasePlayerJoins(1);

        LifeCrystalManager.updatePlayerLifeCrystalsModifier(plr);

        DFUpdateEvents.onJoin(e);
    }

    public static void onClick(PlayerCustomClickEvent event) {
        if (event.getCommonConnection() instanceof PlayerGameConnection connection) {
            Player plr = connection.getPlayer();
            Key key = event.getIdentifier();

            if (key.equals(Keys.welcomeDialogAccept.key())) {
                PlayerData data = DFSmpPlus.playerDataHandler.getPlayerData(plr);
                data.firstTime = false;
                DFSmpPlus.playerDataHandler.writePlayerData(plr, data);
            }
        }
    }

    public static void OnInitChannels(PlayerChannelEvent event) {
        Player plr = event.getPlayer();
        if (event.getChannel().equals(CUSTOM_PROXY_CHANNEL)) DFSmpPlus.messageUtils.sendGeyserRequest(plr);
    }
}
