package com.emzy.shopex.controller;

import com.emzy.shopex.dto.WarehouseItems;
import com.emzy.shopex.model.Warehouse;
import com.emzy.shopex.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> listWarehouses() {
        List<Warehouse> warehouses = warehouseService.findAll();

        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<WarehouseItems> getAllItemsByWarehouseId(@PathVariable Integer id) {
        var warehouseItems = warehouseService.getAllItemsByWarehouseId(id);

        return ResponseEntity.ok(warehouseItems);
    }
}
