package org.ProfitablilityCalculatorForAlbion.restApi;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.PriceHistory;
import org.ProfitablilityCalculatorForAlbion.model.ProfitabilityMeter;
import org.ProfitablilityCalculatorForAlbion.restApi.requestModel.CraftingRecipesRequest;
import org.ProfitablilityCalculatorForAlbion.service.CraftingRecipesService;
import org.ProfitablilityCalculatorForAlbion.service.LocationsService;
import org.ProfitablilityCalculatorForAlbion.service.ProfitabilityCheckService;
import org.ProfitablilityCalculatorForAlbion.service.profitabilityForSingleCity.ProfitabilityFinderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ProfitabilityCheckService profitabilityCheckService;
    private final CraftingRecipesService craftingRecipesService;
    private final ProfitabilityFinderService profitabilityFinderService;
    private final LocationsService locationsService;

    @GetMapping("/getProfitabilityListForItem/{itemName}")
    public List<ProfitabilityMeter> getProfitabilityList(@PathVariable String itemName) {
        return profitabilityCheckService.getProfitabilityListForItem(itemName);
    }

    @GetMapping("/getProfitabilityListForCategory/{category}")
    public List<ProfitabilityMeter> getProfitabilityListForCategory(@PathVariable String category) {
        return profitabilityCheckService.getProfitabilityListForCategory(category);
    }

    @PostMapping("/createCraftingRecipes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createRecipes(@RequestBody CraftingRecipesRequest craftingRecipes) {
        craftingRecipesService.saveCraftingRecipesRequest(craftingRecipes);
    }

    @PostMapping("/addLocations/{locationsName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addLocations(@PathVariable String locationsName) {
        locationsService.saveLocations(locationsName);
    }

    @GetMapping("/getProfitabilityForSingleCity/{itemName}")
    public List<PriceHistory> getPriceHistory(@PathVariable String itemName) {
        return profitabilityFinderService.getPrice(itemName,"Martlock");
    }
}
