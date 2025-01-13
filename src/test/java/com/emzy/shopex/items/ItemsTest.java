package com.emzy.shopex.items;

import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.utils.DataLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ItemsTest {

    private static List<ItemsResponse> items;

    @BeforeAll
    static void setup() {
        items = DataLoader.readItemsFromFile();
    }

    @Test
    void testStream_Filter() {
        List<ItemsResponse> filtered = items.stream().filter(item -> "item1".equals(item.getName())).toList();
        Assertions.assertEquals("item1", filtered.getFirst().getName());
    }

    @Test
    void testStream_sumUpPrices() {
        BigDecimal totalAmount = items.stream().map(ItemsResponse::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Assertions.assertEquals(new BigDecimal("41.30"), totalAmount);
    }
}
