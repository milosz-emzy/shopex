package com.emzy.shopex.controller;

import com.emzy.shopex.dto.ItemRequest;
import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.model.Item;
import com.emzy.shopex.service.ItemService;
import com.emzy.shopex.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/findByExample")
    public ResponseEntity<List<ItemsResponse>> findByExample(@RequestBody ItemRequest itemRequest) {
        List<Item> items = itemService.findItemsByExample(itemRequest);

        List<ItemsResponse> itemsResponse = MapperUtil.mapToItemsResponse(items);

        return new ResponseEntity<>(itemsResponse, HttpStatus.OK);
    }


}
