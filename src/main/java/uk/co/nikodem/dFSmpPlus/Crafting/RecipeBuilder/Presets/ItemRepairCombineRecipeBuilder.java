package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ControlledShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.RecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class ItemRepairCombineRecipeBuilder extends RecipeBuilder {
    private CustomItemRepresentation item;

    public ItemRepairCombineRecipeBuilder setItem(CustomItemRepresentation item) {
        this.item = item;
        return this;
    }

    public ItemRepairCombineRecipeBuilder setItem(Material item) {
        this.item = new CustomItemRepresentation(item);
        return this;
    }

    public ItemRepairCombineRecipeBuilder setItem(ItemStack item) {
        this.item = new CustomItemRepresentation(item);
        return this;
    }

    public ItemRepairCombineRecipeBuilder setItem(DFMaterial item) {
        this.item = new CustomItemRepresentation(item);
        return this;
    }

    @Override
    public Recipe build(RecipeTemplateInfo info, String extra) {
        return new ControlledShapelessRecipeBuilder()
                .addIngredient(item)
                .addIngredient(item)
                .setResult(item)
                .setTransformer((data) -> {
                    ItemStack[] matrix = data.getValue();

                    int itemCount = 0;
                    ItemStack itemA = null;
                    ItemStack itemB = null;

                    for (ItemStack item : matrix) {
                        if (item == null) continue;
                        itemCount++;
                        if (itemCount == 1) itemA = item;
                        else itemB = item;
                    }

                    if (itemCount != 2) return ItemStack.of(Material.AIR);

                    Damageable metaA = (Damageable) itemA.getItemMeta();
                    Damageable metaB = (Damageable) itemB.getItemMeta();

                    if (metaA == null || metaB == null) return ItemStack.of(Material.AIR);
                    int maxDamageA = metaA.hasMaxDamage() ? metaA.getMaxDamage() : itemA.getType().getMaxDurability();
                    int maxDamageB = metaB.hasMaxDamage() ? metaB.getMaxDamage() : itemB.getType().getMaxDurability();
                    if (maxDamageA != maxDamageB) return ItemStack.of(Material.AIR);

                    int finalDamage = maxDamageA - ((maxDamageA - metaA.getDamage()) + (maxDamageA - metaB.getDamage()));

                    ItemStack result = item.getItem();
                    Damageable metaC = (Damageable) result.getItemMeta();
                    if (metaC == null) return ItemStack.of(Material.AIR);

                    metaC.setDamage(Math.max(finalDamage, 0));
                    result.setItemMeta(metaC);

                    return result;
                })
                .build(info, "Repair"+extra);
    }

    @Override
    public Recipe build(RecipeTemplateInfo info) {
        return build(info, "");
    }
}
