package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DFMaterialView implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (sender instanceof Player plr) {
            try {
                if (args.length != 1) return;
                Integer givenPage = Integer.parseInt(args[0]);

                Inventory inv = Bukkit.createInventory(null, 54, "DFMaterialView");

                List<ItemStack> items = new ArrayList<>();
                for (DFMaterial material : DFMaterial.DFMaterialIndex) {
                    items.add(material.toItemStack());
                    for (NamespacedKey modelKey : material.getPossibleModels()) {
                        items.add(material.toItemStack(modelKey));
                    }
                }

                for (int i = 0; i < 54; i++) {
                    int index = givenPage*54 + i;
                    if (index >= items.size()) inv.setItem(i, ItemStack.of(Material.AIR));
                    else inv.setItem(i, items.get(index));
                }
                plr.openInventory(inv);

            } catch (NumberFormatException e) {
                sender.sendMessage("not a number!");
            }
        } else {
            sender.sendMessage(Component.text("You are not a player!"));
        }
    }

    @Override
    public @Nullable String permission() {
        return "dfsmpplus.adminonly";
    }

    @Override
    public String getLabel() {
        return "dfmaterialview";
    }

    @Override
    public String getDescription() {
        return "debug command";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of();
    }
}
