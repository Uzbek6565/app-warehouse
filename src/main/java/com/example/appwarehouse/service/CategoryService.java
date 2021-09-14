package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.CategoryDto;
import com.example.appwarehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Result getCategoryById(Integer id) {
        if (categoryRepository.existsById(id))
            return new Result("Category with ID: " + id, true, categoryRepository.getById(id));
        return new Result("Category not found", false);
    }

    public Result editCategory(Integer id, CategoryDto categoryDto) {
        if (!categoryRepository.existsById(id))
            return new Result("Category not found", false);
        if (categoryRepository.existsByName(categoryDto.getName()))
            return new Result("Category already exists", false);
        if (categoryDto.getParentCategoryId() != null && !categoryRepository.existsById(categoryDto.getParentCategoryId()))
            return new Result("Parent category not found", false);
        Category category = categoryRepository.getById(id);
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null)
            category.setParentCategory(categoryRepository.getById(categoryDto.getParentCategoryId()));
        categoryRepository.save(category);
        return new Result("Category is edited", true);
    }

    public Result deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id))
            return new Result("Category not found", false);
        categoryRepository.deleteById(id);
        return new Result("Category is deleted", true);
    }
}
