package uk.co.nikodem.dFSmpPlus.Player.DFUpdates;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialUpdater;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uk.co.nikodem.dFSmpPlus.Constants.Enums.UpdateResult.*;

public class OnPlayerPickUpItem implements Listener {
    @EventHandler
    public void OnPlayerPickUpItem(PlayerAttemptPickupItemEvent e) {
        ItemStack i = e.getItem().getItemStack();

        if (i.getType() == Material.BUNDLE) {
            BundleMeta meta = (BundleMeta) i.getItemMeta();
            List<ItemStack> newitems = new ArrayList<>();
            for (ItemStack item : meta.getItems()) {
                Enums.UpdateResult res = DFMaterialUpdater.doUpdate(item, false);
                if (!Objects.equals(res, DELETED)) newitems.add(item);
            }
            meta.setItems(newitems);
            i.setItemMeta(meta);
        } else {
            Enums.UpdateResult res = DFMaterialUpdater.doUpdate(i);
            if (Objects.equals(res, DELETED)) {
                e.getItem().remove();
                e.setCancelled(true);
            }
        }
    }
}
