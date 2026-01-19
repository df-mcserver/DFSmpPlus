package uk.co.nikodem.dFSmpPlus.Events.Player;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import uk.co.nikodem.dFSmpPlus.Accessories.Item.Metas.HitmanTechniquesMeta;

public class AsyncChatEvent implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void AsyncChatEvent(io.papermc.paper.event.player.AsyncChatEvent event) {
        Player plr = event.getPlayer();

        boolean isSilenced = HitmanTechniquesMeta.isSilenced(plr);
        if (isSilenced) {
            plr.sendMessage(MiniMessage.miniMessage().deserialize("<hover:show_text:'This message has been silenced.'><dark_red><i>\\<"+MiniMessage.miniMessage().serialize(plr.displayName())+"> "+MiniMessage.miniMessage().serialize(event.message())));
            event.setCancelled(true);
        }
    }
}
