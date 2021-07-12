package org.ProfitablilityCalculatorForAlbion.webclient.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PriceHistoryDto {
    private String location;
    private String item_id;
    private int quality;
    private List<DatumDto> data;
}
