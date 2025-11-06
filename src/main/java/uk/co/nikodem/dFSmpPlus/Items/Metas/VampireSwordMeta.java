package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.GlobalData;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Server.TelemetryUtils;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VampireSwordMeta implements DFMaterialMeta {
    public final int MAX_STAGE = 9;
    public final long KILL_COOLDOWN = TimeUnit.DAYS.toMillis(1);

    @Override
    public void ItemKilledEntity(Player plr, DFMaterial material, Entity entity, EntityDeathEvent event) {
        if (entity instanceof Player target) {
            ItemStack item = plr.getInventory().getItemInMainHand();

            if (!canUpgradeFromTarget(plr, item)) return;

            Integer stage = incrementStage(item);
            if (stage == null) return;
            Sounds.WoodCrash.playSound(plr);
            updateLore(item, stage);
            updateModel(item, stage);
            TelemetryUtils.updateVampireStage(stage);
        }
    };

    @Override
    public void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {
        // work around to buggy attributes
        Integer stage = getStage(weapon);
        if (stage == null) return;

        event.setDamage(event.getDamage() + stage);
    };

    @Nullable
    public Integer getStage(ItemStack item) {
        return DFItemUtils.getInteger(item, Keys.vampireSwordStage);
    }

    @Nullable
    public Integer incrementStage(ItemStack item) {
        Integer currentStage = getStage(item);
        if (currentStage == null) return null;
        if (currentStage + 1 > MAX_STAGE) return null;
        DFItemUtils.set(
                item,
                Keys.vampireSwordStage,
                PersistentDataType.INTEGER,
                currentStage+1
        );

        return currentStage+1;
    }

    public void updateModel(ItemStack item, Integer stage) {
        if (stage == null) return;
        DFItemUtils.setModel(item, "vamp_stage"+stage);
    }

    public void updateLore(ItemStack item, Integer stage) {
        if (item == null) return;
        if (stage == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        List<TextComponent> newLore = new ArrayList<>(DFMaterial.VampireSword.getLore());
        newLore.add(Component.text("  +"+stage+" damage", NamedTextColor.RED));
        meta.lore(newLore);
        item.setItemMeta(meta);
    }

    public void updateMappings(Map<String, Map<String, Long>> mappings) {
        GlobalData data = DFSmpPlus.globalDataHandler.getGlobalData();
        data.vampireSwordMappings = mappings;
        DFSmpPlus.globalDataHandler.writeGlobalData(data);
    }

    public boolean canUpgradeFromTarget(Player plr, ItemStack item) {
        String itemUUID = DFItemUtils.getUUID(item);
        String playerUUID = plr.getUniqueId().toString();
        Map<String, Map<String, Long>> mappings = DFSmpPlus.globalDataHandler.getGlobalData().vampireSwordMappings;
        if (mappings.containsKey(itemUUID)) {
            // check if has been long enough
            Map<String, Long> kills = mappings.get(itemUUID);
            if (kills.containsKey(playerUUID)) {
                long timestamp = kills.get(playerUUID);
                if (new Date().getTime() > timestamp + KILL_COOLDOWN) {
                    // it's been a day
                    kills.replace(playerUUID, new Date().getTime());
                    mappings.replace(itemUUID, kills);
                    updateMappings(mappings);
                    return true;
                } else {
                    return false;
                }
            } else {
                // player hasn't been killed with this sword yet
                kills.put(playerUUID, new Date().getTime());
                mappings.replace(itemUUID, kills);
                updateMappings(mappings);
                return true;
            }
        } else {
            // first kill on the sword
            mappings.put(itemUUID, Map.of(playerUUID, new Date().getTime()));
            updateMappings(mappings);
            return true;
        }
    }
}
