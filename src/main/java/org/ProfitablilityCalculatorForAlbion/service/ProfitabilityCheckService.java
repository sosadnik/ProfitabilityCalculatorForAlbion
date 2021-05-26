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

    public List<ProfitabilityMeter> sortByProfitability(List<CraftingRecipes> recipesList) {

        List<ProfitabilityMeter> profitabilityMeterList = new ArrayList<>();
        for (CraftingRecipes recipes: recipesList){
           profitabilityMeterList.add(calculatorService.costCalculator(recipes));
        }

        return profitabilityMeterList.stream()
                .sorted(Comparator.comparing(ProfitabilityMeter::getDifferencesCostHigherPrice))
                .collect(Collectors.toList());
    }

}
