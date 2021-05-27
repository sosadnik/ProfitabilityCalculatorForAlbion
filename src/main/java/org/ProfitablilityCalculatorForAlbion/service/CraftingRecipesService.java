package org.ProfitablilityCalculatorForAlbion.service;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.ProfitablilityCalculatorForAlbion.model.Ingredients;
import org.ProfitablilityCalculatorForAlbion.repository.CraftingRecipesRepository;
import org.ProfitablilityCalculatorForAlbion.repository.IngredientsRepository;
import org.ProfitablilityCalculatorForAlbion.restApi.requestModel.CraftingRecipesRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CraftingRecipesService {

    private final CraftingRecipesRepository craftingRecipesRepository;
    private final IngredientsRepository ingredientsRepository;
    private final CraftingRecipesMapper craftingRecipesMapper;

    public void save(CraftingRecipes craftingRecipes) {
        if (!craftingRecipesRepository.existsByName(craftingRecipes.getName())) {

            craftingRecipesRepository.save(craftingRecipes);

            for (Ingredients ingredients : craftingRecipes.getIngredients()) {
                ingredients.setCraftingRecipes(craftingRecipes);
                ingredientsRepository.save(ingredients);
            }
        }
    }

    public void saveCraftingRecipesRequest(CraftingRecipesRequest craftingRecipesRequest){
        save(craftingRecipesMapper.map(craftingRecipesRequest));
    }

    public List<CraftingRecipes> getCraftingRecipesListForCategory(String categoryName) {
        return craftingRecipesRepository.findByCategory(categoryName);
    }

    public List<CraftingRecipes> getCraftingRecipesListForItem(String item) {
        return craftingRecipesRepository.findByName(item);
    }


}
