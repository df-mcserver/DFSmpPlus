package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import uk.co.nikodem.dFSmpPlus.Entities.EntityUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;
import uk.co.nikodem.dFSmpPlus.Utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityBucketMeta implements DFMaterialMeta {
    @Override
    public void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {
        event.setCancelled(true);
        Entity entity = event.getRightClicked();

        Material egg = EntityUtils.ConvertEntityToEgg(entity.getType());

        if (egg != null) {
            EntitySnapshot snapshot = entity.createSnapshot();
            if (snapshot == null) return;

            Sounds.UseBucket.playSound(plr);
            entity.remove();

            ItemStack eggItem = new ItemStack(egg);
            SpawnEggMeta meta = (SpawnEggMeta) eggItem.getItemMeta();

            Component customEntityName = entity.customName();
            if (customEntityName != null) meta.displayName(customEntityName);
            meta.setSpawnedEntity(snapshot);

            if (entity instanceof Villager villager) {
                List<Component> lores = meta.lore();
                if (lores == null) lores = new ArrayList<>();
                lores.add(MiniMessage.miniMessage().deserialize("<grey>Level: "+villager.getVillagerLevel()));
                lores.add(MiniMessage.miniMessage().deserialize("<grey>Region: "+StringUtils.NormalCapitalisation(String.valueOf(villager.getVillagerType()))));
                lores.add(MiniMessage.miniMessage().deserialize("<grey>Profession: "+StringUtils.NormalCapitalisation(String.valueOf(villager.getProfession()))));

                meta.lore(lores);
            }

            eggItem.setItemMeta(meta);

            if (plr.getGameMode() == GameMode.CREATIVE) {
                plr.getInventory().addItem(eggItem);
            } else {
                plr.getInventory().setItemInMainHand(eggItem);
            }
        }
    }

    @Override
    public void BucketUseEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {
        event.setCancelled(true);
    }
}
