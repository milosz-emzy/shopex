package com.emzy.shopex.service;

import com.emzy.shopex.dto.WarehouseItems;
import com.emzy.shopex.model.Warehouse;
import com.emzy.shopex.repository.ItemRepository;
import com.emzy.shopex.repository.WarehouseRepository;
import com.emzy.shopex.util.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ItemRepository itemRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, ItemRepository itemRepository) {
        this.warehouseRepository = warehouseRepository;
        this.itemRepository = itemRepository;
    }

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public WarehouseItems getAllItemsByWarehouseId(Integer id) {
        var warehouse = warehouseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Warehouse id: " + id + " has been not found")
        );

        return MapperUtil.mapToWarehouseItems(warehouse, warehouse.getItems());
    }
}
