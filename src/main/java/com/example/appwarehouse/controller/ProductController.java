package com.example.appwarehouse.controller;

import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.ProductDto;
import com.example.appwarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
}
