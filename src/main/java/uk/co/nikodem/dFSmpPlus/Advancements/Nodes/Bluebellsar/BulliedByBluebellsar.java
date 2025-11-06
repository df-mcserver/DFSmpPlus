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

public class BulliedByBluebellsar extends BaseAdvancement implements HiddenVisibility {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "bullied-by-bluebellsar");

    public BulliedByBluebellsar(@NotNull Advancement parent) {
        super(KEY.getKey(),
                new AdvancementDisplay.Builder(
                        DFMaterial.BluebellsarStick.toItemStack(),
                        "Self Medicated")
                        .description("Your actions have consequences.")
                        .taskFrame()
                        .coords(2f, 5f)
                        .showToast()
                        .announceChat()
                        .build()
                , parent);

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
