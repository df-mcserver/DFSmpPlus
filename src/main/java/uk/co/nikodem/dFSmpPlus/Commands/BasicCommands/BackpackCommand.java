package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.BackpackMeta;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;

import java.util.Collection;
import java.util.List;

public class BackpackCommand implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getSender() instanceof Player plr) {
            if (!BackpackMeta.openPlayerBackpack(plr)) {
                plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>You do not have a backpack equipped!"));
            }
        } else {
            commandSourceStack.getSender().sendMessage("You are not player!");
        }
    }

    @Override
    public String getLabel() {
        return "backpack";
    }

    @Override
    public String getDescription() {
        return "Open the backpack if equipped";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of("bag", "b");
    }

}
