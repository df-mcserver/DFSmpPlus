package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptEdits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public interface PerformLootEdit {
    void edit(LootEditTransformerData data);

    public static void addItemToLoot(LootEditTransformerData data, ItemStack item) {
        data.getEvent().getLoot().add(item);
    }

    public static void addItemToLoot(LootEditTransformerData data, DFMaterial item) {
        data.getEvent().getLoot().add(item.toItemStack());
    }

    public static void addItemToLoot(LootEditTransformerData data, DFMaterial item, int amount) {
        data.getEvent().getLoot().add(item.toItemStack(amount));
    }

    public static void addItemToLoot(LootEditTransformerData data, Material item) {
        data.getEvent().getLoot().add(ItemStack.of(item));
    }

    public static void addItemToLoot(LootEditTransformerData data, Material item, int amount) {
        data.getEvent().getLoot().add(ItemStack.of(item, amount));
    }

    public static void replaceModifiedSlotsInLoot(LootEditTransformerData data, ItemStack item) {
        for (int i : data.getModifiedSlots()) {
            data.getEvent().getLoot().set(i, item);
        }
    }

    public static void replaceModifiedSlotsInLoot(LootEditTransformerData data, DFMaterial item) {
        for (int i : data.getModifiedSlots()) {
            ItemStack x = data.getEvent().getLoot().get(i);
            data.getEvent().getLoot().set(i, item.toItemStack(x.getAmount()));
        }
    }

    public static void replaceModifiedSlotsInLoot(LootEditTransformerData data, DFMaterial item, int amount) {
        for (int i : data.getModifiedSlots()) {
            ItemStack x = data.getEvent().getLoot().get(i);
            data.getEvent().getLoot().set(i, item.toItemStack(amount));
        }
    }

    public static void replaceModifiedSlotsInLoot(LootEditTransformerData data, Material item) {
        for (int i : data.getModifiedSlots()) {
            ItemStack x = data.getEvent().getLoot().get(i);
            data.getEvent().getLoot().set(i, ItemStack.of(item, x.getAmount()));
        }
    }

    public static void replaceModifiedSlotsInLoot(LootEditTransformerData data, Material item, int amount) {
        for (int i : data.getModifiedSlots()) {
            ItemStack x = data.getEvent().getLoot().get(i);
            data.getEvent().getLoot().set(i, ItemStack.of(item, amount));
        }
    }
}
