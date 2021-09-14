package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    boolean existsByName(String name);
    Set<Warehouse> findAllByIdIn(Collection<Integer> id);
}
