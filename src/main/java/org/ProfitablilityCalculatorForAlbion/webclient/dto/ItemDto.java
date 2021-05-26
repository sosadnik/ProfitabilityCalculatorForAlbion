package org.ProfitablilityCalculatorForAlbion.webclient.dto;

import lombok.Getter;

import java.util.Date;


@Getter
public class ItemDto {
    private String item_id;
    private String city;
    private int quality;
    private int sell_price_min;
    private Date sell_price_min_date;
    private int sell_price_max;
    private Date sell_price_max_date;
    private int buy_price_min;
    private Date buy_price_min_date;
    private int buy_price_max;
    private Date buy_price_max_date;
}





