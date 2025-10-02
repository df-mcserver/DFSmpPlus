package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets;

import java.util.List;

public class StandardChestplateRecipeBuilder extends PresetArmourRecipeBuilder {

    @Override
    List<String> getShape() {
        return List.of("X X", "XXX", "XXX");
    }
}
