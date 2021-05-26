package org.ProfitablilityCalculatorForAlbion.webclient;


import org.ProfitablilityCalculatorForAlbion.webclient.dto.ItemDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbionDataClient {
    private static final String PRICES_URL = "https://www.albion-online-data.com/api/v2/stats/prices/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ItemDto[] getItem(String itemName, String locations, String qualities){
        return restTemplate.getForObject(
                PRICES_URL + "{itemName}.JSON?locations={locations}&qualities={qualities}",
                ItemDto[].class,
                itemName,
                locations,
                qualities

        );


    }

}
