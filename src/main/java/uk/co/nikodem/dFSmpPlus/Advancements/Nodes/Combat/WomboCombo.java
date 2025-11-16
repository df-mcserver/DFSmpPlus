package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Combat;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class WomboCombo extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "wombo-combo");

    public WomboCombo(@NotNull Advancement parent) {
        super(KEY.getKey(),
                new AdvancementDisplay.Builder(
                        Material.DIAMOND_SWORD,
                        "WOMBO COMBO!")
                        .description("Get a kill streak of 5.")
                        .challengeFrame()
                        .coords(2f, 6f)
                        .showToast()
                        .announceChat()
                        .build()
                , parent);

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
