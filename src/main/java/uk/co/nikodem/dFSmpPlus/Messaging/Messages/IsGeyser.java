package uk.co.nikodem.dFSmpPlus.Messaging.Messages;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Messaging.DFPluginMessageHandler;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;
import uk.co.nikodem.dFSmpPlus.Utils.Serialisation.StringHelper;

public class IsGeyser implements DFPluginMessageHandler {
    @Override
    public void run(@NotNull String channel, @NotNull Player plr, ByteArrayDataInput in) {
        // TODO use out.writeBoolean() and in.readBoolean() instead
        String val = StringHelper.SanitiseString(in.readUTF().toLowerCase().split(" ")[0]);

        if (val.equals("true")) BedrockPlayers.doCheck(plr, true);
        else if (val.equals("false")) BedrockPlayers.doCheck(plr, false);
    }
}
