package org.ProfitablilityCalculatorForAlbion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ProfitabilityMeter {

    private final Double cost;
    private final Integer higherPrice;
    private final String city;
    private final List<SourceIngredients> ingredients;


    @Override
    public String toString() {
        return "ProfitabilityMeter{" +
                "cost=" + cost +
                ", higherPrice=" + higherPrice +
                ", city='" + city + '\'' +
                ", ingredients=" + ingredients +
                '}' + "\n";
    }

    public double getProfitability() {
        double sum = this.higherPrice - this.cost;
        sum = sum + this.cost;
        return ((sum / this.cost) * 100) - 100;
    }
}
