package uk.co.nikodem.dFSmpPlus.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class DFMaterialView implements CommandExecutor {
    private final DFSmpPlus plugin;

    public DFMaterialView(DFSmpPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player plr) {
            try {
                if (args.length != 1) return false;
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
        return true;
    }

}
