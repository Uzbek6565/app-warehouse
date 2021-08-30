package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Output;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.OutputDto;
import com.example.appwarehouse.repository.OutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;


    public Result addOutput(OutputDto outputDto) {

    }

    public List<Output> getAllOutputs() {
        return outputRepository.findAll();
    }

    public Output getOutputById(Integer id) {
        if (outputRepository.existsById(id))
            return outputRepository.getById(id);
        return null;
    }

    public Result editOutput(Integer id, OutputDto outputDto) {

    }

    public Result deleteOutputById(Integer id) {
        if (!outputRepository.existsById(id))
            return new Result("Output not found", false);
        outputRepository.deleteById(id);
        return new Result("Output is deleted", true);
    }
}
