package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeTemplateInfo;

public abstract class RecipeBuilder {
    public NamespacedKey MakeNamespacedKey(String id) {
        return Keys.createCraftingKey(id);
    }

    public String MakeId(RecipeTemplateInfo info, ItemStack item, String extra) {
        return (item.getType().toString().toLowerCase().replace("_", "")+extra+"-"+info.getIdTag()).toLowerCase();
    }

    public abstract Recipe build(RecipeTemplateInfo info, String extra);
    public abstract Recipe build(RecipeTemplateInfo info);
}
