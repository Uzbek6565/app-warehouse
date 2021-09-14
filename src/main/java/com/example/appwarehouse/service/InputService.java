package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Input;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.InputDto;
import com.example.appwarehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public Result addInput(InputDto inputDto) {
        if (!warehouseRepository.existsById(inputDto.getWarehouseId()))
            return new Result("Warehouse not found", false);
        if (!currencyRepository.existsById(inputDto.getCurrencyId()))
            return new Result("Currency not found", false);
        if (!supplierRepository.existsById(inputDto.getSupplierId()))
            return new Result("Client not found", false);
        Input input = new Input(null, inputDto.getDate(), warehouseRepository.getById(inputDto.getWarehouseId()),
                supplierRepository.getById(inputDto.getSupplierId()), currencyRepository.getById(inputDto.getCurrencyId()),
                inputDto.getFactureNumber(), inputDto.getCode());
        inputRepository.save(input);
        return new Result("Input is added", true);
    }

    public List<Input> getAllInputs() {
        return inputRepository.findAll();
    }

    public Result getInputById(Integer id) {
        if (!inputRepository.existsById(id))
            return new Result("Input not found", false, null);
        return new Result("Input found", true, inputRepository.getById(id));
    }

    public Result editInput(Integer id, InputDto inputDto) {
        if (!inputRepository.existsById(id))
            return new Result("Input not found", false);
        if (!warehouseRepository.existsById(inputDto.getWarehouseId()))
            return new Result("Warehouse not found", false);
        if (!currencyRepository.existsById(inputDto.getCurrencyId()))
            return new Result("Currency not found", false);
        if (!supplierRepository.existsById(inputDto.getSupplierId()))
            return new Result("Client not found", false);
        if (inputRepository.existsByFactureNumber(inputDto.getFactureNumber()))
            return new Result("Facture number is used", false);
        Input inputById = inputRepository.getById(id);
        inputById.setDate(inputDto.getDate());
        inputById.setCurrency(currencyRepository.getById(inputDto.getCurrencyId()));
        inputById.setSupplier(supplierRepository.getById(inputDto.getSupplierId()));
        inputById.setWarehouse(warehouseRepository.getById(inputDto.getWarehouseId()));
        inputById.setFactureNumber(inputDto.getFactureNumber());
        inputById.setCode(inputDto.getCode());
        inputRepository.save(inputById);
        return new Result("Input data is edited", true);
    }

    public Result deleteInputById(Integer id) {
        if (!inputRepository.existsById(id))
            return new Result("Input not found", false);
        inputRepository.deleteById(id);
        return new Result("Input is deleted", true);
    }
}
