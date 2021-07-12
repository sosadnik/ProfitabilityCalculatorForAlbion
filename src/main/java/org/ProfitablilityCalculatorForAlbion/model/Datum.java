package org.ProfitablilityCalculatorForAlbion.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Datum {
    private final int itemCount;
    private final int avgPrice;
    private final Date timestamp;
}
