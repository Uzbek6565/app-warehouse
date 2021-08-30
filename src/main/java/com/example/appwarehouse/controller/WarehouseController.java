package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses(){
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id){
        return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Result editWarehouse(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        return warehouseService.editWarehouse(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehousById(@PathVariable Integer id){
        return warehouseService.deleteWarehousById(id);
    }

}
