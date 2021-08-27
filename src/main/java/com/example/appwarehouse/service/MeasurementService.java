package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurement(Measurement measurement){
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("This measurement already exists", false);
        measurementRepository.save(measurement);
        return new Result("Measurement is added", true);
    }

    public List<Measurement> getAllMeasurement() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(Integer id){
        if (measurementRepository.existsById(id))
            return measurementRepository.getById(id);
        return null;
    }

    public Result editMeasurement(Integer id, Measurement measurement) {
        if (!measurementRepository.existsById(id))
            return new Result("Measurement not found", false);
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("Measurement is added", true);
        Measurement measurementById = measurementRepository.getById(id);
        measurementById.setName(measurement.getName());
        measurementById.setActive(measurement.isActive());
        measurementRepository.save(measurementById);
        return new Result("Measurement data is edited", true);
    }


    public Result deleteMeasurement(Integer id) {
        if (!measurementRepository.existsById(id))
            return new Result("Measurement not found", false);
        measurementRepository.deleteById(id);
        return new Result("Measurement is deleted", true);
    }
}
