package uk.co.nikodem.dFSmpPlus.Accessories.Action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.Operations.ChooseItemStackIcon;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.Operations.OnClickAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;

public class AccessoryAction {
    private final ChooseItemStackIcon chooseItemStackIcon;
    private final OnClickAction onClickAction;

    public AccessoryAction(
            ChooseItemStackIcon chooseItemStackIcon,
            OnClickAction onClickAction
    ) {
        this.chooseItemStackIcon = chooseItemStackIcon;
        this.onClickAction = onClickAction;
    }

    public ItemStack getItemStackIcon(Player plr, ItemStack accessoryItem, AccessoryInformation info) {
        return chooseItemStackIcon.doActionIcon(plr, accessoryItem, info);
    }

    public void doOnClick(Player plr, ItemStack accessoryItem, AccessoryInformation info, InventoryClickEvent event) {
        onClickAction.onClick(plr, accessoryItem, info, event);
    }

    public static class Builder {
        private ChooseItemStackIcon chooseItemStackIcon;
        private OnClickAction onClickAction;

        public Builder setItemStackIcon(ItemStack item) {
            this.chooseItemStackIcon = (a, b, c) -> item;
            return this;
        }

        public Builder setChooseItemStackicon(ChooseItemStackIcon chooseItemStackicon) {
            this.chooseItemStackIcon = chooseItemStackicon;
            return this;
        }

        public Builder setOnClickAction(OnClickAction onClickAction) {
            this.onClickAction = onClickAction;
            return this;
        }

        public AccessoryAction create() {
            return new AccessoryAction(chooseItemStackIcon, onClickAction);
        }
    }

}
