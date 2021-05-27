package org.ProfitablilityCalculatorForAlbion.restApi;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.ProfitabilityMeter;
import org.ProfitablilityCalculatorForAlbion.restApi.requestModel.CraftingRecipesRequest;
import org.ProfitablilityCalculatorForAlbion.service.CraftingRecipesService;
import org.ProfitablilityCalculatorForAlbion.service.ProfitabilityCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ProfitabilityCheckService profitabilityCheckService;
    private final CraftingRecipesService craftingRecipesService;

    @RequestMapping("/getProfitabilityListForItem/{itemName}")
    public List<ProfitabilityMeter> getProfitabilityList(@PathVariable String itemName) {
        return profitabilityCheckService.getProfitabilityListForItem(itemName);
    }

    @RequestMapping("/getProfitabilityListForCategory/{category}")
    public List<ProfitabilityMeter> getProfitabilityListForCategory(@PathVariable String category) {
        return profitabilityCheckService.getProfitabilityListForCategory(category);
    }

    @PostMapping("/createCraftingRecipes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createRecipes(@RequestBody CraftingRecipesRequest craftingRecipes) {
        craftingRecipesService.saveCraftingRecipesRequest(craftingRecipes);
    }
}
