package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurement(measurement);
    }

    @GetMapping
    public List<Measurement> getAllMeasurement(){
        return measurementService.getAllMeasurement();
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementById(@PathVariable Integer id){
        return measurementService.getMeasurementById(id);
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.editMeasurement(id,measurement);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        return measurementService.deleteMeasurement(id);
    }

}
