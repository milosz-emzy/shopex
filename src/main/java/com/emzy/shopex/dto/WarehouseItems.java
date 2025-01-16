package com.emzy.shopex.dto;

import com.emzy.shopex.model.Item;
import com.emzy.shopex.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WarehouseItems {

    private Warehouse warehouse;

    private List<Item> items;
}
