package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.CategoryDto;
import com.example.appwarehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName()))
            return new Result("Category already exists", false);
        if (categoryDto.getParentCategoryId() != null && !categoryRepository.existsById(categoryDto.getParentCategoryId()))
            return new Result("Parent category not found", false);

        //Category category = new Category(null, categoryDto.getName(), categoryDto.isActive(), null);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null)
            category.setParentCategory(categoryRepository.getById(categoryDto.getParentCategoryId()));
        categoryRepository.save(category);
        return new Result("Category is created", true);

    }
}
