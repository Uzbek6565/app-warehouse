package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.OutputProduct;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.OutputProductDto;
import com.example.appwarehouse.repository.OutputProductRepository;
import com.example.appwarehouse.repository.OutputRepository;
import com.example.appwarehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProduct(OutputProductDto outputProductDto) {
        if (!productRepository.existsById(outputProductDto.getProductId()))
            return new Result("Product not found", false);
        if (!outputRepository.existsById(outputProductDto.getOutputId()))
            return new Result("Output not found", false);
        OutputProduct outputProduct = new OutputProduct(null, productRepository.getById(outputProductDto.getProductId()),
                outputProductDto.getAmount(), outputProductDto.getPrice(), outputRepository.getById(outputProductDto.getOutputId()));

        outputProductRepository.save(outputProduct);
        return new Result("Output product is added", true);
    }

    public List<OutputProduct> getAllOutputProducts() {
        return outputProductRepository.findAll();
    }

    public Result getOutputProductById(Integer id) {
        if (!outputProductRepository.existsById(id))
            return new Result("Output product not found", false, null);
        return new Result("Output product with ID: " + id, true, outputProductRepository.getById(id));
    }

    public Result editOutputProduct(Integer id, OutputProductDto outputProductDto) {
        if (!outputProductRepository.existsById(id))
            return new Result("Output product not found", false, null);
        if (!productRepository.existsById(outputProductDto.getProductId()))
            return new Result("Product not found", false);
        if (!outputRepository.existsById(outputProductDto.getOutputId()))
            return new Result("Output not found", false);
        OutputProduct outputProductById = outputProductRepository.getById(id);
        outputProductById.setAmount(outputProductDto.getAmount());
        outputProductById.setPrice(outputProductDto.getPrice());
        outputProductById.setOutput(outputRepository.getById(outputProductDto.getOutputId()));
        outputProductById.setProduct(productRepository.getById(outputProductDto.getProductId()));
        outputProductRepository.save(outputProductById);
        return new Result("Output product is edited", true);
    }

    public Result deleteOutputProductById(Integer id) {
        if (!outputProductRepository.existsById(id))
            return new Result("Output product not found", false, null);
        outputProductRepository.deleteById(id);
        return new Result("Output product is deleted", true);
    }
}
