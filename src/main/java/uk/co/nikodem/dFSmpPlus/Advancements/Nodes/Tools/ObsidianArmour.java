package uk.co.nikodem.dFSmpPlus.Advancements.Nodes.Tools;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.jetbrains.annotations.NotNull;
import uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class ObsidianArmour extends BaseAdvancement {
    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "obsidian-armour");

    public ObsidianArmour(@NotNull Advancement parent) {
        super(KEY.getKey(),
                new AdvancementDisplay.Builder(
                        DFMaterial.ObsidianChestplate.toItemStack(),
                        "Crying on the Outside")
                        .description("Wear a full set of Obsidian Armour.")
                        .challengeFrame()
                        .coords(2f, 12f)
                        .showToast()
                        .announceChat()
                        .build()
                , parent);

        DFAdvancementsHandler.registerAdvancement(this);
    }
}
