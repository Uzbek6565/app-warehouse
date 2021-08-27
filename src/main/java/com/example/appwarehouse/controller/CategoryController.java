package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.payload.CategoryDto;
import com.example.appwarehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }
}
