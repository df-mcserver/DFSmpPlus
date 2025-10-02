package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets;

import java.util.List;

public class RightHoeRecipeBuilder extends PresetToolRecipeBuilder {

    @Override
    List<String> getShape() {
        return List.of("XX", "I ", "I ");
    }
}
