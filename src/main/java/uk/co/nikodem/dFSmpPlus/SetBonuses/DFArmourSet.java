package uk.co.nikodem.dFSmpPlus.SetBonuses;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.SetBonuses.Metas.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DFArmourSet {
    // do not touch this list
    public final static List<DFArmourSet> DFArmourSetIndex = new ArrayList<>();

    // Vanilla armour sets
    public static final DFArmourSet Native_Leather = new DFArmourSetBuilder("Leather")
            .setHelmet(Material.LEATHER_HELMET)
            .setChestplate(Material.LEATHER_CHESTPLATE)
            .setLeggings(Material.LEATHER_LEGGINGS)
            .setBoots(Material.LEATHER_BOOTS)
            .setSetBonus("Stops hunger from naturally depleting")
            .addMeta(new HungerlessMeta())
            .create();

    public static final DFArmourSet Native_Chainmail = new DFArmourSetBuilder("Chainmail")
            .setHelmet(Material.CHAINMAIL_HELMET)
            .setChestplate(Material.CHAINMAIL_CHESTPLATE)
            .setLeggings(Material.CHAINMAIL_LEGGINGS)
            .setBoots(Material.CHAINMAIL_BOOTS)
            .setSetBonus("Allows you to mine faster")
            .addMeta(new FastMineMeta())
            .create();

    public static final DFArmourSet Native_Iron = new DFArmourSetBuilder("Iron")
            .setHelmet(Material.IRON_HELMET)
            .setChestplate(Material.IRON_CHESTPLATE)
            .setLeggings(Material.IRON_LEGGINGS)
            .setBoots(Material.IRON_BOOTS)
            .create();

    public static final DFArmourSet Native_Golden = new DFArmourSetBuilder("Golden")
            .setHelmet(Material.GOLDEN_HELMET)
            .setChestplate(Material.GOLDEN_CHESTPLATE)
            .setLeggings(Material.GOLDEN_LEGGINGS)
            .setBoots(Material.GOLDEN_BOOTS)
            .setSetBonus("Allows you to move faster")
            .addMeta(new SpeedMeta())
            .create();

    public static final DFArmourSet Native_Diamond = new DFArmourSetBuilder("Diamond")
            .setHelmet(Material.DIAMOND_HELMET)
            .setChestplate(Material.DIAMOND_CHESTPLATE)
            .setLeggings(Material.DIAMOND_LEGGINGS)
            .setBoots(Material.DIAMOND_BOOTS)
            .setSetBonus("Gives you the power of the conduits")
            .addMeta(new ConduitMeta())
            .create();

    public static final DFArmourSet Native_Netherite = new DFArmourSetBuilder("Netherite")
            .setHelmet(Material.NETHERITE_HELMET)
            .setChestplate(Material.NETHERITE_CHESTPLATE)
            .setLeggings(Material.NETHERITE_LEGGINGS)
            .setBoots(Material.NETHERITE_BOOTS)
            .setSetBonus("Makes you immune to fire")
            .addMeta(new FireImmunityMeta())
            .create();

    // Custom armour sets
    public static final DFArmourSet Copper = new DFArmourSetBuilder("Copper")
            .setBase(DFArmourSet.Native_Iron)
            .setHelmet(DFMaterial.CopperHelmet)
            .setChestplate(DFMaterial.CopperChestplate)
            .setLeggings(DFMaterial.CopperLeggings)
            .setBoots(DFMaterial.CopperBoots)
            .setSetBonus("Has a chance to strike lightning when critical hitting someone, during the rain.")
            .addMeta(new CopperMeta())
            .create();

    public static final DFArmourSet Sculk = new DFArmourSetBuilder("Sculk")
            .setBase(DFArmourSet.Native_Diamond)
            .setHelmet(DFMaterial.SculkHelmet)
            .setChestplate(DFMaterial.SculkChestplate)
            .setLeggings(DFMaterial.SculkLeggings)
            .setBoots(DFMaterial.SculkBoots)
            .setSetBonus("When hit, your opponent is blinded and you become invisible.")
            .addMeta(new SculkArmourMeta())
            .create();

    public static final DFArmourSet Firidium = new DFArmourSetBuilder("Firidium")
            .setBase(DFArmourSet.Native_Iron)
            .setHelmet(DFMaterial.FiridiumHelmet)
            .setChestplate(DFMaterial.FiridiumChestplate)
            .setLeggings(DFMaterial.FiridiumLeggings)
            .setBoots(DFMaterial.FiridiumBoots)
            .setSetBonus("Attackers get set on fire.")
            .addMeta(new FiridiumMeta())
            .create();

    public static final DFArmourSet Obsidian = new DFArmourSetBuilder("Obsidian")
            .setBase(DFArmourSet.Native_Netherite)
            .setHelmet(DFMaterial.ObsidianHelmet)
            .setChestplate(DFMaterial.ObsidianChestplate)
            .setLeggings(DFMaterial.ObsidianLeggings)
            .setBoots(DFMaterial.ObsidianBoots)
            .setSetBonus("Resistance I")
            .addMeta(new ResistanceMeta())
            .create();

    public static boolean hasArmourSetEquipped(Player plr, DFArmourSet set) {
        PlayerInventory inv = plr.getInventory();
        ItemStack helmet = inv.getHelmet();
        ItemStack chestplace = inv.getChestplate();
        ItemStack leggings = inv.getLeggings();
        ItemStack boots = inv.getBoots();
        return compareArmourPieces(helmet, set.getHelmet())
                && compareArmourPieces(chestplace, set.getChestplate())
                && compareArmourPieces(leggings, set.getLeggings())
                && compareArmourPieces(boots, set.getBoots())
                && set.runAdditionalQualifications(plr, helmet, chestplace, leggings, boots);
    }

    public static boolean compareArmourPieces(ItemStack item1, ItemStack item2) {
        if (item1 == null) return false;
        if (item2 == null) return false;

        DFMaterial dfm1 = DFItemUtils.getDFMaterial(item1);
        DFMaterial dfm2 = DFItemUtils.getDFMaterial(item2);

        if ((dfm1 == null && dfm2 != null) || (dfm1 != null && dfm2 == null)) return false;

        if (dfm1 == null && dfm2 == null) {
            return item1.getType() == item2.getType();
        }

        return Objects.equals(dfm1.getNamedId(), dfm2.getNamedId());
    }

    public static boolean hasArmourSetEquippedWithSetBonus(Player plr) {
        for (DFArmourSet set : DFArmourSetIndex) {
            if (!set.hasSetBonus()) continue;
            if (set.playerHasEquipped(plr)) {
                return true;
            }
        }
        return false;
    }


    @Nullable
    public static DFArmourSet getArmourSetEquipped(Player plr) {
        for (DFArmourSet set : DFArmourSetIndex) {
            if (hasArmourSetEquipped(plr, set)) return set;
        }
        return null;
    }

    private final String name;

    private final ItemStack helmet;
    private final ItemStack chestplate;
    private final ItemStack leggings;
    private final ItemStack boots;
    private final String base;

    private final List<DFArmourSetMeta> metas;

    private final String setBonus;
    private final boolean hasSetBonus;

    public DFArmourSet(
            String name,
            ItemStack helmet,
            ItemStack chestplate,
            ItemStack leggings,
            ItemStack boots,
            String base,
            String setBonusText,
            boolean hasSetBonus,
            List<DFArmourSetMeta> metas
    )
    {
        this.name = name;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.base = base;
        this.hasSetBonus = hasSetBonus;
        this.setBonus = setBonusText;
        this.metas = metas;
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public ItemStack getHelmet() {
        return this.helmet;
    }

    @Nullable
    public ItemStack getChestplate() {
        return this.chestplate;
    }

    @Nullable
    public ItemStack getLeggings() {
        return this.leggings;
    }

    @Nullable
    public ItemStack getBoots() {
        return this.boots;
    }

    @Nullable
    public String getBase() {
        return this.base;
    }

    @Nullable
    public String getSetBonusText() {
        return this.setBonus;
    }

    public List<DFArmourSetMeta> getMeta() {return this.metas;}

    public boolean hasMeta() {return !this.metas.isEmpty();}

    public boolean hasSetBonus() {
        return this.hasSetBonus;
    }

    public boolean isCustom() {
        return this.getBase() != null;
    }

    public boolean playerHasEquipped(Player plr) {
        return hasArmourSetEquipped(plr, this);
    }

    public boolean runAdditionalQualifications(Player plr, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        if (this.hasMeta()) {
            for (DFArmourSetMeta meta : this.getMeta()) {
                if (!meta.AdditionalQualification(plr, this, helmet, chestplate, leggings, boots)) return false;
            }
        }
        return true;
    }

    public boolean itemInSet(ItemStack item) {
        return compareArmourPieces(item, this.getHelmet())
                || compareArmourPieces(item, this.getChestplate())
                || compareArmourPieces(item, this.getLeggings())
                || compareArmourPieces(item, this.getBoots());
    }

    public String toString() {
        // this was for debugging
        String customString = getBase() == null ? "vanilla" : getBase();
        return this.getName()+" set: ("+customString+")";
    }
}
