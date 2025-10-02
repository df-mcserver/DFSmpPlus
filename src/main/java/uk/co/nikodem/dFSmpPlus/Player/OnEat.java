package uk.co.nikodem.dFSmpPlus.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static uk.co.nikodem.dFSmpPlus.Constants.Keys.fishPlayer;

public class OnEat implements Listener {
    @EventHandler
    public void OnEat(PlayerItemConsumeEvent event) {
        ItemStack stack = event.getItem();
        ItemMeta meta = stack.getItemMeta();

        Boolean bool = meta.getPersistentDataContainer().get(fishPlayer, PersistentDataType.BOOLEAN);

        if (Boolean.TRUE.equals(bool)) {
            event.getPlayer().addPotionEffect(
                    new PotionEffect(PotionEffectType.NAUSEA, 250, 255, false), false);
            event.getPlayer().addPotionEffect(
                    new PotionEffect(PotionEffectType.POISON, 100, 255, false), false);
            event.getPlayer().addPotionEffect(
                    new PotionEffect(PotionEffectType.HUNGER, 250, 5, false), false);
        }
    }
}
