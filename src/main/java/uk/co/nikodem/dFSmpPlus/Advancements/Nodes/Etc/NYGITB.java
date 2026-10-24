package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Etc;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplayBuilder;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class NYGITB extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "nygitb");

    public NYGITB(@NotNull Advancement parent) {
        super(parent, KEY.getKey(),
                new AdvancementDisplayBuilder(
                        DFMaterial.EntityBucket.toItemStack(),
                        "No, YOU get in the bucket!")
                        .description("Use an entity bucket for the first time.")
                        .taskFrame()
                        .coords(1f, 21f)
                        .showToast()
                        .announceChat()
                        .build());

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
