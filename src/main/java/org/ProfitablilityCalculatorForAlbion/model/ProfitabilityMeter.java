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
        double i = (sum / this.cost) * 100;
        System.out.println("suma: ");
        System.out.println(sum);
        System.out.println("suma/ cost: ");
        System.out.println(i);
        System.out.println("\n");
        return i;
    }
}
