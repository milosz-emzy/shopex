package com.emzy.shopex.util;

import com.emzy.shopex.dto.WarehouseItems;
import com.emzy.shopex.exceptions.ErrorResponse;
import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.model.Item;
import com.emzy.shopex.model.Warehouse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.List;

public class MapperUtil {
    public static List<ItemsResponse> mapToItemsResponse(List<Item> items) {
        return items.stream().map(item -> ItemsResponse.builder()
                        .name(item.getName())
                        .price(item.getPrice())
                        .size(item.getSize())
                        .build())
                .toList();
    }

    public static ErrorResponse getErrorResponse(HttpServletRequest request, String message) {
        return ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.name())
                .messages(List.of(message))
                .path(request.getServletPath())
                .build();
    }

    public static ErrorResponse getErrorResponse(HttpServletRequest request, String message, HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .statusCode(httpStatus.name())
                .messages(List.of(message))
                .path(request.getServletPath())
                .build();
    }

    public static WarehouseItems mapToWarehouseItems(Warehouse warehouse, List<Item> items) {
        return WarehouseItems.builder()
                .warehouse(warehouse)
                .items(items)
                .build();
    }
}
