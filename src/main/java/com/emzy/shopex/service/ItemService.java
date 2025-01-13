package com.emzy.shopex.service;

import com.emzy.shopex.dto.ItemRequest;
import com.emzy.shopex.model.Item;
import com.emzy.shopex.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findItemsByExample(ItemRequest itemRequest) {
        Item item = Item.builder()
                .name(itemRequest.getName())
                .price(itemRequest.getPrice())
                .build();

        Example<Item> example = Example.of(item);

        return itemRepository.findAll(example);
    }

    public List<Item> getPriceSortedItemsByDirection(Sort.Direction sortDirection) {
        return itemRepository.findAll(Sort.by(sortDirection, "price"));
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
}
