package uk.co.nikodem.dFSmpPlus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.Objects;

import static java.lang.Integer.parseInt;

public class GiveDF implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player plr) {
            if (strings.length >= 1) {
                int itemSize = strings.length >= 2 ? parseInt(strings[1]) : 1;
                DFMaterial mat = null;
                for (DFMaterial dfMaterial : DFMaterial.DFMaterialIndex) {
                    if (Objects.equals(dfMaterial.getNamedId(), strings[0])) {
                        mat = dfMaterial;
                        break;
                    }
                }

                if (mat == null) {
                    commandSender.sendMessage("Invalid DFMaterial!");
                } else {
                    ItemStack item = mat.toItemStack(itemSize);
                    DFItemUtils.addUUIDIfMarked(item);
                    plr.getInventory().addItem(item);
                }
            } else {
                commandSender.sendMessage("Invalid argument size! Required 1.");
            }
        } else {
            commandSender.sendMessage("You are not a player!");
        }
        return true;
    }
}
