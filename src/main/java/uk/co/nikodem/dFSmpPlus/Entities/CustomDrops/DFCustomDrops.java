package uk.co.nikodem.dFSmpPlus.Entities.CustomDrops;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class DFCustomDrops {
    // please don't touch lol
    public static List<DFCustomDrops> CustomDropsMap = new ArrayList<>();

    private ItemStack item;
    private int minimum;
    private int maximum;
    private double chance; // decimal
    private DFCustomDropCheck check;
    private EntityType type;

    // Chisel Blocks
    public static void createCustomDropData() {
        new DFCustomDropsBuilder(EntityType.FROG).setItem(Material.VERDANT_FROGLIGHT).setRandomRange(0, 2).setCheck((e) -> ((Frog) e.getEntity()).getVariant() == Frog.Variant.COLD).create();
        new DFCustomDropsBuilder(EntityType.FROG).setItem(Material.PEARLESCENT_FROGLIGHT).setRandomRange(0, 2).setCheck((e) -> ((Frog) e.getEntity()).getVariant() == Frog.Variant.WARM).create();
        new DFCustomDropsBuilder(EntityType.FROG).setItem(Material.OCHRE_FROGLIGHT).setRandomRange(0, 2).setCheck((e) -> ((Frog) e.getEntity()).getVariant() == Frog.Variant.TEMPERATE).create();

        new DFCustomDropsBuilder(EntityType.WARDEN).setItem(DFMaterial.SculkFragment).setRandomRange(1, 5).create();
        new DFCustomDropsBuilder(EntityType.WARDEN).setItem(Material.ECHO_SHARD).setRandomRange(1, 3).create();

        new DFCustomDropsBuilder(EntityType.GOAT).setItem(Material.MUTTON).setRandomRange(1, 2).setCheck((e) -> e.getEntity().getFireTicks() == 0).create();
        new DFCustomDropsBuilder(EntityType.GOAT).setItem(Material.COOKED_MUTTON).setRandomRange(1, 2).setCheck(e -> e.getEntity().getFireTicks() > 0).create();

        new DFCustomDropsBuilder(EntityType.SNIFFER).setItem(Material.MOSS_BLOCK).setRandomRange(1, 2).create();
    }

    public DFCustomDrops(
        ItemStack item,
        int minimum,
        int maximum,
        double chance,
        DFCustomDropCheck check,
        EntityType type
    ) {
        this.item = item;
        this.minimum = minimum;
        this.maximum = maximum;
        this.chance = chance;
        this.check = check;
        this.type = type;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public int getMinimum() {
        return this.minimum;
    }

    public int getMaximum() {
        return this.maximum;
    }

    public double getChance() {
        return this.chance;
    }

    public EntityType getType() {
        return this.type;
    }

    public boolean runCheck(EntityDeathEvent event) {
        if (this.check == null) return true;
        return this.check.runCheck(event);
    }

    public boolean runRandomCheck() {
        return Math.random() <= chance;
    }
}
