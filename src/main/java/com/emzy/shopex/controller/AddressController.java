package com.emzy.shopex.controller;

import com.emzy.shopex.zippo.ZippoClient;
import com.emzy.shopex.zippo.model.ZippoResponse;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final ZippoClient zippoClient;

    @Autowired
    public AddressController(ZippoClient zippoClient) {
        this.zippoClient = zippoClient;
    }

    @GetMapping("/{zipcode}")
    public ResponseEntity<ZippoResponse> getAddress(@Pattern(regexp = "^\\d{2}-\\d{3}$") @PathVariable String zipcode) {
        ZippoResponse body = zippoClient.getResponse(zipcode);
        return ResponseEntity.ok(body);
    }
}
