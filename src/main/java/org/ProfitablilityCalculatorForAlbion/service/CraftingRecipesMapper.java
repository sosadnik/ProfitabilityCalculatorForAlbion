package org.ProfitablilityCalculatorForAlbion.service;

import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.ProfitablilityCalculatorForAlbion.model.Ingredients;
import org.ProfitablilityCalculatorForAlbion.restApi.requestModel.CraftingRecipesRequest;
import org.ProfitablilityCalculatorForAlbion.restApi.requestModel.IngredientsRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CraftingRecipesMapper {

    public CraftingRecipes map(CraftingRecipesRequest craftingRecipes) {
        return CraftingRecipes.builder()
                .name(craftingRecipes.getName())
                .amountIngredients(craftingRecipes.getAmountIngredients())
                .itemValue(craftingRecipes.getItemValue())
                .category(craftingRecipes.getCategory())
                .ingredients(mapIngredients(craftingRecipes.getIngredients()))
                .build();
    }

    public List<Ingredients> mapIngredients(List<IngredientsRequest> list) {
        List<Ingredients> ingredientsList = new ArrayList<>();

        for (IngredientsRequest ingredients : list) {
            ingredientsList.add(Ingredients.builder()
                    .name(ingredients.getName())
                    .amount(ingredients.getAmount())
                    .build());
        }
        return ingredientsList;
    }
}
