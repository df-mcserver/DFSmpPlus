package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.MergingCraftingTable;

import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.CustomItemRepresentation;

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

//        System.out.println("PREPARING");
//
//        ItemStack i1 = null;
//        ItemStack i2 = null;
//
//        for (ItemStack i : items) {
//            System.out.println("A ");
//            if (i != null) {
//                System.out.println("B "+i.getType());
//                if (i1 == null) i1 = i;
//                else if (i2 == null) i2 = i;
//                else return;
//            }
//        }
//
//        if (i1 == null || i2 == null) return;
//
//        System.out.println("BOTH NOT NULL");
//
//        DFMaterial m1 = DFItemUtils.getDFMaterial(i1);
//        DFMaterial m2 = DFItemUtils.getDFMaterial(i2);
//
//        if (m1 == null || m2 == null) return;
//
//        System.out.println("BOTH NOT NULL");
//
//        if (m1.equals(DFMaterial.LocatorCompass) && m2.equals(DFMaterial.EndLocatorCompassModule)
//        || m2.equals(DFMaterial.LocatorCompass) && m1.equals(DFMaterial.EndLocatorCompassModule)) {
//            inventory.setResult(DFMaterial.ComicallyLargeShovel.toItemStack());
//            System.out.println("SET RESULT");
//        }
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
