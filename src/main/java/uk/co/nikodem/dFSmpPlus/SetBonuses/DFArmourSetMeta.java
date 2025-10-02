package uk.co.nikodem.dFSmpPlus.SetBonuses;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

public interface DFArmourSetMeta {
    public default void PlayerAttack(Player plr, DFArmourSet armourSet, ItemStack weapon, EntityDamageByEntityEvent event) {};
    public default void PlayerHunger(Player plr, DFArmourSet armourSet, FoodLevelChangeEvent event) {};
    public default void PlayerHit(Player plr, DFArmourSet armourSet, EntityDamageByEntityEvent event) {};
    public default boolean AdditionalQualification(Player plr, DFArmourSet armourSet, ItemStack equippedHelmet, ItemStack equippedChestplate, ItemStack equippedLeggings, ItemStack equippedBoots) {return true;};
    public default void RunPerSecond(Player plr, DFArmourSet armourSet) {};
}
