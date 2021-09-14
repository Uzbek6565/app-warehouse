package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Supplier;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Result getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable Integer id, Supplier supplier){
        return supplierService.editSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplierById(@PathVariable Integer id){
        return supplierService.deleteSupplierById(id);
    }
}
