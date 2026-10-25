package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.AccessoryUI;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.Player.DialogManager;

import java.util.Collection;
import java.util.List;

public class QuickAccessCommand implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getSender() instanceof Player plr) {
            Dialog dialog = RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG).get(DialogManager.customQuickActions);
            if (dialog != null) plr.showDialog(dialog);
            else plr.showDialog(Dialog.QUICK_ACTIONS);
        } else {
            commandSourceStack.getSender().sendMessage("You are not player!");
        }
    }

    @Override
    public String getLabel() {
        return "quick_access";
    }

    @Override
    public String getDescription() {
        return "Opens the custom quick access menu";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of("quick", "q", "qa");
    }
}
