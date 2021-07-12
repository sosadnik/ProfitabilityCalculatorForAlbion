package org.ProfitablilityCalculatorForAlbion.service.profitabilityForSingleCity;

import org.ProfitablilityCalculatorForAlbion.model.Datum;
import org.ProfitablilityCalculatorForAlbion.model.PriceHistory;
import org.ProfitablilityCalculatorForAlbion.webclient.dto.DatumDto;
import org.ProfitablilityCalculatorForAlbion.webclient.dto.PriceHistoryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceHistoryMapper {

    public PriceHistory map(PriceHistoryDto priceHistoryDtos) {
        return PriceHistory.builder()
                .location(priceHistoryDtos.getLocation())
                .itemName(priceHistoryDtos.getItem_id())
                .data(mapDatum(priceHistoryDtos.getData()))
                .build();
    }

    public List<Datum> mapDatum(List<DatumDto> dtoList) {
        List<Datum> datum = new ArrayList<>();

        for (DatumDto dto : dtoList){
            datum.add(Datum.builder()
                    .itemCount(dto.getItem_count())
                    .avgPrice(dto.getAvg_price())
                    .timestamp(dto.getTimestamp())
                    .build());
        }
        return sortDatum(datum);
    }

    public List<Datum> sortDatum(List<Datum> list){
        return list.stream()
                .sorted(Comparator.comparing(Datum::getTimestamp))
                .collect(Collectors.toList());
    }

}
