package uk.co.nikodem.dFSmpPlus.World;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.World.Loot.AttemptConditions.*;
import uk.co.nikodem.dFSmpPlus.World.Loot.LootEditAttempt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static uk.co.nikodem.dFSmpPlus.World.Loot.AttemptEdits.PerformLootEdit.addItemToLoot;
import static uk.co.nikodem.dFSmpPlus.World.Loot.AttemptEdits.PerformLootEdit.replaceModifiedSlotsInLoot;

public class PopulateChests {
    private static final Random rand = new Random();
    public static final List<LootEditAttempt> attempts = new ArrayList<>();

    static {
        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new ByRandomChance().setMaxInteger(3))
                        .addCondition(new ByOtherLocationSpecificMaterials()
                                .addMaterial(Material.ANCIENT_DEBRIS)
                                .addMaterial(Material.NETHERITE_INGOT)
                                .addMaterial(Material.NETHERITE_SCRAP)
                                .addMaterial(Material.FLINT_AND_STEEL)
                                .addMaterial(Material.NETHER_WART)
                                .addMaterial(Material.GOLDEN_SWORD)
                                .addMaterial(Material.GLISTERING_MELON_SLICE)
                                .addMaterial(Material.LODESTONE))
                        .addCondition(new ByOtherLocationSpecificMaterials()
                                .addMaterial(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                                .setInverted(true))
                        .setFinalEdit(data -> addItemToLoot(data, DFMaterial.ObsidianUpgradeTemplate))
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new ByRandomChance().setMaxInteger(2))
                        .addCondition(new ByOtherLocationSpecificMaterials()
                                .addMaterial(Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                                .addMaterial(Material.MOSS_BLOCK)
                                .addMaterial(Material.ENDER_PEARL))
                        .setFinalEdit(data -> {
                            addItemToLoot(data, DFMaterial.FlowerPowder, getRandomNumber(3, 7));
                        })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new ByRandomChance().setMaxInteger(2))
                        .addCondition(new ByOtherLocationSpecificMaterials()
                                .addMaterial(Material.ECHO_SHARD)
                                .addMaterial(Material.SCULK_CATALYST)
                                .addMaterial(Material.DISC_FRAGMENT_5))
                        .setFinalEdit(data -> {
                            addItemToLoot(data, DFMaterial.SculkFragment, getRandomNumber(3, 7));
                        })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new ByBiome().setBiome(Biome.WARPED_FOREST))
                        .addCondition(new BySpecificMaterial().addMaterial(Material.NETHER_WART),
                                data -> {
                            replaceModifiedSlotsInLoot(data, DFMaterial.WarpedWart);
                                })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new BySpecificMaterial().addMaterial(Material.BUCKET),
                                data -> {
                                    for (int i : data.getModifiedSlots()) {
                                        switch (getRandomNumber(0, 2)) {
                                            case 0:
                                                break;

                                            case 1:
                                                data.getEvent().getLoot().set(i, DFMaterial.CopperBucket.toItemStack());
                                                break;

                                            case 2:
                                                data.getEvent().getLoot().set(i, DFMaterial.GoldBucket.toItemStack());
                                                break;
                                        }
                                    }
                                })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new BySpecificMaterial().addMaterial(Material.BREAD).setRandomMax(3),
                                data -> {
                            replaceModifiedSlotsInLoot(data, DFMaterial.EggSandwich);
                                })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new BySpecificMaterial().addMaterial(Material.OBSIDIAN).setRandomMax(3),
                                data -> {
                                    replaceModifiedSlotsInLoot(data, Material.CRYING_OBSIDIAN);
                                })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new BySpecificMaterial().addMaterial(Material.STICK).setRandomMax(2),
                                data -> {
                                    replaceModifiedSlotsInLoot(data, DFMaterial.HeatProofRod);
                                })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new BySpecificMaterial().addMaterial(Material.IRON_INGOT).setRandomMax(2),
                                data -> {
                                    if (getRandomNumber(0, 1) == 0) {
                                        replaceModifiedSlotsInLoot(data, DFMaterial.FiridiumIngot);
                                    } else {
                                        replaceModifiedSlotsInLoot(data, DFMaterial.FloralIngot);
                                    }
                                })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new BySpecificMaterial().setRandomMax(1).addMaterial(Material.STONE_AXE).addMaterial(Material.STONE_PICKAXE),
                                data -> {
                                    for (int i : data.getModifiedSlots()) {
                                        ItemStack x = data.getEvent().getLoot().get(i);
                                        Damageable damageableX = (Damageable) x.getItemMeta();

                                        ItemStack replacement = DFMaterial.CalcitePickaxe.toItemStack();
                                        if (x.getType().equals(Material.STONE_AXE)) replacement = DFMaterial.CalciteAxe.toItemStack();

                                        Damageable damageableR = (Damageable) replacement.getItemMeta();
                                        damageableR.setDamage(damageableX.getDamage());

                                        replacement.setItemMeta(damageableR);

                                        data.getEvent().getLoot().set(i, replacement);
                                    }
                                })
        );

        attempts.add(
                new LootEditAttempt()
                        .addCondition(new IsChest())
                        .addCondition(new BySpecificMaterial().setRandomMax(1).addMaterial(Material.IRON_BOOTS),
                                data -> {
                                    replaceModifiedSlotsInLoot(data, DFMaterial.FloralBoots);
                                })
        );
    }

    public static void PopulateChests(LootGenerateEvent e) {
        for (LootEditAttempt attempt : attempts) {
            attempt.runAttempt(e);
        }
    }

    public static int getRandomNumber(int min, int max) {
        return rand.nextInt(min, max);
    }
}
