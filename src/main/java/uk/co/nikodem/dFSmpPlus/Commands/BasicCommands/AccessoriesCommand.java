package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.AccessoryUI;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;

import java.util.Collection;
import java.util.List;

public class AccessoriesCommand implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getSender() instanceof Player plr) {
            AccessoryUI.open(plr);
        } else {
            commandSourceStack.getSender().sendMessage("You are not player!");
        }
    }

    @Override
    public String getLabel() {
        return "accessories";
    }

    @Override
    public String getDescription() {
        return "Open the accessories menu, where you can equip and unequip accessories";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of("equip", "a", "A", "accessory");
    }
}
