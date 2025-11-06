package uk.co.nikodem.dFSmpPlus.Items.Metas;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.NewWorld;
import uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools.UsedSpear;
import uk.co.nikodem.dFSmpPlus.Constants.VeinMineable;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class PointyStickMeta implements DFMaterialMeta {
    @Override
    public void ItemKilledEntity(Player plr, DFMaterial material, Entity target, EntityDeathEvent event) {
        if (target instanceof Player) {
            DFAdvancementsHandler.grantAdvancement(plr, UsedSpear.class);
        }
    };

    @Override
    public void ItemMine(Player plr, DFMaterial material, ItemStack tool, BlockBreakEvent event) {
        if (VeinMineable.isVeinLog(event.getBlock().getType())) DFAdvancementsHandler.grantAdvancement(plr, NewWorld.class);
    }
}
