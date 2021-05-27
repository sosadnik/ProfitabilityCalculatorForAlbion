package org.ProfitablilityCalculatorForAlbion.restApi;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.ProfitabilityMeter;
import org.ProfitablilityCalculatorForAlbion.service.ProfitabilityCheckService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ProfitabilityCheckService profitabilityCheckService;

    @RequestMapping("/getProfitabilityListForItem/{itemName}")
    public List<ProfitabilityMeter> getProfitabilityList(@PathVariable String itemName) {
        return profitabilityCheckService.getProfitabilityListForItem(itemName);
    }

    @RequestMapping("/getProfitabilityListForCategory/{category}")
    public List<ProfitabilityMeter> getProfitabilityListForCategory(@PathVariable String category) {
        return profitabilityCheckService.getProfitabilityListForCategory(category);
    }
}
