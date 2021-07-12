package org.ProfitablilityCalculatorForAlbion.service.profitabilityForSingleCity;

import lombok.AllArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.PriceHistory;
import org.ProfitablilityCalculatorForAlbion.webclient.AlbionDataClient;
import org.ProfitablilityCalculatorForAlbion.webclient.dto.PriceHistoryDto;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfitabilityFinderService {
    private final AlbionDataClient albionDataClient;
    private final PriceHistoryMapper priceHistoryMapper;

    public List<PriceHistory> getPrice(String itemName, String location) {
        List<PriceHistory> priceHistoryList = new ArrayList<>();

        for (PriceHistoryDto dto : albionDataClient.getItemPrice(itemName,
                location,
                "1",
                getDataString())) {
            priceHistoryList.add(priceHistoryMapper.map(dto));
        }

        return priceHistoryList;
    }


    public String getDataString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        return format.format(new Date());
    }
}
