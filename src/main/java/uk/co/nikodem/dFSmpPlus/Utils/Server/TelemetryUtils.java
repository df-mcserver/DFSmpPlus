package uk.co.nikodem.dFSmpPlus.Utils.Server;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Global.Data.TelemetryData;

import java.util.List;
import java.util.Objects;

import static uk.co.nikodem.dFSmpPlus.Constants.UUIDs.sebUUID;

public class TelemetryUtils {
    public static void increasePlayerJoins(int amnt) {
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
        data.telemetry_playerJoins += amnt;
        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }

    public static void increaseSmelting(FurnaceSmeltEvent event) {
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
        ItemStack result = event.getResult();
        data.telemetry_itemsCooked += result.getAmount();
        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }

    public static void increaseCrafting(CraftItemEvent event) {
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
        ItemStack result = event.getCurrentItem();
        if (result == null) return;
        data.telemetry_timesCrafted++;
        data.telemetry_itemsCrafted += result.getAmount();
        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }

    public static void increaseObsidianToolsMade(int amnt) {
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
        data.telemetry_obsidianToolsMade += amnt;
        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }

    public static void increaseBlocks(BlockPlaceEvent event) {
        Block block = event.getBlock();
        List<Material> acceptableCrops = List.of(Material.WHEAT, Material.BEETROOTS, Material.CARROTS, Material.POTATOES, Material.PUMPKIN_STEM, Material.MELON_STEM);
        if (acceptableCrops.contains(block.getType())) {
            TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
            data.telemetry_cropsPlanted += 1;
            if (block.getType() == Material.MELON_STEM) data.telemetry_melonPlanted += 1;

            DFSmpPlus.globalDataHandler.writeTelemetryData(data);

        }
    }

    public static void increaseDeath(Entity dead, Entity killer) {
        if (dead == null) return;
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();

        if (killer == null) {
            data.telemetry_playerDeaths++;
            return;
        }

        boolean killerIsPlayer = false;
        if (killer instanceof Player) {
            killerIsPlayer = true;
            if (Objects.equals(killer.getUniqueId().toString(), sebUUID)) data.telemetry_playersKilledBySeb++;
        }

        if (dead instanceof Player) {
            if (killerIsPlayer) data.telemetry_playerKilledInPVP++;
            data.telemetry_playerDeaths++;
            if (Objects.equals(dead.getUniqueId().toString(), sebUUID)) data.telemetry_sebDeathCount++;
        } else data.telemetry_enemiesKilled++;

        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }

    public static void updateVampireStage(int stage) {
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
        if (stage > data.telemetry_highestVampireStage) data.telemetry_highestVampireStage = stage;
        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }

    public static void updateKillStreak(int killstreak) {
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
        if (killstreak > data.telemetry_highestKillStreak) data.telemetry_highestKillStreak = killstreak;
        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }

    public static void increaseChiselUses(int amnt) {
        TelemetryData data = DFSmpPlus.globalDataHandler.getTelemetryData();
        data.telemetry_timesChiselUsed += amnt;
        DFSmpPlus.globalDataHandler.writeTelemetryData(data);
    }
}
