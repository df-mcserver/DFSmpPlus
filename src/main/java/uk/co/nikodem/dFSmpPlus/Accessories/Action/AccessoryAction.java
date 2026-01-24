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
    private final boolean updateIconOnClick;

    public AccessoryAction(
            ChooseItemStackIcon chooseItemStackIcon,
            OnClickAction onClickAction,
            boolean updateIconOnClick
    ) {
        this.chooseItemStackIcon = chooseItemStackIcon;
        this.onClickAction = onClickAction;
        this.updateIconOnClick = updateIconOnClick;
    }

    public ItemStack getItemStackIcon(Player plr, ItemStack accessoryItem, AccessoryInformation info) {
        return chooseItemStackIcon.doActionIcon(plr, accessoryItem, info);
    }

    public boolean shouldUpdateIconOnClick() {
        return updateIconOnClick;
    }

    public void doOnClick(Player plr, ItemStack accessoryItem, AccessoryInformation info, InventoryClickEvent event) {
        onClickAction.onClick(plr, accessoryItem, info, event);
    }

    public static class Builder {
        private ChooseItemStackIcon chooseItemStackIcon;
        private OnClickAction onClickAction;
        private Boolean updateIconOnClick = null;

        public Builder setItemStackIcon(ItemStack item) {
            this.chooseItemStackIcon = (a, b, c) -> item;
            return this;
        }

        public Builder setUpdateIconOnClick(boolean shouldUpdate) {
            this.updateIconOnClick = shouldUpdate;
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
            return new AccessoryAction(chooseItemStackIcon, onClickAction, updateIconOnClick == null || updateIconOnClick);
        }
    }

}
