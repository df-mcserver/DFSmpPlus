package uk.co.nikodem.dFSmpPlus.SetBonuses;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import javax.annotation.Nullable;

public class DFArmourSetUtils {
    @Nullable
    public static DFArmourSet getPlayersArmourSet(@Nullable Player plr) {
        if (plr == null) return null;
        PlayerInventory inv = plr.getInventory();

        return DFArmourSet.getArmourSetEquipped(inv);
    }
}
