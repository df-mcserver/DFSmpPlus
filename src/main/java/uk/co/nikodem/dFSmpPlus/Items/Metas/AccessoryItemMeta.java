package uk.co.nikodem.dFSmpPlus.Items.Metas;

import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class AccessoryItemMeta implements DFMaterialMeta {
    public final AccessoryInformation information;

    public AccessoryItemMeta(AccessoryInformation information) {
        this.information = information;
    }
}
