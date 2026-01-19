package uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryInformation;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.AccessoryMeta;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowerBootsMeta implements AccessoryMeta {
    public static Map<Material, List<Map.Entry<Material, Material>>> plantable;
    static {
        plantable = new HashMap<>();
        plantable.put(Material.FARMLAND, List.of(
                Map.entry(Material.WHEAT_SEEDS, Material.WHEAT),
                Map.entry(Material.BEETROOT_SEEDS, Material.BEETROOTS),
                Map.entry(Material.POTATO, Material.POTATOES),
                Map.entry(Material.CARROT, Material.CARROTS),
                Map.entry(Material.MELON_SEEDS, Material.MELON_STEM),
                Map.entry(Material.PUMPKIN_SEEDS, Material.PUMPKIN_STEM)));
        plantable.put(Material.SOUL_SAND, List.of(
                Map.entry(Material.NETHER_WART, Material.NETHER_WART)));
    }

    @Override
    public void UserInteract(Player plr, ItemStack accessory, AccessoryInformation info, PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) {
            Block steppedOnBlock = event.getClickedBlock();
            if (steppedOnBlock == null) return;
            if (steppedOnBlock.getType().equals(Material.FARMLAND)) {
                event.setCancelled(true);
            }
        }
    };

    @Override
    public void UserMove(Player plr, ItemStack accessory, AccessoryInformation info, PlayerMoveEvent event) {
        if (!event.hasExplicitlyChangedBlock()) return;
        Block farmBlock = plr.getLocation().getBlock();
        Block plantBlock = plr.getLocation().add(new Vector(0, 1, 0)).getBlock();

        if (plantBlock.getType() == Material.AIR) {
            for (Map.Entry<Material, List<Map.Entry<Material, Material>>> planteableEntry : plantable.entrySet()) {
                if (farmBlock.getType() == planteableEntry.getKey()) {
                    boolean isMainHand = true;
                    Material finalMaterial = null;
                    for (Map.Entry<Material, Material> plantableMaterial : planteableEntry.getValue()) {
                        ItemStack itemInMainHand = plr.getInventory().getItemInMainHand();
                        if (DFItemUtils.getDFMaterial(itemInMainHand) != null) continue;
                        if (itemInMainHand.getType() == plantableMaterial.getKey()) {
                            finalMaterial = plantableMaterial.getValue();
                            break;
                        }

                        ItemStack itemInOffHand = plr.getInventory().getItemInOffHand();
                        if (DFItemUtils.getDFMaterial(itemInOffHand) != null) continue;
                        if (itemInOffHand.getType() == plantableMaterial.getKey()) {
                            isMainHand = false;
                            finalMaterial = plantableMaterial.getValue();
                            break;
                        }
                    }

                    if (finalMaterial == null) break;


                    BlockType blockType = finalMaterial.asBlockType();
                    if (blockType == null) return;

                    plr.getWorld().playSound(plr, blockType.createBlockData().getSoundGroup().getPlaceSound(), 1F, 1F);
                    plantBlock.setType(finalMaterial);
                    if (isMainHand) plr.getInventory().getItemInMainHand().setAmount(plr.getInventory().getItemInMainHand().getAmount() - 1);
                    else plr.getInventory().getItemInOffHand().setAmount(plr.getInventory().getItemInOffHand().getAmount() - 1);

                    break;
                }
            }
        }
    };
}
