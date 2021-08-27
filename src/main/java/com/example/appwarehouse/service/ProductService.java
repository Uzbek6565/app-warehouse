package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Product;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.ProductDto;
import com.example.appwarehouse.repository.AttachmentRepository;
import com.example.appwarehouse.repository.CategoryRepository;
import com.example.appwarehouse.repository.MeasurementRepository;
import com.example.appwarehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto) {
        if (productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId()))
            return new Result("Product already exists", false);
        if (!categoryRepository.existsById(productDto.getCategoryId()))
            return new Result("Category not found", false);
        if (!attachmentRepository.existsById(productDto.getPhotoId()))
            return new Result("Attachment not found", false);
        if (!measurementRepository.existsById(productDto.getMeasurementId()))
            return new Result("Measurement not found", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.getById(productDto.getCategoryId()));
        product.setAttachmentList(attachmentRepository.getById(productDto.getMeasurementId()));
        product.setMeasurement(measurementRepository.getById(productDto.getMeasurementId()));
        product.setCode("1");//TO DO
        productRepository.save(product);
        return new Result("Product is added", true);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        if (productRepository.existsById(id))
            return productRepository.getById(id);
        return null;
    }


    public Result editProduct(Integer id, ProductDto productDto) {
        if (!productRepository.existsById(id))
            return new Result("Product not found", false);
        if (productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId()))
            return new Result("Product already exists", false);
        if (!categoryRepository.existsById(productDto.getCategoryId()))
            return new Result("Category not found", false);
        if (!attachmentRepository.existsById(productDto.getPhotoId()))
            return new Result("Attachment not found", false);
        if (!measurementRepository.existsById(productDto.getMeasurementId()))
            return new Result("Measurement not found", false);
        Product product = productRepository.getById(id);
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.getById(productDto.getCategoryId()));
        product.setAttachmentList(attachmentRepository.getById(productDto.getMeasurementId()));
        product.setMeasurement(measurementRepository.getById(productDto.getMeasurementId()));
        product.setCode("1");//TO DO
        productRepository.save(product);
        return new Result("Product is edited", true);
    }

    public Result deleteProduct(Integer id){
        if (!productRepository.existsById(id))
            return new Result("Product not found", false);
        productRepository.deleteById(id);
        return new Result("Product is deleted", true);
    }
}
