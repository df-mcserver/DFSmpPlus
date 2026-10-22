package uk.co.nikodem.dFSmpPlus.Data.Global.Data.Types;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.HashMap;

public class ArbitraryItemStorage implements InventoryHolder {
    public int maxSlots;
    public String uuid;
    public String title;
    public InventoryType type;
    public HashMap<Integer, ItemStack> items;

    public ArbitraryItemStorage(String uuid, String title, int maxSlots, InventoryType type) {
        this.uuid = uuid;
        this.title = title;
        this.maxSlots = maxSlots;
        this.type = type;
        this.items = new HashMap<>();
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory inv = DFSmpPlus.getPlugin(DFSmpPlus.class).getServer().createInventory(this, maxSlots, MiniMessage.miniMessage().deserialize(title));

        return inv;
    }
}
