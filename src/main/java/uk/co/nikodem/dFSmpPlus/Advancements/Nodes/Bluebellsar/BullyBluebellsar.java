package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Bluebellsar;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class BullyBluebellsar extends BaseAdvancement implements HiddenVisibility {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "bully-bluebellsar");

    public BullyBluebellsar(@NotNull Advancement parent) {
        super(KEY.getKey(),
                new AdvancementDisplay.Builder(
                        DFMaterial.BluebellsarStick.toItemStack(),
                        "meanie...")
                        .description("Defeat the Bluebellsar Stick.")
                        .taskFrame()
                        .coords(3f, 5f)
                        .showToast()
                        .announceChat()
                        .build()
                , parent);

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
