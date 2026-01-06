package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import uk.co.nikodem.dFSmpPlus.Entities.EntityUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.PresetSoundData;
import uk.co.nikodem.dFSmpPlus.Utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityBucketMeta implements DFMaterialMeta {
    public final boolean storesEntitySnapshot;
    public final PresetSoundData sound;

    public EntityBucketMeta(boolean storesEntitySnapshot, PresetSoundData sound) {
        this.storesEntitySnapshot = storesEntitySnapshot;
        this.sound = sound;
    }

    @Override
    public void ItemUseOnEntity(Player plr, DFMaterial material, ItemStack item, PlayerInteractEntityEvent event) {
        event.setCancelled(true);
        LivingEntity entity = (LivingEntity) event.getRightClicked();

        Material egg = EntityUtils.ConvertEntityToEgg(entity.getType());

        if (egg != null) {
            EntitySnapshot snapshot = entity.createSnapshot();
            if (snapshot == null && storesEntitySnapshot) return;

            sound.playSound(plr);
            entity.remove();

            ItemStack eggItem = new ItemStack(egg);

            if (storesEntitySnapshot) {
                SpawnEggMeta meta = (SpawnEggMeta) eggItem.getItemMeta();

                Component customEntityName = entity.customName();
                if (customEntityName != null) meta.displayName(customEntityName);
                meta.setSpawnedEntity(snapshot);

                List<Component> lores = meta.lore();
                if (lores == null) lores = new ArrayList<>();

                switch (entity) {
                    case Villager villager -> {
                        lores.add(MiniMessage.miniMessage().deserialize("<grey>Level: " + villager.getVillagerLevel()));
                        lores.add(MiniMessage.miniMessage().deserialize("<grey>Region: " + StringUtils.NormalCapitalisation(String.valueOf(villager.getVillagerType()))));
                        lores.add(MiniMessage.miniMessage().deserialize("<grey>Profession: " + StringUtils.NormalCapitalisation(String.valueOf(villager.getProfession()))));
                    }
                    case ZombieVillager villager -> {
                        if (villager.isConverting())
                            lores.add(MiniMessage.miniMessage().deserialize("<light_purple>Treated"));
                        lores.add(MiniMessage.miniMessage().deserialize("<grey>Region: " + StringUtils.NormalCapitalisation(String.valueOf(villager.getVillagerType()))));
                        lores.add(MiniMessage.miniMessage().deserialize("<grey>Profession: " + StringUtils.NormalCapitalisation(String.valueOf(villager.getVillagerProfession()))));
                    }
                    case Creeper creeper -> {
                        if (creeper.isPowered()) lores.add(MiniMessage.miniMessage().deserialize("<aqua>Charged"));
                    }
                    case Enderman enderman -> {
                        BlockData heldBlockData = enderman.getCarriedBlock();
                        if (heldBlockData != null) {
                            ItemStack heldItem = ItemStack.of(heldBlockData.getPlacementMaterial());
                            if (heldItem.getType() != Material.AIR)
                                lores.add(MiniMessage.miniMessage().deserialize("<light_purple>Holding ").append(heldItem.displayName()));
                        }
                    }
                    case Bee bee -> {
                        if (bee.hasNectar()) lores.add(MiniMessage.miniMessage().deserialize("<dark_aqua>Has nectar"));
                        if (bee.hasStung()) lores.add(MiniMessage.miniMessage().deserialize("<red>Has stung"));
                    }
                    default -> {
                    }
                }

                if (entity instanceof Ageable ageable) {
                    if (!ageable.isAdult()) lores.add(MiniMessage.miniMessage().deserialize("<dark_aqua>Baby"));
                }

                EntityEquipment equipment = entity.getEquipment();
                if (equipment != null) { // doesn't work for endermen
                    ItemStack heldItem = equipment.getItemInMainHand();
                    if (heldItem.getType() != Material.AIR) lores.add(MiniMessage.miniMessage().deserialize("<light_purple>Holding ").append(heldItem.displayName()));
                }

                if (lores.isEmpty()) lores.add(MiniMessage.miniMessage().deserialize("<light_purple>Contains data"));
                meta.setMaxStackSize(1);

                meta.lore(lores);

                eggItem.setItemMeta(meta);
            }

            plr.setCooldown(eggItem, 20);

            if (item.getAmount() == 1 && plr.getGameMode() != GameMode.CREATIVE) {
                plr.getInventory().setItemInMainHand(eggItem);
            } else {
                if (plr.getGameMode() != GameMode.CREATIVE) item.setAmount(item.getAmount() - 1);
                plr.getInventory().addItem(eggItem);
            }
        }
    }

    @Override
    public void BucketEmptyEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketEmptyEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void BucketFillEvent(Player plr, DFMaterial material, ItemStack item, PlayerBucketFillEvent event) {
        event.setCancelled(true);
    }
}
