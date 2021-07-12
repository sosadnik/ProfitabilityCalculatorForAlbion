package org.ProfitablilityCalculatorForAlbion.restApi.requestModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class CraftingRecipesRequest {

    private final String name;

    private final Integer amountIngredients;

    private final Integer itemValue;

    private final String category;

    private final List<IngredientsRequest> ingredients;

}
