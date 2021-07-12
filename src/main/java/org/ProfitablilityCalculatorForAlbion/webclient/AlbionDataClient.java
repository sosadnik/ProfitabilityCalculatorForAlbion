package org.ProfitablilityCalculatorForAlbion.webclient;


import org.ProfitablilityCalculatorForAlbion.webclient.dto.ItemDto;
import org.ProfitablilityCalculatorForAlbion.webclient.dto.PriceHistoryDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbionDataClient {

    private static final String API_URL = "https://www.albion-online-data.com/api/v2/stats/";
    private static final String ARGUMENTS_URL = "{itemName}.JSON?locations={locations}&qualities={qualities}";
    private final RestTemplate restTemplate = new RestTemplate();

    public ItemDto[] getItem(String itemName, String locations, String qualities) {
        return restTemplate.getForObject(
                API_URL + "prices/" + ARGUMENTS_URL,
                ItemDto[].class,
                itemName,
                locations,
                qualities
        );
    }

    public PriceHistoryDto[] getItemPrice(String itemName, String location, String qualities, String date) {
        return restTemplate.getForObject(
                API_URL + "history/"+ ARGUMENTS_URL + "&date={date}&time-scale=1",
                PriceHistoryDto[].class,
                itemName,
                location,
                qualities,
                date
        );

    }
}
