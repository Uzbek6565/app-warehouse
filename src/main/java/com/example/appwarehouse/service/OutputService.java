package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Output;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.OutputDto;
import com.example.appwarehouse.repository.ClientRepository;
import com.example.appwarehouse.repository.CurrencyRepository;
import com.example.appwarehouse.repository.OutputRepository;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ClientRepository clientRepository;

    public Result addOutput(OutputDto outputDto) {
        if (!warehouseRepository.existsById(outputDto.getWarehouseId()))
            return new Result("Warehouse not found", false);
        if (!currencyRepository.existsById(outputDto.getCurrencyId()))
            return new Result("Currency not found", false);
        if (!clientRepository.existsById(outputDto.getClientId()))
            return new Result("Client not found", false);
        if (outputRepository.existsByFactureNumber(outputDto.getFactureNumber()))
            return new Result("Facture number is already used", false);
        Output output = new Output(null, outputDto.getDate(), warehouseRepository.getById(outputDto.getWarehouseId()),
                currencyRepository.getById(outputDto.getCurrencyId()), outputDto.getFactureNumber(), outputDto.getCode(),
                clientRepository.getById(outputDto.getClientId()));
        outputRepository.save(output);
        return new Result("Output is added", true);
    }

    public List<Output> getAllOutputs() {
        return outputRepository.findAll();
    }

    public Result getOutputById(Integer id) {
        if (outputRepository.existsById(id))
            return new Result("Output with ID: " + id, true, outputRepository.getById(id));
        return new Result("Output not found", false);
    }

    public Result getOutputByFactureNumber(String factureNumber) {
        if (outputRepository.existsByFactureNumber(factureNumber))
            return new Result("Output by facture number " + factureNumber + "is found", true, outputRepository.getOutputByFactureNumber(factureNumber));
        return new Result("Output by facture number " + factureNumber + "is not found", false, null);
    }

    public Result editOutput(Integer id, OutputDto outputDto) {
        if (!outputRepository.existsById(id))
            return new Result("Output not found", false);
        if (!warehouseRepository.existsById(outputDto.getWarehouseId()))
            return new Result("Warehouse not found", false);
        if (!currencyRepository.existsById(outputDto.getCurrencyId()))
            return new Result("Currency not found", false);
        if (!clientRepository.existsById(outputDto.getClientId()))
            return new Result("Client not found", false);
        if (outputRepository.existsByFactureNumber(outputDto.getFactureNumber()))
            return new Result("Facture number is already used", false);

        Output outputById = outputRepository.getById(id);
        outputById.setClient(clientRepository.getById(outputDto.getClientId()));
        outputById.setCurrency(currencyRepository.getById(outputDto.getCurrencyId()));
        outputById.setWarehouse(warehouseRepository.getById(outputDto.getWarehouseId()));
        outputById.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(outputById);
        return new Result("Output data is edited", true);
    }

    public Result deleteOutputById(Integer id) {
        if (!outputRepository.existsById(id))
            return new Result("Output not found", false);
        outputRepository.deleteById(id);
        return new Result("Output is deleted", true);
    }
}
