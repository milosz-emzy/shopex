package com.emzy.shopex.controller;

import lombok.Builder;

import java.util.List;

public record ErrorResponse(
        String statusCode,
        String path,
        List<String> messages
) {

    @Builder
    public ErrorResponse {
    }
}
