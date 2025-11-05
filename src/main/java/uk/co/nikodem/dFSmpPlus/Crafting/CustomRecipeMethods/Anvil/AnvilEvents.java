package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil;

import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AnvilEvents {
    public static List<AnvilRecipe> recipes = new ArrayList<>();

    public static void onAnvilEvent(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        ItemStack base = inventory.getFirstItem();
        ItemStack addition = inventory.getSecondItem();

        if (base == null) return;
        if (addition == null) return;

        for (AnvilRecipe recipe : recipes) {
            if (doesAnvilInventoryMatchRecipe(base, addition, recipe)) {
                ItemStack result;
                if (recipe.getTransformer() == null) {
                    result = recipe.getResult().clone();
                } else {
                    Map.Entry<ItemStack, ItemStack> data = recipe.getTransformer().apply(
                            Map.entry(recipe, Map.entry(base, addition))
                    );

                    result = data.getKey().clone();
                }

                String renameText = event.getView().getRenameText();
                if (!Objects.equals(renameText, "")) {
                    ItemMeta meta = result.getItemMeta();
                    meta.displayName(Component.text(renameText));
                    result.setItemMeta(meta);
                }
                event.setResult(result);
            }
        }
    }

    public static void onAnvilInventoryClick(InventoryClickEvent event) {

    }

    public static boolean doesAnvilInventoryMatchRecipe(ItemStack base, ItemStack addition, AnvilRecipe recipe) {
        return recipe.getBase().runCheck(base) && recipe.getAddition().runCheck(addition);
    }
}
