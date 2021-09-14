package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Input;
import com.example.appwarehouse.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByFactureNumber(String factureNumber);

}
