package service;

import org.ProfitablilityCalculatorForAlbion.model.ItemProperty;
import org.ProfitablilityCalculatorForAlbion.model.SourceIngredients;
import org.ProfitablilityCalculatorForAlbion.service.CalculatorService;
import org.ProfitablilityCalculatorForAlbion.webclient.AlbionDataClient;
import org.ProfitablilityCalculatorForAlbion.webclient.dto.ItemDto;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CalculatorServiceTest {

    private AlbionDataClient albionDataClient;
    private CalculatorService calculatorService;

    @Before
    public void setup() {
        albionDataClient = mock(AlbionDataClient.class);
        calculatorService = new CalculatorService(albionDataClient);
    }

    @Test
    public void getListItem_WhenInputString_ShouldReturnList() {
        ItemDto[] priceClientItem = new ItemDto[2];
        priceClientItem[0] = ItemDto.builder()
                .item_id("T4_BAG")
                .city("Bridgewatch")
                .quality(2)
                .sell_price_min(2000)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 1))
                .build();
        priceClientItem[1] = ItemDto.builder()
                .item_id("T4_BAG")
                .city("Martlock")
                .quality(2)
                .sell_price_min(653)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 20))
                .build();

        List<ItemProperty> expected = new ArrayList<>();
        expected.add(new ItemProperty(
                "T4_BAG", "Bridgewatch", 2, 2000, LocalDate.of(2021, Calendar.JUNE, 1)));
        expected.add(new ItemProperty(
                "T4_BAG", "Martlock", 2, 653, LocalDate.of(2021, Calendar.JUNE, 20)));

        when(albionDataClient.getItem(anyString(), anyString(), anyString())).thenReturn(priceClientItem);
        List<ItemProperty> actual = calculatorService.getListItem("T4_BAG");

        assertEquals(expected, actual);
    }

    //Cannot read the array length because
    @Test
    public void findHighestPrice_WhenInputString_ShouldReturnItemProperty() {
        List<ItemProperty> itemPropertyList = new ArrayList<>();
        itemPropertyList.add(new ItemProperty(
                "T4_BAG", "Bridgewatch", 2, 2000, LocalDate.of(2021, Calendar.JUNE, 31)));
        itemPropertyList.add(new ItemProperty(
                "T4_BAG", "Martlock", 2, 653, LocalDate.of(2021, Calendar.JUNE, 20)));
        itemPropertyList.add(new ItemProperty(
                "T4_BAG", "Lymhurst", 2, 555, LocalDate.of(2021, Calendar.JUNE, 20)));
        ItemProperty expected = (new ItemProperty(
                "T4_BAG", "Bridgewatch", 2, 2000, LocalDate.of(2021, Calendar.JUNE, 31)));

        when(calculatorService.getListItem("T4_BAG")).thenReturn(itemPropertyList);
        ItemProperty actual = calculatorService.findHighestPrice("T4_BAG");

        assertEquals(expected, actual);
    }

    @Test
    public void getTotalCost_WhenInputList_ShouldReturnDouble() {
        List<SourceIngredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(SourceIngredients.builder()
                .name("T5_ITEM")
                .price(200)
                .amountIngredients(3)
                .build());
        ingredientsList.add(SourceIngredients.builder()
                .name("T3_ITEM")
                .price(100)
                .amountIngredients(2)
                .build());

        double actual = calculatorService.getTotalCost(ingredientsList, 50);

        assertEquals(189.5, actual, 2);
    }
}
