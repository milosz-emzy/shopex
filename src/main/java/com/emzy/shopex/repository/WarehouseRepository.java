package com.emzy.shopex.repository;

import com.emzy.shopex.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    //solves n+1 problem
    @Override
    @Query("SELECT w FROM Warehouse w LEFT JOIN FETCH w.items")
    List<Warehouse> findAll();

    //solves n+1 problem
    @Override
    @Query("SELECT w FROM Warehouse w LEFT JOIN FETCH w.items WHERE w.id=?1")
    Optional<Warehouse> findById(Integer id);
}
