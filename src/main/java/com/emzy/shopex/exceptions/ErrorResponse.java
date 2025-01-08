package com.emzy.shopex.exceptions;

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

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", path='" + path + '\'' +
                ", messages=" + messages +
                '}';
    }
}
