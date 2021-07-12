package org.ProfitablilityCalculatorForAlbion.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PriceHistory {
    private final String location;
    private final String itemName;
    private final List<Datum> data;
}
