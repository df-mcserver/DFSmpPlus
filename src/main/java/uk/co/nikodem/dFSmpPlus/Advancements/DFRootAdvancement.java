package uk.co.nikodem.dFSmpPlus.Advancements;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import static uk.co.nikodem.dFSmpPlus.Advancements.DFAdvancementsHandler.ADVANCEMENT_NAMESPACE;

public class DFRootAdvancement extends RootAdvancement {

    public static AdvancementKey KEY = new AdvancementKey(ADVANCEMENT_NAMESPACE, "firsttimer");

    public DFRootAdvancement(@NotNull AdvancementTab advancementTab) {
        super(advancementTab,
                KEY.getKey(),
                new AdvancementDisplay(
                        Material.CRIMSON_NYLIUM,
                        "Server",
                        AdvancementFrameType.TASK,
                        false,
                        false,
                        0f, 0f,
                        "I'm sure you'll love it here."
                ),
                "textures/block/red_nether_bricks.png");
    }
}
