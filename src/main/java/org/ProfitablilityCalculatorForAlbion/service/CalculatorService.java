package org.ProfitablilityCalculatorForAlbion.service;

import lombok.RequiredArgsConstructor;
import org.ProfitablilityCalculatorForAlbion.model.*;
import org.ProfitablilityCalculatorForAlbion.webclient.AlbionDataClient;
import org.ProfitablilityCalculatorForAlbion.webclient.dto.ItemDto;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@ComponentScan
@RequiredArgsConstructor
public class CalculatorService {

    private final AlbionDataClient albionDataClient;

    public ProfitabilityMeter costCalculator(CraftingRecipes craftingRecipes) {
        List<SourceIngredients> list = new ArrayList<>();

        for (Ingredients pair : craftingRecipes.getIngredients()) {
            list.add(SourceIngredients.builder()
                    .name(pair.getName())
                    .city(findLowestPrice(pair.getName()).getCity())
                    .price(pair.getAmount() * findLowestPrice(pair.getName()).getPrice())
                    .amountIngredients(pair.getAmount())
                    .build());
        }
        return ProfitabilityMeter.builder()
                .cost(getTotalCost(list, craftingRecipes.getItemValue()))
                .ingredients(list)
                .higherPrice(findHighestPrice(craftingRecipes.getName()).getPrice())
                .city(findHighestPrice(craftingRecipes.getName()).getCity())
                .build();
    }

    public double getTotalCost(List<SourceIngredients> list, Integer itemValue) {
        int sum = list.stream()
                .map(SourceIngredients::getPrice)
                .mapToInt(Integer::intValue).sum();

        return (sum * 0.63) + (itemValue * (1 / 20) * 100);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public ItemProperty findLowestPrice(String name) {
        List<ItemProperty> listItem = getListItem(name);
        return listItem.stream()
                .filter(itemProperty -> itemProperty.getPrice() > 0)
                .min(Comparator.comparing(ItemProperty::getPrice))
                .get();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public ItemProperty findHighestPrice(String name) {
        List<ItemProperty> propertyList = getListItem(name);
        return propertyList.stream()
                .max(Comparator.comparing(ItemProperty::getPrice))
                .get();
    }

    public List<ItemProperty> getListItem(String itemName) {

        String locations = "Bridgewatch,Martlock,Lymhurst,Thetford,Fort Sterling";
        ItemDto[] priceClientItem = albionDataClient.getItem(itemName, locations, "1");

        List<ItemProperty> itemPropertyList = new ArrayList<>();
        for (ItemDto x : priceClientItem) {
            itemPropertyList.add(ItemProperty.builder()
                    .name(x.getItem_id())
                    .city(x.getCity())
                    .quality(x.getQuality())
                    .price(x.getSell_price_min())
                    .priceDate(x.getSell_price_min_date())
                    .build());
        }
        return itemPropertyList;
    }
}
