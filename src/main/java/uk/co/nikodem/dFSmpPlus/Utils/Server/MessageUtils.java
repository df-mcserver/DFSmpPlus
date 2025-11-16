package uk.co.nikodem.dFSmpPlus.Utils.Server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MessageUtils {
    public static final String CUSTOM_PROXY_CHANNEL = "df:proxy";
    private final Plugin plugin;

    public MessageUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    public void initiateChannels() {
        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, CUSTOM_PROXY_CHANNEL);
//        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void sendGeyserRequest(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("IsGeyser");
        player.sendPluginMessage(this.plugin, CUSTOM_PROXY_CHANNEL, out.toByteArray());
    }

//    public void sendPlayerToServer(Player player, String server) {
//        ByteArrayDataOutput out = ByteStreams.newDataOutput();
//        out.writeUTF("Connect");
//        out.writeUTF(server);
//        player.sendPluginMessage(this.plugin, "BungeeCord", out.toByteArray());
//    }
}
