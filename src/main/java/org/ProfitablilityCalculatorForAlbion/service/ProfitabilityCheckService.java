package org.ProfitablilityCalculatorForAlbion.service;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.ProfitablilityCalculatorForAlbion.model.ProfitabilityMeter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfitabilityCheckService {

    private final CalculatorService calculatorService;
    private final RecipesIterationService recipesIterationService;
    private final CraftingRecipesService craftingRecipesService;

    public List<ProfitabilityMeter> getProfitabilityListForItem(String item) {
        List<ProfitabilityMeter> profitabilityMeterList = new ArrayList<>();

        for (CraftingRecipes recipes : craftingRecipesService.getCraftingRecipesListForItem(item)) {
            getIterationRecipesList(profitabilityMeterList, recipes);
        }
        return sortByProfitability(profitabilityMeterList);
    }

    public List<ProfitabilityMeter> getProfitabilityListForCategory(String categoryName) {
        List<ProfitabilityMeter> profitabilityMeterList = new ArrayList<>();

        for (CraftingRecipes recipes : craftingRecipesService.getCraftingRecipesListForCategory(categoryName)) {
            getIterationRecipesList(profitabilityMeterList, recipes);
        }
        return sortByProfitability(profitabilityMeterList);
    }

    public void getIterationRecipesList(List<ProfitabilityMeter> profitabilityMeterList, CraftingRecipes recipes) {
        for (CraftingRecipes iterationRecipes : recipesIterationService.getListFromRecipeIterations(recipes)) {
            profitabilityMeterList.add(calculatorService.costCalculator(iterationRecipes));
        }
    }

    private List<ProfitabilityMeter> sortByProfitability(List<ProfitabilityMeter> profitabilityMeterList) {
        return profitabilityMeterList.stream()
                .sorted(Comparator.comparing(ProfitabilityMeter::getDifferencesCostHigherPrice))
                .collect(Collectors.toList());
    }

}
