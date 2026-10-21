package uk.co.nikodem.dFSmpPlus.Crafting.Recipes;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.persistence.PersistentDataType;
import uk.co.nikodem.dFSmpPlus.Constants.Keys;
import uk.co.nikodem.dFSmpPlus.Crafting.CraftingTemplate;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.CustomItemRepresentation;
import uk.co.nikodem.dFSmpPlus.Crafting.CustomRecipeMethods.SmithingTable.SmithingTableRecipe;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.*;
import uk.co.nikodem.dFSmpPlus.Crafting.RecipeBuilder.Presets.Etc.DirectConversionRecipeBuilder;
import uk.co.nikodem.dFSmpPlus.Items.DFItemUtils;
import uk.co.nikodem.dFSmpPlus.Items.DFMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessoryRecipes extends CraftingTemplate {
    @Override
    public List<Recipe> populateRecipes() {
        List<Recipe> recipesToAdd = new ArrayList<>();

        addBackpackRecipes(recipesToAdd);

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.LuckyHorseshoe)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "luckyhorseshoe")
                        .shape("FGN", "GIG", "NGF")
                        .setIngredient('G', Material.GOLD_INGOT)
                        .setIngredient('N', Material.GOLD_NUGGET)
                        .setIngredient('I', Material.BREEZE_ROD)
                        .setIngredient('F', Material.FEATHER)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ElytraBraces)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "elytrabraces")
                        .shape("NI", "IN")
                        .setIngredient('N', Material.IRON_BARS)
                        .setIngredient('I', Material.IRON_NUGGET)
        );

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.LuckyHorseshoe)
                .setAddition(DFMaterial.ElytraBraces)
                .setResult(DFMaterial.BracedHorseshoe)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.ElytraBraces)
                .setAddition(DFMaterial.LuckyHorseshoe)
                .setResult(DFMaterial.BracedHorseshoe)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.BracedHorseshoe)
                .setAddition(DFMaterial.ContaminatedMembrane)
                .setResult(DFMaterial.ContaminatedBracedHorseshoe)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.ContaminatedMembrane)
                .setAddition(DFMaterial.BracedHorseshoe)
                .setResult(DFMaterial.ContaminatedBracedHorseshoe)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.ContaminatedElytraBraces)
                .setAddition(DFMaterial.LuckyHorseshoe)
                .setResult(DFMaterial.ContaminatedBracedHorseshoe)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.LuckyHorseshoe)
                .setAddition(DFMaterial.ContaminatedElytraBraces)
                .setResult(DFMaterial.ContaminatedBracedHorseshoe)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.ContaminatedMembrane)
                .setAddition(DFMaterial.ElytraBraces)
                .setResult(DFMaterial.ContaminatedElytraBraces)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.ElytraBraces)
                .setAddition(DFMaterial.ContaminatedMembrane)
                .setResult(DFMaterial.ContaminatedElytraBraces)
                .assign();

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.ContaminatedMembrane)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "contaminatedmembrane")
                        .shape("PMP", " C ")
                        .setIngredient('M', Material.PHANTOM_MEMBRANE)
                        .setIngredient('P', Material.POISONOUS_POTATO)
                        .setIngredient('C', Material.FIRE_CHARGE)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CobaltShield)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "cobaltshield")
                        .shape("NLN", "LGL", " N ")
                        .setIngredient('L', Material.LAPIS_LAZULI)
                        .setIngredient('G', Material.GOLD_INGOT)
                        .setIngredient('N', RecipeChoice.exactChoice(new ItemStack(Material.IRON_NUGGET)))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VeinMinerEssence)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "veinessence")
                        .shape("AAA", "XAP", "AAA")
                        .setIngredient('A', Material.AMETHYST_SHARD)
                        .setIngredient('P', RecipeChoice.exactChoice(DFMaterial.VeinPickaxe.toItemStack()))
                        .setIngredient('X', RecipeChoice.exactChoice(DFMaterial.VeinAxe.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FiridiumEssence)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "autosmeltessence")
                        .shape("NAN", "NNN", "PNS")
                        .setIngredient('N', RecipeChoice.exactChoice(DFMaterial.FiridiumNugget.toItemStack()))
                        .setIngredient('P', RecipeChoice.exactChoice(DFMaterial.FiridiumPickaxe.toItemStack()))
                        .setIngredient('A', RecipeChoice.exactChoice(DFMaterial.FiridiumAxe.toItemStack()))
                        .setIngredient('S', RecipeChoice.exactChoice(DFMaterial.FiridiumShovel.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.SplitEssence)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "splitessence")
                        .shape(" A ", "LEL", " B ")
                        .setIngredient('A', RecipeChoice.exactChoice(DFMaterial.FiridiumEssence.toItemStack()))
                        .setIngredient('B', RecipeChoice.exactChoice(DFMaterial.VeinMinerEssence.toItemStack()))
                        .setIngredient('E', RecipeChoice.exactChoice(ItemStack.of(Material.ENDER_EYE)))
                        .setIngredient('L', RecipeChoice.exactChoice(ItemStack.of(Material.LAPIS_LAZULI)))
        );

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.BootsOfSwiftness)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "hermesboots")
                        .addIngredient(Material.LEATHER_BOOTS)
                        .addIngredient(Material.FEATHER)
                        .addIngredient(Material.CACTUS)
                        .addIngredient(Material.STRING)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.FlowerBoots)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "flowerboots")
                        .shape(" O ", "OXO", " O ")
                        .setIngredient('X', RecipeChoice.exactChoice(DFMaterial.FloralBoots.toItemStack()))
                        .setIngredient('O', RecipeChoice.exactChoice(DFMaterial.FlowerPowder.toItemStack()))
        );

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.BootsOfSwiftness)
                .setAddition(DFMaterial.FlowerBoots)
                .setResult(DFMaterial.FlowerBootsOfSwiftness)
                .assign();

        new AnvilRecipeBuilder()
                .setBase(DFMaterial.FlowerBoots)
                .setAddition(DFMaterial.BootsOfSwiftness)
                .setResult(DFMaterial.FlowerBootsOfSwiftness)
                .assign();

        recipesToAdd.add(
                new ShapelessRecipeBuilder()
                        .setOutput(DFMaterial.HitmanTechniquesBook)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "htbook")
                        .addIngredient(Material.BOOK)
                        .addIngredient(Material.TOTEM_OF_UNDYING)
                        .addIngredient(Material.TNT)
                        .addIngredient(Material.SHIELD)
                        .addIngredient(Material.SPECTRAL_ARROW)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.VacuumAccessory)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "vacuum")
                        .shape(" X ", "XOX", "IOI")
                        .setIngredient('X', RecipeChoice.exactChoice(ItemStack.of(Material.IRON_INGOT)))
                        .setIngredient('O', Material.WIND_CHARGE)
                        .setIngredient('I', Material.IRON_BARS)
        );

        return recipesToAdd;
    }

    public void addBackpackRecipes(List<Recipe> recipesToAdd) {
        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.CopperBackpack)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .build(getInfo(), "copper-backpack")
                        .shape("XXX", "XOX", "XXX")
                        .setIngredient('X', Material.COPPER_INGOT)
                        .setIngredient('O', Material.CHEST)
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.IronBackpack)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .transformOutput(data -> backpackUuidConversionFunction(DFMaterial.CopperBackpack, data))
                        .build(getInfo(), "iron-backpack")
                        .shape("XXX", "XOX", "XXX")
                        .setIngredient('X', Material.IRON_INGOT)
                        .setIngredient('O', RecipeChoice.predicateChoice((item -> DFMaterial.CopperBackpack.isSimilar(item)), DFMaterial.CopperBackpack.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.GoldBackpack)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .transformOutput(data -> backpackUuidConversionFunction(DFMaterial.IronBackpack, data))
                        .build(getInfo(), "gold-backpack")
                        .shape("XXX", "XOX", "XXX")
                        .setIngredient('X', Material.GOLD_INGOT)
                        .setIngredient('O', RecipeChoice.predicateChoice((item -> DFMaterial.IronBackpack.isSimilar(item)), DFMaterial.IronBackpack.toItemStack()))
        );

        recipesToAdd.add(
                new ShapedRecipeBuilder()
                        .setOutput(DFMaterial.DiamondBackpack)
                        .setCategory(CraftingBookCategory.EQUIPMENT)
                        .transformOutput(data -> backpackUuidConversionFunction(DFMaterial.GoldBackpack, data))
                        .build(getInfo(), "diamond-backpack")
                        .shape("XXX", "XOX", "XXX")
                        .setIngredient('X', Material.DIAMOND)
                        .setIngredient('O', RecipeChoice.predicateChoice((item -> DFMaterial.GoldBackpack.isSimilar(item)), DFMaterial.GoldBackpack.toItemStack()))
        );

        recipesToAdd.add(
                new SmithingTableRecipeBuilder()
                        .setBase(new CustomItemRepresentation(DFMaterial.DiamondBackpack))
                        .setTemplate(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .setAddition(Material.NETHERITE_INGOT)
                        .setResult(DFMaterial.NetheriteBackpack.toItemStack())
                        .setTransformer(data -> {
                            SmithingTableRecipe recipe = data.getKey();
                            PrepareSmithingEvent event = data.getValue();

                            ItemStack base = event.getInventory().getInputEquipment();

                            if (base == null) return ItemStack.empty();
                            if (base.isEmpty()) return ItemStack.empty();
                            String uuid = DFItemUtils.getUUID(base);

                            if (uuid == null) return ItemStack.empty();

                            ItemStack result = recipe.getResult().clone();
                            DFItemUtils.set(result, Keys.markedForUUID, PersistentDataType.BOOLEAN, false);
                            DFItemUtils.set(result, Keys.UUID, PersistentDataType.STRING, uuid);

                            return result;
                        })
                        .build(getInfo(), "netherite-backpack")
        );
    }

    private ItemStack backpackUuidConversionFunction(DFMaterial similar, Map.Entry<ShapedRecipe, PrepareItemCraftEvent> data) {
        PrepareItemCraftEvent event = data.getValue();
        ShapedRecipe recipe = data.getKey();

        return backpackUuidConversionFunction(similar, event.getInventory().getMatrix(), recipe.getResult());
    }

    private ItemStack backpackUuidConversionFunction(DFMaterial similar, ItemStack[] ingredients, ItemStack result_from_recipe) {
        for (ItemStack item : ingredients) {
            if (item == null) continue;
            if (item.isEmpty()) continue;

            if (similar.isSimilar(item)) {
                String uuid = DFItemUtils.getUUID(item);

                if (uuid == null) return ItemStack.empty();

                ItemStack result = result_from_recipe.clone();
                DFItemUtils.set(result, Keys.markedForUUID, PersistentDataType.BOOLEAN, false);
                DFItemUtils.set(result, Keys.UUID, PersistentDataType.STRING, uuid);

                return result;
            }
        }

        return ItemStack.empty();
    }

    @Override
    public String getRecipesID() {
        return "accessory";
    }
}
