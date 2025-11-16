package uk.co.nikodem.dFSmpPlus.Commands.LegacyCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player plr) {
            Inventory inv = Bukkit.createInventory(null, 27, "Bin - Put unwanted items here");
            plr.openInventory(inv);
        } else {
            sender.sendMessage("You are not player!");
        }
        return true;
    }
}
