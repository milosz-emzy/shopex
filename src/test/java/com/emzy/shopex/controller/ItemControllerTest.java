package com.emzy.shopex.controller;

import com.emzy.shopex.dto.ItemsResponse;
import com.emzy.shopex.exceptions.ErrorResponse;
import com.emzy.shopex.service.ItemService;
import com.emzy.shopex.utils.DataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemService itemService;

    CollectionType collectionType = new ObjectMapper().getTypeFactory().constructCollectionType(List.class, ItemsResponse.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void sortedByPrice() throws Exception {
        String asc = "ASC";
        Mockito.when(itemService.getPriceSortedItemsByDirection(Sort.Direction.fromString(asc))).thenReturn(DataProvider.getItems());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/items/sortedByPrice")
                        .param("sortDirection", asc))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        List<ItemsResponse> itemsResponses = objectMapper.readValue(contentAsString, collectionType);

        Assertions.assertEquals(itemsResponses.get(0).getName(), DataProvider.getItems().get(0).getName());
        Assertions.assertEquals(itemsResponses.get(1).getName(), DataProvider.getItems().get(1).getName());
        Assertions.assertEquals(-1, itemsResponses.get(0).getPrice().compareTo(itemsResponses.get(1).getPrice()));

    }

    @Test
    void sortedByPrice_onMethodArgumentTypeMismatchException() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/items/sortedByPrice")
                        .param("sortDirection", "incorrect_sort_direction_value"))
                .andExpect(status().isBadRequest())
                .andReturn();
        ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertEquals(errorResponse.statusCode(), HttpStatus.BAD_REQUEST.name());
    }
}