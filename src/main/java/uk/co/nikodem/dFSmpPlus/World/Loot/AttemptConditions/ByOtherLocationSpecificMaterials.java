package uk.co.nikodem.dFSmpPlus.World.Loot.AttemptConditions;

import org.bukkit.Material;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.World.Loot.LootEditCondition;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ByOtherLocationSpecificMaterials implements LootEditCondition {
    private List<Material> otherMaterials = new ArrayList<>();
    private boolean inverted = false;

    public ByOtherLocationSpecificMaterials addMaterial(Material material) {
        this.otherMaterials.add(material);
        return this;
    }

    public ByOtherLocationSpecificMaterials setInverted(boolean inverted) {
        this.inverted = inverted;
        return this;
    }

    @Override
    public @Nullable List<Integer> runCheck(LootGenerateEvent event) {
        InventoryHolder holder = event.getInventoryHolder();
        if (holder == null) return null;

        for (ItemStack item : event.getLoot()) {
            if (item == null) continue;

            if (otherMaterials.contains(item.getType())) {
                if (!inverted) return List.of();
            }
        }

        return inverted ? List.of() : null;
    }
}
