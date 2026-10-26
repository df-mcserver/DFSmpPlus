package uk.co.nikodem.dFSmpPlus.Player;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// this plugin doesn't have a native tpa feature, nor does it hook into another plugin
// what it does is:
// - stops /tpa, /tpahere, /tpaccept, /tpdeny or /tpacancel from being ran when the player is in combat
// - stops /tpaccept <player> if <player> is in combat
// reference plugin: https://hangar.papermc.io/Jelly-Pudding/SimpleTPA
public class TpaPreventionDuringCombat {
    public static List<String> tpaCommands = List.of("/tpa", "/tpahere", "/tpaccept", "/tpdeny", "/tpacancel");
    public static List<String> externalTpaCommands = List.of("/tpahere", "/tpaccept");

    public static void onRunCommand(PlayerCommandPreprocessEvent event) {
        Player plr = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        String command = args[0];

        if (CombatLoggingManager.isInCombat(plr)) {
            if (tpaCommands.contains(command)) {
                plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>You cannot use tpa during combat!"));
                event.setCancelled(true);
            }
        } else {
            if (externalTpaCommands.contains(command) && args.length > 1) {
                String playerName = args[1];

                Set<Player> playerMatches = Bukkit.getOnlinePlayers().stream().filter(x -> x.getName().equals(playerName)).collect(Collectors.toSet());
                for (Player target : playerMatches) { // should theoretically only run once (one player per uuid)
                    if (CombatLoggingManager.isInCombat(target)) {
                        plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>That player is in combat!"));
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
