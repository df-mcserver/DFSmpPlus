package uk.co.nikodem.dFSmpPlus.Items.Metas;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Tool;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class ComicallyLargeItemMeta implements DFMaterialMeta {
    public void ItemCreated(DFMaterial material, ItemStack item) {
        item.setData(DataComponentTypes.TOOL, Tool.tool()
                .defaultMiningSpeed(0.1f).build());
    }

    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {
        plr.getPersistentDataContainer().set(Keys.comicallyLarge, PersistentDataType.STRING, event.getBlock().translationKey());
        plr.broadcastSlotBreak(EquipmentSlot.HAND);
//        Sounds.BrokenTool.playSoundLocally(plr);
        tool.setAmount(0);
        plr.damage(1000000.0);
    }
}
