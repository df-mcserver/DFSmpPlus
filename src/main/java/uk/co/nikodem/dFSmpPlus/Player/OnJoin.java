package uk.co.nikodem.dFSmpPlus.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import uk.co.nikodem.dFSmpPlus.Constants.Enums;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialUpdater;
import uk.co.nikodem.dFSmpPlus.Player.LifeCrystals.LifeCrystalManager;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uk.co.nikodem.dFSmpPlus.Constants.Enums.UpdateResult.*;

public class OnJoin implements Listener {

    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        Player plr = e.getPlayer();

        for (CraftingTemplate template : DFSmpPlus.craftingTemplateList) {
            template.discoverRecipes(plr);
        }

        LifeCrystalManager.updatePlayerLifeCrystalsModifier(plr);

        boolean atleastOneUpdate = false;
        boolean atleastOneDeletion = false;

        for (ItemStack i : plr.getInventory()) {
            if (i != null) {
                if (i.getType() == Material.BUNDLE) {
                    BundleMeta meta = (BundleMeta) i.getItemMeta();
                    List<ItemStack> newitems = new ArrayList<>();
                    for (ItemStack item : meta.getItems()) {
                        Enums.UpdateResult res = DFMaterialUpdater.doUpdate(item, false);
                        if (!atleastOneUpdate && Objects.equals(res, UPDATED)) atleastOneUpdate = true;
                        if (!atleastOneDeletion && Objects.equals(res, DELETED)) atleastOneDeletion = true;

                        if (!Objects.equals(res, DELETED)) newitems.add(item);
                    }
                    meta.setItems(newitems);
                    i.setItemMeta(meta);
                } else {
                    Enums.UpdateResult res = DFMaterialUpdater.doUpdate(i);
                    if (!atleastOneUpdate && Objects.equals(res, UPDATED)) atleastOneUpdate = true;
                    if (!atleastOneDeletion && Objects.equals(res, DELETED)) atleastOneDeletion = true;
                }
            }
        }

        if (atleastOneUpdate) {
            Component msg = MiniMessage.miniMessage().deserialize("<aqua>Some items in your inventory have been updated!");
            plr.sendMessage(msg);
            Sounds.Notification.playSoundLocally(plr);
        }

        if (atleastOneDeletion) {
            Component msg = MiniMessage.miniMessage().deserialize("<red>Some items in your inventory have been removed due to invalid data!");
            plr.sendMessage(msg);
            Sounds.Notification.playSoundLocally(plr);
        }
    }
}
