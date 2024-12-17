package com.emzy.shopex.util;

import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.model.Item;

import java.util.List;

public class MapperUtil {
    public static List<ItemsResponse> mapToItemsResponse(List<Item> items) {
        return items.stream().map(item -> ItemsResponse.builder()
                        .name(item.getName())
                        .price(item.getPrice())
                        .build())
                .toList();
    }
}
