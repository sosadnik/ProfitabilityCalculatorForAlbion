package org.ProfitablilityCalculatorForAlbion.service;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.ProfitablilityCalculatorForAlbion.model.Ingredients;
import org.ProfitablilityCalculatorForAlbion.repository.CraftingRecipesRepository;
import org.ProfitablilityCalculatorForAlbion.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CraftingRecipesService {

    private final CraftingRecipesRepository craftingRecipesRepository;
    private final IngredientsRepository ingredientsRepository;

    public void save(CraftingRecipes craftingRecipes) {
        if (!craftingRecipesRepository.existsByName(craftingRecipes.getName())) {

            craftingRecipesRepository.save(craftingRecipes);

            for (Ingredients ingredients : craftingRecipes.getIngredients()) {
                ingredients.setCraftingRecipes(craftingRecipes);
                ingredientsRepository.save(ingredients);
            }
        }
    }
}
