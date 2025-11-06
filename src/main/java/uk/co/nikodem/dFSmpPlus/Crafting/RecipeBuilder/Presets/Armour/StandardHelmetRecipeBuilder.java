package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Armour;

import java.util.List;

public class StandardHelmetRecipeBuilder extends PresetArmourRecipeBuilder {
    @Override
    List<String> getShape() {
        return List.of("XXX", "X X");
    }
}
