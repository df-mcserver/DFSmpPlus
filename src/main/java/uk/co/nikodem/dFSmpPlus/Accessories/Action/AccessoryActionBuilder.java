package uk.co.nikodem.dFSmpPlus.Accessories.Action;

import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.Operations.ChooseItemStackIcon;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.Operations.OnClickAction;

public class AccessoryActionBuilder {
    private ChooseItemStackIcon chooseItemStackIcon;
    private OnClickAction onClickAction;

    public AccessoryActionBuilder setItemStackIcon(ItemStack item) {
        this.chooseItemStackIcon = (a, b, c) -> item;
        return this;
    }

    public AccessoryActionBuilder setChooseItemStackicon(ChooseItemStackIcon chooseItemStackicon) {
        this.chooseItemStackIcon = chooseItemStackicon;
        return this;
    }

    public AccessoryActionBuilder setOnClickAction(OnClickAction onClickAction) {
        this.onClickAction = onClickAction;
        return this;
    }

    public AccessoryAction build() {
        return new AccessoryAction(chooseItemStackIcon, onClickAction);
    }
}
