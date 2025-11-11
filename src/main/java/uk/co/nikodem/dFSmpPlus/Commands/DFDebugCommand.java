package uk.co.nikodem.dFSmpPlus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Player.Waypoints.WaypointManager;

public class DFDebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
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
            WaypointManager.CreateWaypoint(plr, plr.getLocation(), "TEST");
        }
        return true;
    }
}
