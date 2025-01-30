package com.emzy.shopex.controller;

import com.emzy.shopex.zippo.ZippoClient;
import com.emzy.shopex.zippo.model.Place;
import com.emzy.shopex.zippo.model.ZippoResponse;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(controllers = AddressController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ZippoClient zippoClient;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAddress() throws Exception {
        String zipcode = "87-100";

        String country = "Poland";
        ZippoResponse zippoResponse = new ZippoResponse(
                "87-100",
                country,
                "PL",
                List.of(new Place(
                        "Toruń",
                        "18.6",
                        "state",
                        "",
                        "53.0333")
                )
        );
        Mockito.when(zippoClient.getResponse(zipcode)).thenReturn(zippoResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/address/{zipcode}", zipcode)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ZippoResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ZippoResponse.class);

        assertThat(response.country()).isEqualTo(country);
    }
}