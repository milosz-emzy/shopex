package com.emzy.shopex.util;

import com.emzy.shopex.controller.ErrorResponse;
import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.model.Item;
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
}
