package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Etc.CoralRehydrationRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Etc.DirectConversionRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.ShapedRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.DFSmpPlus;

import java.util.ArrayList;
import java.util.List;

public class CoralRecipes extends CraftingTemplate {
    public CoralRecipes(DFSmpPlus plugin) {
        super(plugin);
    }

    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addCoralBlockRehydration(recipesToAdd);
        addCoralRehydration(recipesToAdd);
        addCoralFanRehydration(recipesToAdd);

        addCoralToCoralFansRecipes(recipesToAdd);
        addCoralFansToCoralRecipes(recipesToAdd);
        addCoralBlockToCoralRecipes(recipesToAdd);
        addCoralToCoralBlockRecipes(recipesToAdd);

        return recipesToAdd;
    }

    public void addCoralBlockRehydration(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.BRAIN_CORAL_BLOCK)
                        .setMaterial(Material.DEAD_BRAIN_CORAL_BLOCK)
                        .setGroup("braincoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadblocktoblock")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.BUBBLE_CORAL_BLOCK)
                        .setMaterial(Material.DEAD_BUBBLE_CORAL_BLOCK)
                        .setGroup("bubblecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadblocktoblock")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.FIRE_CORAL_BLOCK)
                        .setMaterial(Material.DEAD_FIRE_CORAL_BLOCK)
                        .setGroup("firecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadblocktoblock")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.TUBE_CORAL_BLOCK)
                        .setMaterial(Material.DEAD_TUBE_CORAL_BLOCK)
                        .setGroup("tubecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadblocktoblock")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.HORN_CORAL_BLOCK)
                        .setMaterial(Material.DEAD_HORN_CORAL_BLOCK)
                        .setGroup("horncoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadblocktoblock")
        );
    }

    public void addCoralRehydration(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.BRAIN_CORAL)
                        .setMaterial(Material.DEAD_BRAIN_CORAL)
                        .setGroup("braincoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadcoraltocoral")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.BUBBLE_CORAL)
                        .setMaterial(Material.DEAD_BUBBLE_CORAL)
                        .setGroup("bubblecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadcoraltocoral")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.FIRE_CORAL)
                        .setMaterial(Material.DEAD_FIRE_CORAL)
                        .setGroup("firecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadcoraltocoral")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.TUBE_CORAL)
                        .setMaterial(Material.DEAD_TUBE_CORAL)
                        .setGroup("tubecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadcoraltocoral")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.HORN_CORAL)
                        .setMaterial(Material.DEAD_HORN_CORAL)
                        .setGroup("horncoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadcoraltocoral")
        );
    }

    public void addCoralFanRehydration(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.BRAIN_CORAL_FAN)
                        .setMaterial(Material.DEAD_BRAIN_CORAL_FAN)
                        .setGroup("braincoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadfantofan")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.BUBBLE_CORAL_FAN)
                        .setMaterial(Material.DEAD_BUBBLE_CORAL_FAN)
                        .setGroup("bubblecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadfantofan")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.FIRE_CORAL_FAN)
                        .setMaterial(Material.DEAD_FIRE_CORAL_FAN)
                        .setGroup("firecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadfantofan")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.TUBE_CORAL_FAN)
                        .setMaterial(Material.DEAD_TUBE_CORAL_FAN)
                        .setGroup("tubecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadfantofan")
        );

        recipesToAdd.add(
                new CoralRehydrationRecipeBuilder()
                        .setItem(Material.HORN_CORAL_FAN)
                        .setMaterial(Material.DEAD_HORN_CORAL_FAN)
                        .setGroup("horncoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "deadfantofan")
        );
    }

    public void addCoralToCoralFansRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.BRAIN_CORAL)
                        .setOutput(Material.BRAIN_CORAL_FAN)
                        .setGroup("braincoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralfan")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.BUBBLE_CORAL)
                        .setOutput(Material.BUBBLE_CORAL_FAN)
                        .setGroup("bubblecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralfan")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.FIRE_CORAL)
                        .setOutput(Material.FIRE_CORAL_FAN)
                        .setGroup("firecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralfan")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.TUBE_CORAL)
                        .setOutput(Material.TUBE_CORAL_FAN)
                        .setGroup("tubecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralfan")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.HORN_CORAL)
                        .setOutput(Material.HORN_CORAL_FAN)
                        .setGroup("horncoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralfan")
        );
    }

    public void addCoralFansToCoralRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.BRAIN_CORAL_FAN)
                        .setOutput(Material.BRAIN_CORAL)
                        .setGroup("braincoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralfantocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.BUBBLE_CORAL_FAN)
                        .setOutput(Material.BUBBLE_CORAL)
                        .setGroup("bubblecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralfantocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.FIRE_CORAL_FAN)
                        .setOutput(Material.FIRE_CORAL)
                        .setGroup("firecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralfantocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.TUBE_CORAL_FAN)
                        .setOutput(Material.TUBE_CORAL)
                        .setGroup("tubecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralfantocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.HORN_CORAL_FAN)
                        .setOutput(Material.HORN_CORAL)
                        .setGroup("horncoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralfantocoral")
        );
    }

    public void addCoralBlockToCoralRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.BRAIN_CORAL_BLOCK)
                        .setOutput(Material.BRAIN_CORAL, 4)
                        .setGroup("braincoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralblocktocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.BUBBLE_CORAL_BLOCK)
                        .setOutput(Material.BUBBLE_CORAL, 4)
                        .setGroup("bubblecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralblocktocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.FIRE_CORAL_BLOCK)
                        .setOutput(Material.FIRE_CORAL, 4)
                        .setGroup("firecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralblocktocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.TUBE_CORAL_BLOCK)
                        .setOutput(Material.TUBE_CORAL, 4)
                        .setGroup("tubecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralblocktocoral")
        );

        recipesToAdd.add(
                new DirectConversionRecipeBuilder()
                        .setSource(Material.HORN_CORAL_BLOCK)
                        .setOutput(Material.HORN_CORAL, 4)
                        .setGroup("horncoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coralblocktocoral")
        );
    }

    public void addCoralToCoralBlockRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.BRAIN_CORAL_BLOCK)
                        .setGroup("braincoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralblock")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.BRAIN_CORAL)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.BUBBLE_CORAL_BLOCK)
                        .setGroup("bubblecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralblock")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.BUBBLE_CORAL)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.FIRE_CORAL_BLOCK)
                        .setGroup("firecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralblock")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.FIRE_CORAL)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.TUBE_CORAL_BLOCK)
                        .setGroup("tubecoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralblock")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.TUBE_CORAL)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(Material.HORN_CORAL_BLOCK)
                        .setGroup("horncoral")
                        .setCategory(CraftingBookCategory.MISC)
                        .build(getInfo(), "coraltocoralblock")
                        .shape("XX", "XX")
                        .setIngredient('X', Material.HORN_CORAL)
        );
    }

    @Override
    public String getRecipesID() {
        return "coral";
    }
}
