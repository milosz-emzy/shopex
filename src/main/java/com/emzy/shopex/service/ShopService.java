package com.emzy.shopex.service;

import com.emzy.shopex.dto.FactureResponseDTO;
import com.emzy.shopex.model.Item;
import com.emzy.shopex.repository.ItemRepository;
import com.emzy.shopex.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShopService {

    private final ItemRepository itemRepository;

    @Autowired
    public ShopService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public FactureResponseDTO purchase(List<Integer> itemsId) {
        List<Item> items = itemRepository.findAllById(itemsId);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Item item : items) {
            totalAmount = totalAmount.add(item.getPrice());

        }

        return FactureResponseDTO.builder()
                .items(MapperUtil.mapToItemsResponse(items))
                .amount(totalAmount)
                .build();
    }
}
