package org.ProfitablilityCalculatorForAlbion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ItemProperty {

    private String name;
    private String city;
    private int quality;
    private int price;
    private LocalDate priceDate;


    @Override
    public String toString() {
        return "ItemProperty{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", quality=" + quality +
                ", price=" + price +
                ", priceDate=" + priceDate +
                '}';
    }
}
