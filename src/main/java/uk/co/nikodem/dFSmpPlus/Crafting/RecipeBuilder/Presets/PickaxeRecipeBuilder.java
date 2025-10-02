package uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets;

import java.util.List;

public class PickaxeRecipeBuilder extends PresetToolRecipeBuilder {

    @Override
    List<String> getShape() {
        return List.of("XXX", " I ", " I ");
    }
}
