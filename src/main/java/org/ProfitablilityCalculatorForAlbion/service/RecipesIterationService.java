package org.ProfitablilityCalculatorForAlbion.service;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.ProfitablilityCalculatorForAlbion.model.Ingredients;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipesIterationService {

    public List<CraftingRecipes> iterationCraftingRecipes(CraftingRecipes recipes) {
        List<CraftingRecipes> recipesList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            List<Ingredients> ingredientsList = new ArrayList<>();
            for (Ingredients x : recipes.getIngredients()) {
                ingredientsList.add(Ingredients.builder()
                        .name(iterationString(x.getName(), i))
                        .amount(incrementationAmountIngredients(x.getAmount(), i))
                        .build());
            }
            recipesList.add(
                    CraftingRecipes.builder()
                            .name(iterationString(recipes.getName(), i))
                            .itemValue(incrementationItemValuePerTier(recipes.getItemValue(), i))
                            .ingredients(ingredientsList)
                            .amountIngredients(recipes.getAmountIngredients())
                            .build());
        }
        return addLevelItem(recipesList);
    }

    public List<CraftingRecipes> addLevelItem(List<CraftingRecipes> list) {
        List<CraftingRecipes> craftingRecipesList = new ArrayList<>(list);

        for (CraftingRecipes recipes : list) {
            for (int i = 0; i < 3; i++) {
                List<Ingredients> ingredientsList = new ArrayList<>();
                for (Ingredients ingredients : recipes.getIngredients()) {
                    ingredientsList.add(Ingredients.builder()
                            .name(addLevelToName(ingredients.getName(), i))
                            .amount(ingredients.getAmount())
                            .build());
                }
                craftingRecipesList.add(CraftingRecipes.builder()
                        .name(addLevelToName(recipes.getName(), i))
                        .itemValue(incrementationItemValuePerLevel(recipes.getItemValue(), i))
                        .amountIngredients(recipes.getAmountIngredients())
                        .ingredients(ingredientsList)
                        .build());
            }
        }
        return craftingRecipesList;
    }

    public Integer incrementationAmountIngredients(Integer amount, Integer iterator) {
        if (iterator > 3) {
            return amount + 3;
        } else if (amount == 1) return amount;
        else {
            return amount + iterator;
        }
    }

    public Integer incrementationItemValuePerLevel(Integer itemValue, Integer iterator) {
        for (int i = 0; i < iterator + 1; i++) {
            itemValue = Math.toIntExact(Math.round(itemValue * 2.015));
        }
        return Math.round(itemValue);
    }

    public Integer incrementationItemValuePerTier(Integer itemValue, Integer iterator) {
        for (int i = 0; i < iterator; i++) {
            itemValue = Math.toIntExact(Math.round(itemValue * 2.015));
        }
        return Math.round(itemValue);
    }

    public String addLevelToName(String itemName, Integer iterator) {
        return itemName + "_LEVEL" + (iterator + 1) + "@" + (iterator + 1);
    }

    public String iterationString(String itemName, Integer iterator) {
        Integer actualValue = Integer.valueOf(itemName.substring(1, 2));

        return itemName.replaceFirst("[0-9]", String.valueOf(actualValue + iterator));
    }
}
