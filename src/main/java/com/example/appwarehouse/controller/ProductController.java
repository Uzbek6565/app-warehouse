package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Product;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.ProductDto;
import com.example.appwarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id, ProductDto productDto){
        return productService.editProduct(id, productDto);
    }
}
