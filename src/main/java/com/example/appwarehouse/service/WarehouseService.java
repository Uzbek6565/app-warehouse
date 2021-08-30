package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;


    public Result addWarehouse(Warehouse warehouse) {
        if (warehouseRepository.existsByName(warehouse.getName()))
            return new Result("This warehouse already exists", false);
        warehouseRepository.save(warehouse);
        return new Result("Warehouse is added", true);
    }


    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Integer id) {
        if (warehouseRepository.existsById(id))
            return warehouseRepository.getById(id);
        return null;
    }


    public Result editWarehouse(Integer id, Warehouse warehouse) {
        if (warehouseRepository.existsByName(warehouse.getName()))
            return new Result("This warehouse already exists", false);
        if (!warehouseRepository.existsById(id))
            return new Result("Warehouse not found", false);
        Warehouse warehouseById = warehouseRepository.getById(id);
        warehouseById.setName(warehouse.getName());
        warehouseRepository.save(warehouseById);
        return new Result("Warehouse data is edited", true);
    }

    public Result deleteWarehousById(Integer id) {
        if (!warehouseRepository.existsById(id))
            return new Result("Warehouse not found", false);
        warehouseRepository.deleteById(id);
        return new Result("Warehouse is deleted", true);
    }
}
