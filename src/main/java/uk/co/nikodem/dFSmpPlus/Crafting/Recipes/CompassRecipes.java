package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ControlledShapelessRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompassRecipes extends CraftingTemplate {
    public CompassRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        doEndRecipe(recipesToAdd);

        return recipesToAdd;
    }

    public void doEndRecipe(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ControlledShapelessRecipeBuilder()
                        .addIngredient(new CustomItemRepresentation(DFMaterial.LocatorCompass, (itemStack) -> {
                            ItemMeta meta = itemStack.getItemMeta();
                            if (meta == null) return false;
                            List<Component> newLore = new ArrayList<>(DFMaterial.LocatorCompass.getLore());
                            return Objects.equals(meta.lore(), newLore);
                        }))
                        .addIngredient(DFMaterial.EndLocatorCompassModule)
                        .setResult((r) -> {
                                    ItemStack item = DFMaterial.LocatorCompass.toItemStack();
                                    ItemMeta meta = item.getItemMeta();
                                    meta.lore(
                                            List.of(
                                            Component.text("i've been modified lol hehehe"))
                                    );
                                    item.setItemMeta(meta);
                                    return item;
                                }
                        )
                        .build(getInfo())
        );
    }

    @Override
    public String getRecipesID() {
        return "compass";
    }
}
