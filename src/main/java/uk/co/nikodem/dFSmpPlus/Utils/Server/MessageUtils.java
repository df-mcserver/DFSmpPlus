package uk.co.nikodem.dFSmpPlus.Utils.Server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

public class MessageUtils {
    public static final String CUSTOM_PROXY_CHANNEL = "df:proxy";

    public void initiateChannels() {
        DFSmpPlus.getPlugin().getServer().getMessenger().registerOutgoingPluginChannel(DFSmpPlus.getPlugin(), CUSTOM_PROXY_CHANNEL);
//        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void sendGeyserRequest(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("IsGeyser");
        player.sendPluginMessage(DFSmpPlus.getPlugin(), CUSTOM_PROXY_CHANNEL, out.toByteArray());
    }

//    public void sendPlayerToServer(Player player, String server) {
//        ByteArrayDataOutput out = ByteStreams.newDataOutput();
//        out.writeUTF("Connect");
//        out.writeUTF(server);
//        player.sendPluginMessage(this.plugin, "BungeeCord", out.toByteArray());
//    }
}
