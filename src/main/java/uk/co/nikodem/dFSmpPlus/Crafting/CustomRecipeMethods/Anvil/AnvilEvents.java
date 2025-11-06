package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.view.AnvilView;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.Transformer.AnvilTransformData;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.Transformer.AnvilTransformResponse;
import uk.co.nikodem.dFSmpPlus.Crafting.OnCraft;

import java.util.*;

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
                    AnvilTransformResponse data = recipe.getTransformer().apply(
                            new AnvilTransformData(recipe, base, addition)
                    );

                    if (data == null) continue;

                    result = data.getResult().clone();
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
        Inventory inv = event.getClickedInventory();
        if (inv == null) return;
        if (inv.getType() != InventoryType.ANVIL) return;
        if (event.getRawSlot() != 2) return;

        AnvilInventory inventory = (AnvilInventory) inv;

        HumanEntity plr = event.getWhoClicked();

        AnvilView view = (AnvilView) event.getView();

        ItemStack base = inventory.getFirstItem();
        ItemStack addition = inventory.getSecondItem();

        if (base == null || addition == null) return;

        Block block = inventory.getLocation().getBlock();

        for (AnvilRecipe recipe : recipes) {
            if (doesAnvilInventoryMatchRecipe(base, addition, recipe)) {
                ItemStack result;
                ItemStack additionResult = null;
                if (recipe.getTransformer() == null) {
                    result = recipe.getResult().clone();
                } else {
                    AnvilTransformResponse data = recipe.getTransformer().apply(
                            new AnvilTransformData(recipe, base, addition)
                    );

                    if (data == null) continue;

                    result = data.getResult().clone();
                    additionResult = data.getAdditionResult().clone();
                }

                if (!plr.getItemOnCursor().getType().equals(Material.AIR) && !event.isShiftClick()) return;

                String renameText = view.getRenameText();
                if (!Objects.equals(renameText, "")) {
                    ItemMeta meta = result.getItemMeta();
                    meta.displayName(Component.text(renameText));
                    result.setItemMeta(meta);
                }

                OnCraft.OnCraft(result, (Player) plr, null);

                if (event.isShiftClick()) plr.getInventory().addItem(result);
                else plr.setItemOnCursor(result);

                inventory.setFirstItem(ItemStack.of(Material.AIR));
                inventory.setSecondItem(additionResult == null ? ItemStack.of(Material.AIR) : additionResult);
                inventory.setResult(ItemStack.of(Material.AIR));
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1, 1);
                if (new Random().nextInt(3) == 0 && plr.getGameMode() != GameMode.CREATIVE) {
                    String data = block.getBlockData().getAsString();
                    switch (block.getType()) {
                        case ANVIL:
                            block.setBlockData(Bukkit.createBlockData(data.replace("anvil", "chipped_anvil")));
                            break;
                        case CHIPPED_ANVIL:
                            block.setBlockData(Bukkit.createBlockData(data.replace("chipped_anvil", "damaged_anvil")));
                            break;
                        case DAMAGED_ANVIL:
                            block.getWorld().playSound(block.getLocation(), Sound.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1, 1);
                            block.breakNaturally(new ItemStack(Material.AIR));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public static boolean doesAnvilInventoryMatchRecipe(ItemStack base, ItemStack addition, AnvilRecipe recipe) {
        return recipe.getBase().runCheck(base) && recipe.getAddition().runCheck(addition);
    }
}
