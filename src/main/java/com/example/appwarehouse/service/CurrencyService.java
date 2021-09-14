package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.repository.ClientRepository;
import com.example.appwarehouse.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addCurrency(Currency currency) {
        if(currencyRepository.existsByName(currency.getName()))
            return new Result("Currency already exists", false);
        currencyRepository.save(currency);
        return new Result("Currency is added", true);
    }


    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }


    public Result getCurrencyById(Integer id) {
        if (currencyRepository.existsById(id))
            return new Result("Currency with ID: " + id, true, currencyRepository.getById(id));
        return new Result("Currency already exists", false);
    }

    public Result editCurrency(Integer id, Currency currency) {
        if (currencyRepository.existsByName(currency.getName()))
            return new Result("Currency already exists", false);
        if (!currencyRepository.existsById(id))
            return new Result("Currency not found", false);
        Currency currencyById = currencyRepository.getById(id);
        currencyById.setName(currency.getName());
        currencyRepository.save(currencyById);
        return new Result("Currency data is edited", true);
    }


    public Result deleteCurrency(Integer id) {
        if (!currencyRepository.existsById(id))
            return new Result("Currency not found", false);
        currencyRepository.deleteById(id);
        return new Result("Currency is deleted", true);
    }
}
