package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Repair;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.Transformer.AnvilTransformResponse;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.AnvilRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.RecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

public class ItemRepairAnvilRecipeBuilder extends RecipeBuilder {
    private CustomItemRepresentation item;
    private CustomItemRepresentation addition;

    public ItemRepairAnvilRecipeBuilder setItem(CustomItemRepresentation item) {
        this.item = item;
        return this;
    }

    public ItemRepairAnvilRecipeBuilder setItem(Material item) {
        this.item = new CustomItemRepresentation(item);
        return this;
    }

    public ItemRepairAnvilRecipeBuilder setItem(ItemStack item) {
        this.item = new CustomItemRepresentation(item);
        return this;
    }

    public ItemRepairAnvilRecipeBuilder setItem(DFMaterial item) {
        this.item = new CustomItemRepresentation(item);
        return this;
    }

    public ItemRepairAnvilRecipeBuilder setAddition(CustomItemRepresentation addition) {
        this.addition = addition;
        return this;
    }

    public ItemRepairAnvilRecipeBuilder setAddition(Material addition) {
        this.addition = new CustomItemRepresentation(addition);
        return this;
    }

    public ItemRepairAnvilRecipeBuilder setAddition(ItemStack addition) {
        this.addition = new CustomItemRepresentation(addition);
        return this;
    }

    public ItemRepairAnvilRecipeBuilder setAddition(DFMaterial addition) {
        this.addition = new CustomItemRepresentation(addition);
        return this;
    }

    // we don't use these build functions
    // because it's an anvil recipe - it doesn't have a Recipe class
    @Override
    public Recipe build(RecipeTemplateInfo info, String extra) {
        return null;
    }

    @Override
    public Recipe build(RecipeTemplateInfo info) {
        return null;
    }

    public void assign() {
        new AnvilRecipeBuilder()
                .setBase(item)
                .setAddition(addition)
                .setResult(item)
                .setTransformer((data) -> {
                    ItemStack base = data.getBase();
                    ItemStack addition = data.getAddition();
                    Damageable meta = (Damageable) base.getItemMeta();
                    if (meta == null) return null;

                    if (meta.getDamage() == 0) return null;

                    int maxDamage = meta.hasMaxDamage() ? meta.getMaxDamage() : base.getType().getMaxDurability();
                    int additionCount = addition.getAmount();
                    int uses = 0;
                    int damageLeft = meta.getDamage();
                    int repairAmount = maxDamage / 4;

                    for (int i = 0; i < additionCount; i++) {
                        if (damageLeft <= 0) break;
                        damageLeft = damageLeft - repairAmount;
                        uses++;
                    }

                    meta.setDamage(Math.max(damageLeft, 0));

                    ItemStack result = base.clone();
                    result.setItemMeta(meta);

                    ItemStack additionResult = addition.clone();
                    additionResult.setAmount(addition.getAmount() - uses);

                    return new AnvilTransformResponse(
                            result, additionResult
                    );
                })
                .assign();
    }
}
