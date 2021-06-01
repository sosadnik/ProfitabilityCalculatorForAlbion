package service;

import org.ProfitablilityCalculatorForAlbion.model.*;
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
    private CalculatorService mockCalculatorService;

    @Before
    public void setup() {
        albionDataClient = mock(AlbionDataClient.class);
        mockCalculatorService = mock(CalculatorService.class);
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

    @Test
    public void findHighestPrice_WhenInputString_ShouldReturnItemProperty() {
        ItemDto[] priceClientItem = new ItemDto[2];
        priceClientItem[0] = ItemDto.builder()
                .item_id("T4_BAG")
                .city("Bridgewatch")
                .quality(2)
                .sell_price_min(2000)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 1))
                .build();
        priceClientItem[1] = ItemDto.builder()
                .item_id("T4_BAR")
                .city("Martlock")
                .quality(2)
                .sell_price_min(653)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 20))
                .build();
        ItemProperty expected = (new ItemProperty(
                "T4_BAG", "Bridgewatch", 2, 2000, LocalDate.of(2021, Calendar.JUNE, 1)));

        when(albionDataClient.getItem(anyString(), anyString(), anyString())).thenReturn(priceClientItem);
        ItemProperty actual = calculatorService.findHighestPrice("T4_BAG");

        assertEquals(expected, actual);
    }

    @Test
    public void findLowestPrice_WhenInputString_ShouldReturnItemProperty() {
        ItemDto[] priceClientItem = new ItemDto[2];
        priceClientItem[0] = ItemDto.builder()
                .item_id("T4_BAG")
                .city("Bridgewatch")
                .quality(2)
                .sell_price_min(2000)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 1))
                .build();
        priceClientItem[1] = ItemDto.builder()
                .item_id("T4_BAR")
                .city("Martlock")
                .quality(2)
                .sell_price_min(653)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 20))
                .build();
        ItemProperty expected = (new ItemProperty(
                "T4_BAR", "Martlock", 2, 653, LocalDate.of(2021, Calendar.JUNE, 20)));

        when(albionDataClient.getItem(anyString(), anyString(), anyString())).thenReturn(priceClientItem);
        ItemProperty actual = calculatorService.findLowestPrice("T4_BAG");

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

    @Test
    public void costCalculator_WhenInputObject_ShouldReturnCorrectProfitabilityMeter() {
        List<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredients("T4_BAG", 2));
        ingredientsList.add(new Ingredients("T3_ITEM", 2));
        CraftingRecipes recipes = new CraftingRecipes("T6_ITEM", 2, 30, "Resource", ingredientsList);
        List<SourceIngredients> sourceIngredientsList = new ArrayList<>();
        sourceIngredientsList.add(SourceIngredients.builder().name("T4_BAG").price(1306).city("Martlock").amountIngredients(2).build());
        sourceIngredientsList.add(SourceIngredients.builder().name("T3_ITEM").price(1306).city("Martlock").amountIngredients(2).build());
        ProfitabilityMeter expected = new ProfitabilityMeter(1645.56, 2000, "Bridgewatch", sourceIngredientsList);
        ItemProperty itemProperty = (new ItemProperty(
                "T5_ITEM", "Bridgewatch", 2, 150, LocalDate.of(2021, Calendar.JUNE, 31)));
        ItemDto[] priceClientItem = new ItemDto[2];
        priceClientItem[0] = ItemDto.builder()
                .item_id("T4_BAG")
                .city("Bridgewatch")
                .quality(2)
                .sell_price_min(2000)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 1))
                .build();
        priceClientItem[1] = ItemDto.builder()
                .item_id("T4_BAR")
                .city("Martlock")
                .quality(2)
                .sell_price_min(653)
                .sell_price_min_date(LocalDate.of(2021, Calendar.JUNE, 20))
                .build();

        when(mockCalculatorService.findLowestPrice(anyString())).thenReturn(itemProperty);
        when(mockCalculatorService.findHighestPrice(anyString())).thenReturn(itemProperty);
        when(albionDataClient.getItem(anyString(), anyString(), anyString())).thenReturn(priceClientItem);
        ProfitabilityMeter actual = calculatorService.costCalculator(recipes);

        assertEquals(expected, actual);
    }
}
