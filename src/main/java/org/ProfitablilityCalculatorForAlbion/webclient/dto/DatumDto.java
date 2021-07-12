package org.ProfitablilityCalculatorForAlbion.webclient.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class DatumDto {
    private int item_count;
    private int avg_price;
    private Date timestamp;

}
