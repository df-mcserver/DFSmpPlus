package uk.co.nikodem.dFSmpPlus.Utils.Serialisation;

import org.bukkit.inventory.ItemStack;

import java.util.stream.Stream;

public class ItemStackSerialiser {
    public byte[] serialiseItemStacks(ItemStack... items) {
        ItemStack[] data = (ItemStack[]) Stream.of(items).toArray();

        return ItemStack.serializeItemsAsBytes(data);
    }

    public ItemStack[] deserialiseItemStacks(byte[] data) {
        return ItemStack.deserializeItemsFromBytes(data);
    }
}
