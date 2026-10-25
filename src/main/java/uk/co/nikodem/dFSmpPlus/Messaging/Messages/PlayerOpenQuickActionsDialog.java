package uk.co.nikodem.dFSmpPlus.Messaging.Messages;

import com.google.common.io.ByteArrayDataInput;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.dialog.DialogLike;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Messaging.DFPluginMessageHandler;
import uk.co.nikodem.dFSmpPlus.Player.BedrockPlayers;
import uk.co.nikodem.dFSmpPlus.Player.DialogManager;
import uk.co.nikodem.dFSmpPlus.Utils.Serialisation.StringHelper;

public class PlayerOpenQuickActionsDialog implements DFPluginMessageHandler {
    @Override
    public void run(@NotNull String channel, @NotNull Player plr, ByteArrayDataInput in) {
        Dialog dialog = RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG).get(DialogManager.customQuickActions);
        if (dialog != null) plr.showDialog(dialog);
        else plr.showDialog(Dialog.QUICK_ACTIONS);
    }
}
