package org.ProfitablilityCalculatorForAlbion.restApi.requestModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class IngredientsRequest {

    private String name;
    private Integer amount;
}
