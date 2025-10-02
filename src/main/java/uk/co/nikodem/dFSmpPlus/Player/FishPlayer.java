package uk.co.nikodem.dFSmpPlus.Player;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static uk.co.nikodem.dFSmpPlus.Constants.Keys.fishPlayer;

public class FishPlayer {
    public static Map<UUID, Integer> oxygenAmounts = new HashMap<UUID, Integer>();
    public static Map<UUID, Salmon> fishEntity = new HashMap<UUID, Salmon>();

    public static Salmon createFish(Player seb) {
        World world = seb.getLocation().getWorld();
        Salmon fish = (Salmon) world.spawnEntity(seb.getLocation(), EntityType.SALMON);
        fish.setCustomNameVisible(false);
        fish.setInvulnerable(true);
        fish.setNoPhysics(true);
        fish.setAI(false);
        fish.setCollidable(false);
        fish.setCanPickupItems(false);
        fish.setVariant(Salmon.Variant.SMALL);

        seb.getCollidableExemptions().add(fish.getUniqueId());
        return fish;
    }

    public static Salmon getFishFish(Player seb) {
        if (fishEntity.containsKey(seb.getUniqueId())) {
            Salmon fish = fishEntity.get(seb.getUniqueId());
            if (fish.isDead()) {
                Salmon newFish = createFish(seb);
                fishEntity.replace(seb.getUniqueId(), fish, newFish);

                return newFish;
            }
            return fish;
        } else {
            Salmon fish = createFish(seb);

            fishEntity.put(seb.getUniqueId(), fish);

            return fish;
        }
    }

    public static void onFishRespawn(Player seb) {
        seb.setInvisible(true);
    }

    public static void onMove(Player seb) {
        Entity fish = getFishFish(seb);
        fish.teleport(seb);
    }

    public static void doOxygen(DFSmpPlus plugin) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int tick=0;
            public void run() {
                tick+=1;
                for(World world : Bukkit.getWorlds()) {
                    for(Player player : world.getPlayers()) {
                        waterBreather(player, tick);
                    }
                }
            }
        }, 0L, 1L);
    }

    public static Boolean isWaterSource(Block block) {
        if(block.getType() == Material.WATER) {
            BlockData blockData = block.getBlockData();
            if(blockData instanceof Levelled){
                Levelled lv = (Levelled)blockData;
                if(lv.getLevel() == lv.getMaximumLevel()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean inWaterColumn(Player player) {
        Location location = player.getEyeLocation();
        if(isWaterSource(location.getBlock())) {
            while(location.getBlockY()>0) {
                location.add(0, -1, 0);
                if(location.getBlock().getType().equals(Material.SOUL_SAND)) {
                    return true;
                }
                if(!isWaterSource(location.getBlock())) {
                    return false;
                }
            }
        }
        return false;
    }

    public static void waterBreather(Player player, int tick) {
        if (!isFishPlayer(player)) return;
        if(!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
            if(!oxygenAmounts.containsKey(player.getUniqueId())) {
                oxygenAmounts.put(player.getUniqueId(), 300);
            }
            //When the player is no longer submerged in water or is in a bubble column
            if(!player.getEyeLocation().getBlock().getType().equals(Material.WATER) || inWaterColumn(player)) {
                //the air supply value decreases each tick.
                if(!player.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
                    oxygenAmounts.put(player.getUniqueId(), oxygenAmounts.get(player.getUniqueId()) - 1);
                }
                //Respiration gives a chance for air supply to not decrease itself per tick.
                //Chance is x/(x + 1), where x is the level of enchantment.
                if(player.getInventory().getHelmet()!=null) {
                    ItemStack helmet = player.getInventory().getHelmet();
                    if(helmet.getEnchantments().containsKey(Enchantment.RESPIRATION)) {
                        int respirationLevel = helmet.getEnchantments().get(Enchantment.RESPIRATION);
                        if(Math.random()>(respirationLevel/(respirationLevel+1))) {
                            oxygenAmounts.put(player.getUniqueId(), oxygenAmounts.get(player.getUniqueId()) + 1);
                        }
                    }
                }
                //when the air supply value reaches -20.
                if(oxygenAmounts.get(player.getUniqueId()) <= -20) {
                    player.damage(0.7D,
                            DamageSource.builder(DamageType.DROWN).build());
                    //Air resets to 0 after damaging.
                    oxygenAmounts.put(player.getUniqueId(), 0);
                }
            } else {
                //every 0.2 seconds (4 game ticks)
                if(tick % 4 == 0) {
                    //their oxygen regenerates by 1 bubble (30 air)
                    oxygenAmounts.put(player.getUniqueId(), oxygenAmounts.get(player.getUniqueId()) + 30);
                    if(oxygenAmounts.get(player.getUniqueId()) > 300) {
                        oxygenAmounts.put(player.getUniqueId(), 300);
                    }
                }
            };
            player.setRemainingAir(oxygenAmounts.get(player.getUniqueId()));
        }
    }

    public static boolean isFishPlayer(Player plr) {
        return plr.getName().equals("Bastian2010");
    }

    public static void applyFishChanges(Player seb) {
        ModifyAttribute(seb, Attribute.MAX_HEALTH,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "MaxHealth"),
                        -17D,
                        AttributeModifier.Operation.ADD_NUMBER
                )
        );

        ModifyAttribute(seb, Attribute.SCALE,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "Scale"),
                        -0.7D,
                        AttributeModifier.Operation.ADD_NUMBER
                )
        );

        ModifyAttribute(seb, Attribute.JUMP_STRENGTH,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "JumpStrength"),
                        -0.62D,
                        AttributeModifier.Operation.ADD_NUMBER
                )
        );

        ModifyAttribute(seb, Attribute.WATER_MOVEMENT_EFFICIENCY,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "WaterMovementEfficiency"),
                        6D,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1
                )
        );

        ModifyAttribute(seb, Attribute.BLOCK_BREAK_SPEED,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "BlockBreakSpeed"),
                        0.4D,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1
                )
        );

        ModifyAttribute(seb, Attribute.BLOCK_INTERACTION_RANGE,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "BlockInteractionRange"),
                        0.5D,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1
                )
        );

        ModifyAttribute(seb, Attribute.MOVEMENT_SPEED,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "MovementSpeed"),
                        0.05D,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1
                )
        );

        ModifyAttribute(seb, Attribute.SUBMERGED_MINING_SPEED,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "SubmergedMiningSpeed"),
                        1.5D,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1
                )
        );

        ModifyAttribute(seb, Attribute.OXYGEN_BONUS,
                new AttributeModifier(
                        new NamespacedKey("FishPlayer", "OxygenBonus"),
                        1024D,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1
                )
        );
    }

    public static void removeFishChanges(Player plr) {
        plr.setInvisible(false);
        resetAttributes(plr, Attribute.MAX_HEALTH, Attribute.ATTACK_KNOCKBACK, Attribute.SCALE, Attribute.JUMP_STRENGTH, Attribute.WATER_MOVEMENT_EFFICIENCY, Attribute.BLOCK_BREAK_SPEED, Attribute.BLOCK_INTERACTION_RANGE, Attribute.MOVEMENT_SPEED, Attribute.SUBMERGED_MINING_SPEED, Attribute.OXYGEN_BONUS);
    }

    public static void resetAttributes(Player plr, Attribute... attributes) {
        for (Attribute attribute : attributes) {
            AttributeInstance i = plr.getAttribute(attribute);
            if (i != null) {
                for (AttributeModifier mod : i.getModifiers()) {
                    if (mod.getKey().getNamespace().equals("FishPlayer")) {
                        i.removeModifier(mod.getKey());
                    }
                }
            }
        }
    }

    public static void ModifyAttribute(Player plr, Attribute attribute, AttributeModifier mod) {
        AttributeInstance i = plr.getAttribute(attribute);
        if (i != null) {
            i.addModifier(mod);
        }
    }

    public static void onFishDeath(Player seb) {
        ItemStack fish = new ItemStack(Material.SALMON);
        ItemMeta meta = fish.getItemMeta();
        MiniMessage mm = MiniMessage.miniMessage();
        meta.displayName(mm.deserialize("<red>"+mm.serialize(seb.displayName())+ "'s dead fish corpse"));
        meta.getPersistentDataContainer().set(fishPlayer, PersistentDataType.BOOLEAN, true);
        fish.setItemMeta(meta);
        seb.getLocation().getWorld().dropItem(seb.getLocation(), fish);
    }
}
