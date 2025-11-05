package uk.co.nikodem.dFSmpPlus.Player.LifeCrystals;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Data.Player.PlayerData;

import javax.annotation.Nullable;

public class LifeCrystalManager {
    public static final int maxLifeCrystals = 10;
    public static final Attribute attribute = Attribute.MAX_HEALTH;

    public static boolean playerHasLifeCrystals(Player plr) {
        Integer amount = DFSmpPlus.playerDataHandler.getPlayerData(plr).lifeCrystals;
        if (amount == null) return false;

        return amount > 0;
    }

    @Nullable
    public static Integer getPlayerLifeCrystals(Player plr) {
        return DFSmpPlus.playerDataHandler.getPlayerData(plr).lifeCrystals;
    }

    public static void setPlayerLifeCrystals(Player plr, int amount) {
        PlayerData temp = DFSmpPlus.playerDataHandler.getPlayerData(plr);
        temp.lifeCrystals = amount;
        DFSmpPlus.playerDataHandler.writePlayerData(
                plr, temp
        );
    }

    public static void updatePlayerLifeCrystalsModifier(Player plr) {
        Integer amount = getPlayerLifeCrystals(plr);
        if (amount == null) return;

        AttributeInstance attr = plr.getAttribute(attribute);
        if (attr == null) return;

        removePlayerLifeCrystalModifier(plr);

        AttributeModifier newMod = new AttributeModifier(
                Keys.lifecrystal,
                amount*2,
                AttributeModifier.Operation.ADD_NUMBER
        );

        attr.addModifier(newMod);

        plr.setAbsorptionAmount(amount);
    }

    public static void removePlayerLifeCrystalModifier(Player plr) {
        AttributeInstance attr = plr.getAttribute(attribute);
        if (attr == null) return;

        AttributeModifier currentMod = attr.getModifier(Keys.lifecrystal);

        if (currentMod != null) attr.removeModifier(Keys.lifecrystal);
    }
}
