package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Input;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.InputDto;
import com.example.appwarehouse.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public List<Input> getAllInputs(){
        return inputService.getAllInputs();
    }

    @GetMapping("/{id}")
    public Result getInputById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.editInput(id,inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputById(@PathVariable Integer id){
        return inputService.deleteInputById(id);
    }
}
