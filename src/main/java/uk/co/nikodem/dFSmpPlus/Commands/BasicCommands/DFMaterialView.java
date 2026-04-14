package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DFMaterialView implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (sender instanceof Player plr) {
            try {
                if (args.length != 1) return;
                Integer givenPage = Integer.parseInt(args[0]);

                Inventory inv = new DFMaterialViewInventory().getInventory();

                List<ItemStack> items = new ArrayList<>();
                for (Map.Entry<String, DFMaterial> entry : DFMaterial.DFMaterialIndex.entrySet()) {
                    DFMaterial material = entry.getValue();
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

    public static class DFMaterialViewInventory implements InventoryHolder {
        private final Inventory baseInventory;

        public DFMaterialViewInventory() {
            baseInventory = DFSmpPlus.getPlugin(DFSmpPlus.class).getServer().createInventory(this, 54, Component.text("DFMaterialView"));
        }

        @Override
        public @NotNull Inventory getInventory() {
            return baseInventory;
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
