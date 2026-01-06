package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EntityEquipment;
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
    public final boolean storesEntitySnapshot;

    public EntityBucketMeta(boolean storesEntitySnapshot) {
        this.storesEntitySnapshot = storesEntitySnapshot;
    }

    @Override
    public void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {
        event.setCancelled(true);
        LivingEntity entity = (LivingEntity) event.getRightClicked();

        Material egg = EntityUtils.ConvertEntityToEgg(entity.getType());

        if (egg != null) {
            EntitySnapshot snapshot = entity.createSnapshot();
            if (snapshot == null && storesEntitySnapshot) return;

            Sounds.UseBucket.playSound(plr);
            entity.remove();

            ItemStack eggItem = new ItemStack(egg);

            if (storesEntitySnapshot) {
                SpawnEggMeta meta = (SpawnEggMeta) eggItem.getItemMeta();

                Component customEntityName = entity.customName();
                if (customEntityName != null) meta.displayName(customEntityName);
                meta.setSpawnedEntity(snapshot);

                List<Component> lores = meta.lore();
                if (lores == null) lores = new ArrayList<>();

                if (entity instanceof Villager villager) {
                    lores.add(MiniMessage.miniMessage().deserialize("<grey>Level: "+villager.getVillagerLevel()));
                    lores.add(MiniMessage.miniMessage().deserialize("<grey>Region: "+ StringUtils.NormalCapitalisation(String.valueOf(villager.getVillagerType()))));
                    lores.add(MiniMessage.miniMessage().deserialize("<grey>Profession: "+StringUtils.NormalCapitalisation(String.valueOf(villager.getProfession()))));
                } else if (entity instanceof ZombieVillager villager) {
                    if (villager.isConverting()) lores.add(MiniMessage.miniMessage().deserialize("<light_purple>Treated"));
                    lores.add(MiniMessage.miniMessage().deserialize("<grey>Region: "+StringUtils.NormalCapitalisation(String.valueOf(villager.getVillagerType()))));
                    lores.add(MiniMessage.miniMessage().deserialize("<grey>Profession: "+StringUtils.NormalCapitalisation(String.valueOf(villager.getVillagerProfession()))));
                } else if (entity instanceof Creeper creeper) {
                    if (creeper.isPowered()) lores.add(MiniMessage.miniMessage().deserialize("<aqua>Charged"));
                }

                EntityEquipment equipment = entity.getEquipment();
                if (equipment != null) {
                    ItemStack heldItem = equipment.getItemInMainHand();
                    if (heldItem.getType() != Material.AIR) lores.add(MiniMessage.miniMessage().deserialize("<light_purple>Holding ").append(heldItem.displayName()));
                }

                if (lores.isEmpty()) lores.add(MiniMessage.miniMessage().deserialize("<light_purple>Contains data"));
                meta.setMaxStackSize(1);

                meta.lore(lores);

                eggItem.setItemMeta(meta);
            }

            if (plr.getGameMode() != GameMode.CREATIVE) item.setAmount(item.getAmount() - 1);
            plr.getInventory().addItem(eggItem);
        }
    }

    @Override
    public void BucketEmptyEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEmptyEvent event) {
        event.setItemStack(DFMaterial.EntityBucket.toItemStack());
    }
}
