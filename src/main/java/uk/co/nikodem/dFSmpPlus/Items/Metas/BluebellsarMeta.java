package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;

public class BluebellsarMeta implements DFMaterialMeta {
    public void ItemAttack(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER
        || event.getEntity().getType() == EntityType.ENDER_DRAGON
        || event.getEntity().getType() == EntityType.WITHER
        || event.getEntity().getType() == EntityType.WARDEN) {
            event.setDamage(0);
        } else {
            event.setDamage(event.getDamage() * 10);
        }
    }

    public void ItemAttackWhileOffhand(Player plr, DFMaterial material, ItemStack weapon, EntityDamageByEntityEvent event) {
        if (plr.getPersistentDataContainer().has(Keys.bullyBluebellsarStick)) {
            if (plr.getPersistentDataContainer().get(Keys.bullyBluebellsarStick, PersistentDataType.INTEGER) < 0) {
                plr.sendMessage(MiniMessage.miniMessage().deserialize("<i><grey>Bluebellsar Stick whispers to you: we're not doing this again"));

                plr.getInventory().setItemInOffHand(ItemStack.of(Material.AIR));
                plr.broadcastSlotBreak(EquipmentSlot.OFF_HAND);
            }
        }
        if (event.getDamage() >= plr.getHealth()) {
            plr.getPersistentDataContainer().set(Keys.bluebellsarDeath, PersistentDataType.BOOLEAN, true);
        } else {
            int random = (int )(Math.random() * ((100 + 1) / event.getDamage()));
            if (random == 1) {
                int bullyCount = plr.getPersistentDataContainer().has(Keys.bullyBluebellsarStick) ? plr.getPersistentDataContainer().get(Keys.bullyBluebellsarStick, PersistentDataType.INTEGER) : 0;
                plr.getPersistentDataContainer().set(Keys.bullyBluebellsarStick, PersistentDataType.INTEGER, bullyCount + 1);

                String msg = "???";

                switch (bullyCount) {
                    case 0:
                        msg = "can you stop pls";
                        break;

                    case 1:
                        msg = "no please it hurts";
                        break;

                    case 2:
                        msg = "why are you doing this";
                        break;

                    case 3:
                        msg = "please stop im begging";
                        break;

                    case 4:
                        msg = "it really hurts y'know";
                        break;

                    case 5:
                        msg = "what do i have to do to make you stop";
                        break;

                    case 6:
                        msg = "do you want diamonds? you already used some";
                        break;

                    case 7:
                        msg = "genuinely stop please";
                        break;

                    case 8:
                        msg = "im going to break if you continue this";
                        break;

                    case 9:
                        msg = "i hope you're happy";
                        break;
                }

                plr.sendMessage(MiniMessage.miniMessage().deserialize("<i><grey>Bluebellsar Stick whispers to you: "+msg));

                if (bullyCount >= 9) {
                    plr.getInventory().setItemInOffHand(ItemStack.of(Material.AIR));
                    plr.broadcastSlotBreak(EquipmentSlot.OFF_HAND);
                    plr.getPersistentDataContainer().set(Keys.bullyBluebellsarStick, PersistentDataType.INTEGER, -100);
                }
            }
        }
        plr.damage(event.getDamage());
        event.setDamage(0);
    }
}
