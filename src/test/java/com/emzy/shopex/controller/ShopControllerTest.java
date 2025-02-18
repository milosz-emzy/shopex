package com.emzy.shopex.controller;

import com.emzy.shopex.dto.FactureResponse;
import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ShopController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShopService shopService;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void shouldPurchaseAndCalculateProperAmount() throws Exception {
        ItemsResponse itemsResponse = getItemResponse("item1", "10.01");
        ItemsResponse itemsResponse1 = getItemResponse("item2", "10.02");
        FactureResponse factureResponse = FactureResponse.builder()
                .items(List.of(itemsResponse, itemsResponse1))
                .amount(new BigDecimal("20.03"))
                .build();

        Mockito.when(shopService.purchase(List.of(1, 2))).thenReturn(factureResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/shop/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(1, 2))))
                .andExpect(status().isCreated())
                .andReturn();
        FactureResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), FactureResponse.class);

        assertThat(response.getAmount()).isEqualTo(factureResponse.getAmount());
        assertThat(response.getItems()).usingRecursiveComparison().isEqualTo(factureResponse.getItems());
    }

    private static ItemsResponse getItemResponse(String item1, String val) {
        return ItemsResponse.builder().name(item1).price(new BigDecimal(val)).build();
    }


}