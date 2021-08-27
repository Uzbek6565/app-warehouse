package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public boolean existsByName(String name);
}
