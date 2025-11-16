package uk.co.nikodem.dFSmpPlus.Messaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;
import uk.co.nikodem.dFSmpPlus.Messaging.Messages.IsGeyser;
import uk.co.nikodem.dFSmpPlus.Utils.Serialisation.StringHelper;

import java.util.HashMap;
import java.util.Map;

import static uk.co.nikodem.dFSmpPlus.Utils.Server.MessageUtils.CUSTOM_PROXY_CHANNEL;

public class MessageListener implements PluginMessageListener {
    public static final HashMap<String, DFPluginMessageHandler> messageHandlers = new HashMap<>();
    private final DFSmpPlus plugin;

    public MessageListener(DFSmpPlus plugin) {
        this.plugin = plugin;
    }

    public void initialiseMessageHandlers() {
        messageHandlers.put("IsGeyser", new IsGeyser());

        this.plugin.getServer().getMessenger().registerIncomingPluginChannel(this.plugin, CUSTOM_PROXY_CHANNEL, this);
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte @NotNull [] message) {
        if (!channel.equals(CUSTOM_PROXY_CHANNEL)) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String command = StringHelper.SanitiseString(in.readUTF().split(" ")[0]);

        DFPluginMessageHandler handler = messageHandlers.get(command);
        if (handler != null) handler.run(channel, player, in);
    }
}
