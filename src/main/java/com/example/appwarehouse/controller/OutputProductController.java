package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Output;
import com.example.appwarehouse.entity.OutputProduct;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.OutputDto;
import com.example.appwarehouse.payload.OutputProductDto;
import com.example.appwarehouse.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @GetMapping
    public List<OutputProduct> getAllOutputProducts(){
        return outputProductService.getAllOutputProducts();
    }

    @GetMapping("/{id}")
    public Result getOutputProductById(@PathVariable Integer id){
        return outputProductService.getOutputProductById(id);
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutputProduct(id,outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProductById(@PathVariable Integer id){
        return outputProductService.deleteOutputProductById(id);
    }
}
