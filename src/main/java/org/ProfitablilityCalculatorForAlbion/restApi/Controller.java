package org.ProfitablilityCalculatorForAlbion.restApi;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.CraftingRecipes;
import org.ProfitablilityCalculatorForAlbion.model.ProfitabilityMeter;
import org.ProfitablilityCalculatorForAlbion.service.ProfitabilityCheckService;
import org.ProfitablilityCalculatorForAlbion.service.RecipesIterationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final RecipesIterationService recipesIterationService;
    private final ProfitabilityCheckService profitabilityCheckService;

    @RequestMapping("/getList")
    public List<CraftingRecipes> getCraftingList() {
        return recipesIterationService.itemNameIterator();
    }

    @RequestMapping("/getFullList")
    public List<CraftingRecipes> getCraftingListWithLevel() {
        return recipesIterationService.addLevelItem(recipesIterationService.itemNameIterator());
    }

    @RequestMapping("/getProfitabilityList")
    public List<ProfitabilityMeter> getProfitabilityList() {
        return profitabilityCheckService.sortByProfitability(getCraftingListWithLevel());
    }
}
