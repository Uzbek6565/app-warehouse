package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Supplier;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier) {
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
            return new Result("Supplier already exists", false);
        supplierRepository.save(supplier);
        return new Result("Supplier is added", true);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Integer id) {
        if (supplierRepository.existsById(id))
            return supplierRepository.getById(id);
        return null;
    }

    public Result editSupplier(Integer id, Supplier supplier) {
        if (!supplierRepository.existsById(id))
            return new Result("Supplier not found", false);
        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber()))
            return new Result("Supplier already exists", false);
        Supplier supplierById = supplierRepository.getById(id);
        supplierById.setPhoneNumber(supplier.getPhoneNumber());
        supplierById.setName(supplier.getName());
        supplierRepository.save(supplierById);
        return new Result("Supplier data is edited", true);
    }

    public Result deleteSupplierById(Integer id) {
        if (!supplierRepository.existsById(id))
            return new Result("Supplier not found", false);
        supplierRepository.deleteById(id);
        return new Result("Supplier isdeleted",true);
    }
}
