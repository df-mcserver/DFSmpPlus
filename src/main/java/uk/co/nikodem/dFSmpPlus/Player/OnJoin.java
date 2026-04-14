package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChannelEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import uk.co.nikodem.dFSmpPlus.Accessories.AccessoryManager;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
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

    public static void OnInitChannels(PlayerChannelEvent event) {
        Player plr = event.getPlayer();
        if (event.getChannel().equals(CUSTOM_PROXY_CHANNEL)) DFSmpPlus.messageUtils.sendGeyserRequest(plr);
    }
}
