package com.emzy.shopex.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemRequest {
    private String name;
    private BigDecimal price;

}
