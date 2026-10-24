package uk.co.nikodem.dFSmpPlus.Events.Player.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ArmoredHorseInventory;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.BackpackMeta;
import uk.co.nikodem.dFSmpPlus.Accessories.Player.AccessoryUI;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc.ITTDDTA;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.Anvil.AnvilEvents;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.SmithingTableEvents;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Player.EnderEggStoragePreventer;
import uk.co.nikodem.dFSmpPlus.SetBonuses.SetBonusText;

public class InventoryClickEvent implements Listener {
    @EventHandler
    public void InventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent event) {
        EnderEggStoragePreventer.onInventoryClick(event);
        SetBonusText.onInventoryClick(event);
        AnvilEvents.onAnvilInventoryClick(event);
        SmithingTableEvents.onSmithingTableCraft(event);
        AccessoryUI.onInventoryClick(event);
        BackpackMeta.onInventoryClick(event);

        if (event.getInventory() instanceof ArmoredHorseInventory horseInventory) {
            Bukkit.getScheduler().runTaskLater(DFSmpPlus.getPlugin(), () -> {
                ItemStack item = horseInventory.getArmor();
                if (item == null) return;
                if (!item.isEmpty()) DFAdvancementsHandler.grantAdvancement((Player) event.getWhoClicked(), ITTDDTA.class);
            }, 1L);
        }
    }
}
