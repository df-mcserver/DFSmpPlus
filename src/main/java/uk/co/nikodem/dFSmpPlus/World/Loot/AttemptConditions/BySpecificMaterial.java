package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptConditions;

import org.bukkit.Material;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.World.Loot.LootEditCondition;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BySpecificMaterial implements LootEditCondition {
    private static final Random rand = new Random();

    private final List<Material> materials = new ArrayList<>();
    private int max = 0;

    public BySpecificMaterial addMaterial(Material material) {
        this.materials.add(material);
        return this;
    }

    public BySpecificMaterial setRandomMax(int max) {
        this.max = max;
        return this;
    }

    @Override
    public @Nullable List<Integer> runCheck(LootGenerateEvent event) {
        InventoryHolder holder = event.getInventoryHolder();
        if (holder == null) return null;

        List<Integer> slots = new ArrayList<>();

        for (int i = 0; i < event.getLoot().size(); i++) {
            ItemStack item = event.getLoot().get(i);
            if (item == null) continue;

            if (materials.contains(item.getType())) {
                if (max > 0) {
                    if (rand.nextInt(0, max) == 0) slots.add(i);
                } else {
                    slots.add(i);
                }
            }
        }

        return slots.isEmpty() ? null : slots;
    }
}
