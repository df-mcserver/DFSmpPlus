package uk.co.nikodem.dFSmpPlus.SetBonuses;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class DFArmourSetUtils {
    @Nullable
    public static DFArmourSet getPlayersArmourSet(@Nullable Player plr) {
        if (plr == null) return null;
        return DFArmourSet.getArmourSetEquipped(plr);
    }
}
