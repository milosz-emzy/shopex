package com.emzy.shopex.service;

import com.emzy.shopex.dto.FactureResponseDTO;
import com.emzy.shopex.model.Item;
import com.emzy.shopex.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ShopService shopService;

    @Test
    void shouldCalculateProperTotalAmount() {
        Item item1 = Item.builder()
                .id(1)
                .price(BigDecimal.valueOf(30.30))
                .build();
        Item item2 = Item.builder()
                .price(BigDecimal.valueOf(70.70))
                .id(2)
                .build();
        BigDecimal totalAmount = item1.getPrice().add(item2.getPrice());

        Mockito.when(itemRepository.findAllById(List.of(1, 2))).thenReturn(List.of(item1, item2));

        FactureResponseDTO factureResponseDTO = shopService.purchase(List.of(1, 2));

        assertEquals(factureResponseDTO.getAmount(), totalAmount);
    }

}