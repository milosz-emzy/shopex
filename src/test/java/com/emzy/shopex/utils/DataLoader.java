package com.emzy.shopex.utils;

import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.model.ItemSize;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<ItemsResponse> readItemsFromFile() {
        JsonNode jsonNode;
        List<ItemsResponse> items = new ArrayList<>();
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/items.json")) {
            jsonNode =  objectMapper.readValue(inputStream, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read json data");
        }

        JsonNode itemsNode = Optional.ofNullable(jsonNode)
                .map(j -> j.get("items"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));

        for (JsonNode itemNode: itemsNode) {
            items.add(createItemFromNode(itemNode));
        }

        return items;
    }

    private static ItemsResponse createItemFromNode(JsonNode itemNode) {
        return ItemsResponse.builder()
                .name(itemNode.get("name").asText())
                .size(ItemSize.valueOf(itemNode.get("size").asText()))
                .price(new BigDecimal(itemNode.get("price").asText()))
                .build();
    }
}
