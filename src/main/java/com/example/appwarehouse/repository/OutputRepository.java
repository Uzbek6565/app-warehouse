package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    Output getOutputByFactureNumber(String factureNumber);
    boolean existsByFactureNumber(String factureNumber);
}
