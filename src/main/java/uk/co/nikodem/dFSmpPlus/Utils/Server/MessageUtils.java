package uk.co.nikodem.dFSmpPlus.Utils.Server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import uk.co.nikodem.dFSmpPlus.Utils.Serialisation.StringHelper;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class MessageUtils {
    public static final String CUSTOM_PROXY_CHANNEL = "df:proxy";
    private final Plugin plugin;

    public MessageUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    public void initiateChannels() {
        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, CUSTOM_PROXY_CHANNEL);
        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void sendGeyserRequest(Player player) {
        System.out.println("SENT IsGeyser");
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("IsGeyser");
        player.sendPluginMessage(this.plugin, CUSTOM_PROXY_CHANNEL, out.toByteArray());
    }

    public void sendPlayerToServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(this.plugin, "BungeeCord", b.toByteArray());
    }
}
