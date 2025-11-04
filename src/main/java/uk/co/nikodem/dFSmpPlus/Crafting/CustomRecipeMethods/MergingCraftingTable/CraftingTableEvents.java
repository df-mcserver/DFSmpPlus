package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.MergingCraftingTable;

import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;

import java.util.*;

public class CraftingTableEvents {
    public static List<ControlledShapelessRecipe> recipes = new ArrayList<>();

    public static void onCraftingTableEvent(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack[] matrix = inventory.getMatrix();

        for (ControlledShapelessRecipe recipe : recipes) {
            if (doesCraftingInventoryMatchRecipe(matrix, recipe)) {
                ItemStack result = recipe.getTransformer() == null ? recipe.getResult() : recipe.getTransformer().apply(Map.entry(recipe, matrix));
                inventory.setResult(result);
            }
        }
    }

    public static boolean doesCraftingInventoryMatchRecipe(ItemStack[] matrix, ControlledShapelessRecipe recipe) {
        List<CustomItemRepresentation> reprs = new ArrayList<>(Arrays.stream(recipe.getIngredients()).toList());

        if (reprs.isEmpty()) return false;

        for (ItemStack item : matrix) {
            if (item == null) continue;
            Iterator<CustomItemRepresentation> it = reprs.iterator();
            while (it.hasNext()) {
                CustomItemRepresentation repr = it.next();
                if (repr.runCheck(item)) {
                    it.remove();
                    break;
                }
            }
        }
        return reprs.isEmpty();
    }
}
