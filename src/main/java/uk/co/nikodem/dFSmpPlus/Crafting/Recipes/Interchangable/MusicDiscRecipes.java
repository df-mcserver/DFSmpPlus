package uk.co.nikodem.dFSmpPlus.Crafting.Recipes.Interchangable;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.StonecutterRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.ArrayList;
import java.util.List;

public class MusicDiscRecipes extends CraftingTemplate {
    public MusicDiscRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        List<Material> allMusicDiscs = List.of(
                Material.MUSIC_DISC_11,
                Material.MUSIC_DISC_OTHERSIDE,
                Material.MUSIC_DISC_13,
                Material.MUSIC_DISC_5,
                Material.MUSIC_DISC_BLOCKS,
                Material.MUSIC_DISC_CAT,
                Material.MUSIC_DISC_CHIRP,
                Material.MUSIC_DISC_CREATOR,
                Material.MUSIC_DISC_CREATOR_MUSIC_BOX,
                Material.MUSIC_DISC_FAR,
                Material.MUSIC_DISC_LAVA_CHICKEN,
                Material.MUSIC_DISC_MALL,
                Material.MUSIC_DISC_MELLOHI,
                Material.MUSIC_DISC_OTHERSIDE,
                Material.MUSIC_DISC_PIGSTEP,
                Material.MUSIC_DISC_PRECIPICE,
                Material.MUSIC_DISC_RELIC,
                Material.MUSIC_DISC_STAL,
                Material.MUSIC_DISC_STRAD,
                Material.MUSIC_DISC_TEARS,
                Material.MUSIC_DISC_WAIT,
                Material.MUSIC_DISC_WARD
        );

        int i = 0;
        for (Material musicDisc : allMusicDiscs) {
            for (Material subMusicDisc : allMusicDiscs) {
                recipesToAdd.add(
                        new StonecutterRecipeBuilder()
                                .setSource(musicDisc)
                                .setOutput(subMusicDisc)
                                .build(getInfo(), "music"+Integer.toString(i))
                );
                i++;
            }
        }

        return recipesToAdd;
    }

    @Override
    public String getRecipesID() {
        return "musicdiscs-interchangable";
    }
}
