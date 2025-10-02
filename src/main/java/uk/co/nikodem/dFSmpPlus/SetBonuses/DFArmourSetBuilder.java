package uk.co.nikodem.dFSmpPlus.SetBonuses;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFArmourSetBuilder {

    private final String name;

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private List<DFArmourSetMeta> metas;

    private String setBonusText;
    private String base;

    public DFArmourSetBuilder(String name) {
        this.name = name;
    }

    public DFArmourSetBuilder setHelmet(ItemStack i) {
        this.helmet = i;
        return this;
    }

    public DFArmourSetBuilder setHelmet(DFMaterial i) {
        this.helmet = i.toItemStack();
        return this;
    }

    public DFArmourSetBuilder setHelmet(Material m) {
        this.helmet = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setChestplate(ItemStack i) {
        this.chestplate = i;
        return this;
    }

    public DFArmourSetBuilder setChestplate(DFMaterial i) {
        this.chestplate = i.toItemStack();
        return this;
    }

    public DFArmourSetBuilder setChestplate(Material m) {
        this.chestplate = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setLeggings(ItemStack i) {
        this.leggings = i;
        return this;
    }

    public DFArmourSetBuilder setLeggings(DFMaterial i) {
        this.leggings = i.toItemStack();
        return this;
    }

    public DFArmourSetBuilder setLeggings(Material m) {
        this.leggings = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setBoots(ItemStack i) {
        this.boots = i;
        return this;
    }

    public DFArmourSetBuilder setBoots(DFMaterial i) {
        this.boots = i.toItemStack();
        return this;
    }

    public DFArmourSetBuilder setBoots(Material m) {
        this.boots = new ItemStack(m);
        return this;
    }

    public DFArmourSetBuilder setBase(String name) {
        this.base = name;
        return this;
    }

    public DFArmourSetBuilder setBase(DFArmourSet set) {
        this.base = set.getName();
        return this;
    }

    public DFArmourSetBuilder setSetBonus(String setBonusText) {
        this.setBonusText = setBonusText;
        return this;
    }

    public DFArmourSetBuilder addMeta(DFArmourSetMeta... metas) {
        this.metas.addAll(Arrays.asList(metas));
        return this;
    }

    public DFArmourSet create() {
        DFArmourSet newSet = new DFArmourSet(
                this.name,
                this.helmet,
                this.chestplate,
                this.leggings,
                this.boots,
                this.base,
                this.setBonusText,
                this.setBonusText != null,
                this.metas
        );
        DFArmourSet.DFArmourSetIndex.add(newSet);
        return newSet;
    }
}
