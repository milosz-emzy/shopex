package com.emzy.shopex.utils;

import com.emzy.shopex.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class DataProvider {

    public static List<Item> getItems() {
        Item item1 = Item.builder()
                .id(1)
                .name("item1")
                .price(BigDecimal.valueOf(30.30))
                .build();
        Item item2 = Item.builder()
                .price(BigDecimal.valueOf(70.70))
                .name("item2")
                .id(2)
                .build();

        return List.of(
                item1,
                item2
        );
    }
}
