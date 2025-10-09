package uk.co.nikodem.dFSmpPlus.Items.Metas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterialMeta;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import javax.annotation.Nullable;
import java.util.Date;

public class MagicMirrorMeta implements DFMaterialMeta {
    public final MiniMessage mm;

    public MagicMirrorMeta() {
        mm = MiniMessage.miniMessage();
    }

    @Override
    public void ItemUse(Player plr, DFMaterial material, ItemStack item, PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);

            if (onMagicMirrorCooldown(plr)) return;
            setMagicMirrorCooldown(plr);

            Location destination = getBedLocation(plr);

            if (inCombat(plr)) {
                sendCombatMessage(plr);
                return;
            }

            if (destination == null) {
                sendNoBedMessage(plr);
                return;
            }

            // run playTeleportingEffect so that both sides of the magic mirror are effected
            playTeleportingEffect(plr.getLocation());
            playTeleportingEffect(destination);
            plr.teleport(destination);

            sendSuccessfulTeleportMessage(plr);
        }
    }


    // just so that my ide stops saying that bed location cannot be null
    // yes it can
    @Nullable
    public Location getBedLocation(Player plr) {
        return plr.getRespawnLocation();
    }

    public boolean onMagicMirrorCooldown(Player plr) {
        Long timestamp = DFSmpPlus.playerData.getTemporaryLong(plr, "LastMagicMirror");
        if (timestamp == null) {
            return false;
        } else {
            long current = new Date().getTime();

            return current < timestamp + (1000);
        }
    }

    public void setMagicMirrorCooldown(Player plr) {
        DFSmpPlus.playerData.setTemporaryData(plr, "LastMagicMirror", new Date().getTime());
    }

    public boolean inCombat(Player plr) {
        // TODO do this
        return false;
    }

    public void playTeleportingEffect(Location loc) {
        World world = loc.getWorld();
        Sounds.Teleport.playSound(loc);
        world.spawnParticle(Particle.GLOW_SQUID_INK, loc, 15);
    }

    public void sendSuccessfulTeleportMessage(Player plr) {
        int random = (int )(Math.random() * 4096 + 1);
        if (random == 69) {
            plr.sendMessage(mm.deserialize("<dark_purple>You wake up in your bed in a cold sweat.."));
        } else {
            plr.sendMessage(mm.deserialize("<dark_purple>Teleported to your bed!"));
        }
    }

    public void sendNoBedMessage(Player plr) {
        Sounds.FailedTeleport.playSoundLocally(plr);
        plr.sendMessage(mm.deserialize("<red>You don't have a bed!"));
    }

    public void sendCombatMessage(Player plr) {
        Sounds.FailedTeleport.playSoundLocally(plr);
        plr.sendMessage(mm.deserialize("<red>You're currently in combat!"));
    }
}