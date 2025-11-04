package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.MergingCraftingTable;

import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;

public class CraftingTableEvents {
    public static List<ControlledShapelessRecipe> recipes = new ArrayList<>();

    public static void onCraftingTableEvent(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack[] items = inventory.getMatrix();

        for (ControlledShapelessRecipe recipe : recipes) {
            if (doesCraftingInventoryMatchRecipe(items, recipe)) {
                ItemStack result = recipe.getResult();
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

        int matches = 0;
        for (CustomItemRepresentation representation : recipe.getIngredients()) {
            if (representation == null) continue;
            for (ItemStack item : matrix) {
                if (item == null) continue;
                if (representation.runCheck(item)) matches++;
            }
        }

        if (matches == recipe.getIngredients().length) return true;
        return false;
    }
}
