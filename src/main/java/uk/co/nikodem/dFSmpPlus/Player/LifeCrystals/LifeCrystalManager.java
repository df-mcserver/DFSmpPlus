package uk.co.nikodem.dFSmpPlus.Player.LifeCrystals;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import javax.annotation.Nullable;

public class LifeCrystalManager {
    public static final String keyName = "LifeCrystals";
    public static final int maxLifeCrystals = 10;
    public static final Attribute attribute = Attribute.MAX_HEALTH;

    public static boolean playerHasLifeCrystals(Player plr) {
        Integer amount = DFSmpPlus.playerData.getInt(plr, keyName);
        if (amount == null) return false;

        return amount > 0;
    }

    @Nullable
    public static Integer getPlayerLifeCrystals(Player plr) {
        return DFSmpPlus.playerData.getInt(plr, keyName);
    }

    public static void setPlayerLifeCrystals(Player plr, int amount) {
        DFSmpPlus.playerData.setData(plr, keyName, amount);
        DFSmpPlus.playerData.saveData();
    }

    public static void updatePlayerLifeCrystalsModifier(Player plr) {
        Integer amount = getPlayerLifeCrystals(plr);
        if (amount == null) return;

        AttributeInstance attr = plr.getAttribute(attribute);
        if (attr == null) return;

        removePlayerLifeCrystalModifier(plr);

        AttributeModifier newMod = new AttributeModifier(
                Keys.lifefruit,
                amount*2,
                AttributeModifier.Operation.ADD_NUMBER
        );

        attr.addModifier(newMod);

        plr.setAbsorptionAmount(amount);
    }

    public static void removePlayerLifeCrystalModifier(Player plr) {
        AttributeInstance attr = plr.getAttribute(attribute);
        if (attr == null) return;

        AttributeModifier currentMod = attr.getModifier(Keys.lifefruit);

        if (currentMod != null) attr.removeModifier(Keys.lifefruit);
    }
}
