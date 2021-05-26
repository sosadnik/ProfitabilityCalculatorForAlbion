package org.ProfitablilityCalculatorForAlbion.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SourceIngredients {

    private String name;
    private String city;
    private Integer price;
    private Integer amountIngredients;


    @Override
    public String toString() {
        return "SourceIngredients{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", price=" + price +
                ", amountIngredients=" + amountIngredients +
                '}';
    }
}
