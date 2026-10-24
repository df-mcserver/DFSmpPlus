package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.BackpackMeta;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.GlobalData;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.Types.BackpackItemStorage;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.Collection;
import java.util.List;

public class BinCommand implements DFBasicCommand, InventoryHolder {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getSender() instanceof Player plr) {
            Inventory inv = this.getInventory();
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

    @Override
    public @NotNull Inventory getInventory() {
        return Bukkit.createInventory(this, 27, "Bin - Put unwanted items here");
    }

    public static void onInventoryClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();

        if (inv.getHolder() instanceof BinCommand) {
            for (ItemStack item : inv.getContents()) {
                if (item == null) continue;
                if (item.isEmpty()) continue;

                AccessoryInformation info = DFItemUtils.getAccessoryInformation(item);
                if (info.hasMeta()) {
                    for (AccessoryMeta meta : info.getMeta()) {
                        if (meta.getClass().equals(BackpackMeta.class)) {
                            BackpackMeta.onBackpackItemBinned(item);
                        }
                    }
                }
            }
        }
    }
}
