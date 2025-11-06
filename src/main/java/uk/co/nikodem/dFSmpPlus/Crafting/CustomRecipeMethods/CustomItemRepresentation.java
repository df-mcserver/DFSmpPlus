package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.Objects;
import java.util.function.Function;

public class CustomItemRepresentation {
    private final ItemStack item;
    private boolean strict = true;
    private Function<ItemStack, Boolean> check;

    public CustomItemRepresentation(ItemStack item) {
        this.item = item;
    }

    public CustomItemRepresentation(Material item) {
        this.item = new ItemStack(item);
    }

    public CustomItemRepresentation(DFMaterial item) {
        this.item = item.toItemStack();
    }

    public CustomItemRepresentation(ItemStack item, boolean strict) {
        this.item = item;
        this.strict = strict;
    }

    public CustomItemRepresentation(Material item, boolean strict) {
        this.item = new ItemStack(item);
        this.strict = strict;
    }

    public CustomItemRepresentation(DFMaterial item, boolean strict) {
        this.item = item.toItemStack();
        this.strict = strict;
    }

    public CustomItemRepresentation(ItemStack item, Function<ItemStack, Boolean> check) {
        this.item = item;
        this.check = check;
    }

    public CustomItemRepresentation(Material item, Function<ItemStack, Boolean> check) {
        this.item = new ItemStack(item);
        this.check = check;
    }

    public CustomItemRepresentation(DFMaterial item, Function<ItemStack, Boolean> check) {
        this.item = item.toItemStack();
        this.check = check;
    }

    public CustomItemRepresentation(ItemStack item, Function<ItemStack, Boolean> check, boolean strict) {
        this.item = item;
        this.check = check;
        this.strict = strict;
    }

    public CustomItemRepresentation(Material item, Function<ItemStack, Boolean> check, boolean strict) {
        this.item = new ItemStack(item);
        this.check = check;
        this.strict = strict;
    }

    public CustomItemRepresentation(DFMaterial item, Function<ItemStack, Boolean> check, boolean strict) {
        this.item = item.toItemStack();
        this.check = check;
        this.strict = strict;
    }

    public CustomItemRepresentation setStrict(boolean strict) {
        this.strict = strict;
        return this;
    }

    public CustomItemRepresentation setCheck(Function<ItemStack, Boolean> check) {
        this.check = check;
        return this;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public boolean runBaseCheck(ItemStack item) {
        // this is the same check as the dummy check
        // this makes sure that the recipe is recognised
        // and removed if invalid
        if (item == null) return false;
        return item.getType() == this.item.getType();
    }

    public boolean runCheck(ItemStack item) {
        boolean result = false;
        if (item == null) return false;

        if (getStrict()) {
//            result = item.isSimilar(this.item);
            if (item.getType() == this.item.getType()) {
                ItemMeta metaA = item.getItemMeta();
                ItemMeta metaB = this.item.getItemMeta();

                if (metaA.hasCustomModelData() && metaB.hasCustomModelData()) {
                    if (metaA.getCustomModelData() == metaB.getCustomModelData()) {
                        result = true;
                    }
                } else {
                    result = (!metaA.hasCustomModelData() || metaB.hasCustomModelData()) && (metaA.hasCustomModelData() || !metaB.hasCustomModelData());
                }

                if (result) {
                    DFMaterial materialA = DFItemUtils.getDFMaterial(item);
                    DFMaterial materialB = DFItemUtils.getDFMaterial(this.item);

                    if ((materialA != null && materialB == null) || (materialA == null && materialB != null)) {
                        // both materials differ
                        result = false;
                    } else {
                        if (materialA != null && materialB != null) {
                            if (!Objects.equals(materialA.getNamedId(), materialB.getNamedId())) {
                                result = false;
                            }
                        }
                    }
                }
            }
        }
        else result = item.getType() == this.item.getType();

        if (result) {
            if (check == null) return true;
            return this.check.apply(item);
        } else return result;
    }

    public boolean getStrict() {
        return this.strict;
    }
}
