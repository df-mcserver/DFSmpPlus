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
}
