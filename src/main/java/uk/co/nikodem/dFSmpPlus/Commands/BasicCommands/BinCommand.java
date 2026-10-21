package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;

import java.util.Collection;
import java.util.List;

public class BinCommand implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getSender() instanceof Player plr) {
            Inventory inv = Bukkit.createInventory(null, 27, "Bin - Put unwanted items here");
            plr.openInventory(inv);
        } else {
            commandSourceStack.getSender().sendMessage("You are not player!");
        }
    }

    @Override
    public String getLabel() {
        return "bin";
    }

    @Override
    public String getDescription() {
        return "Open an inventory which clears when you close it.";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of("trash", "rubbish", "garbage");
    }
}
