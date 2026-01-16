package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryAction;
import uk.co.nikodem.dFSmpPlus.Accessories.Action.AccessoryActionBuilder;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.List;

public class Test2Meta implements AccessoryMeta {
    public List<AccessoryAction> GetAccessoryActions() {
        return List.of(new AccessoryActionBuilder()
                .setChooseItemStackicon(((plr, accessoryitem, info) -> DFMaterial.CalciteChestplate.toItemStack()))
                .setOnClickAction((plr, accessoryitem, info, event) -> {
                    plr.sendMessage("yES. ");
                })
                .build());
    };
}
