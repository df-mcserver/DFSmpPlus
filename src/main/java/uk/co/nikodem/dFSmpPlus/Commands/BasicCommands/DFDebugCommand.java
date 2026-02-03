package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DFDebugCommand implements DFBasicCommand {
    private final List<String> subcommands = List.of(
            "damage",
            "waypointtest"
    );

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        String cmd = args[0].toLowerCase().replaceAll("\\p{C}", "");
        if (cmd.equals("damage")) {
            sender.sendMessage("damage command");
            int damage = Integer.parseInt(args[1]);
            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            DFItemUtils.setDamage(item, damage);
            sender.sendMessage("applied "+damage+" damage");
        } else if (cmd.equals("waypointtest")) {
            sender.sendMessage("waypointtest command");
            Player plr = (Player) sender;
            plr.sendMessage(WaypointManager.CreateNewWaypoint(plr, plr.getLocation(), UUID.randomUUID().toString(), new Random().nextLong(65535)).toString());
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        String cmd = args[0].toLowerCase().replaceAll("\\p{C}", "");

        if (!subcommands.contains(cmd)) return subcommands;
        else return List.of();
    }

    @Override
    public @Nullable String permission() {
        return "dfsmpplus.adminonly";
    }

    @Override
    public String getLabel() {
        return "dfdebug";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of();
    }
}
