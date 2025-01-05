package com.emzy.shopex.controller;


import com.emzy.shopex.dto.FactureResponse;
import com.emzy.shopex.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<FactureResponse> purchaseItems(@RequestBody List<Integer> itemsId) {
        FactureResponse factureResponse = shopService.purchase(itemsId);

        return new ResponseEntity<>(factureResponse, HttpStatus.CREATED);
    }
}
