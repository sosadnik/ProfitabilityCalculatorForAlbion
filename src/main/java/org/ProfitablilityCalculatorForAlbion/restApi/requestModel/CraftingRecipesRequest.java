package org.ProfitablilityCalculatorForAlbion.restApi.requestModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class CraftingRecipesRequest {

    private String name;

    private Integer amountIngredients;

    private Integer itemValue;

    private String category;

    private List<IngredientsRequest> ingredients;

}
