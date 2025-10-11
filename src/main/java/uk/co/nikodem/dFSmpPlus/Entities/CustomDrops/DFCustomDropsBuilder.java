package uk.co.nikodem.dFSmpPlus.Entities.CustomDrops;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class DFCustomDropsBuilder {
    private EntityType entityType;
    private ItemStack item;
    private Integer minimum;
    private Integer maximum;
    private Double chance; // decimal
    private DFCustomDropCheck check;

    public DFCustomDropsBuilder(EntityType entityType) {
        this.entityType = entityType;
    }

    public DFCustomDropsBuilder setItem(Material material) {
        this.item = ItemStack.of(material);
        return this;
    }

    public DFCustomDropsBuilder setItem(ItemStack item) {
        this.item = item;
        return this;
    }

    public DFCustomDropsBuilder setItem(DFMaterial material) {
        this.item = material.toItemStack();
        return this;
    }

    public DFCustomDropsBuilder setAmount(int amnt) {
        this.minimum = amnt;
        this.maximum = amnt;
        return this;
    }

    public DFCustomDropsBuilder setRandomRange(int min, int max) {
        this.minimum = min;
        this.maximum = max;
        return this;
    }

    public DFCustomDropsBuilder setChance(double chance) {
        this.chance = chance;
        return this;
    }

    public DFCustomDropsBuilder setCheck(DFCustomDropCheck check) {
        this.check = check;
        return this;
    }

    public DFCustomDrops create() {
        DFCustomDrops drops = new DFCustomDrops(
                item == null ? ItemStack.of(Material.AIR) : item,
                minimum == null ? 1 : minimum,
                maximum == null ? 1 : maximum,
                chance == null ? 1D : chance,
                check,
                entityType
        );
        DFCustomDrops.CustomDropsMap.add(drops);

        return drops;
    }
}
