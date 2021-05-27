package org.ProfitablilityCalculatorForAlbion.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProfitabilityMeter {

    private Double cost;
    private Integer higherPrice;
    private String city;
    private List<SourceIngredients> ingredients;


    @Override
    public String toString() {
        return "ProfitabilityMeter{" +
                "cost=" + cost +
                ", higherPrice=" + higherPrice +
                ", city='" + city + '\'' +
                ", ingredients=" + ingredients +
                '}' + "\n";
    }

    public double getDifferencesCostHigherPrice() {
        double sum = this.higherPrice - this.cost;
        sum = sum + this.cost;
        return (sum / this.cost) * 100;
    }
}
