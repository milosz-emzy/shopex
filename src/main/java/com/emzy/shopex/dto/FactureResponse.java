package com.emzy.shopex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FactureResponse {

    private List<ItemsResponse> items;
    private BigDecimal amount;
}
