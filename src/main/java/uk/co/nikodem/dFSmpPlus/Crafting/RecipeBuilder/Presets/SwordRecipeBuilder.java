package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets;

import java.util.List;

public class SwordRecipeBuilder extends PresetToolRecipeBuilder {

    @Override
    List<String> getShape() {
        return List.of("X", "X", "I");
    }
}
