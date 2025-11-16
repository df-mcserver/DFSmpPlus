package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class BreakTargetDummy extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "break-target-dummy");

    public BreakTargetDummy(@NotNull Advancement parent) {
        super(KEY.getKey(),
                new AdvancementDisplay.Builder(
                        DFMaterial.TargetDummy.toItemStack(),
                        "Took out my anger")
                        .description("Break a target dummy.")
                        .challengeFrame()
                        .coords(1f, 14f)
                        .showToast()
                        .announceChat()
                        .build()
                , parent);

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
