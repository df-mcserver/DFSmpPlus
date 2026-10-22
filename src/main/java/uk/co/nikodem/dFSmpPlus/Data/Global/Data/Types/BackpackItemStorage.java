package uk.co.nikodem.dFSmpPlus.Data.Global.Data.Types;

import org.bukkit.event.inventory.InventoryType;

public class BackpackItemStorage extends ArbitraryItemStorage{
    public BackpackItemStorage(String uuid, String title, int maxSlots, InventoryType type) {
        super(uuid, title, maxSlots, type);
    }
}
