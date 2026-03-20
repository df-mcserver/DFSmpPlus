package uk.co.nikodem.dFSmpPlus.Commands.BasicCommands;

import com.google.j2objc.annotations.WeakOuter;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import uk.co.nikodem.dFSmpPlus.Commands.DFBasicCommand;
import uk.co.nikodem.dFSmpPlus.Player.Combat.CombatLoggingManager;
import uk.co.nikodem.dFSmpPlus.Utils.Sound.Sounds;

import java.util.Collection;
import java.util.List;


public class SpawnCommand implements DFBasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (sender instanceof Player plr) {
            Location spawn = getRealWorld().getSpawnLocation();

            if (inCombat(plr)) {
                sendCombatMessage(plr);
                return;
            }

            Sounds.Teleport.playSound(plr.getLocation());
            plr.getLocation().getWorld().spawnParticle(Particle.GLOW_SQUID_INK, plr.getLocation(), 15);
            plr.teleport(spawn, PlayerTeleportEvent.TeleportCause.COMMAND);
            Sounds.Teleport.playSound(spawn);
            spawn.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, spawn, 15);

            plr.setNoDamageTicks(100);
            sendSuccessMessage(plr);
        } else {
            sender.sendMessage("You are not player!");
        }
    }

    public World getRealWorld() {
        World defaultWorld = Bukkit.getWorld("world");
        if (defaultWorld != null && defaultWorld.getEnvironment() == World.Environment.NORMAL) return defaultWorld;
        else {
            for (World world : Bukkit.getWorlds()) {
                if (world.getEnvironment() == World.Environment.NETHER) return world;
            }

            return Bukkit.getWorlds().getFirst();
        }
    }

    public boolean inCombat(Player plr) {
        return CombatLoggingManager.isInCombat(plr);
    }

    public void sendSuccessMessage(Player plr) {
        plr.sendMessage(MiniMessage.miniMessage().deserialize("<dark_purple>Teleported to spawn!"));
    }

    public void sendCombatMessage(Player plr) {
        Sounds.FailedTeleport.playSoundLocally(plr);
        plr.sendMessage(MiniMessage.miniMessage().deserialize("<red>You're currently in combat!"));
    }

    @Override
    public String getLabel() {
        return "spawn";
    }

    @Override
    public String getDescription() {
        return "Teleport back to the server spawn, if you meet the requirements";
    }

    @Override
    public Collection<String> getAliases() {
        return List.of("tptospawn", "tpspawn", "tospawn");
    }
}
