package uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import uk.co.nikodem.dFSmpPlus.Crafting.OnCraft;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static uk.co.nikodem.dFSmpPlus.Constants.MagicNumbers.*;

public class SmithingTableEvents {

    public static List<SmithingTableRecipe> recipes = new ArrayList<>();

    public static void onSmithingTableEvent(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();

        // use
        ItemStack template = inventory.getItem(SmithingTable_Template);
        ItemStack base = inventory.getItem(SmithingTable_Base);
        ItemStack addition = inventory.getItem(SmithingTable_Addition);

        if (template == null) return;
        if (base == null) return;
        if (addition == null) return;

        // smithing table is full, expecting output

        for (SmithingTableRecipe recipe : recipes) {
            boolean shouldWork = true;

            if (!recipe.getBase().runBaseCheck(base)) shouldWork = false;
            if (shouldWork && !recipe.getTemplate().runBaseCheck(template)) shouldWork = false;
            if (shouldWork && !recipe.getAddition().runBaseCheck(addition)) shouldWork = false;

            if (shouldWork) {
                // the recipe is a match, running extra checks and narrowing the recipe
                // to make sure it is valid

                boolean willWork = true;

                if (!recipe.getBase().runCheck(base)) willWork = false;
                if (willWork && !recipe.getTemplate().runCheck(template)) willWork = false;
                if (willWork && !recipe.getAddition().runCheck(addition)) willWork = false;

                if (willWork) {

                    // set the item to the (transformed) item

                    ItemStack result = recipe.transformResult();

                    // apply standard changes

                    ItemMeta meta = result.getItemMeta();
                    ItemMeta baseMeta = base.getItemMeta();
                    if (baseMeta.hasEnchants()) {
                        for (Map.Entry<Enchantment, Integer> entry : base.getEnchantments().entrySet()) {
                            Enchantment ench = entry.getKey();
                            int level = entry.getValue();
                            if (!meta.hasEnchant(ench)) {
                                meta.addEnchant(ench, level, true);
                            }

                        }
                    }

                    if (DFItemUtils.itemIsRenamed(base)) {
                        meta.displayName(baseMeta.displayName());
                    }

                    if (meta instanceof Damageable damageableA && baseMeta instanceof Damageable damageableB) {
                        damageableA.setDamage(damageableB.getDamage());
                        result.setItemMeta(meta);
                    }

                    event.setResult(result);
                } else {
                    // remove result if the recipe should work, but fails to do so

                    event.setResult(new ItemStack(Material.AIR));
                }
            }
        }
    }

    public static void onSmithingTableCraft(InventoryClickEvent event) {
        Inventory inv = event.getClickedInventory();
        if (inv == null) return;
        if (inv.getType() != InventoryType.SMITHING) return;

        SmithingInventory inventory = (SmithingInventory) inv;
        if (event.getRawSlot() != 3) return;

        HumanEntity plr = event.getWhoClicked();

        ItemStack item = inventory.getResult();

        OnCraft.OnCraft(item, (Player) plr, null);
    }
}
