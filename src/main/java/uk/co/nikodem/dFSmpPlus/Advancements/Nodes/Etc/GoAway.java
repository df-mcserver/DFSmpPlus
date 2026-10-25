package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplayBuilder;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class GoAway extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "go_away");

    public GoAway(@NotNull Advancement parent) {
        super(parent, KEY.getKey(),
                new AdvancementDisplayBuilder(
                        ItemStack.of(Material.WANDERING_TRADER_SPAWN_EGG),
                        "GO AWAY")
                        .description("Kill a wandering trader")
                        .taskFrame()
                        .coords(1f, 16f)
                        .showToast()
                        .announceChat()
                        .build());

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
