package com.emzy.shopex.items;

import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.utils.DataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemsTest {

    private static List<ItemsResponse> items;

    @BeforeAll
    static void setup() {
        items = DataLoader.readItemsFromFile();
    }

    @Test
    void testStream_Filter() {
        List<ItemsResponse> filtered = items.stream().filter(item -> "item1".equals(item.getName())).toList();
        assertThat(filtered).hasSize(1);
        assertThat(filtered.getFirst().getName()).isEqualTo("item1");
    }

    @Test
    void testStream_sumUpPrices() {
        BigDecimal totalAmount = items.stream().map(ItemsResponse::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal expectedAmount = new BigDecimal("41.30");
        assertThat(totalAmount).isEqualByComparingTo(expectedAmount);
        assertThat(totalAmount).usingComparator(BigDecimal::compareTo).isEqualTo(expectedAmount);
    }
}
