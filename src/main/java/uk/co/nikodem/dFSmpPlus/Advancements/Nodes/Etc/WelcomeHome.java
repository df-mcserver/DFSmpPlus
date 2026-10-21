package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplayBuilder;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class WelcomeHome extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "welcome-home");

    public WelcomeHome(@NotNull Advancement parent) {
        super(parent, KEY.getKey(),
                new AdvancementDisplayBuilder(
                        DFMaterial.MagicMirror.toItemStack(),
                        "Welcome home")
                        .description("Use a magic mirror for the first time.")
                        .taskFrame()
                        .coords(1f, 20f)
                        .showToast()
                        .announceChat()
                        .build());

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
