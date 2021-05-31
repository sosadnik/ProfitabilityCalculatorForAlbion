package org.ProfitablilityCalculatorForAlbion.webclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String item_id;
    private String city;
    private int quality;
    private int sell_price_min;
    private LocalDate sell_price_min_date;
    private int sell_price_max;
    private LocalDate sell_price_max_date;
    private int buy_price_min;
    private LocalDate buy_price_min_date;
    private int buy_price_max;
    private LocalDate buy_price_max_date;
}





