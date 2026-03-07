package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplayBuilder;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class NetheriteTech extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "netherite-tech");

    public NetheriteTech(@NotNull Advancement parent) {
        super(parent, KEY.getKey(),
                new AdvancementDisplayBuilder(
                        Material.ANCIENT_DEBRIS,
                        "Netherite Tech")
                        .description("Chisel ancient debris.")
                        .challengeFrame()
                        .coords(3f, 2f)
                        .showToast()
                        .announceChat()
                        .build());

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
