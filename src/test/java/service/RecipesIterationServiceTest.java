package service;

import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.ProfitablilityCalculatorForAlbion.model.Ingredients;
import org.ProfitablilityCalculatorForAlbion.service.RecipesIterationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecipesIterationServiceTest {

    private RecipesIterationService recipesIterationService;

    @Before
    public void setup() {
        recipesIterationService = new RecipesIterationService();
    }

    @Test
    public void iterationTierInName_WhenInputString_ShouldReturnString() {
        String itemName = "T5_BAG";
        Integer iterator = 2;
        String expected = "T7_BAG";

        String actual = recipesIterationService.iterationTierInName(itemName, iterator);

        assertEquals(expected, actual);
    }

    @Test
    public void addLevelToName_WhenInputString_ShouldReturnString() {
        String itemName = "T5_BAG";
        Integer iterator = 1;
        String expected = "T5_BAG_LEVEL2@2";

        String actual = recipesIterationService.addLevelToName(itemName, iterator);

        assertEquals(expected, actual);
    }

    @Test
    public void incrementationItemValuePerTier_WhenInputInteger_ShouldReturnInteger() {
        Integer itemValue = 30;
        Integer iterator = 4;
        Integer expected = 492;

        Integer actual = recipesIterationService.incrementationItemValuePerTier(itemValue, iterator);

        assertEquals(expected, actual);
    }

    @Test
    public void incrementationItemValuePerLevel_WhenInputInteger_ShouldReturnInteger() {
        Integer itemValue = 20;
        Integer iterator = 3;
        Integer expected = 328;

        Integer actual = recipesIterationService.incrementationItemValuePerLevel(itemValue, iterator);

        assertEquals(expected, actual);
    }

    @Test
    public void incrementationAmountIngredients_WhenIteratorIsGreaterThanThree_ShouldReturnCorrectValue() {
        Integer amount = 3;
        Integer iterator = 5;
        Integer expected = 6;

        Integer actual = recipesIterationService.incrementationAmountIngredients(amount, iterator);

        assertEquals(expected, actual);
    }

    @Test
    public void addLevelItem_WhenInputList_ShouldReturnList() {
        List<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredients("T4_ITEM", 2));
        List<CraftingRecipes> recipesList = new ArrayList<>();
        recipesList.add(new CraftingRecipes("T5_ITEM", 1, 30, "Resources", ingredientsList));

        List<CraftingRecipes> actual = recipesIterationService.addLevelItem(recipesList);

        assertEquals(4, actual.size());

    }

    @Test
    public void getListFromRecipeIterations_WhenInputObject_ShouldReturnListObjects() {
        List<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredients("T4_ITEM", 2));
        CraftingRecipes recipes = new CraftingRecipes("T5_ITEM", 1, 30, "Resources", ingredientsList);

        List<CraftingRecipes> actual = recipesIterationService.getListFromRecipeIterations(recipes);

        Assert.assertEquals(16,actual.size());
    }
}
