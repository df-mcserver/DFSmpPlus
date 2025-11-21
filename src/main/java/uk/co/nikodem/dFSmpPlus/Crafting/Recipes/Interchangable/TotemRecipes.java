package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.Interchangable;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Etc.DirectConversionRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.StonecutterRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class TotemRecipes extends CraftingTemplate {
    public TotemRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        List<NamespacedKey> allTotemModels = DFMaterial.CustomTotem.getPossibleModels();

        int i = 0;
        for (NamespacedKey model : allTotemModels) {
            recipesToAdd.add(
                    new StonecutterRecipeBuilder()
                            .setSource(new RecipeChoice.ExactChoice(ItemStack.of(Material.TOTEM_OF_UNDYING)))
                            .setOutput(DFItemUtils.setModel(ItemStack.of(Material.TOTEM_OF_UNDYING), model))
                            .build(getInfo(), "totem"+i)
            );

            recipesToAdd.add(
                    new DirectConversionRecipeBuilder()
                            .setSource(DFItemUtils.setModel(ItemStack.of(Material.TOTEM_OF_UNDYING), model))
                            .setOutput(ItemStack.of(Material.TOTEM_OF_UNDYING))
                            .setGroup("totems-reverse")
                            .setCategory(CraftingBookCategory.EQUIPMENT)
                            .build(getInfo(), "totem-reverse"+i)
            );
            i++;
        }

        return recipesToAdd;
    }

    @Override
    public String getRecipesID() {
        return "totems-interchangeable";
    }
}
