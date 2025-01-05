package com.emzy.shopex.dto;

import com.emzy.shopex.model.ItemSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemsResponse {

    private String name;
    private BigDecimal price;
    private ItemSize size;
}
