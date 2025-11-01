package uk.co.nikodem.dFSmpPlus.Commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;


public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player plr) {
            Location spawn = plr.getWorld().getSpawnLocation();

            if (inCombat(plr)) {
                sendCombatMessage(plr);
                return true;
            }

            playTeleportingEffect(plr.getLocation());
            playTeleportingEffect(spawn);
            plr.teleport(spawn);
            plr.setNoDamageTicks(100);
            sendSuccessMessage(plr);
        } else {
            sender.sendMessage("You are not player!");
        }
        return true;
    }

    public boolean inCombat(Player plr) {
        // TODO do this
        return false;
    }

    public void sendSuccessMessage(Player plr) {
        plr.sendMessage(MiniMessage.miniMessage().deserialize("<dark_purple>Teleported to spawn!"));
    }

    public void sendCombatMessage(Player plr) {
        Sounds.FailedTeleport.playSoundLocally(plr);
        plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>You're currently in combat!"));
    }

    public void playTeleportingEffect(Location loc) {
        World world = loc.getWorld();
        Sounds.Teleport.playSound(loc);
        world.spawnParticle(Particle.GLOW_SQUID_INK, loc, 15);
    }
}
