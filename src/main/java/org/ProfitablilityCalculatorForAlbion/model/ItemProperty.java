package org.ProfitablilityCalculatorForAlbion.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ItemProperty {
    private String name;
    private String city;
    private int quality;
    private int price;
    private Date priceDate;




}
