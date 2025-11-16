package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Combat;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class NotEvenCloseBaby extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "not-even-close-baby");

    public NotEvenCloseBaby(@NotNull Advancement parent) {
        super(KEY.getKey(),
                new AdvancementDisplay.Builder(
                        Material.SHIELD,
                        "Not even close, baby!")
                        .description("Get out of combat with only 1 heart or less remaining.")
                        .challengeFrame()
                        .coords(1f, 6f)
                        .showToast()
                        .announceChat()
                        .build()
                , parent);

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
