package uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeRemovals.Queries.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecipeRemover {
    private static List<RecipeRemovalQuery> queries = new ArrayList<>();

    public static void Run() {
        List<RecipeWithIngredientRemoval> ingredientRemovals = new ArrayList<>();

        for (RecipeRemovalQuery q : queries) {
            if (q instanceof RecipeWithResultRemoval query) {
                List<Recipe> recipes = Bukkit.getRecipesFor(query.getResult());
                Iterator<Recipe> recipeIterator = recipes.iterator();

                while (recipeIterator.hasNext()) {
                    Recipe recipe = recipeIterator.next();
                    NamespacedKey key = getRecipeKey(recipe);
                    if (key == null) continue;
                    boolean shouldRemove = true;
                    if (query.getOnlyUseMinecraftNamespace() && !key.getNamespace().equals("minecraft")) shouldRemove = false;

                    if (shouldRemove) {
                        Bukkit.removeRecipe(key);
                    }
                }
            } else if (q instanceof RecipeWithIngredientRemoval query) {
                ingredientRemovals.add(query);
            }
        }

        List<Recipe> recipesToReAdd = new ArrayList<>();

        Iterator<Recipe> recipeIterator = Bukkit.getServer().recipeIterator();

        while (recipeIterator.hasNext()) {
            Recipe r = recipeIterator.next();

            for (RecipeWithIngredientRemoval query : ingredientRemovals) {
                RecipeChoice ingredient = query.getIngredient();

                boolean hasIngredient = containsIngredient(r, ingredient);

                if (hasIngredient) {
                    NamespacedKey key = getRecipeKey(r);
                    if (key == null) continue;
                    boolean shouldRemove = true;

                    if (query.getOnlyUseMinecraftNamespace() && !key.getNamespace().equals("minecraft")) shouldRemove = false;

                    if (shouldRemove) {

                        if (query instanceof RecipeWithIngredientReplace replaceQuery) {
                            recipesToReAdd.add(createSimilarRecipe(r, ingredient, replaceQuery.getReplacementIngredient()));
                            Bukkit.removeRecipe(key);
                        } else {
                            Bukkit.removeRecipe(key);
                        }
                    }
                }
            }
        }

        for (Recipe recipe : recipesToReAdd) {
            Bukkit.addRecipe(recipe);
        }
    }

    public static boolean containsIngredient(Recipe r, RecipeChoice ingredient) {
        switch (r) {
            case ShapedRecipe recipe -> {
                for (Map.Entry<Character, RecipeChoice> entry : recipe.getChoiceMap().entrySet()) {
                    if (entry == null) continue;
                    if (entry.getValue() == null) continue;
                    if (entry.getValue().equals(ingredient)) return true;

                    // probably only a material choice left
                    if (entry.getValue() instanceof RecipeChoice.MaterialChoice choices) {
                        return MaterialChoiceContainsRecipeChoice(choices, ingredient);
                    }
                }
                return false;
            }
            case ShapelessRecipe recipe -> {
                for (RecipeChoice choice : recipe.getChoiceList()) {
                    if (choice == null) continue;
                    if (choice.equals(ingredient)) return true;

                    // probably only a material choice left

                    if (choice instanceof RecipeChoice.MaterialChoice choices) {
                        return MaterialChoiceContainsRecipeChoice(choices, ingredient);
                    }
                }
                return false;
            }
            case StonecuttingRecipe recipe -> {
                return recipe.getInputChoice().equals(ingredient);
            }
            case FurnaceRecipe recipe -> {
                return recipe.getInputChoice().equals(ingredient);
            }
            case BlastingRecipe recipe -> {
                return recipe.getInputChoice().equals(ingredient);
            }
            case SmokingRecipe recipe -> {
                return recipe.getInputChoice().equals(ingredient);
            }
            default -> {
                return false;
            }
        }
    }

    public static NamespacedKey appendToNamespacedKey(NamespacedKey key, String append) {
        return new NamespacedKey(key.getNamespace(), key.getKey()+append);
    }

    @Nullable
    public static NamespacedKey getRecipeKey(Recipe r) {
        if (r instanceof ShapedRecipe recipe) {
            return recipe.getKey();
        } else if (r instanceof ShapelessRecipe recipe) {
            return recipe.getKey();
        } else if (r instanceof StonecuttingRecipe recipe) {
            return recipe.getKey();
        } else if (r instanceof FurnaceRecipe recipe) {
            return recipe.getKey();
        } else if (r instanceof BlastingRecipe recipe) {
            return recipe.getKey();
        } else if (r instanceof SmokingRecipe recipe) {
            return recipe.getKey();
        }
        return null;
    }

    public static boolean MaterialChoiceContainsRecipeChoice(RecipeChoice.MaterialChoice materialChoice, RecipeChoice contains) {
        List<Material> ings = new ArrayList<>();
        if (contains instanceof RecipeChoice.ExactChoice c) {
            for (ItemStack item : c.getChoices()) {
                ings.add(item.getType());
            }
        } else if (contains instanceof RecipeChoice.MaterialChoice c) {
            ings = c.getChoices();
        }

        for (Material currentIng : materialChoice.getChoices()) {
            for (Material ing : ings) {
                if (currentIng.equals(ing)) return true;
            }
        }

        return false;
    }

    @Nullable
    public static Recipe createSimilarRecipe(Recipe r, RecipeChoice removedIngredient, RecipeChoice addedIngredient) {
        if (r instanceof ShapedRecipe recipe) {
            NamespacedKey key = appendToNamespacedKey(recipe.getKey(), "-replaced");
            ShapedRecipe shapedRecipe = new ShapedRecipe(key, recipe.getResult());
            shapedRecipe.shape(recipe.getShape());
            shapedRecipe.setGroup(recipe.getGroup());
            shapedRecipe.setCategory(recipe.getCategory());

            for (Map.Entry<Character, RecipeChoice> entry : recipe.getChoiceMap().entrySet()) {
                if (entry == null) continue;
                if (entry.getValue() == null) continue;
                if (entry.getValue().equals(removedIngredient)) {
                    shapedRecipe.setIngredient(entry.getKey(), addedIngredient);
                } else {
                    if (entry.getValue() instanceof RecipeChoice.MaterialChoice materialChoice) {
                        if (MaterialChoiceContainsRecipeChoice(materialChoice, removedIngredient)) {
                            shapedRecipe.setIngredient(entry.getKey(), addedIngredient);
                        } else {
                            shapedRecipe.setIngredient(entry.getKey(), entry.getValue());
                        }
                    } else {
                        shapedRecipe.setIngredient(entry.getKey(), entry.getValue());
                    }
                }
            }

            return shapedRecipe;
        } else if (r instanceof ShapelessRecipe recipe) {
            NamespacedKey key = appendToNamespacedKey(recipe.getKey(), "-replaced");
            ShapelessRecipe shapelessRecipe = new ShapelessRecipe(key, recipe.getResult());
            shapelessRecipe.setGroup(recipe.getGroup());
            shapelessRecipe.setCategory(recipe.getCategory());

            for (RecipeChoice choice : recipe.getChoiceList()) {
                if (choice == null) continue;
                if (choice.equals(removedIngredient)) {
                    shapelessRecipe.addIngredient(addedIngredient);
                } else {
                    if (choice instanceof RecipeChoice.MaterialChoice materialChoice) {
                        if (MaterialChoiceContainsRecipeChoice(materialChoice, removedIngredient)) {
                            shapelessRecipe.addIngredient(addedIngredient);
                        } else {
                            shapelessRecipe.addIngredient(choice);
                        }
                    } else {
                        shapelessRecipe.addIngredient(choice);
                    }
                }
            }

            return shapelessRecipe;
        } else if (r instanceof StonecuttingRecipe recipe) {
            NamespacedKey key = appendToNamespacedKey(recipe.getKey(), "-replaced");
            StonecuttingRecipe stonecuttingRecipe = new StonecuttingRecipe(key, recipe.getResult(), recipe.getInputChoice());
            stonecuttingRecipe.setGroup(recipe.getGroup());

            if (stonecuttingRecipe.getInputChoice() == removedIngredient) stonecuttingRecipe.setInputChoice(addedIngredient);
            else stonecuttingRecipe.setInputChoice(recipe.getInputChoice());

            return stonecuttingRecipe;
        } else if (r instanceof FurnaceRecipe recipe) {
            NamespacedKey key = appendToNamespacedKey(recipe.getKey(), "-replaced");
            FurnaceRecipe furnaceRecipe = new FurnaceRecipe(key, recipe.getResult(), recipe.getInputChoice(), recipe.getExperience(), recipe.getCookingTime());
            furnaceRecipe.setCategory(recipe.getCategory());
            furnaceRecipe.setGroup(recipe.getGroup());

            if (furnaceRecipe.getInputChoice() == removedIngredient) furnaceRecipe.setInputChoice(addedIngredient);
            else furnaceRecipe.setInputChoice(recipe.getInputChoice());

            return furnaceRecipe;
        } else if (r instanceof BlastingRecipe recipe) {
            NamespacedKey key = appendToNamespacedKey(recipe.getKey(), "-replaced");
            BlastingRecipe blastingRecipe = new BlastingRecipe(key, recipe.getResult(), recipe.getInputChoice(), recipe.getExperience(), recipe.getCookingTime());
            blastingRecipe.setCategory(recipe.getCategory());
            blastingRecipe.setGroup(recipe.getGroup());

            if (blastingRecipe.getInputChoice() == removedIngredient) blastingRecipe.setInputChoice(addedIngredient);
            else blastingRecipe.setInputChoice(recipe.getInputChoice());

            return blastingRecipe;
        } else if (r instanceof SmokingRecipe recipe) {
            NamespacedKey key = appendToNamespacedKey(recipe.getKey(), "-replaced");
            SmokingRecipe smokingRecipe = new SmokingRecipe(key, recipe.getResult(), recipe.getInputChoice(), recipe.getExperience(), recipe.getCookingTime());
            smokingRecipe.setCategory(recipe.getCategory());
            smokingRecipe.setGroup(recipe.getGroup());

            if (smokingRecipe.getInputChoice() == removedIngredient) smokingRecipe.setInputChoice(addedIngredient);
            else smokingRecipe.setInputChoice(recipe.getInputChoice());

            return smokingRecipe;
        }

        return null;
    }

    public static List<RecipeRemovalQuery> getQueries() {
        return queries;
    }

    public static void setQueries(List<RecipeRemovalQuery> queries) {
        RecipeRemover.queries = queries;
    }

    public static void addQuery(RecipeRemovalQuery query) {
        RecipeRemover.queries.add(query);
    }
}
